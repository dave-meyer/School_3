package unternehmen;
import interfaces.*;

/**
 * Jeder Angestellte hat einen Grundgehalt. Das Interface schreibt vor, dass
 * die Abwetztiefe berechnet wird.
 * 
 * @author DI Martin Kampenhuber
 */
public abstract class Angestellter extends Mitarbeiter implements IAngestellter {
	
	private double grundgehalt;

	public Angestellter(String n, double g) {
		super(n);
		grundgehalt = g;
	}
	
	public double getGrundgehalt() {
		return grundgehalt;
	}
	
	public double getAbwetztiefe() {
		return getMonatsauszahlung() / 1000.0;
	}	
	
	public String toString() {
		return super.toString() + "'Angestellter'\t"; 
	}
}
