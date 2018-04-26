package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;

import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Random;

public class FarbenRatenGUI_WB extends JFrame {
	private JPanel panel;
	private JLabel text;
	private JPanel colorPanel;
	private JLabel lblDieseFarbeMuss;
	private JPanel levelPanel;
	private JCheckBox chckbxEasy;
	private JCheckBox chckbxNormal;
	private JCheckBox chckbxHard;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	// händisch hinzugefügte Variable
	private int easy = 3;				//Je nach Spielstärke werden mehr oder weniger
	private int normal = 6;				//Buttons dargestellt
	private int hard = 15;
	private int size = 0;				//size x size = Anzahl der Farbbuttons
	private int tries = 0;				//Rateversuche	
	private int hits = 0;
	private ArrayList<JButton> buttons; //ArryList sämtlicher Farbbuttons

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FarbenRatenGUI_WB frame = new FarbenRatenGUI_WB();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FarbenRatenGUI_WB() {
		initialize();
	}
	private void initialize() {
		setTitle("Farbenraten - designed by WindowBuilder");
		//Hauptframe initialisieren
		setBounds(100, 100, 550, 550);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout(10, 10));
		
		//JPanel für Farbbuttons instanziieren (CENTER)
		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "JPanel - GridLayout(size,size) - CENTER d. JFrames", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(1, 0, 0, 0));
		
		//JLabel für Statistikwerte instanziieren (SOUTH)
		text = new JLabel("Hier kommen die Statistikwerte hin");
		getContentPane().add(text, BorderLayout.SOUTH);
		
		//Panel des zu erratenden Hintergrundfarbe (NORTH)		
		colorPanel = new JPanel();
		colorPanel.setBorder(new TitledBorder(null, "JPanel - Standardlayout - NORTH d. JFrames", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(colorPanel, BorderLayout.NORTH);
		lblDieseFarbeMuss = new JLabel("Diese Farbe muss erraten werden!!!!");
		lblDieseFarbeMuss.setForeground(Color.WHITE);
		lblDieseFarbeMuss.setFont(new Font("Courier New", Font.BOLD, 18));
		colorPanel.add(lblDieseFarbeMuss);
		
		//Panel zur Auswahl der Schwierigkeitsstufe		
		levelPanel = new JPanel();
		levelPanel.setBorder(new TitledBorder(null, "JPanel - BoxLayout", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(levelPanel, BorderLayout.EAST);
		levelPanel.setLayout(new BoxLayout(levelPanel, BoxLayout.Y_AXIS));
		chckbxEasy = new JCheckBox("Easy");
		chckbxEasy.addItemListener(new CheckboxListener(easy));
		buttonGroup.add(chckbxEasy);
		levelPanel.add(chckbxEasy);
		chckbxNormal = new JCheckBox("normal                 ");
		chckbxNormal.addItemListener(new CheckboxListener(normal));
		buttonGroup.add(chckbxNormal);
		levelPanel.add(chckbxNormal);
		chckbxHard = new JCheckBox("hard");
		chckbxHard.addItemListener(new CheckboxListener(hard));
		buttonGroup.add(chckbxHard);
		levelPanel.add(chckbxHard);
		chckbxNormal.doClick(); //Bei Spielbeginn - "Normalstufe"
		
		//los gehts - mit Standardeinstellungen
		area();			//Buttons und zu erratende Hintergrundfarbe neu aufbauen
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
			button.addActionListener(new FarbButtonListener());
			//Zufallsfarbe erstellen
			int red = (int) (Math.random() * 255);
			int green = (int) (Math.random() * 255);
			int blue = (int) (Math.random() * 255);
			button.setBackground(new Color(red, green, blue));
			panel.add(button);
			buttons.add(button);
		}
		panel.validate();	//Panellayout hat sich dynamisch geändert ==> validate() ist notwendig
		this.repaint();		//alles neu zeichnen
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
		text.setText("Versuche: " + tries + "    Treffer: " + hits + "     Erfolg in %: "+ percent);
	}	
	
	private class CheckboxListener implements ItemListener {
		private int length;		//Größe des Spielfeldes, das Checkbox bestimmt, wird fixiert
		
		public CheckboxListener(int length) 
		{
			this.length = length;
		}

		@Override
		public void itemStateChanged(ItemEvent ev) {
			JCheckBox cb = (JCheckBox)ev.getSource();
			if (cb.isSelected()) {
				size = length;
				tries = 0;
				hits = 0;
				area();
				recalculate();			
			}
			
		}
	} //END CheckboxListener

	/**
	 * Jeder Button hat seinen eigenen Listener. Wenn der Button gedrückt wurde,
	 * ist zu prüfen, ob dessen Farbe mit der Hintergrundfarbe des Ratepanels
	 * übereinstimmt. Die Statistikwerte sind dann entsprechend anzupassen.
	 */
	public class FarbButtonListener implements ActionListener 
	{
		/**
		 * Aus dem ActionEvent wird der JButton ermittelt, der gedrückt
		 * wurde. Dessen Hintergrundfarbe wird mit dem Ratepanel 
		 * verglichen.
		 */
		public void actionPerformed(ActionEvent e) 
		{
			JButton button = (JButton)e.getSource();
			tries++;
			//richtige Farbe erwischt??
			if (button.getBackground().equals(colorPanel.getBackground())) 
			{
				hits++;
			} 
			recalculate();	//Statistikzeile anzeigen
			area();			//Buttons und zu erratende Hintergrundfarbe neu bestimmen
		}
	} //END FarbButtonListener
} //END FarbenRatenGUI_WB
