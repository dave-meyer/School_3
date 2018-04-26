/**
 * Eine Laufente hat als spezielle Eigenschaft ihre Geschwindigkeit. Davon
 * ist auch die zu berechnende Mistmenge abhängig.
 * 
 * @author DI Martin Kampenhuber
 * @version 1.0
 */
public class Laufente extends Ente {
	
	private double geschwindigkeit;
	
	/**
	 * Eine Ente mit bekannter Geschwindigkeit wird erzeugt
	 * 
	 * @param n Name der Ente
	 * @param g Geschwindigkeit der Ente
	 */
	public Laufente (String n, double g) {
		super(n);
		geschwindigkeit = g;
		System.out.printf("\tGeschwindigkeit: %,.2f \n", geschwindigkeit);
	}
	
	/**
	 * Eine Ente mit unbekannter Geschwindigkeit ist zu konstruieren. Der
	 * Standardwert "10.0" wird festgelegt.
	 * 
	 * @param n Name der Ente
	 */
	public Laufente(String n) {
		this(n, 10);
	}
	
	/**
	 * Eine Ente ohne Namen und unbekannter Geschwindigkeit ist zu instanziieren.
	 * Die Standardwerte werden fixiert.
	 */
	public Laufente() {
		super();
		geschwindigkeit = 10.0;
		System.out.printf("\tGeschwindigkeit: %,.2f \n", geschwindigkeit);
	}
	
	public String toString() {
		return super.toString() + "\tGeschwindigkeit: " + geschwindigkeit + "\tbin eine Laufente!";
	}

	/**
	 * Die Mistmenge ergibt sich aus Futtermenge multipliziert mit
	 * der Futteranzahl und dividiert durch die Geschwindigkeit.
	 * 
	 * @param futtermenge Futtermenge, die die Ente bekommt.
	 */
	public double calculateMistmenge(double futtermenge) {
		return futtermenge * getFuetteranzahl() / geschwindigkeit;
	}
}
