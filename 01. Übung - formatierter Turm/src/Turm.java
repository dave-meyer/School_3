/**
 * Die Klasse Turm nimmt bei Programmstart 2 Laufzeitparameter auf
 * Der erste Parameter ist die Startzahl der Turmrechnung.
 * Der Zweite Parameter gibt die Anzahl der Durchläufe an.
 * 
 * Das Programm berechnet den Turm und gibt ihn ansprechend formatiert aus.
 * 
 * @author 	DI Martin Kampenhuber
 * @version 1.0
 */
public class Turm {

	/**
	 * Die gesamte Turmberechnung und Darstellung erfolgt in der 
	 * main-Methode der Klasse
	 * 
	 * @param args[0] Startzahl
	 * @param args[1] Anzahl der Durchläufe
	 */
	public static void main(String[] args) {
		double startzahl	= new Double(args[0]); 	//erstes Laufzeitargument
		int  durchlaeufe	= new Integer(args[1]); //Anzahl der Durchläufe
		
		System.out.println("Folgende Turmrechnung ist zu lösen:");
		System.out.println("===================================");
		System.out.println("Startzahl:\t\t" + startzahl);
		System.out.println("Multiplikation bis:\t" + durchlaeufe);
		System.out.println();
		
		//Turm berechnen
		double zahl = startzahl;
		for (int i = 2; i <= durchlaeufe; i++) {
			System.out.printf("Durchlauf %d:\t %,.2f * %d = %,.2f%n", i-1, zahl, i, zahl*=i);
		}
		for (int i = 2; i <= durchlaeufe; i++) {
			System.out.printf("Durchlauf %d:\t %,.2f : %d = %,.2f%n", i-1, zahl, i, zahl/=i);
		}
		
		//Kontrolle, ob Startzahl nach Berechnungen erreicht wurde, oder ob
		//es Rundungsdifferenzen gibt.
		System.out.println("\nStartzahl: "+ startzahl + '\t' + "Endzahl: " + zahl);
		if (startzahl == zahl) {
			System.out.println("GRATULATION! Es gab keine Rundungsfehler - der Turm wurde korrekt berechnet!");
		} else {
			System.out.println("OJEHHH! Es gab Rundungsfehler - bitte Algorithmus kontrollieren!!");
		}
	}

}
