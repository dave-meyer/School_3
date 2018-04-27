/**
 * 
 * Erbt von der Klasse 'Ente'
 * 
 * Enthaelt zusaetzlich noch den Geschwindigkeit, exclusiv fuer Laufenten 
 * Die Berechnung der Mistmenge wird fuer Laufenten erweitert
 * 
 * Die Standardgeschwindigkeit betraegt 10.0
 * 
 * @author David Meyer
 * @version 1.0
 */
public class Laufente extends Ente {

	/**
	 * Die Geschwindigkeit von Laufenten
	 */
	private double geschwindigkeit;
	/**
	 * Die Standardgeschwindigkeit (falls keine spezifische angegeben wird)
	 */
	private final static double standardgeschw = 10.0;
	
	/**
	 * <u> 1 Konstruktor </u> 
	 * Keine Eigenschaften mitgegeben. <br>
	 * Es wird ein Standardwert als Name und ein Standardwert als Geschwindigkeit verwendet. 
	 * Der Name besteht aus 'DuckyNamenlos' und der Nummer der Ente <br>
	 * Gibt eine Bestaetigungsnachricht mit Eigenschaften der Ente aus
	 */
	public Laufente() {
		super();
		geschwindigkeit = standardgeschw;
		System.out.printf("Geschwindigkeit: %,.2f \n", standardgeschw);
	}
	
	/**
	 * <u> 2 Konstruktor </u>
	 * Nur der Name wird mitgegeben. <br>
	 * Es wird ein Standardwert als Geschwindigkeit verwendet.
	 * Der 3. Konstruktor wird aufgerufen
	 * @param n Name, der fuer die Ente vorgesehen ist
	 */
	public Laufente(String n) {
		this(n, standardgeschw);
	}
	
	/**
	 * <u> 3 Konstruktor </u>
	 * Der Name und die Geschwindigkeit mitgegeben. 
	 * Es gibt eine Fehlerabfrage fuer den Geschwindigkeit. <br>
	 * Gibt eine Bestaetigungsnachricht mit Eigenschaften der Ente aus
	 * @param n Name, der fuer die Ente vorgesehen ist
	 * @param g Geschwindigkeit, der fuer die Ente vorgesehen ist
	 */
	public Laufente(String n, double g) {
		super(n);
		geschwindigkeit = g;
		if(g < 0) {
			System.out.printf("FEHLER bei Ente %s: Die Geschwindigkeit hat einen ungueltigen Wert. \nEs wird der Standardwert verwendet.", n);
			geschwindigkeit = standardgeschw;
		}
		System.out.printf("Geschwindigkeit: %,.2f \n", geschwindigkeit);
	}
	
	@Override
	/**
	 * Berechnet die Mistmenge einer Laufente aus der futtermenge (wird mitgegeben), der Geschwindigkeit und der futteranzahl <br>
	 * @param futtermenge wie viel Futter pro Fuetterung gefressen wird
	 * @return Gibt Mistmenge zurueck
	 */
	public double calculateMistmenge(double futtermenge) {
		return (super.calculateMistmenge(futtermenge) / geschwindigkeit);
	}
	
	@Override
	/**
	 * Gibt Namen (von Methode in Super-Klasse), Geschwindigkeit und Entenart zurueck
	 * @return Formatierte Ausgabe von Eigenschaften
	 */
	public String toString() {
		return super.toString() + String.format("Geschwindigkeit: %,.2f \t ist eine Laufente!", geschwindigkeit);
	}
}