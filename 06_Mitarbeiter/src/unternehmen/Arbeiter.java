package unternehmen;

/**
 * Erbt von der Klasse Mitarbeiter
 *
 * Arbeiter haben zusaetzlich noch einen Stundenlohn
 * 
 * @author David Meyer
 * @version 1.0
 *
 */
public class Arbeiter extends Mitarbeiter {
	
	/** 
	 * Der Stundenlohn des Arbeiters
	 */
	private double stundenlohn;
	
	/**
	 * Konstruktor, welcher den Namen empfaengt (und diesen an den Konstruktor der Superklasse weitergibt)
	 * und den Stundenlohn empfaengt
	 * @param n Der Name des Arbeiters 
	 * @param stundenlohn_ Der Stundenlohn des Arbeiters
	 */
	public Arbeiter(String n, double stundenlohn_) {
		super(n);
		stundenlohn = stundenlohn_;
	}
	
	/**
	 * Die Monatsauszahlung von Arbeitern errechnet sich aus dem Stundenlohn multipliziert mit 160
	 * @return die Monatsauszahlung des Arbeiters
	 */
	public double getMonatsauszahlung() {
		return stundenlohn * 160;
	}
	
	@Override
	/**
	 * Gibt den Namen, Stundenlohn und die Monatsauszahlung von einem Arbeiter aus
	 * der Name kommt von der toString Methode der Superklasse
	 * @return Schoene Ausgabe von den Eigenschaften
	 */
	public String toString() {
		return String.format("%s 'Arbeiter' \t Stundenlohn: %,.2f € \t Monatsauszahlung: %,.2f €", super.toString(), stundenlohn, getMonatsauszahlung());
	}
	

}
