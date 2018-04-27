// Array List importieren
import java.util.ArrayList;

// Interfaces aus anderen Packages importieren
import interfaces.IAngestellter;
import interfaces.IMitarbeiter;

// Klassen aus anderen Packages importieren
import unternehmen.Mitarbeiter;

import unternehmen.Angestellter;
import unternehmen.Arbeiter;

import unternehmen.Buckler;
import unternehmen.Schlipstraeger;

/**
 * Testet die Funktionen aller anderen Klassen
 * 
 * Instanziiert mehrere Objekte, speichert sie in eine Liste
 * und gibt deren Eigenschaften aus
 * 
 * @author David Meyer
 * @version 1.0
 */
public class MitarbeiterTest {

	/**
	 * Speichert alle Mitarbeiter in der Firma <br>
	 * Ist global, weil die Variabel in mehreren Methoden verwendet wird <br>
	 * Man koennte als Typ auch 'Mitarbeiter' verwenden
	 */
	public static ArrayList<IMitarbeiter> firma = new ArrayList<IMitarbeiter>();
	/**
	 * Speichert alle Angestellten in der Firma <br>
	 * Ist global, weil die Variabel in mehreren Methoden verwendet wird <br>
	 * Man koennte als Typ auch 'Angestellter' verwenden
	 */
	public static ArrayList<IAngestellter> angestellte = new ArrayList<IAngestellter>();
	
	/**
	 * Hiermit startet das Programm <br>
	 * <u>Taetigkeiten: </u><br>
	 * <ul>
	 * <li>Instanziiert 7 Mitarbeiter in 'firma'</li>
	 * <li>Gibt die Mitarbeiter mit ihren Eigenschaften und der Gesamtauszahlsumme aus</li>
	 * <li>Gibt nur die Angestellten aus und speichert sie seperat in 'angestellte'</li>
	 * </ul>
	 * @param args Startparameter: hier keine
	 */
	public static void main(String[] args) {

		mitarbeiterInstanziieren();
		mitarbeiterAusgeben();
		System.out.println("");
		angestellteAusgeben();
	}
	
	/**
	 * Instanziiert 2 Arbeiter, 2 Buckler und 3 Schlipstraeger
	 * und speichert alle in der 'firma' Liste
	 */
	public static void mitarbeiterInstanziieren() {
		firma.add(new Arbeiter("Hans", 10.0));
		firma.add(new Arbeiter("Franz", 10.0));
		
		firma.add(new Buckler("Peter", 1600));
		firma.add(new Buckler("Klaus", 1450));
		
		firma.add(new Schlipstraeger("Ernst", 2000, "rot"));
		firma.add(new Schlipstraeger("Rosa", 1800, "schwarz"));
		firma.add(new Schlipstraeger("Frida", 1900, "grau"));
	}
	
	/**
	 * Gibt alle Mitarbeiter mit deren Eigenschaften und die monatliche Auszahlungssumme in der Firma aus
	 */
	public static void mitarbeiterAusgeben() {
		/**
		 * monatliche Gesamtauszahlungssumme
		 */
		double summe = 0;
		for(IMitarbeiter mitarbeiter : firma) {
			System.out.println(mitarbeiter);
			summe += mitarbeiter.getMonatsauszahlung();
		}
		System.out.println("==================================================================================================================================");
		System.out.printf("monatliche Auszahlungssumme in der Firma: %,.2f € \n", summe);
	}
	
	/**
	 * Gibt alle Angestellten aus und speichert sie in einer eigenen Liste
	 */
	public static void angestellteAusgeben() {
		System.out.println("Alle Angestellten:");
		System.out.println("~~~~~~~~~~~~~~~~~~~~");
		for(IMitarbeiter mitarbeiter : firma) {
	
			// Checkt, ob der Mitarbeiter auch ein Angestellter ist
			if(mitarbeiter instanceof IAngestellter) {
				angestellte.add((IAngestellter) mitarbeiter);
				
				System.out.printf("Name: %s \t Sesselabwetztiefe: %,.2f \n", mitarbeiter.getName(), ((IAngestellter) mitarbeiter).getAbwetztiefe());
			}
		}
		System.out.println("==================================================================================================================================");
	}
}