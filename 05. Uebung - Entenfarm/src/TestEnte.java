import java.util.*;

/**
 * Das Testprogramm erzeugt unterschiedlichste Enten, nimmt diese in einem Stall auf
 * und kalkuliert die tägliche Mistmenge
 * 
 * @author DI Martin Kampenhuber
 * @version 1.0
 */
public class TestEnte {

	//Die ArrayList aller Enten - würde auch ArrayList<Ente> gehen???
	private static ArrayList<Ente> stall = new ArrayList<Ente>();
	
	/**
	 * Die Main-Methode ruft die statischen Methoden zum Erzeugen
	 * der Enten, dem Ausdrucken des Stalls und der Berechnung
	 * der Mistmenge auf.
	 * 
	 * @param args es gibt keine Laufzeitparameter
	 */
	public static void main(String[] args) {
		entenErzeugen();
		System.out.println();
		stallAusdrucken();
		System.out.println();
		mistMengeBerechnen();
	}
	
	/**
	 * Die unterschiedlichsten Entenobjekte werden instanziiert
	 * und im Entenstall aufgenommen.
	 */
	private static void entenErzeugen() {
		stall.add(new Laufente());
		stall.add(new Laufente("ResiLaufiLosi"));
		stall.add(new Laufente("KurtiSchnelli", 20.0));
		stall.add(new Hausente());
		stall.add(new Hausente("HausiFaulie"));
		stall.add(new Hausente("HausiSchlafi", 3.0));
	}

	/**
	 * alle im Stall befindlichen Enten werden am Bildschirm 
	 * ausgegeben.
	 */
	private static void stallAusdrucken() {
		for (Ente ente : stall) {
			System.out.println(ente);
		}
	}
	
	/**
	 * Die Mistmenge sämtlicher Enten wird berechnet.
	 */
	private static void mistMengeBerechnen() {
		double mist = 0.0;
		for (Ente ente : stall) {
			mist += ente.calculateMistmenge(new Random().nextDouble());
		}
		System.out.printf("Im Stall sind %d Enten.\n", Ente.entenanzahl);
		System.out.printf("Diese produzieren täglich %,.2f Kilo Mist.",mist);
	}
}
