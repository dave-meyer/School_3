package control;
import java.awt.event.*;

import view.MainFrame;

/**
 * Diese Klasse ist eine Listener-Vorlage fuer Checkboxen
 * Es werden 3 Instanzen dieser Klasse erstellt, also 3 CheckboxListener
 * fuer 3 Checkboxen <br>
 * Diese Instanz wird benachrichtigt (die itemStateChanged Methode wird ausgefuehrt) wenn
 * die Checkbox gecheckt/entcheckt wurde
 * 
 * Jede Checkbox steht fuer einen Schwierigkeitsgrad (Einfach, Normal, Schwierig)
 * 
 * Nachdem ein neuer Schwierigkeitsgrad gewaehlt wurde, werden manche Teile
 * des Frames im MainFrame Objekt neu erstellt
 * 
 * Es Kann nur eine Checkbox auf einmal aktiviert sein, wegen der ButtonGroup
 * 
 * @author David Meyer
 * @version 1.1
 */
public class CheckboxListener implements ItemListener  {

	/**
	 * Das MainFrame Objekt, das in der main-Methode instanziiert wird <br>
	 * Damit wird auf die Methoden und Variablen im MainFrame Objekt zugegriffen,
	 * zum Beispiel: Frame, Checkboxen, GridLayout, usw
	 * 
	 * Variable ist static, da aus der Klasse CheckboxListener 3 Objekte instanziiert werden,
	 * welche alle auf das selbe MainFrame Objekt zugreifen
	 * Dank static muss die Variable nur einmal fuer alle 3 Objekte erstellt und zugewiesen werden
	 * Ohne static muesste die Variable fuer alle 3 Objekte neu zugewiesen werden 
	 */
	public static MainFrame frame;
	
	@Override
	/**
	 * Wird ausgefuehrt, wenn eine neue Checkbox aktiviert wurde <br>
	 * Das heisst, dass der Schwierigkeitsgrad geaendert wurde <br>
	 * Es kann (wegen der ButtonGroup) nur eine Checkbox auf einmal gecheckt sein  <br>
	 * <ul>
	 * <li> Einfach: 9 Ratebuttons ((0+1)*3)^2 </li>
	 * <li> Normal: 36 Ratebuttons ((1+1)*3)^2 </li>
	 * <li> Schwierig: 81 Ratebuttons ((2+1)*3)^2 </li>
	 * </ul>
	 */
	public void itemStateChanged(ItemEvent e) {

		// Alle Chechkboxen durchgehen
		for(int i = 0; i < frame.checkboxen.length; i++) {
			
			// Checken, ob Checkbox gecheckt ist
			if(frame.checkboxen[i].isSelected()) {
				// Alle Ratebuttons aus dem Panel entfernen
				frame.panel.removeAll();
				// Das Raster auf die Anzahl an Ratebuttons anpassen
				frame.raster.setColumns((i+1)*3);
				frame.raster.setRows((i+1)*3);
				frame.anzahlbuttons = ((i+1)*3) * ((i+1)*3);
				
				// Neue Runde wird begonnen, ohne die Zaehler zu erhoehen (0), da nicht geraten wurde, sondern nur der Schwierigkeitsgrad geandert
				frame.neueRunde(0);
				// OPTIONAL: Wenn die aktive Checkbox gefunden wurde, und nur eine Checkbox auf einmal gecheckt sein kann,
				// muss die Abfrage/Suche nach anderen aktiven Checkboxen nicht mehr ausgefuehrt werden
				break;
			}
		}
	}
}