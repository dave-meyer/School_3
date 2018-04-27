package unternehmen;

import interfaces.IMitarbeiter;
/**
 * Unterliegt dem Interface IMitarbeiter
 * Superklasse fuer die Klassen Arbeiter, Angestellter, Buckler und Schlipstraeger
 * 
 * Mitarbeiter haben einen Namen, eine toString Methode fuer die schoene Ausgabe des Namens,
 * eine Methode die den Namen ausgibt, einen Konstruktor der den Namen empfaengt und eine
 * Methode, welche spaeter die Monatsauszahlung ausgibt
 * 
 * @author David Meyer
 * @version 1.0
 *
 */

public abstract class Mitarbeiter implements IMitarbeiter {

	/**
	 * Der Name des Mitarbeiters
	 */
	String name;
	
	/**
	 * Konstruktor, welcher den Namen empfaengt und speichert 
	 * @param n Name des Mitarbeiters
	 */
	public Mitarbeiter(String n) {
		name = n;
	}
	
	@Override
	/**
	 * Gibt den Namen des Mitarbeiters zurueck
	 * @return Name des Mitarbeiters
	 */
	public String getName() {
		return name;
	}

	@Override
	/**
	 * Die Subklassen muessen eine Methode haben,
	 * welche die Monatsauszahlung zurueckgibt
	 */
	public abstract double getMonatsauszahlung();
	
	@Override
	/**
	 * gibt vorerst nur den Namen aus, da jeder Mitarbeiter einen hat <br>
	 * Wird spaeter von subklassen mit spezifischen Eigenschaften erweitert
	 */
	public String toString() {
		return String.format("%s \t", name);
	}

}