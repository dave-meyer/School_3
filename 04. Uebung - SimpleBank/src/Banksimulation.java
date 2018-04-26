/**
 * Die Testklasse instanziiert 2 Giro- und 2 Sparb�cher. Diese werden in einem
 * Array aufgenommen. Die Ein- und Auszahlungen auf die Konten sind so gestaltet,
 * dass die Grenzwerte getestet werden und somit �berpr�ft wird, ob die Rahmen.. u.dgl.
 * auch richtig ber�cksichtigt werden.
 * 
 * @author DI Martin Kampenhuber
 */
public class Banksimulation {

	static Konto[] konten = new Konto[100];
	static int anz = 0;		//Anzahl instanziierter Konten
	
	public static void main(String[] args) {
		kontenInstanziieren();
		vermoegenDrucken();
		einUndAuszahlen();
		vermoegenDrucken();
	}
	
	/**
	 * Instanziieren von 2 Giro- und 2 Sparb�chern
	 */
	private static void kontenInstanziieren() {
		Girokonto g1 = new Girokonto("GI001", 1500); konten[anz++] = g1;
		Girokonto g2 = new Girokonto("GI002", 3000); konten[anz++] = g2;
		Sparbuch s1 = new Sparbuch("SP001"); konten[anz++] = s1;
		Sparbuch s2 = new Sparbuch("SP002"); konten[anz++] = s2;
	}
	
	/**
	 * Eine Konten�bersicht mit dem Gesamtverm�gen wird am Bildschirm ausgegeben.
	 */
	private static void vermoegenDrucken() {
		double gesamtvermoegen = 0;
		System.out.println("\nSie verf�gen bei unserer Bank �ber folgende Konten:");
		System.out.println("===================================================");
		for (int i = 0; i < anz; i++) {
			System.out.println(konten[i]);
			gesamtvermoegen += konten[i].getKontostand();
		}
		System.out.println("---------------------------------------------------");
		System.out.printf("Ihr Gesamtverm�gen betr�gt %,.2f Euro\n", gesamtvermoegen);
		System.out.println("===================================================\n\n");
	}
	
	/**
	 * Die Ein- und Auszahlungen werden durchgef�hrt. Dabei sind die "Grenzf�lle"
	 * zu testen, ob auch Rahmen und Negativwerte richtig verarbeitet werden.
	 */
	private static void einUndAuszahlen() {
		//auf alle Konten einzahlen
		for (int i = 0; i < anz; i++) {
			konten[i].einZahlen((i+1) * 1000);
		}
		//erfolgreiche und nicht erfolgreiche Abhebungen testen
		konten[0].ausZahlen(2499);	//1. Girokonto
		konten[1].ausZahlen(5001);	//2. Girokonto
		konten[2].ausZahlen(2999);	//1. Sparbuch
		konten[3].ausZahlen(4001);	//2. Sparbuch
	}
	
}