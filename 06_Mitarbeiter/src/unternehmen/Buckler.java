package unternehmen;

/**
 * Erbt von der Klasse Angestellter, welche von der Klasse Mitarbeiter erbt
 * 
 * Buckler haben zusaetzlich keine weiteren Eigenschaften
 * 
 * @author David Meyer
 * @version 1.0
 *
 */
public class Buckler extends Angestellter {

	/**
	 * Konstruktor, welcher den Namen empfaengt 
	 * und den Grundlohn empfaengt und sie an den Konstruktor der Superklasse weiterleitet
	 * 
	 * @param n Der Name des Bucklers 
	 * @param grundlohn_ Der Grundlohn des Bucklers
	 */
	public Buckler(String n, double grundlohn_) {
		super(n, grundlohn_);
	}
	
	/**
	 * Die Abwetztiefe von Bucklern ist der Quotient aus seiner Monatsauszahlung und 1000
	 * @return Die Abwetztiefe des Bucklers
	 */
	public double getAbwetztiefe() {
		return this.getMonatsauszahlung() / 1000;
	}

	/**
	 * Die Monatsauszahlung von Bucklern ist der Grundlohn multipliziert mit 0,9
	 * @return Die Monatsauszahlung des Bucklers
	 */
	public double getMonatsauszahlung() {
		return grundlohn * 0.9;
	}
	
	// die toString Methode wird komplett aus der Superklasse uebernommen
}