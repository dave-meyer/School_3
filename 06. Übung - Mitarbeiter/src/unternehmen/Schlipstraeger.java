package unternehmen;

/**
 * Ein Schlipstraeger verfuegt ueber eine Krawatte; diese bestimmt die
 * Abwetztiefe.
 * 
 * @author DI Martin Kampenhuber
 */
public class Schlipstraeger extends Angestellter {

	private String krawattenfarbe;
	
	public Schlipstraeger(String n, double g, String kf) {
		super(n, g);
		krawattenfarbe = kf;
	}

	public double getMonatsauszahlung() {
		return super.getGrundgehalt() + 200.0;
	}
	
	public double getAbwetztiefe() {
		if (krawattenfarbe.equals("schwarz")) {
			return getMonatsauszahlung() / 900.0;
		} else {
			return getMonatsauszahlung() / 800.0;
		}
	}	
	
	public String toString() {
		return super.toString() + "\t'Schlipsträger'" 
								+ "\tKrawattenfarbe: " + krawattenfarbe
								+ "\tSesselabwetztiefe: " + getAbwetztiefe() 
								+ "\tMonatsauszahlung: " + getMonatsauszahlung();
	}
}
