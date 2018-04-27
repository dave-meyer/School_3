package unternehmen;

import interfaces.IAngestellter;

/**
 * Erbt von der Klasse Mitarbeiter
 * Unterliegt dem Interface IAngestellter
 * Superklasse fuer die Klassen Buckler und Schlipstraeger
 * 
 * Angestellte haben zusaetzlich noch einen Grundlohn und eine Abwetztiefe
 * 
 * @author David Meyer
 * @version 1.0
 *
 */
public abstract class Angestellter extends Mitarbeiter implements IAngestellter {

	public double grundlohn;
	
	/**
	 * Konstruktor, welcher den Namen und den Grundlohn empfaengt
	 * Der Name wird an den Konstruktor der Superklasse weitergeleitet
	 * 
	 * @param n Name des Angestellten
	 * @param grundlohn_ Grundlohn des Angestellten
	 */
	public Angestellter(String n, double grundlohn_) {
		super(n);
		grundlohn = grundlohn_;
	}
	
	/**
	 * Die Subklassen muessen eine Methode besitzen,
	 * welche die Abwetztiefe zurueckgibt
	 */
	public abstract double getAbwetztiefe();
	
	@Override
	/**
	 * Gibt den Namen, die Bezeichnung als Angestellter,
	 * die weitere Unterbezeichnung fuer Angestellte (Buckler oder Schlipstraeger),
	 * die Sesselabwetztiefe und die Monatsauszahlung schoen aus
	 * 
	 * Der Name kommt von der toString Methode der Superklasse,
	 * Die Unterbezeichnung fuer Angestellte (Buckler oder Schlipstraeger) kommt von dem 
	 * Namen der jeweiligen Klasse (der gesamte Name lautet z.B.: 'unternehmen.Buckler'.
	 * Deswegen muessen die ersten 12 Zeichen abgeschnitten werden, damit 'unternehmen.' verschwindet),
	 * Die Sesselabwetztiefe / Monatsauszahlung kommt von den jeweiligen Getter-Methoden 
	 * 
	 */
	public String toString() {
		return String.format("%s 'Angestellter' %s \t Sesselabwetztiefe: %,.2f \t Monatsauszahlung: %,.2f €", super.toString(), this.getClass().getName().substring(12), this.getAbwetztiefe(), this.getMonatsauszahlung());
	} 
}
