package unternehmen;
import interfaces.*;

/**
 * Jeder Mitarbeiter hat einen Namen; dieser wird �ber den
 * Konstruktor mitgegeben und kann im Nachhinein nicht mehr
 * ver�ndert werden (keine Settermethode).
 *  
 * @author DI Martin Kampenhuber
 */
public abstract class Mitarbeiter implements IMitarbeiter {

	private String name;
	
	public Mitarbeiter(String n) {
		name = n;
	}

	public String getName() {
		return name;
	}
	
	public String toString() {
		return name;
	}
}
