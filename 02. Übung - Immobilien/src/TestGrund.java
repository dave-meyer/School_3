import java.util.*;

/**
 * Die Klasse erm�glicht die Eingabe von diversen Grundst�cksdaten. Mit diesen
 * Eigenschaften werden die Gesamtfl�che und der Gesamtwert s�mtlicher
 * Grundst�cke berechnet.
 *  
 * @author DI Martin Kampenhuber
 * @version 1.0
 */
public class TestGrund {
	
	/** Die instanziierten Grundst�cke werden in diesem Array verwaltet */
	static Grundstueck[] gruende = new Grundstueck[100];
	/** Z�hler f�r die erfassten Grundst�cksobjekte */
	static int grundanzahl = 0;								
	
	/**
	 * Die main-Methode separiert den Programmaufbau in 2 logische Bl�cke.
	 * - das Erfassen der Grundst�cksdaten und
	 * - die Berechnung der Ergebnisdaten
	 * @param args - Laufzeitparameter werden nicht ausgewertet
	 */
	public static void main(String[] args) {
		grundstueckeErfassen();
		zeigErgebnis();
	}
	
	/**
	 * Mit Hilfe eines Scannerobjektes werden die Grundst�cksdaten - L�nge,
	 * Breite und Quadratmeterpreis - erfasst.
	 */
	static void grundstueckeErfassen() {
		Scanner scan = new Scanner(System.in);
		String weitereGrundst�cke = "j";
		while ((weitereGrundst�cke.equals("j")) ||
			   (weitereGrundst�cke.equals("J"))) {
			//neues Grundst�ckobjekt instanziieren und Eigenschaftswerte erfassen
			Grundstueck fleck = new Grundstueck();
			System.out.print("Grundst�cksbezeichnung: ");
			fleck.setBezeichnung(scan.next());
			System.out.print("L�nge: ");
			fleck.setLaengeInMeter(scan.nextDouble());
			System.out.print("Breite: ");
			fleck.setBreiteInMeter(scan.nextDouble());
			System.out.print("Preis pro qm: ");
			fleck.setPreisProQM(scan.nextDouble());
			gruende[grundanzahl] = fleck;
			grundanzahl++;
			//Soll noch ein weiteres Grundst�ck erfasst werden?
			System.out.print("weiteres Grundst�ck (j/n)?: ");
			weitereGrundst�cke = scan.next();
		}
		scan.close();
	}
	
	/**
	 * Mit Hilfe der erfassten Grundst�cke wirde die Gesamtfl�che und der Gesamtwert
	 * ermittelt und am Bildschirm ausgegeben.
	 */
	static void zeigErgebnis() {
		double gesamtFlaeche = 0.0;
		double gesamtWert = 0.0;
		System.out.println("Sie besitzen " + grundanzahl + " Grundst�cke:");
		System.out.println("=====================================");
		for (int i = 0; i < grundanzahl; i++) {
			Grundstueck fleck = gruende[i];
			System.out.printf("%s \t %,.2f qm\t� %,.2f � \tWert: %,.2f �\n", 
					fleck.getBezeichnung(), fleck.getFlaeche(), fleck.getPreisProQM(), fleck.getWert());
			gesamtFlaeche 	+= fleck.getFlaeche();
			gesamtWert 		+= fleck.getWert();
		}
		System.out.println("-------------------------------------");
		System.out.printf("Gesamtfl�che der Grundst�cke: %,.2f qm \n", gesamtFlaeche);
		System.out.printf("Gesamtwert ihrer Grundst�cke: %,.2f � \n", gesamtWert);
		System.out.println("=====================================");
	}
}
