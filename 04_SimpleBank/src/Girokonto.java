// Fuer die Darstellung einer Zahl mit fuehrenden 0
import java.text.DecimalFormat;
/**
 * Hier werden die Kontonummer, der Kontostand und der Rahmen eines Girokontos gespiechert.
 * 
 * Man kann auf Girokonten beliebige (logische) Werte einzahlen.
 * Man kann auf Girokonten beliebige (logische) Werte auszahlen, sofern der Rahmen nicht unterschritten wird. <br>
 * 
 * Diese Klasse erbt von der Klasse 'Konto' 
 * und ueberschreibt die Methoden 'ausZahlung' und 'toString'
 * 
 * @author David Meyer
 * @version 1.0
 *
 */
public class Girokonto extends Konto {
	/**
	 * Gibt die Anzahl der aktuell erstellten Girokonten +1 an <br>
	 * Wird fuer die Kontonummer-Vergabe verwendet
	 */
	private static int anzahlgkonten = 1;
	/**
	 * Der Rahmen des Girokontos
	 * (Um wie viel € das Konto ueberzogen werden darf)
	 */
	private double rahmen;
	
	// Konstruktor
	/**
	 * Ruft den Konstruktor der Superklasse auf und gibt ihm die Kontonummer mit. <br>
	 * Die Kontonummer besteht aus 'GI' (fuer Girokonto) und einer fortlaufenden Nummer
	 * mit fuehrenden 0
	 * @param r Der gewuenschte Rahmen fuer das Girokonto (wird auf Fehler ueberprueft)
	 */
	public Girokonto (double r) {
		super(String.format("GI%s", new DecimalFormat("000").format(anzahlgkonten)));
		if(r <= 0) {
			System.out.println("FEHLER in %s: Der Wert fuer den Rahmen ist ungueltig! Es wurde der Standardwert 0 als Rahmen verwendet.");
			rahmen = 0;
		}
		else {
			rahmen = r;
		}
		anzahlgkonten++;
		System.out.printf("Ein neues Girokonto mit der Kontonummer %s wurde erfolgreich erstellt. Der Rahmen betraegt %,.2f €.\n", kontonummer, rahmen);
	}

	/**
	 * Zahlt einen bestimmten Betrag,
	 * nach Beachtung des Rahmens und einer Fehlerabfrage mit Fehlermeldung,
	 * von dem Konto aus
	 * 
	 * @param betrag Der gewuenschte Auszahlungsbetrag
	 */
	@Override
	public void ausZahlung(double betrag) {
		if(betrag <= 0) {
			System.out.printf("FEHLER in %s: Der Betrag %,.2f € ist ungueltig!\n", kontonummer, betrag);
		}
		else if((kontostand - betrag) >= (rahmen*(-1))) {
			kontostand -= betrag;
			System.out.printf("Es wurden erfolgreich %,.2f € von dem Konto %s abgehoben. Der Kontostand betraegt jetzt %,.2f €.\n", betrag, kontonummer, kontostand);
		}
		else {
			System.out.printf("FEHLER in %s: Sie besitzen über zu wenig Geld, um %,.2f € auszahlen zu koennen!\n", kontonummer, betrag);
		}
	}
	
	/**
	 * Gibt eine schoene Ausgabe von dem Kontotyp und den Attributen Kontonummer, Kontostand und Rahmen zurueck
	 * @return Schoene Ausgabe von dem Kontotyp den Attributen Kontostand, Kontonummer und Rahmen
	 */
	@Override
	public String toString() {
		return String.format("GIROKONTO \t", "") + super.toString() + String.format("\t Rahmen: %,.2f", rahmen);
	}
}