package exceptions;

import model.Trinker;

/**
 * Die Klasse beinhaltet s�mtliche Methoden zur Pr�fung der Eingaben
 * Diese einzelnen Pr�fmethoden werfen eine WrongInputException, wenn etwas
 * nicht interpretiert werden konnte. Diese Exception gibt dann eine
 * entsprechende Fehlermeldung am Bildschirm aus.
 */
public class ErrorHandling {

	public static void checkKilo(String kiloS) throws WrongInputException {
		double kilo = 0;
		try {
			kilo = new Double(kiloS);
		} catch(Exception ex) {
			throw new WrongInputException("Das eingegebene Gewicht '" + kiloS + "' konnte nicht interpretiert werden!");
		}
	}
	
	public static void checkAlkohol(String alkS) throws WrongInputException {
		double alk = 0;
		try {
			alk = new Double(alkS);
		} catch(Exception ex) {
			throw new WrongInputException("Der eingegebene Alkoholwert '" + alkS + "' konnte nicht interpretiert werden!");
		}
	}

	public static void checkLiter(String literS) throws WrongInputException {
		double liter = 0;
		try {
			liter = new Double(literS);
		} catch(Exception ex) {
			throw new WrongInputException("Die eingegebene Menge '" + literS + "' konnte nicht interpretiert werden!");
		}
	}
	
	public static void checkAnzahlGetraenke(int maxanzahl) throws WrongInputException {
		if (Trinker.getGetraenke().size() >= maxanzahl) {
			throw new WrongInputException("Die maximale Getr�nkeanzahl von " + maxanzahl + " Getr�nken wurde erreicht!");
		}
	}
	
}
