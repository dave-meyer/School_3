package exceptions;

import view.AlkoholGUI;

/**
 * Enthaelt static Methoden, um bestimmte eingetragene Werte in
 * {@link view.AlkoholGUI} auf ihre Sinnhaftigkeit zu ueberpruefen
 * 
 * Werte werden nur ueberpruft und bei falscher Eingabe eine {@link WrongInputException} ausgegeben
 * 
 * @author David Meyer
 * @version 1.0
 * @see view.AlkoholGUI
 * @see WrongInputException
 */
public class ErrorHandling {

	public static void checkKilo(String kiloS) throws WrongInputException {
		
		try {
			// versuchen, String in double zu verwandeln
			// falls Fehler -> NullPointerException oder NumberFormatException -> WrongInputException
			// eventuell koennte man nach einer Sinnhaftigkeit der eingebenen Masse abfragen (z.B. Ober- und Untergrenzen)
			Double.parseDouble(kiloS);
		}
		catch(NumberFormatException ex) {
			throw new WrongInputException("Die eingebene Masse muss eine Zahl sein!");
		}
		catch(NullPointerException ex) {
			throw new WrongInputException("Es wurde keine Masse eingegeben!");
		}
		
	}
	
	/**
	 * Checkt, ob der eingegebene Alkoholgehalt sinnvoll ist
	 * (eine Zahl und nicht mehr als 100%)
	 * 
	 * @param alkS eine Zeichenkette mit (hoffentlich) einem Prozentwert fuer das Getraenk
	 * @throws WrongInputException falls alkS nicht sinnvoll ist (kann ein Popup Fenster oeffnen)
	 */
	public static void checkAlkohol(String alkS) throws WrongInputException {
		
		try {
			// versuchen, String in double zu verwandeln
			// falls Fehler -> NullPointerException oder NumberFormatException -> WrongInputException
			// falls mehr als 100% -> WrongInputException
			
			if(Double.parseDouble(alkS) > 100) {
				
				throw new WrongInputException("Der eingegebene Alkoholgehalt kann nicht mehr als 100% sein!");
			}
		}
		// Falls alkS keine Zahl
		catch(NumberFormatException ex) {
			throw new WrongInputException("Der eingegebene Alkoholgehalt muss eine Zahl sein!");
		}
		// Falls alkS kein Wert hat
		catch(NullPointerException ex) {
			throw new WrongInputException("Es wurde kein Alkoholgehalt eingegeben!");
		}
	}
	
	/**
	 * Checkt, ob die eingegebene Literanzahl sinnvoll ist
	 * (eine Zahl)
	 * 
	 * @param alkS eine Zeichenkette mit (hoffentlich) einem Literwert fuer das Getraenk
	 * @throws WrongInputException falls literS nicht sinnvoll ist (kann ein Popup Fenster oeffnen)
	 */
	public static void checkLiter(String literS) throws WrongInputException {
		
		try {
			// versuchen, String in double zu verwandeln
			// falls Fehler -> NullPointerException oder NumberFormatException -> WrongInputException
			Double.parseDouble(literS);
			
		}
		// Falls literS keine Zahl
		catch(NumberFormatException ex) {
			throw new WrongInputException("Die eingegebene konsumierte Menge muss eine Zahl sein!");
		}
		// Falls literS kein Wert hat
		catch(NullPointerException ex) {
			throw new WrongInputException("Es wurde keine konsumierte Menge eingegeben!");
		}
	}
	
	/**
	 * Checkt, ob die maximale Anzahl an eintragbaren Getraenken (AlkoholGUI.MAX_GETRAENKE, normalerweise 10) erreicht wurde
	 * bei Ueberschreitung gibt es Exception
	 * 
	 * @param maxanzahl das wievielte Getraenk das jetzt eingetragene Getraenk ist
	 * @throws WrongInputException Falls das jetzt eingetragene Getraenk das Limit ueberschreiten wuerde
	 */
	public static void checkAnzahlGetraenke(int maxanzahl) throws WrongInputException {
		
		if(maxanzahl > AlkoholGUI.MAX_GETRAENKE) throw new WrongInputException("Es koennen nur maximal " + AlkoholGUI.MAX_GETRAENKE + " Getraenke eingegeben werden!");
	}
	
}