// ArrayListen und Random Klassen importieren
import java.util.ArrayList;
import java.util.Random;

/**
 * Testet die Funktionalitaeten von den Enten-Klassen
 * 
 * @author David Meyer
 * @version 1.0
 *
 */
public class TestEnte {
	
	/**
	 * Enthaelt alle Hausenten und Laufenten in unserem Stall
	 */
	public static ArrayList<Ente> stall = new ArrayList<Ente>();
	
	/**
	 * Macht folgendes:
	 * <ul>
	 * <li>Erzeugt 6 Enten</li>
	 * <li>Zeigt deren Eigenschaften an</li>
	 * <li>Berechnet deren taeglichen Mist</li>
	 * </ul>
	 * 
	 * @param args Laufzeitparameter (in diesem Projekt keine)
	 */
	public static void main(String args[]) {
		System.out.println("=================================================================================");
		entenErzeugen();
		stallAusdrucken();
		mistMengeBerechnen();
		System.out.println("=================================================================================");
	}
	
	/**
	 * Erzeugt 3 Hausenten und 3 Laufenten
	 * Jeweils eine ohne Eigenschaften,
	 * eine nur mit einem Namen
	 * und eine mit Namen und spezifischer Eigenschaft
	 */
	public static void entenErzeugen() {
		stall.add(new Hausente());
		stall.add(new Hausente("Entetainment"));
		stall.add(new Hausente("DonaldDuck", 5.3));
		
		stall.add(new Laufente());
		stall.add(new Laufente("DasEnteIstNah"));
		stall.add(new Laufente("QuietscheEnte", 15.2));
		System.out.println("");
	}
	
	/**
	 * Gibt Informationen der Enten im Stall aus
	 */
	public static void stallAusdrucken() {
		
		for(int i = 0; i < stall.size(); i++) {
			System.out.println(stall.get(i).toString());
		}
		System.out.println("");
	}
		
	/**
	 * Berechnet und zeigt die taegliche Mistmenge aller Enten
	 */
	public static void mistMengeBerechnen() {
		/**
		 * Mist, den alle Enten zusammen im Stall taeglich verursachen
		 */
		double gesamtmist = 0;
		/**
		 * Index fuer folgende for-Schleife (zur Ermittlung der taeglichen Kotmenge)
		 * wird noch vor der Schleife deklariert, damit sie danach noch weiter verwendet werden kann
		 * 
		 * Alternativ koennte man die 'Entenanzahl' Variabel der 'Ente' Klasse verwenden, jedoch ist diese private
		 */
		int i = 0;
		// Geht durch jede Ente im Stall durch und summiert den Kot auf
		for(; i < stall.size(); i++) {
			// Der Kot jeder Ente wird mit einem Zufallswert als Futtermenge berechnet und zusammenadiert
			gesamtmist += stall.get(i).calculateMistmenge(new Random().nextDouble());
		}
		// Fuer plural und singular Formen gibt es eine Abfrage von der Anzahl der Enten im Stall
		switch(i) {
			case 0: System.out.println("Im Stall sijd keine Enten. \n"); break;
			case 1: System.out.printf("Im Stall ist eine Ente.\nDiese produziert taeglich %,.2f Kilo Mist. \n", gesamtmist); break; // Bei einer Ente
			default: System.out.printf("Im Stall sind %d Enten.\nDiese produzieren taeglich %,.2f Kilo Mist. \n", i, gesamtmist); break; // Bei mehreren Enten
		}
	}
}