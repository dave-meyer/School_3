/**
 * In dieser Klasse werden die Klassen 'Konto', 'Girokonto' und 'Sparbuch'
 * mit einer Simulation von Ein- und Auszahlungen getestet
 * 
 * @author David Meyer
 * @version 1.0
 * 
 */
public class TestKonto {

	/**
	 * Hier werden 4 Konten gespeichert:
	 * 2 Sparbuecher und 2 Girokonten <br>
	 * Der Typ des Arrays ist 'Konto', damit man die Kontoarten 'Girokonto' und 'Sparbuch' darin speichern kann. 
	 * Das funktioniert, weil die beiden Klassen von 'Konto' erben
	 */
	public static Konto[] konten = new Konto[4];
	
	/**
	 * Beim Start des Programms wird folgendes ausgefuehrt:
	 * <ul>
	 * <li>Instanziiert 4 Konten </li>
	 * <li>zeigt deren Daten </li>
	 * <li>fuehrt einige Zahlungen durch </li>
	 * <li>zeigt deren Daten erneut</li>
	 * </ul>
	 * 
	 * @param args  Die Startparameter (keine Vorhanden)
	 */
	public static void main(String[] args) {
		kontenInstanziieren();
		vermoegenDrucken();
		einUndAuszahlen();
		vermoegenDrucken();
	}
	
	/**
	 * Instanziiert 2 Sparbuecher und 2 Girokonten (mit Rahmen) und speichert die Objekte in dem 'konten' Array
	 */
	public static void kontenInstanziieren() {
		konten[0] = new Sparbuch();
		konten[1] = new Sparbuch();
		konten[2] = new Girokonto(1000);
		konten[3] = new Girokonto(3200);
		System.out.println("");
	}
	
	/**
	 * Zeigt Kontostand, Kontonummer (und Rahmen bei Girokonten) und Gesamtvermoegen/Gesamtschulden formatiert an
	 */
	public static void vermoegenDrucken() {
		/**
		 * Das Gesamtvermoegen alle registrierten Konten
		 */
		double vermoegen = 0;
		System.out.println("Sie besitzen folgende Konten bei unserer Bank:");
		System.out.println("==============================================");
		for(int i = 0; i <= 3; i++) {
			System.out.println(konten[i]);
			vermoegen += konten[i].getKontostand();
		}
		System.out.println("----------------------------------------------");
		System.out.printf("Ihr Gesamtvermögen beträgt: %,.2f €.\n", vermoegen);
		System.out.println("==============================================\n");
	}
	
	/**
	 * Fuehrt ein paar Test-Zahlungen an allen Konten durch. 
	 * Hierbei werden vor allem die Grenzwerte  der Konten getestet 
	 */
	public static void einUndAuszahlen() {
		konten[0].einZahlung(500);
		konten[0].ausZahlung(499);
		konten[0].ausZahlung(2);
		
		konten[1].ausZahlung(10);
		konten[1].ausZahlung(-1);
		
		konten[2].einZahlung(2000);
		konten[2].ausZahlung(3000);
		konten[2].ausZahlung(1);
		
		konten[3].ausZahlung(3200);
		System.out.println("");
	}
}