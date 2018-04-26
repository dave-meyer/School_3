/**
 * Ein Sparbuch erweitert die Kontoklasse und erbt somit sämtliche Eigenschaften
 * und Methoden der Kontoklasse. Da bei einem Sparbuch die allgemeinen Ein- und
 * Auszahlungsmethoden verwendet werden können, braucht nichts überschrieben
 * werden. Einzig die Bildschirmrepräsentation ist zu erweitern bzw. die
 * diesbezügliche Methode zu überschreiben.
 * 
 * @author DI Martin Kampenhuber
 */
public class Sparbuch extends Konto {
	
	public Sparbuch(String knr) {
		super(knr);
	}
	
	public String toString() {
		return "SPARBUCH:\t" + super.toString();
	}
}
