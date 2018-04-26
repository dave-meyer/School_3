import java.util.*;

/**
 * Die Klasse ermöglicht die Eingabe von diversen Grundstücksdaten. Mit diesen
 * Eigenschaften werden die Gesamtfläche und der Gesamtwert sämtlicher
 * Grundstücke berechnet.
 *  
 * @author DI Martin Kampenhuber
 * @version 1.0
 */
public class TestGrund {
	
	/** Die instanziierten Grundstücke werden in diesem Array verwaltet */
	static Grundstueck[] gruende = new Grundstueck[100];
	/** Zähler für die erfassten Grundstücksobjekte */
	static int grundanzahl = 0;								
	
	/**
	 * Die main-Methode separiert den Programmaufbau in 2 logische Blöcke.
	 * - das Erfassen der Grundstücksdaten und
	 * - die Berechnung der Ergebnisdaten
	 * @param args - Laufzeitparameter werden nicht ausgewertet
	 */
	public static void main(String[] args) {
		grundstueckeErfassen();
		zeigErgebnis();
	}
	
	/**
	 * Mit Hilfe eines Scannerobjektes werden die Grundstücksdaten - Länge,
	 * Breite und Quadratmeterpreis - erfasst.
	 */
	static void grundstueckeErfassen() {
		Scanner scan = new Scanner(System.in);
		String weitereGrundstücke = "j";
		while ((weitereGrundstücke.equals("j")) ||
			   (weitereGrundstücke.equals("J"))) {
			//neues Grundstückobjekt instanziieren und Eigenschaftswerte erfassen
			Grundstueck fleck = new Grundstueck();
			System.out.print("Grundstücksbezeichnung: ");
			fleck.setBezeichnung(scan.next());
			System.out.print("Länge: ");
			fleck.setLaengeInMeter(scan.nextDouble());
			System.out.print("Breite: ");
			fleck.setBreiteInMeter(scan.nextDouble());
			System.out.print("Preis pro qm: ");
			fleck.setPreisProQM(scan.nextDouble());
			gruende[grundanzahl] = fleck;
			grundanzahl++;
			//Soll noch ein weiteres Grundstück erfasst werden?
			System.out.print("weiteres Grundstück (j/n)?: ");
			weitereGrundstücke = scan.next();
		}
		scan.close();
	}
	
	/**
	 * Mit Hilfe der erfassten Grundstücke wirde die Gesamtfläche und der Gesamtwert
	 * ermittelt und am Bildschirm ausgegeben.
	 */
	static void zeigErgebnis() {
		double gesamtFlaeche = 0.0;
		double gesamtWert = 0.0;
		System.out.println("Sie besitzen " + grundanzahl + " Grundstücke:");
		System.out.println("=====================================");
		for (int i = 0; i < grundanzahl; i++) {
			Grundstueck fleck = gruende[i];
			System.out.printf("%s \t %,.2f qm\tá %,.2f € \tWert: %,.2f €\n", 
					fleck.getBezeichnung(), fleck.getFlaeche(), fleck.getPreisProQM(), fleck.getWert());
			gesamtFlaeche 	+= fleck.getFlaeche();
			gesamtWert 		+= fleck.getWert();
		}
		System.out.println("-------------------------------------");
		System.out.printf("Gesamtfläche der Grundstücke: %,.2f qm \n", gesamtFlaeche);
		System.out.printf("Gesamtwert ihrer Grundstücke: %,.2f € \n", gesamtWert);
		System.out.println("=====================================");
	}
}
