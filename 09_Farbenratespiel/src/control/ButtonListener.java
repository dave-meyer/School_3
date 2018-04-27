package control;
import java.awt.event.*;

import javax.swing.JButton;

import view.MainFrame;

/**
 * Eine Listenerklasse fuer die Ratebuttons
 * Jeder Ratebutton (ein Button ohne Text und mit zufaellig gewaehlten Hintergrundfarbe) 
 * bekommt eine Instanz dieser Klasse als ActionListener
 * 
 * Diese Instanz wird benachrichtigt (die actionPerformed Methode wird ausgefuehrt) wenn
 * der Button gedrueckt wurde
 * 
 * @author David Meyer
 * @version 1.1
 */
public class ButtonListener implements ActionListener {
	/**
	 * Das MainFrame Objekt, das in der main-Methode instanziiert wird <br>
	 * Damit wird auf die Methoden und Variablen im MainFrame Objekt zugegriffen,
	 * zum Beispiel: Titelpanel, Frame, neueRunde() usw 
	 */
	public static MainFrame frame;
	
	@Override
	/**
	 * Wenn ein Button gedrueckt wurde, wird die Hintergrundfarbe dieses Buttons mit der Hintergrundfarbe des Titel-Panels verglichen
	 * Wenn die Farben uebereinstimmen wurde richtig geraten, wenn nicht, dann nicht
	 */
	public void actionPerformed(ActionEvent e) {

		if(((JButton) e.getSource()).getBackground() == frame.titel.getBackground()) {
			frame.neueRunde(2);
		}
		else {
			frame.neueRunde(1);
		}
	}
}