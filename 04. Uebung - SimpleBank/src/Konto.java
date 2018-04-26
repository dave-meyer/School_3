/**
 * Di Kontoklasse ist die Superklasse f�r Girokonten und Sparb�cher.
 * Jedes Konto besitzt eine Kontonummer und nat�rlich seinen aktuellen Kontostand.
 * Auf ein Konto kann ein- und ausgezahlt werden. Auszahlungen sind nur m�glich,
 * wenn das Konto gedeckt ist.
 * 
 * @author DI Martin Kampenhuber *
 */
public class Konto {
	
	private String kontonummer = "";
	private double kontostand = 0.0;
	
	/**
	 * Konstruktor f�r die Instanziierung eines Kontoobjektes.
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
	 * Einzahlungen k�nnen immer erfolgen - es erfolgt keine �berpr�fung.
	 * Eine diesbez�gliche Informationszeile wird am Bildschirm ausgegeben.
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
					           auszahlungsbetrag + " Euro nicht m�glich!");
		}
	}
	
	/**
	 * Ein Konto ist grunds�tzlich gedeckt, wenn die Abhebung des gew�nschten
	 * Betrages das Konto zumindest auf 0 Euro bleibt.
	 * @param betrag - der zu �berpr�fende Auszahlungsbetrag
	 * @return true, wenn Konto zumindest 0 bleibt - ansonsten false
	 */
	public boolean isKontoGedeckt(double betrag) {
		if ((kontostand - betrag) >= 0) return true; else return false;
	}
	
	/**
	 * Eine Bildschirmrepr�sentation eines Kontos.
	 */
	public String toString() {
		return String.format("Kontonummer: %s \tKontostand: � %,.2f", kontonummer, kontostand);
	}
}
