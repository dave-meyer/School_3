/**
 * Eine Vorlage fuer Girokonten und Sparbuecher.
 * Speichert die Kontonummer und den aktuellen Kontostand eines Kontos. 
 * Man kann auf Konten beliebige (logische) Werte einzahlen.
 * Man kann auf Konten beliebige (logische) Werte auszahlen, sofern genug Geld auf dem Konto vorhanden ist.
 * 
 * @author David Meyer
 * @version 1.0
 *
 */
public class Konto {

	/**
	 * Die Kontonummer des Kontos
	 * (bestehend aus Abkuerzung fuer Kontotyp und fortlaufender Nummer)
	 */
	protected String kontonummer;
	/**
	 * Der aktuelle Kontostand des Kontos <br>
	 * Jedes neue Konto startet mit einem Startbetrag von 0.00 €
	 */
	protected double kontostand;
	
	// Konstruktor
	/**
	 * Erstellt ein neues Konto mit gewuenschter Kontonummer
	 * (Abkuerzung vom Kontotyp und fortlaufende Nummer werden von Sub-Klassen uebergeben)
	 * <br> Der Kontostand betraegt am Beginn 0.00 €
	 * 
	 * @param nummer Die Kontonummer des zu erstellenden Kontos
	 */
	public Konto(String nummer) {
		kontostand = 0.0;
		kontonummer = nummer;
	}
	
	/**
	 * Zahlt einen bestimmten Betrag, nach einer Fehlerabfrage mit Fehlermeldung, von dem Konto aus
	 * <br> Wird in der Sub-Klasse 'Girokonto' ueberschrieben
	 * 
	 * @param betrag Der gewuenschte Auszahlungs-Betrag
	 */
	public void ausZahlung(double betrag) {
		if(betrag <= 0) {
			System.out.printf("FEHLER in %s: Der Betrag %,.2f € ist ungueltig!\n", kontonummer, betrag);
		}
		else if((kontostand - betrag) < 0) {
			System.out.printf("FEHLER in %s: Sie besitzen über zu wenig Geld, um %,.2f € auszahlen zu koennen!\n", kontonummer, betrag);
		}
		else {
			kontostand -= betrag;
			System.out.printf("Es wurden erfolgreich %,.2f € von dem Konto %s abgehoben. Der Kontostand betraegt jetzt %,.2f €.\n", betrag, kontonummer, kontostand);
		}
	}
	/**
	 * Zahlt einen bestimmten Betrag, nach einer Fehlerabfrage mit Fehlermeldung, auf das Konto ein
	 * 
	 * @param betrag Der gewuenschte Einzahlungs-Betrag
	 */
	public void einZahlung(double betrag) {
		if(betrag <= 0) {
			System.out.printf("FEHLER in %s: Der Betrag %,.2f € ist ungueltig!\n", kontonummer, betrag);
		}
		else {
			kontostand += betrag;
			System.out.printf("Es wurden erfolgreich %,.2f € auf das Konto %s eingezhalt. Der Kontostand betraegt jetzt %,.2f €.\n", betrag, kontonummer, kontostand);
		}
	}
	/**
	 * Gibt den Kontostand zurueck
	 * @return Den Kontostand des Kontos
	 */
	public double getKontostand() {
		return kontostand;
	}
	/**
	 * Gibt die Kontonummer zurueck
	 * @return Die Kontonummer des Kontos
	 */
	public String getKontonummer() {
		return kontonummer;
	}
	/**
	 * Gibt eine schoene Ausgabe von den Attributen kontonummer und kontostand zurueck
	 * <br> Wird in den Sub-Klassen ueberschrieben
	 * @return Schoene Ausgabe von den Attributen
	 * 
	 */
	@Override
	public String toString() {
		return String.format("Kontonummer: %s \t Kontostand: %,.2f €", kontonummer, kontostand);
	}
}
