/**
 * Di Kontoklasse ist die Superklasse für Girokonten und Sparbücher.
 * Jedes Konto besitzt eine Kontonummer und natürlich seinen aktuellen Kontostand.
 * Auf ein Konto kann ein- und ausgezahlt werden. Auszahlungen sind nur möglich,
 * wenn das Konto gedeckt ist.
 * 
 * @author DI Martin Kampenhuber *
 */
public class Konto {
	
	private String kontonummer = "";
	private double kontostand = 0.0;
	
	/**
	 * Konstruktor für die Instanziierung eines Kontoobjektes.
	 * 
	 * @param knr - Die Kontonummer (alfanum.)
	 */
	public Konto(String knr) {
		kontonummer = knr;
	}
	
	public double getKontostand() {
		return kontostand;
	}
	

	/**
	 * Einzahlungen können immer erfolgen - es erfolgt keine Überprüfung.
	 * Eine diesbezügliche Informationszeile wird am Bildschirm ausgegeben.
	 * 
	 * @param einzahlungsbetrag
	 */
	public void einZahlen(double einzahlungsbetrag) {
		kontostand += einzahlungsbetrag;
		System.out.println("EIN" + "\t" + kontonummer + "\t" +
						   einzahlungsbetrag + "\t" + "akt. Kontostand: " +
						   kontostand);
	}
	
	/**
	 * Wenn das Konto gedeckt ist, darf der Auszahlungsbetrag ausbezahlt werden.
	 * Ansonsten erfolgt eine Fehlermeldung am Bildschirm.
	 * 
	 * @param auszahlungsbetrag
	 */
	public void ausZahlen(double auszahlungsbetrag) {
		if (isKontoGedeckt(auszahlungsbetrag)) {
			kontostand -= auszahlungsbetrag;
			System.out.println("AUS" + "\t" + kontonummer + "\t" +
					   auszahlungsbetrag + "\t" + "akt. Kontostand: " +
					   kontostand);
		} else {
			System.out.println(kontonummer + " ... Auszahlung von " +
					           auszahlungsbetrag + " Euro nicht möglich!");
		}
	}
	
	/**
	 * Ein Konto ist grundsätzlich gedeckt, wenn die Abhebung des gewünschten
	 * Betrages das Konto zumindest auf 0 Euro bleibt.
	 * @param betrag - der zu überprüfende Auszahlungsbetrag
	 * @return true, wenn Konto zumindest 0 bleibt - ansonsten false
	 */
	public boolean isKontoGedeckt(double betrag) {
		if ((kontostand - betrag) >= 0) return true; else return false;
	}
	
	/**
	 * Eine Bildschirmrepräsentation eines Kontos.
	 */
	public String toString() {
		return String.format("Kontonummer: %s \tKontostand: € %,.2f", kontonummer, kontostand);
	}
}
