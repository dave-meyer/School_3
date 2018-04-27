import java.awt.FlowLayout;
import java.awt.event.*;

import javax.swing.*;
/**
 * Diese Klasse erstellt einen Frame mit 3 Buttons,
 * welche jeweils wieder ein neues Window erstellen
 * 
 * @author David Meyer
 * @version 1.0
 */
public class ErsteGUI implements ActionListener {
	
	/**
	 * 3 GUI Buttons
	 */
	static JButton button1, button2, button3;
	/**
	 * Der Rahmen mit dem Fenster, in dem 3 Buttons sind
	 */
	static JFrame frame = new JFrame();
	
	/**
	 * Main Methode wird bei Start des Programms aufgerufen
	 * @param args Laufzeitparameter (hier keine)
	 */
	public static void main(String[] args) {
		/**
		 * Instanz der eigenen Klasse erstellen <br>
		 * Darin kann die 'los' Methode aufgerufen werden, welche 
		 * sonst nicht verfuegbar waere, weil sie nicht <k>static</k> ist
		 */
		ErsteGUI gui = new ErsteGUI();
		gui.los();
	}
	
	/**
	 * Instanziiert 3 neue Buttons, speichert sie in die Button Variablen,
	 * traegt das Objekt (this) in die 'ActionEvent-senden'-Liste der Buttons ein,
	 * fuegt die Buttons zur Content-Pane des Frames ein
	 * und stellt sonstige Einstellungen ein <br>
	 * 
	 * Diese Methode darf nicht <k>static</k> sein, weil das Schluesselwort
	 * 'this' in ihr verwendet wird, welches nur in einer Instanz einer Klasse
	 * (und nicht in der Klasse selbst) verfuegbar ist
	 */
	public void los() {
		// Buttons erstellen
		button1 = new JButton("Quadrat zeichnen");
		button2 = new JButton("Bild anzeigen");
		button3 = new JButton("Zufallskreis");
		
		// dieses Objekt in die ActionEvent-Liste der Buttons eintragen
		button1.addActionListener(this);
		button2.addActionListener(this);
		button3.addActionListener(this);
		
		// Buttons dem Frame hinzufuegen
		frame.getContentPane().add(button1);
		frame.getContentPane().add(button2);
		frame.getContentPane().add(button3);
		
		// Bei Klicken auf das 'X' soll nicht nur das Fenster, sondern das ganze Programm geschlossen werden
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// FlowLaylout einstellen (damit sind mehrere Buttons moeglich)
		frame.setLayout(new FlowLayout());
		
		// Breite und Hoehe des Rahmens einstellen
		frame.setSize(500, 130);
		// Sichtbar machen
		frame.setVisible(true);
	}
	
	/**
	 *	Wird ausgefuehrt, wenn ein ActionEvent passiert ist
	 *	Wird hier nur von Buttons ausgefuehrt
	 *
	 *	@param event Das Event, welches aufgetreten ist
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		/*
		switch(((JButton) event.getSource()).getName()) {
			case "button1": break;
			case "button2": break;
			case "button3": break;
		}
		*/
		
		// Wenn der erste Button gedrueckt wurde
		if(event.getSource().equals(button1)) {
			
			// Neues MyRectWindow erstellen
			MyRectWindow rectWindow = new MyRectWindow();
			rectWindow.setSize(100, 100);
			rectWindow.setVisible(true);
		}
		
		// Wenn der zweite Button gedrueckt wurde
		if(event.getSource().equals(button2)) {
			
			// neues MyImageWindow erstellen
			MyImageWindow imageWindow = new MyImageWindow();
			imageWindow.setSize(500, 500);
			imageWindow.setVisible(true);
		}
		
		// Wenn der dritte Button gedrueckt wurde
		if(event.getSource().equals(button3)) {
			
			// neues CircleWindow erstellen
			MyCircleWindow CircleWindow = new MyCircleWindow();
			CircleWindow.setSize(500, 500);
			CircleWindow.setVisible(true);
		}
	}
}