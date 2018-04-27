package view;
import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import java.awt.Font;
import javax.swing.JButton;

/**
 * Hier entsteht das ganze Farbenratespiel <br>
 * 
 * Es werden eine bestimmte Anzahl an Farben angezeigt,
 * wobei eine davon mit der Farbe im oberen Bereich uebereinstimmt
 * Der Spieler muss genau diese Farbe erraten <br>
 * 
 * In einem Label werden Informationen zum aktuellen Spielverlauf angezeigt
 * (Anzahl der Versuche, Anzahl der Treffer, Trefferquote in %)
 *    
 * Links gibt es eine Auswahl fuer den Schwierigkeitsgrad (3 Checkboxen): <br>
 * <ul>
 * <li> Leicht: 9 Ratefarben </li>
 * <li> Normal: 36 Ratefarben </li>
 * <li> Schwer: 81 Ratefarben </li>
 * </ul>
 * 
 * @author David Meyer
 * @version 1.0
 */
public class HauptFrame extends JFrame {
	
	/** Enthaelt die Checkboxen fuer die Schwierigkeitsgradeinstellung */
	private final ButtonGroup buttonGroup = new ButtonGroup();
	
	/** Panel im Norden des Frames, welches die zu erratene Hintergrundfarbe und lblTitel enthaelt */
	JPanel panelNorth;
	/** Panel im Osten des Frames, welches die Checkboxen fuer die Schwierigkeitsgradaenderung enthaelt */
	JPanel panelEast;
	/** Panel im Sueden des Frames, welches lblStatistik enthaelt */
	JPanel panelSouth;
	/** Panel in der Mitte des Frames, welche die Ratebuttons enthaelt*/
	JPanel panelCenter;
	/** Enthaelt die Anweisung des Spiels (im panelNorth) */
	JLabel lblTitel;
	/** Enthaelt die Statistik des aktuellen Spiels (im panelSouth)*/
	JLabel lblStatistik;
	
	/** Anzahl von getaetigten Versuchen, die richtige Farbe zu erraten */
	int versuche;
	/** Anzahl der Ratebuttons */
	int anzahlbuttons;
	/**
	 * Wie oft richtig geraten wurde
	 * Teffer muss double sein, da int mit int berechnet nur int zurueckgibt
	 */
	double treffer;
	/** Trefferquote in % (Treffer/Versuche * 100) */
	double trefferquote;
	
	/** Liste mit Ratebuttons */
	ArrayList<JButton> buttons = new ArrayList<JButton>();
	
	/** Generiert "zufaellige" Zahlen */
	Random zufallsgenerator;

	/**
	 * Launch the application.
	 * @param args Laufzeitparameter - Hier keine
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HauptFrame frame = new HauptFrame();
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
	public HauptFrame() {
		
		// Variablen Standardwerte zuweisen
		versuche = 0;
		treffer = 0.0;
		trefferquote = 0.0;
		anzahlbuttons = 9;
		zufallsgenerator = new Random();
		
		// Titel des Frames einstellen 
		this.setTitle("Farbenraten");
		
		getContentPane().setBackground(Color.ORANGE);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		panelNorth = new JPanel();
		getContentPane().add(panelNorth, BorderLayout.NORTH);
		
		lblTitel = new JLabel("Diese Farbe muss erraten werden");
		lblTitel.setForeground(Color.RED);
		lblTitel.setFont(new Font("Arial", Font.PLAIN, 11));
		panelNorth.add(lblTitel);
		
		panelEast = new JPanel();
		getContentPane().add(panelEast, BorderLayout.EAST);
		panelEast.setLayout(new BoxLayout(panelEast, BoxLayout.Y_AXIS));
		
		// Erste Checkbox
		JCheckBox chckbxNewCheckBox = new JCheckBox("Einfach");
		chckbxNewCheckBox.addActionListener(new ChckbxNewCheckBoxActionListener());
		chckbxNewCheckBox.setSelected(true);
		buttonGroup.add(chckbxNewCheckBox);
		panelEast.add(chckbxNewCheckBox);
		
		// Zweite Checkbox
		JCheckBox chckbxNewCheckBox_1 = new JCheckBox("Normal");
		chckbxNewCheckBox_1.addActionListener(new ChckbxNewCheckBox_1ActionListener());
		buttonGroup.add(chckbxNewCheckBox_1);
		panelEast.add(chckbxNewCheckBox_1);
		
		// Dritte Checkbox
		JCheckBox chckbxNewCheckBox_2 = new JCheckBox("Schwierig");
		chckbxNewCheckBox_2.addActionListener(new ChckbxNewCheckBox_2ActionListener());
		buttonGroup.add(chckbxNewCheckBox_2);
		panelEast.add(chckbxNewCheckBox_2);
		
		panelSouth = new JPanel();
		getContentPane().add(panelSouth, BorderLayout.SOUTH);
		panelSouth.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		lblStatistik = new JLabel("Statistik");
		panelSouth.add(lblStatistik);
		
		panelCenter = new JPanel();
		getContentPane().add(panelCenter, BorderLayout.CENTER);
		panelCenter.setLayout(new GridLayout(3, 3, 0, 0));
		
		// Die Buttons hier zu erstellen ist eigentlich unnoetig
		// Sie werden zur Uebung mit dem Umgang  mit dem Windowbuilder erstellt
		JButton btnNewButton = new JButton("");
		buttons.add(btnNewButton);
		panelCenter.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("");
		buttons.add(btnNewButton_1);
		panelCenter.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("");
		buttons.add(btnNewButton_2);
		panelCenter.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("");
		buttons.add(btnNewButton_3);
		panelCenter.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("");
		buttons.add(btnNewButton_4);
		panelCenter.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("");
		buttons.add(btnNewButton_5);
		panelCenter.add(btnNewButton_5);
		
		JButton btnNewButton_6 = new JButton("");
		buttons.add(btnNewButton_6);
		panelCenter.add(btnNewButton_6);
		
		JButton btnNewButton_7 = new JButton("");
		buttons.add(btnNewButton);
		panelCenter.add(btnNewButton_7);
		
		JButton btnNewButton_8 = new JButton("");
		buttons.add(btnNewButton);
		panelCenter.add(btnNewButton_8);
		
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Buttons erstellen und Listener zuweisen
		neueRunde(0);
	}
	
	/**
	 * Eine neue Runde beginnt, das heisst, dass die Buttons geloescht und neu erstellt werden muessen,
	 * Wenn der Spieler nur den Schwierigkeitsgrad geaendert hat, wird diese Methode auch ausgefuehrt, jedoch
	 * wird es nicht als Versuch/Treffer gewertet <br>
	 * Wenn der Spieler richtig geraten hat, werden Treffer und Versuche erhoeht und die Trefferquote neu berechnet
	 * Wenn der Spieler falsch geraten hat, wird nur die Anzahl der Versuche erhoeht
	 * 
	 * Der Parameter getroffen: <br>
	 * <ul>
	 * <li> 0: Runden-Reset (anderer Schwierigkeitsgrad) </li>
	 * <li> 1: Nicht getroffen (nur Versuche erhoeht) </li>
	 * <li> 2: Getroffen (Treffer und Versuche erhoeht) </li>
	 * </ul>
	 * 
	 * @param getroffen Sagt, ob richtig (2) oder falsch (1) geraten wurde, oder, ob die Runde einfach so resettet (0) werden soll
	 */
	public void neueRunde(int getroffen) {
		// Die Buttons loeschen und wieder neu erstellen
		buttonsErstellen();
		panelNorth.setBackground(buttons.get(zufallsgenerator.nextInt(buttons.size()-1)).getBackground());
		
		// OPTIONAL: Die Farbe der Schrift im North ist die invertierte Farbe der Hintergrundfarbe im North, damit man die Schrift besser lesen kann
		lblTitel.setForeground(new Color(255-panelNorth.getBackground().getRed(), 255-panelNorth.getBackground().getGreen(), 255-panelNorth.getBackground().getBlue()));
		if(getroffen > 0) versuche++;
		if(getroffen == 2) treffer++;
		if(treffer != 0) trefferquote = treffer/versuche * 100.0;
		else trefferquote = 0;
		lblStatistik.setText(String.format("Versuche: %d \t Treffer: %,.0f \t Trefferquote: %,.4f%% ", versuche, treffer, trefferquote));
		// Den Frame updaten
		this.validate();
	}
	
	/**
	 * Zuerst alle Buttons in der Button-Liste und in dem Panel loeschen
	 * Danach so viele Buttons erstellen, wie fuer den aktuellen Schwierigkeitsgrad notwendig sind
	 * Die 3 Farbkanaele fuer die Hintergrundfarbe der jeweiligen Buttons werden zufaellig gewaehlt
	 * Jeder Button bekommt eine eigene ButtonListener Instanz und wird zum Panel und zur Button-Liste hinzugefuegt
	 */
	public void buttonsErstellen() {
		
		panelCenter.removeAll();
		buttons.clear();
		for(int i = 0; i < anzahlbuttons; i++) {
			buttons.add(new JButton());
			buttons.get(i).setBackground(new Color(zufallsgenerator.nextInt(255), zufallsgenerator.nextInt(255), zufallsgenerator.nextInt(255)));
			buttons.get(i).setVisible(true);
			buttons.get(i).addActionListener(new ButtonListener());
			panelCenter.add(buttons.get(i));
		}
	}
	
	/**
	 * Aendert die Anzahl der Buttons und passt das GridLayout im panelCenter darauf an
	 * Desweiteren wird eine neue Runde gestartet
	 * 
	 * @param anzahl Anzahl der Buttons in eine Reihe
	 */
	public void anzahlButtonsAendern(int anzahl) {
		
		// Das Raster auf die Anzahl an Ratebuttons anpassen
		((GridLayout) HauptFrame.this.panelCenter.getLayout()).setColumns(anzahl);
		((GridLayout) HauptFrame.this.panelCenter.getLayout()).setRows(anzahl);
		HauptFrame.this.anzahlbuttons = anzahl * anzahl;
		
		// Neue Runde wird begonnen, ohne die Zaehler zu erhoehen (0), da nicht geraten wurde, sondern nur der Schwierigkeitsgrad geandert
		HauptFrame.this.neueRunde(0);
	}
	
	/**
	 * Eine innere Listenerklasse fuer die Ratebuttons
	 * Jeder Ratebutton (ein Button ohne Text und mit zufaellig gewaehlten Hintergrundfarbe) 
	 * bekommt eine Instanz dieser Klasse als ActionListener
	 * 
	 * Diese Instanz wird benachrichtigt (die actionPerformed Methode wird ausgefuehrt) wenn
	 * der Button gedrueckt wurde
	 * 
	 * @author David Meyer
	 * @version 1.0
	 */
	public class ButtonListener implements ActionListener {
		
		@Override
		/**
		 * Wenn ein Button gedrueckt wurde, wird die Hintergrundfarbe dieses Buttons mit der Hintergrundfarbe des Titel-Panels verglichen
		 * Wenn die Farben uebereinstimmen wurde richtig geraten, wenn nicht, dann nicht
		 */
		public void actionPerformed(ActionEvent e) {

			// Wenn der richtige Button gedrueckt wurde
			if(((JButton) e.getSource()).getBackground() == panelNorth.getBackground()) {
				HauptFrame.this.neueRunde(2);
			}
			else {
				HauptFrame.this.neueRunde(1);
			}
		}
	}
	
	/**
	 * Listener fuer Checkbox 
	 * Wenn leichteste Schwierigkeitsstufe gewaehlt wurde 
	 * 
	 * Action Listener werden nur aufgerufen, wenn die Checkbox selected wird
	 * Deswegen: keine Abfrage, ob Checkbox selected ist noetig
	 * 
	 * Es wird die Anzahl der Ratebuttons auf 9(3x3) veraendert und eine neue Runde gestartet
	 * 
	 * @author David Meyer
	 * @version 1.0
	 */
	private class ChckbxNewCheckBoxActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			anzahlButtonsAendern(3);
		}
	}
	/**
	 * Listener fuer Checkbox 
	 * Wenn mittlere Schwierigkeitsstufe gewaehlt wurde 
	 * 
	 * Action Listener werden nur aufgerufen, wenn die Checkbox selected wird
	 * Deswegen: keine Abfrage, ob Checkbox selected ist noetig
	 * 
	 * Es wird die Anzahl der Ratebuttons auf 36(6x6) veraendert und eine neue Runde gestartet
	 * 
	 * @author David Meyer
	 * @version 1.0
	 */
	private class ChckbxNewCheckBox_1ActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			anzahlButtonsAendern(6);
		}
	}
	/**
	 * Listener fuer Checkbox 
	 * Wenn schwerste Schwierigkeitsstufe gewaehlt wurde 
	 * 
	 * Action Listener werden nur aufgerufen, wenn die Checkbox selected wird
	 * Deswegen: keine Abfrage, ob Checkbox selected ist noetig
	 * 
	 * Es wird die Anzahl der Ratebuttons auf 81(9x9) veraendert und eine neue Runde gestartet
	 * 
	 * @author David Meyer
	 * @version 1.0
	 */
	private class ChckbxNewCheckBox_2ActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			
			anzahlButtonsAendern(9);
		}
	}
}