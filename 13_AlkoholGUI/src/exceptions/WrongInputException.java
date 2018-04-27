package exceptions;

import java.awt.Component;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 * Exception, falls ein eingegebener Wert (zB in {@link view.AlkoholGUI} ) nicht sinnvoll ist
 * 
 * Enthaelt Methoden zum Ausgeben in der Konsole und in einem Popupfenster
 * @author David Meyer
 * @version 1.0
 * @see view.AlkoholGUI
 * @see ErrorHandling
 */
public class WrongInputException extends Exception {
	
	/** Fehlermeldung (kann in Konsole und Popupfenster angezeigt werden)*/
	String errortext;
	
	/**
	 * Erzeugt neue Exception mit der mitgegebenen Fehlermeldung
	 * @param error Fehlermeldung
	 */
	public WrongInputException(String error) {
		errortext = error;
	}
	
	/**
	 * Oeffnet ein PopuFenster mit der Fehlermeldung
	 * @param parent Parentcomponent (vorzugsweise der gesamte Frame) fuer das Popup
	 */
	public void showOptionPane(Component parent) {
		JOptionPane.showMessageDialog(parent, errortext, "Falsche Eingabe", JOptionPane.OK_OPTION, null);
	}
	
	/**
	 * Gibt die Fehlermeldung in der Konsole aus
	 */
	public void fehlermeldungAusgeben() {
	
		System.out.println(errortext);
	}
}