package model;

/**
 * Die Toolklasse besteht aus einer Menge von nützlichen statischen
 * Methoden, die in vielen Programmen genutzt werden können.
 * (in unserem Bespiel... in Wirklichkeit nur 1 Methode)
 * 
 * @author DI Martin Kampenhuber
 * @version 1.0
 * 
 */
public class Tool {

	/**
	 * Die Methode berechnet nach der Widmarkformel den Anteil Alkohol im Blut in
	 * Promille:
	 * Promille = Alkoholmenge in Gramm/(Körpergewicht in kg * 0,7)
	 * Wobei 1 Liter Alkohol 800 Gramm entsprechen.
	 * 
	 * @param alkinliter Der konsumierte Alkohol in Liter
	 * @param koerpergewicht Das Körpergewicht des Trinkers in Kilo
	 * @return Der Anteil Alkohol im Blut in Promille
	 */
	public static double getPromilleAlkoholWidmark(double alkinliter, double koerpergewicht) {
		double alkingramm = alkinliter * 800;
		double promille = alkingramm / (koerpergewicht * 0.7); 
		return promille * 0.85;	//Noch 15 % Abzug berücksichtigen
	}
}
