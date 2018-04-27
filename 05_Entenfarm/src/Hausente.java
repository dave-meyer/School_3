/**
 * 
 * Erbt von der Klasse 'Ente'
 * 
 * Enthaelt zusaetzlich noch den faulheitsgrad, exclusiv fuer Hausenten 
 * Die Berechnung der Mistmenge wird fuer Hausenten erweitert
 * 
 * Der Standardfaulheitsgrad betraegt 2.0
 * 
 * @author David Meyer
 * @version 1.0
 */
public class Hausente extends Ente {
	
	/** 
	 * Der Faulheitsgrad von Hausenten
	 */
	private double faulheitsgrad;
	/**
	 * Der Standardfaulheitsgrad (falls keiner mitgegeben wird)
	 */
	private final static double standardfaulheit = 2.0;
	
	/**
	 * <u> 1 Konstruktor </u> 
	 * Keine Eigenschaften mitgegeben. <br>
	 * Es wird ein Standardwert als Name und ein Standardwert als faulheitsgrad verwendet. 
	 * Der Name besteht aus 'DuckyNamenlos' und der Nummer der Ente <br>
	 * Gibt eine Bestaetigungsnachricht mit Eigenschaften der Ente aus
	 */
	public Hausente() {
		super();
		faulheitsgrad = standardfaulheit;
		System.out.printf("Faulheitsgrad: %,.2f \n", faulheitsgrad);
	}
	
	/**
	 * <u> 2 Konstruktor </u>
	 * Nur der Name wird mitgegeben. <br>
	 * Es wird ein Standardwert als faulheitsgrad verwendet.
	 * Der 3. Konstruktor wird aufgerufen
	 * @param n Name, der fuer die Ente vorgesehen ist
	 */
	public Hausente(String n) {
		this(n, standardfaulheit);
	}
	
	/**
	 * <u> 3 Konstruktor </u>
	 * Der Name und der Faulheitsgrad mitgegeben. 
	 * Es gibt eine Fehlerabfrage fuer den Faulheitsgrad. <br>
	 * Gibt eine Bestaetigungsnachricht mit Eigenschaften der Ente aus
	 * @param n Name, der fuer die Ente vorgesehen ist
	 * @param f Faulheitsgrad, der fuer die Ente vorgesehen ist
	 */
	public Hausente(String n, double f) {
		super(n);
		faulheitsgrad = f;
		if(f < 0) {
			System.out.printf("FEHLER bei Ente %s: Der Faulheitsgrad hat einen ungueltigen Wert. \nEs wird der Standardwert verwendet.", n);
			faulheitsgrad = standardfaulheit;
		}
		System.out.printf("Faulheitsgrad: %,.2f \n", faulheitsgrad);
	}
	
	@Override
	/**
	 * Berechnet die Mistmenge einer Hausente aus der futtermenge (wird mitgegeben), dem faulheitsgrad und der futteranzahl <br>
	 * @param futtermenge wie viel Futter pro Fuetterung gefressen wird
	 * @return Gibt Mistmenge zurueck
	 */
	public double calculateMistmenge(double futtermenge) {
		return (super.calculateMistmenge(futtermenge) * faulheitsgrad);
	}
	
	@Override
	/**
	 * Gibt Namen (von Methode in Super-Klasse), Faulheitsgrad und Entenart zurueck
	 * @return Formatierte Ausgabe von Eigenschaften
	 */
	public String toString() {
		return super.toString() + String.format("Faulheitsgrad: %,.2f \t ist eine Hausente!", faulheitsgrad);
	}
}