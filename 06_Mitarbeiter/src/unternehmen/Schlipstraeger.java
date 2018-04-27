package unternehmen;

/**
 * Erbt von der Klasse Angestellter, welche von der Klasse Mitarbeiter erbt
 * 
 * Schlipstraeger haben zusaetzlich eine Krawattenfarbe
 * 
 * @author David Meyer
 * @version 1.0
 *
 */
public class Schlipstraeger extends Angestellter {

	/**
	 * Die Farbe der Krawatte des Schlipstraegers
	 */
	String krawattenfarbe;
	
	/**
	 * Konstruktor, welcher den Namen, den Grundlohn und die Krawattenfarbe
	 * empfaengt und speichert (der Name und der Grundlohn werden an den Konstrukor der Superklasse weitergeleitet)
	 * @param n Der Name des Schlipstraegers
	 * @param grundlohn_ Der Grundlohndes Schlipstraegers
	 * @param krawattenfarbe_ Die Krawattenfarbe des Schlipstraegers
	 */
	public Schlipstraeger(String n, double grundlohn_, String krawattenfarbe_) {
		super(n, grundlohn_);
		krawattenfarbe = krawattenfarbe_;
	}
	
	@Override
	/**
	 * Die Abwetztiefe eines Schlipstraegers haengt von der Krawattenfarbe ab <br>
	 * <b>schwarze Krawatte</b> Monatsauszahlung dividiert durch 900 <br>
	 * <b>anderes farbige Krawatte</b> Monatsauszahlung durch 800 <br>
	 * 
	 * @retrun Die Abwetztiefe des Schlipstraegers
	 */
	public double getAbwetztiefe() {
		if(krawattenfarbe == "schwarz") {
			return this.getMonatsauszahlung() / 900;
		}
		else {
			return this.getMonatsauszahlung() / 800;
		}
	}

	@Override
	/**
	 * Die Monatsauszahlung von Schlipstraegern errechnet sich aus dem Grundlohn erhoeht um 200
	 * @return die Monatsauszahlung von Schlipstraegern
	 */
	public double getMonatsauszahlung() {
		return grundlohn + 200;
	}

	@Override
	/**
	 * Gibt zusaetzlich noch die Krawattenfarbe aus
	 * @return Schoene Ausgabe der Eigenschaften
	 */
	public String toString() {
		return String.format("%s \t Krawattenfarbe: %s", super.toString(), krawattenfarbe);
	}
}
