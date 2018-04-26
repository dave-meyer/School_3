package view;

import java.awt.*;
import java.util.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import control.*;

/**
 * Die Klasse ermöglicht ein einfaches Farbenratespiel
 */
public class FarbenRatenGUI {
	
	private JFrame frmClickTheRight;	//Hauptframe für sämtliche GUI-Komponenten
	private int size;					//size x size = Anzahl der Farbbuttons
	public int tries;					//Rateversuche	
	public int hits;					
	private int easy = 3;				//Je nach Spielstärke werden mehr oder weniger
	private int normal = 6;				//Buttons dargestellt
	private int hard = 15;
	private ArrayList<JButton> buttons; //ArryList sämtlicher Farbbuttons
	private JPanel panel;				//Nimmt im CENTER sämtliche Farbbuttons auf
	private JPanel colorPanel;			//Panel dessen Hintergrundfarbe zu erraten ist (NORTH)
	private JPanel levelPanel;			//Panel für Radiobuttons zur Auswahl der Spielstärke
	private JCheckBox b1, b2, b3;		//Radiobuttons zur Spielstärkenauswahl
	private ButtonGroup bgroup;			//Checkboxgroup, dass nur 1 Radiobutton ausgewählt werden kann
	private JTextField text;			//Zur Anzeige der Statistikwerte im SOUTH

	public static void main(String[] args) {
		FarbenRatenGUI raten = new FarbenRatenGUI();
		raten.los();
	}
	
	/**
	 * "easy" als Standardeinstellung und GUI aufbauen
	 */
	public void los() {
		size = normal;
		initialize();
		frmClickTheRight.setVisible(true);
	}
	
	/**
	 * GUI-Aufbau und Eventhandling definieren.
	 */
	private void initialize() {
		//Hauptframe instanziieren
		frmClickTheRight = new JFrame();
		frmClickTheRight.setTitle("Farbenratespiel");
		frmClickTheRight.setBounds(100, 100, 550, 550); //Frame 100,100 Pixel v. linken, oberen BS-Rand 450x550
		frmClickTheRight.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmClickTheRight.getContentPane().setLayout(new BorderLayout(10, 10)); //Zwischen Bereichen ein 10,10 Abstand
		
		//Panel für Farbbuttons instanziieren (CENTER)
		panel = new JPanel();
		panel.setBorder(new TitledBorder("JPanel - GridLayout(size,size) - CENTER d. JFrames"));
		frmClickTheRight.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(size, size, 0, 0));	//size x size = Anzahl Farbbuttons
		
		//Textfield für Statistikwerte instanziieren (SOUTH)
		text = new JTextField();
		text.setBorder(new TitledBorder("JTextField - SOUTH d. JFrames"));
		frmClickTheRight.getContentPane().add(text, BorderLayout.SOUTH);
		
		//Panel des zu erratenden Hintergrundfarbe (NORTH)
		colorPanel = new JPanel();
		colorPanel.setBorder(new TitledBorder("JPanel - Standardlayout - NORTH d. JFrames"));
		Font bigFont = new Font("courier", Font.BOLD, 18);
		JLabel text = new JLabel("Diese Hintergrundfarbe muss erraten werden!!!");
		text.setFont(bigFont);
		text.setForeground(Color.white);
		colorPanel.add(text);
		frmClickTheRight.getContentPane().add(colorPanel, BorderLayout.NORTH);
		
		//Panel zur Auswahl der Schwierigkeitsstufe
		levelPanel = new JPanel();
		levelPanel.setBorder(new TitledBorder("JPanel - BoxLayout"));
		levelPanel.setLayout(new BoxLayout(levelPanel, BoxLayout.Y_AXIS));
		bgroup = new ButtonGroup();
		b1 = new JCheckBox("easy"); bgroup.add(b1);
		b1.addItemListener(new CheckboxListener(this, easy));
		b2 = new JCheckBox("normal                    "); bgroup.add(b2); 
		b2.addItemListener(new CheckboxListener(this, normal));
		b2.doClick(); //Bei Spielbeginn - "Normalstufe"
		b3 = new JCheckBox("hard"); bgroup.add(b3);
		b3.addItemListener(new CheckboxListener(this, hard));
		levelPanel.add(b1);
		levelPanel.add(b2);
		levelPanel.add(b3);
		frmClickTheRight.getContentPane().add(levelPanel, BorderLayout.EAST);

		area();	//Buttons und zu erratende Hintergrundfarbe neu aufbauen
		recalculate();	//Statistikzeile aktualisieren
	}	
	
	/**	
	 * Das Buttonpanel wird komplett neu aufgebaut.
	 * Abhängig von der gewählten Spielstärke wird eine Anzahl von Buttons
	 * instanziiert. Jeder Button bekommt seinen eigenen Listener und eine
	 * zufällig gewählte Hintergrundfarbe. Die Buttons werden in eine ArrayList
	 * aufgenommen, dass daraus wiederum zufällig einer entnommen werden kann,
	 * der dann die Hintergrundfarbe der zu erratenden Panelhintergrundfarbe
	 * definiert.
	 */
	public void area() 
	{	
		buttons = new ArrayList<JButton>();
		panel.setLayout(new GridLayout(size, size,2,2));  //2 Pixel Abstand zw. den Buttons
		panel.removeAll();					//alle "alten" Buttons aus dem Panel entfernen.
		for (int i = 0; i < (size * size); i++) {
			JButton button = new JButton();
			button.addActionListener(new FarbButtonListener(this));
			//Zufallsfarbe erstellen
			int red = (int) (Math.random() * 255);
			int green = (int) (Math.random() * 255);
			int blue = (int) (Math.random() * 255);
			button.setBackground(new Color(red, green, blue));
			panel.add(button);
			buttons.add(button);
		}
		panel.validate();	//Panellayout hat sich dynamisch geändert ==> validate() ist notwendig
		frmClickTheRight.repaint();	//alles neu zeichnen
		//zu erratende Farbe aus den bestehenden Buttons ermitteln
		colorPanel.setBackground(buttons.get(new Random().nextInt(size * size)).getBackground());
	}
	
	/**
	 * Die Statistikzeile wird aus den vorhandenen Statistikdaten neu aufgebaut und
	 * angezeigt.
	 */
	public void recalculate() 
	{
		double nHits = hits;
		double nTries = tries;
		double percent = nHits / nTries * 100;
		Font font = new Font("courier", Font.BOLD, 14);
		text.setFont(font);
		text.setForeground(Color.red);
		text.setText("Versuche: " + tries + "\t Treffer: " + hits + "\tErfolg in %: "+ percent);
	}
	
	//+++++++ Benötigte Getters und Setters für die Listener ++++++++++
	public void setHits(int hits) {
		this.hits = hits;
	}
	
	public void setTries(int tries) {
		this.tries = tries;
	}
	
	public void setSize(int size) {
		this.size = size;
	}
	
	public JFrame getFrmClickTheRight() {
		return frmClickTheRight;
	}
	
	public int getTries() {
		return tries;
	}
	
	public int getHits() {
		return hits;
	}
	
	public int getSize() {
		return size;
	}
	
	public JPanel getColorPanel() {
		return colorPanel;
	}

}
