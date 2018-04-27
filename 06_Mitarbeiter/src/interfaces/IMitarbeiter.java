package interfaces;

/**
 * Fuer die Mitarbeiterklasse (und deren Subklassen)
 * Besagt, dass die Miarbeiterklasse die Methoden <br>
 * <ul>
 * <li>getname</li>
 * <li>getMonatsauszahlung<li>
 * <li>toString</li>
 * </ul> <br>
 * haben muss
 * 
 * @author David Meyer
 * @version 1.0
 *
 */
public interface IMitarbeiter {

	/**
	 * @return Name des Mitarbeiters
	 */
	public String getName();
	
	/**
	 * @return Monatsauszahlung/Gehalt des Mitarbeiters
	 */
	public double getMonatsauszahlung();
	
	/**
	 * @return Schoene Ausgabe der Eigenschaften
	 */
	public String toString();	
}