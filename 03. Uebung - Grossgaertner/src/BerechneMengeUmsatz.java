/**
 * Die Klasse instanziiert in der main-Methode 4 Grundstücke, legt die Preise fuer
 * die benötigten Zäune und Fertigrasen fest und berechnet die benötigten Gesamt-
 * mengen und die dabei erzielten Umsätze.
 *  
 * @author DI Martin Kampenhuber
 * @version 1.0
 */
public class BerechneMengeUmsatz {

	/**
	 * Der gesamte Programmablauf findet in der Main-Methode statt.
	 * 
	 * @param args - es werden keine Laufzeitparameter übergeben
	 */
	public static void main(String[] args) {
		Grundstueck[] gruende = new Grundstueck[100];
		int anz = 4;

		//2 Rechteckige Grundstücke instanziieren und Preise fixieren
		gruende[0] = new Grundstueck("Grund_RE 1", 40.6, 23.4);
		gruende[0].setPreismeterzaun(12.7);
		gruende[0].setPreisqmeterrasen(27.4);
		gruende[1] = new Grundstueck("Grund_RE 2", 56.6, 35.4);
		gruende[1].setPreismeterzaun(13.7);
		gruende[1].setPreisqmeterrasen(37.4);
		
		//2 Quadratische Grundstücke instanziieren und Preise fixieren
		gruende[2] = new Grundstueck("Grund_QU 1", 50.7);
		gruende[2].setPreismeterzaun(11.7);
		gruende[2].setPreisqmeterrasen(24.4);
		gruende[3] = new Grundstueck("Grund_QU 2", 65.2);
		gruende[3].setPreismeterzaun(21.9);
		gruende[3].setPreisqmeterrasen(44.7);
		
		//Tagesmengen und Umsaetze kalkulieren und anzeigen
		System.out.println("Heute ist folgende Arbeit zu leisten - "
						 + "dabei werde ich folgende Umsaetze erzielen:");
		System.out.println("======================================="
						 + "===========================================");
		//Daten anzeigen und Gesamtmengen berechnen
		double gesmzaun = 0, gesqmrasen = 0;
		double gesumzaun = 0, gesumrasen = 0;
		for (int i = 0; i < anz; i++) {
			gruende[i].printDaten();
			gesmzaun += gruende[i].getUmfang();
			gesqmrasen += gruende[i].getFlaeche();
			gesumzaun += gruende[i].getUmsatzZaun();
			gesumrasen += gruende[i].getUmsatzRasen();
		}
		System.out.println("=============================================================================================================");
		System.out.printf("Benötigte Meter Zaun: %,.2f\nBenötigte Quadratmeter Fertigrasen: %,.2f\n",
				          gesmzaun, gesqmrasen);
		System.out.printf("Gesamtumsatz Zaun: %, .2f\nGesamtumsatz Fertigrasen: %,.2f\n",
				          gesumzaun, gesumrasen);
		System.out.printf("Am Tagesende klingeln in der Kasse: € %,.2f !!", gesumzaun+gesumrasen);
	}
}
