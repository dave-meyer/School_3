package unternehmen;

/**
 * Jeder Arbeiter hat zur Berechnung seiner Monatsauszahlung einen
 * Stundenlohn. Die Monatsauszahlung ergibt sich durch eine einfache
 * Multiplikation mit 160.
 * 
 * @author DI Martin Kampenhuber
 */
public class Arbeiter extends Mitarbeiter {
	
	private double stundenlohn;
	
	public Arbeiter(String n, double sl) {
		super(n);
		stundenlohn = sl;
	}
	
	public double getMonatsauszahlung() {
		return stundenlohn * 160;
	}
	
	public String toString() {
		return super.toString() + "\t'Arbeiter'" +
								  "\tStundenlohn: " + stundenlohn + 
								  "\tMonatsauszahlung: " + getMonatsauszahlung();
	}
}
