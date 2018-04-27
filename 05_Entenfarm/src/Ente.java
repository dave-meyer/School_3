/**
 * Eine Vorlage fuer Hausenten und Laufenten.
 * Speichert die Futteranzahl und den Namen fuer jede Ente individuell
 * und die Anzahl der Gesamtenten
 * Man kann die generelle Mistmenge in Abhaengigkeit 
 * von der Futtermenge und der Futteranzahl berechnen 
 * 
 * @author David Meyer
 * @version 1.0
 *
 */
public class Ente {
	
	/**
	 * Anzahl der existenten Enten
	 */
	private static int entenanzahl = 0;
	/**
	 * Die Anzahl der Futtervorgaenge: Es ist keine Beschreibung zu
	 * diesem Wert gegeben - ich habe den Wert 1 angenommen
	 */
	private int futteranzahl = 1;
	/**
	 * Name der Ente
	 */
	private String name;
	
	/** 
	 * <u> 1 Konstruktor: </u>
	 * Keine Eigenschaften mitgegeben. <br>
	 * Es wird ein Standardwert als Name verwendet. 
	 * Er besteht aus 'DuckyNamenlos' und der Nummer der Ente
	 * 
	 * Der 2 Konstruktor wird aufgerufen
	 */
	public Ente() {
		this("DuckyNamenlos" + (entenanzahl+1));
	}
	
	/**
	 * <u> 2 Konstruktor: </u>
	 * Wenn Name mitgegeben wird. <br>
	 * Gibt eine Bestaetigungsnachricht mit Eigenschaften der Ente aus
	 * 
	 * @param n Name, der fuer die Ente vorgesehen ist
	 */
	public Ente(String n) {
		entenanzahl++;
		name = n;
		System.out.printf("Ente Nr. %d - '%s' \t wurde geboren! \t", entenanzahl, name);
	}
	
	/**
	 * Berechnet die generelle Mistmenge aus der futtermenge (wird mitgegeben) und der futteranzahl
	 * @param futtermenge wie viel Futter pro Fuetterung gefressen wird
	 * @return Gibt Mistmenge zurueck
	 */
	public double calculateMistmenge(double futtermenge) {
		return (futtermenge * futteranzahl);
	}
	
	@Override
	/**
	 * Formatierte Ausgabe des Namens <br>
	 * (wird von sub-Klassen ueberschrieben und erweitert)
	 * @return formatierte Ausgabe des Namens
	 */
	public String toString() {
		return String.format("Name: %s \t", name);
	}
}
