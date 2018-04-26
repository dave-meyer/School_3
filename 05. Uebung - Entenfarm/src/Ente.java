/**
 * Eine Ente kann auf 2 unterschiedliche Arten instanziiert werden - mit und ohne Namen.
 * Die toString()-Methode gibt den Namen am Bildschirm aus. 
 *  
 * @author DI Martin Kampenhuber
 * @version 1.0
 */
public abstract class Ente {

	public static int entenanzahl = 0;
	private static final int fuetteranzahl = 2;	//Anzahl täglicher Fütterung
	private String name;
	
	/**
	 * Eine Ente mit bekanntem Namen wird erzeugt
	 * 
	 * @param n Name der Ente
	 */
	public Ente(String n) {
		entenanzahl++;		//Eine neue Ente wurde geboren!
		name = n;
		System.out.printf("Ente Nummer: %d - '%s' \twurde geboren!!", entenanzahl, name);
	}
	
	/**
	 * Jede Subklasse muss diese Methode implementieren ==> abstract
	 * @param futtermenge
	 * @return berechnete Mistmenge
	 */
	public abstract double calculateMistmenge(double futtermenge);

	/**
	 * Eine namenlose Ente wird erzeugt - sie bekommt den Namen "DuckyNamenlos" plus
	 * einer laufenden Nummer.
	 */
	public Ente() {
		this("DuckyNamenlos" + entenanzahl);	//Eine neue Ente ohne expl. Namen
	}
	
	public int getFuetteranzahl() {
		return fuetteranzahl;
	}
	
	/**
	 * Der Name wird am BS ausgegeben.
	 */
	public String toString() {
		return "Name: " + name;
	}
}
