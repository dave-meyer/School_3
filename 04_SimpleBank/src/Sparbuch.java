// Fuer die Darstellung einer Zahl mit fuehrenden 0
import java.text.DecimalFormat;
/**
 * 
 * Speichert die Kontonummer und den aktuellen Kontostand eines Sparbuches. 
 * Man kann auf Sparbuecher beliebige (logische) Werte einzahlen.
 * Man kann auf Sparbuecher beliebige (logische) Werte auszahlen, sofern genug Geld auf dem Konto vorhanden ist.
 * 
 * @author David Meyer
 * @version 1.0
 *
 */
public class Sparbuch extends Konto {
	/**
	 * Gibt die Anzahl der aktuell erstellten Sparbuecher +1 an <br>
	 * Wird fuer die Kontonummer-Vergabe verwendet
	 */
	private static int anzahlsparbuecher = 1;
	
	// Konstruktor
	/**
	 * Ruft den Konstruktor der Superklasse auf und gibt ihm die Kontonummer mit. 
	 * Die Kontonummer besteht aus 'SP' (fuer Sparbuch) und einer fortlaufenden Nummer
	 * mit fuehrenden 0
	 */
	public Sparbuch() {
		super(String.format("SP%s", new DecimalFormat("000").format(anzahlsparbuecher)));
		anzahlsparbuecher++;
		System.out.printf("Ein neues Sparbuch mit der Kontonummer %s wurde erfolgreich erstellt.\n", kontonummer);
	}
	
	/**
	 * Gibt eine schoene Ausgabe von dem Kontotyp und den Attributen Kontonummer und Kontostand zurueck
	 * @return Schoene Ausgabe von dem Kontotyp und den Attributen Kontostand und Kontonummer
	 */
	@Override
	public String toString() {
		return String.format("SPARBUCH \t", "") + super.toString(); 
	}
}