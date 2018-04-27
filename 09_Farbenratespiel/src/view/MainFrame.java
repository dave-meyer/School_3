package view;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;

import control.ButtonListener;
import control.CheckboxListener;

/**
 * Hier entsteht das ganze Farbenratespiel <br>
 * 
 * Es werden eine bestimmte Anzahl an Farben angezeigt,
 * wobei eine davon mit der Farbe im oberen Bereich uebereinstimmt
 * Der Spieler muss genau diese Farbe erraten <br>
 * 
 * In einem Textfeld werden Informationen zum aktuellen Spielverlauf angezeigt
 * (Anzahl der Versuche, Anzahl der Treffer, Trefferquote)
 *    
 * Links gibt es eine Auswahl fuer den Schwierigkeitsgrad (3 Checkboxen): <br>
 * <ul>
 * <li> Leicht: 9 Ratefarben </li>
 * <li> Normal: 36 Ratefarben </li>
 * <li> Schwer: 81 Ratefarben </li>
 * </ul>
 * 
 * @author David Meyer
 * @version 1.1
 */
public class MainFrame {

	/**
	 * Der Hauptframe (dort ist alles von der GUI drin)
	 */
	JFrame frame;
	/**
	 * In diesem Panel sind die Ratebuttons drin
	 */
	public JPanel panel;
	/**
	 * In diesem Panel sind alle Checkboxen fuer die Schwierigkeitseinstellung drin
	 */
	JPanel check;
	/**
	 * In diesem Panel ist der Titeltext und die zu erratende Farbe drin
	 */
	public JPanel titel;
	/**
	 * In diesem Label ist der Titeltext selbst drin
	 */
	JLabel titeltext;
	/**
	 * In diesem Textfeld stehen die anzahl der Versuche, Anzahl der Treffer und Trefferquote drin
	 */
	JTextField textfeld;
	/**
	 * In diesem Checkbox Array sind die 3 Checkboxen drin
	 */
	public JCheckBox[] checkboxen = new JCheckBox[3]; 
	/**
	 * In dieser ButtonGroup sind die Checkboxen geordnet 
	 * (hat nichts mit GUI zu tun, sonder verhindert nur, dass mehrere Checkboxen auf einmal aktiv sein koennen)
	 */
	ButtonGroup checkgruppe;
	
	/**
	 * In diesem GridLayout werden die Ratebuttons angeordnet
	 */
	public GridLayout raster;
	/**
	 * Anzahl der Ratebuttons (am Anfang 9)
	 */
	public int anzahlbuttons = 9;
	/**
	 * Generiert spaeter zufaellige Farben und
	 * waehlt dann eine zufaellige davon aus (die Farbe, die erraten werden muss)
	 */
	Random zufallsgenerator;
	
	/**
	 * Anzahl von Versuchen, die richtige Farbe zu erraten
	 */
	int versuche = 0;
	/**
	 * Wie oft richtig geraten wurden
	 * Teffer muss double sein, da int mit int berechnet nur int zurueckgibt
	 */
	double treffer = 0.0;
	/**
	 * Trefferquote in % (Treffer/Versuche * 100)
	 */
	double trefferquote = 0.0;
	/**
	 * Eine Liste mit Ratebuttons
	 */
	ArrayList<JButton> buttons = new ArrayList<JButton>();
	
	/**
	 * Wird zu Beginn des Programms ausgefuehrt
	 * @param args Laufzeitparameter (diesmal keine)
	 */
	public static void main(String[] args) {
		/**
		 * Gesamtes GUI (Objekt der dieser Klasse)
		 */
		MainFrame gui = new MainFrame();
		// Den Listener Klassen dieses Objekt uebergeben, damit diese auf dieses zugreifen koennen
		CheckboxListener.frame = gui;
		ButtonListener.frame = gui;
		// Das "Spiel" starten
		gui.los();
	}
	
	/**
	 * <u>Spiel wird gestartet:</u> <br>
	 * <ul>
	 * <li>Zufallsgenerator erstellen</li>
	 * <li>Frame erstellen/konfigurieren</li>
	 * <li>3 Checkboxen (um Schwierigkeitsgrad einzustellen) erstellen/konfigurieren</li>
	 * <li>Checkboxen gruppieren</li>
	 * <li>Panel mit Buttons erstellen/konfigurieren</li>
	 * <li>Titelzeile erstellen/konfiguerieren</li>
	 * <li>Textfeld mit Spieldaten erstellen/konfiguerieren</li>
	 * <li>Ratebuttons erstellen/konfiguerieren</li>
	 * </ul>
	 * <br>
	 * Waehrend des Spielablaufs werden manche Panels neu aufgebaut
	 * 
	 */
	public void los() {
		
		// Einen Zufallsgenerator erstellen
		zufallsgenerator = new Random();
		
		// Fenster konfigurieren
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(500, 150);
		frame.setSize(550, 550);
		frame.setVisible(true);
		// Titel des Frames einstellen 
		frame.setTitle("Farbenraten");
		
		// Eine neue Checkboxgruppe erstellen (damit nur eine Checkbox auf einmal aktiviert sein kann)
		checkgruppe = new ButtonGroup();
		
		// Das Panel in dem die Checkboxen sind erstellen
		check = new JPanel();
		// Ein BoxLayout auf diesem Panel einstellen (auf Y-Achse angeordnet -> Checkboxen sind untereinander)
		check.setLayout(new BoxLayout(check, BoxLayout.Y_AXIS));
		check.setPreferredSize(new Dimension(100, 100));
		// 3 Checkboxen erstellen und beschriften
		checkboxen[0] = new JCheckBox("Einfach");
		checkboxen[1] = new JCheckBox("Normal");
		checkboxen[2] = new JCheckBox("Schwierig");
		// Die erste Checkbox soll bei Beginn des Programms schon aktiviert sein
		checkboxen[0].setSelected(true);
		
		// Folgendes wird fuer jeden Button gemacht:
		for(int i = 0; i < 3; i++) {
			checkboxen[i].setVisible(true);
			// Einen CheckboxListener hinzufuegen (damit wir wissen, wann die Checkboxen aktiviert/deaktiviert wurden)
			checkboxen[i].addItemListener(new CheckboxListener());
			// Optional: Einen Hover Text hinzufuegen (wird angeziegt, wenn Mauszeiger auf Checkbox ist)
			checkboxen[i].setToolTipText("Schwierigkeit");
			// Der CheckboxGruppe die Checkboxen hinzufuegen
			checkgruppe.add(checkboxen[i]);
			// Dem Panel die Checkboxen hinzufuegen
			check.add(checkboxen[i]);
		}
		// An der rechten Seite des Fensters wird das Panel mit den Checkboxen hinzugefuegt
		frame.add(BorderLayout.EAST, check);
		
		// Neues Grid Layout mit (vorerst) 3 Zeilen und 3 Spalten erstellen
		raster = new GridLayout();
		raster.setColumns(3);
		raster.setRows(3);
		
		// Das Panel, in dem sich die Buttons befinden, erstellen 
		panel = new JPanel();
		panel.setVisible(true);
		panel.setSize(120, 120);
		
		// Den Panel, in dem der Titel und die zu erratende Hintergrundfarbe ist erstellen 
		titel = new JPanel();
		titel.setPreferredSize(new Dimension(300, 50));
		
		// Das Panel mit den Buttons ein GridLayout hinzufuegen
		panel.setLayout(raster);
		// Das Panel mit dem Buttons in der Mitte des Fensters hinzufuegen
		frame.add(BorderLayout.CENTER, panel);
		
		// Den Titeltext erstellen
		titeltext = new JLabel();
		titeltext.setText("Diese Hintergrundfarbe muss erraten werden!");
		
		// Titeltext dem Panel hinzufuegen
		titel.add(titeltext);
		// Dem Fenster das Panel mit dem Titel hinzufuegen
		frame.add(BorderLayout.NORTH, titel);
		
		// Textfeld erstellen und unten (SOUTH) im Fesnter einfuegen
		textfeld = new JTextField();
		frame.add(BorderLayout.SOUTH, textfeld);
		
		// Eine neue Runde erstellen
		// Parameter 0: Anzahl der Treffer und Anzahl der Versuche wird nicht erhoeht
		neueRunde(0);
	
	}
	/**
	 * Eine neue Runde beginnt, das heisst, dass die Buttons geloescht und neu erstellt werden muessen,
	 * wenn der Spieler nur den Schwierigkeitsgrad geaendert hat, wird diese Methode auch ausgefuehrt, jedoch
	 * wird es nicht als Versuch/Treffer gewertet <br>
	 * Wenn der Spieler richtig geraten hat, werden Treffer und Versuche erhoeht und die Trefferquote neu berechnet
	 * Wenn der Spieler falsch geraten har, wird nur die Anzahl der Versuche erhoeht
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
		// Die loeschen und wieder neu erstellen
		buttonsErstellen();
		/*
		 * Alternative fuer naechste Zeile:
		 * int zufallszahl = zufallsgenerator.nextInt(buttons.size()-1);
		 * Color farbe = buttons.get(zufallszahl).getBackground();
		 * title.setBackground(farbe);
		 */
		titel.setBackground(buttons.get(zufallsgenerator.nextInt(buttons.size()-1)).getBackground());
		
		// OPTIONAL: Die Farbe der Schrift im North ist die invertierte Farbe der hintergrundfarbe im North, damit man die Schrift besser lesen kann
		titeltext.setForeground(new Color(255-titel.getBackground().getRed(), 255-titel.getBackground().getGreen(), 255-titel.getBackground().getBlue()));
		if(getroffen > 0) versuche++;
		if(getroffen == 2) treffer++;
		if(treffer != 0) trefferquote = treffer/versuche * 100.0;
		else trefferquote = 0;
		textfeld.setText(String.format("Versuche: %d \t Treffer: %,.0f \t Trefferquote in %%: %,.4f ", versuche, treffer, trefferquote));
		// Den Frame updaten (frame.repaint() hat nicht funktioniert...)
		frame.validate();
	}
	
	/**
	 * Zuerst alle Buttons in der Button-Liste und in dem Panel loeschen
	 * Danach so viele Buttons erstellen, wie fuer die aktuelle Schwierigkeit notwendig sind
	 * Die 3 Farbkanaele fuer die Hintergrundfarbe der jeweiligen Buttons werden zufaellig gewaehlt
	 * Jeder Button bekommt eine eigene ButtonListener Instanz und wird zum Panel und zur Button-Liste hinzugefuegt
	 */
	public void buttonsErstellen() {
		
		panel.removeAll();
		buttons.clear();
		for(int i = 0; i < anzahlbuttons; i++) {
			buttons.add(new JButton());
			buttons.get(i).setBackground(new Color(zufallsgenerator.nextInt(255), zufallsgenerator.nextInt(255), zufallsgenerator.nextInt(255)));
			buttons.get(i).setVisible(true);
			buttons.get(i).addActionListener(new ButtonListener());
			panel.add(buttons.get(i));
		}
	}
}