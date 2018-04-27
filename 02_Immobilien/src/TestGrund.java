// Scanner Klasse importieren
import java.util.Scanner;

/**
 * In dieser Klasse wird das Programm getestet.
 * 
 * @author David Meyer
 * @version 1.1
 */
public class TestGrund {
	
	/**
	 * Objekt 'scan' wird  verwendet, um Texte bzw
	 * Zahlen aus der Konsole auszulesen
	 */
	public static Scanner scan = new Scanner(System.in);
	/**
	 * Im Array 'gruende' werden alle Grundstuecke gespeichert
	 */
	public static Grundstueck[] gruende = new Grundstueck[100];
	
	/**
	 * Oeffnet Dialog, mit welchem der Benutzer seine
	 * Grundstuecksdaten speichern kann
	 */
	static public void grundstueckeErfassen() {
			
			// Neues Grundstueck erstellen und in gruende Array speichern
			gruende[Grundstueck.grundanzahl] = new Grundstueck(); 
			
			// Dialog:
				// Name des zu speichernden Wertes anzeigen
			System.out.print("Grundstuecksbezeichnung: ");
				// Vom Benutzer eingegebenen Wert auslesen und speichern
			gruende[Grundstueck.grundanzahl].bezeichnung = scan.next();
			
			System.out.print("Laenge (in Metern): ");
			gruende[Grundstueck.grundanzahl].laengeinmeter = scan.nextDouble();
			
			System.out.print("Breite (in Metern): ");
			gruende[Grundstueck.grundanzahl].breiteinmeter = scan.nextDouble();
			
			System.out.print("Preis (pro  Quadratmeter): ");
			gruende[Grundstueck.grundanzahl].preisproqm = scan.nextDouble();
			
			// Anzahl der Grundstuecke erhoehen
			Grundstueck.grundanzahl++;
			
			System.out.print("Weiteres Grundstueck? (j/n): ");
			String antwort = scan.next();
			/*
			 *  Wenn Benutzer 'Nein' gewaehlt hat: Dialog schlieﬂen und Infos anzeigen
			 *  Ansonsten: Dialog nocheinmal oeffnen
			 */
			if(antwort.equals("j")) {
				grundstueckeErfassen();
			}
			else {
				/*
				 *  Die Gesamtinformationen der eben
				 *  eingetragenen Grundteucke anzeigen
				 */
				zeigErgebnis();
			}
	}
	/**
	 * Zeigt Uebersicht ueber alle eingetragenen
	 * bzw gespiecherten Grundsteucke
	 * Zeigt: Anzahl der Grundstuecke, Einzelwerte aller Grundsteucke,
	 * die Gesamtflaeche und den Wert aller Grundstuecke zusammen
	 */
	static public void zeigErgebnis() {
		
		System.out.println("Sie besitzen " + Grundstueck.grundanzahl + " Grundstuecke: ");
		System.out.println("=====================================");
		double gesamtflaeche = 0;
		double gesamtwert = 0;
		for(int i = 0; i < Grundstueck.grundanzahl; i++) {
			gesamtflaeche += gruende[i].getFlaeche();
			gesamtwert += gruende[i].getWert();
			System.out.printf("%s \t %,.2f qm \t je %,.2f Ä \t Wert: %,.2f Ä \n", gruende[i].getBezeichnung(),
						gruende[i].getFlaeche(), gruende[i].getPreisProQM(), gruende[i].getWert());
		}
		System.out.println("-------------------------------------");
		System.out.printf("Gesamtflaeche der Grundstuecke: %,.2f qm \n", gesamtflaeche);
		System.out.printf("Gesamtwert der Grundstuecke: %,.2f Ä \n", gesamtwert);
		System.out.println("=====================================");
	}
	
	/**
	 * @param args In diesem Array werden alle Startwerte gespeichert
	 * Methode 'main' dient zum Testen der Anwendung
	 */
	public static void main(String[] args) {
		// Dialog zur Grundsteuckspeicherung oeffnen
		grundstueckeErfassen();
	}
}
