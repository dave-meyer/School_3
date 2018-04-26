/**
 * Ein Sparbuch erweitert die Kontoklasse und erbt somit s�mtliche Eigenschaften
 * und Methoden der Kontoklasse. Da bei einem Sparbuch die allgemeinen Ein- und
 * Auszahlungsmethoden verwendet werden k�nnen, braucht nichts �berschrieben
 * werden. Einzig die Bildschirmrepr�sentation ist zu erweitern bzw. die
 * diesbez�gliche Methode zu �berschreiben.
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
