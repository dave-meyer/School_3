/**
 * @author David Meyer
 * @version 1.0
 * 
 * Hier werden 4 Gruendsteucke erstellt (2 rechteckige, 2 quadratische) und ihnen Werte zugewiesen
 * Am Ende werden alle Einzelwerte und alle Gesamtwerte ausgegeben
 */
public class BerechneMengeUmsatz {

	/** @param args in diesem String Array werden die Laufzeitparameter gespeichert */
	public static void main(String[] args) {
		/** In diesem Array werden die Grundstuecke gespeichert */
		Grundstuecke[] gruende = new Grundstuecke[100];
		
		// 4 Grundstuecke mit verschiedenen Werten erstellen und in gruende Array speichern
		gruende[0] = new Grundstuecke("Bernhart", 40, 20);
		gruende[1] = new Grundstuecke("Lara", 95, 60);
		gruende[2] = new Grundstuecke("Valentina", 50);
		gruende[3] = new Grundstuecke("Klaus", 50);
		
		// Den Preis fuer 1 Meter Zaun bei jedem Objekt setzen
		gruende[0].setPreismeterzaun(10);
		gruende[1].setPreismeterzaun(15);
		gruende[2].setPreismeterzaun(17.90);
		gruende[3].setPreismeterzaun(5);
		
		// Den Preis fuer 1 qm Rasen bei jedem Objekt setzen
		gruende[0].setPreisqmeterrasen(20);
		gruende[1].setPreisqmeterrasen(30);
		gruende[2].setPreisqmeterrasen(34);
		gruende[3].setPreisqmeterrasen(10);
		
		/** Anzahl der benoetigten Meter Zaun fuer alle Grundstuecke */
		double gesamtmeterzaun = 0;
		/** Anzahl der benoetigten Quatrameter Kunstrasen fuer alle Grundstuecke */
		double gesamtqmeterrasen = 0;
		/** Der Umsatz alle Zaeune */
		double gesamtumsatzzaun = 0;
		/** Der Umsatz des gesamten Kunstrasens */
		double gesamtumsatzrasen = 0;
		System.out.println("Heute ist folgende Arbeit zu erledigen - hierbei werde ich folgende Umsaetze erzielen:");
		System.out.println("*======================================================================================*");
		for(int i = 0; i < Grundstuecke.anzahl; i++) {
			System.out.print((i+1) + "]: ");
			gruende[i].printDaten();
			gesamtmeterzaun += gruende[i].getUmfang();
			gesamtqmeterrasen += gruende[i].getFlaeche();
			gesamtumsatzzaun += gruende[i].getUmsatzZaun();
			gesamtumsatzrasen += gruende[i].getUmsatzRasen();
		}
		System.out.println("*======================================================================================*");
		System.out.printf("Benoetigte Meter Zaun: %,.2f %n", gesamtmeterzaun);
		System.out.printf("Benoetigte Quadratmeter Fertigrasen: %,.2f %n", gesamtqmeterrasen);
		System.out.printf("Gesamtumsatz Zaun: %,.2f %n", gesamtumsatzzaun);
		System.out.printf("Gesamtumsatz Fertigrasen:  %,.2f %n", gesamtumsatzrasen);
		System.out.printf("GESAMTUMSATZ: %,.2f %n", gesamtumsatzzaun + gesamtumsatzrasen);

	}
}
