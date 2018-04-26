package unternehmen;

/**
 * Ein Buckler ist ein spezieller Angestellter, dessen Monatsauszahlung
 * unterschiedlich berechnet wird.
 *  
 * @author DI Martin Kampenhuber
 */
public class Buckler extends Angestellter {

	public Buckler(String n, double g) {
		super(n, g);
	}
	
	public double getMonatsauszahlung() {
		return getGrundgehalt() * 0.9;
	}
	
	public String toString() {
		return super.toString() + "\t'Buckler'" 
								+ "\tSesselabwetztiefe: " + getAbwetztiefe() 
							    + "\tMonatsauszahlung: " + getMonatsauszahlung();
	}
}
