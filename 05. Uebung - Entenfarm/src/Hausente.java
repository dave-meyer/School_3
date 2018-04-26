/**
 * Jede Hausente hat einen Faulheitsgrad - ist dieser beim Konstruieren der Ente nicht bekannt,
 * wird fix "2" angenommen. 
 *  
 * @author DI Martin Kampenhuber
 * @version 1.0
 */
public class Hausente extends Ente {

	private double faulheitsgrad;
	
	/**
	 * Eine Ente mit bekanntem Faulheitsgrad wird erzeugt
	 * 
	 * @param n Name der Ente
	 * @param f Faulheitsgrad der Ente
	 */
	public Hausente(String n, double f) {
		super(n);
		faulheitsgrad = f;
		System.out.printf("\tFaulheitsgrad: %,.2f \n", faulheitsgrad);
	}
	
	/**
	 * Eine Ente mit unbekanntem Faulheitsgrad ist zu konstruieren. Der
	 * Standardwert "2" wird festgelegt.
	 * 
	 * @param n Name der Ente
	 */
	public Hausente(String n) {
		this(n, 2);
	}
	
	/**
	 * Eine Ente ohne Namen und unbekanntem Faulheitsgrad ist zu instanziieren.
	 * Die Standardwerte werden fixiert.
	 */
	public Hausente() {
		super();
		faulheitsgrad = 2;
		System.out.printf("\tFaulheitsgrad: %,.2f \n", faulheitsgrad);
	}
	
	public String toString() {
		return super.toString() + "\tFaulheitsgrad: " + faulheitsgrad + "\tbin eine Hausente!";
	}	
	
	/**
	 * Die Mistmenge ergibt sich aus Futtermenge multipliziert mit
	 * der Futteranzahl und dem Faulheitsgrad.
	 * 
	 * @param futtermenge Futtermenge, die die Ente bekommt.
	 */
	public double calculateMistmenge(double futtermenge) {
		return futtermenge * getFuetteranzahl() * faulheitsgrad;
	}	
}
