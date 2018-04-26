/**
 * Ein Girokonto erweitert die Kontoklasse und erbt somit grundsätzliche
 * sämtliche Eigenschaften und Methoden eines Kontos. Zusätzlich verfügt
 * ein Girokonto über einen Rahmen. Dieser Rahmen definiert wie weit
 * ein Girokonto durch eine gewünschte Abhebung negativ werden darf.
 * 
 * @author DI Martin Kampenhuber
 */
public class Girokonto extends Konto {

	/**
	 * Der Rahmen wie weit das Konto negativ werden darf.
	 */
	private double rahmen = 0.0;
	
	/**
	 * Im Konstruktor werden die Kontonummer und der Rahmen des Girokontos
	 * fixiert.
	 * @param knr - Kontonummer des Girokontos
	 * @param r - Rahmen des Girokontos
	 */
	public Girokonto(String knr, double r) {
		super(knr);
		rahmen = r;
	}
	
	/**
	 * Die allgemeine Prüfmethode des Kontos wird im Girokonto
	 * ü b e r s c h r i e b e n ! ! Dies ist notwendig, da ja der 
	 * Rahmen des Girokontos bei der Prüfung zu berücksichtigen ist!!
	 */
	public boolean isKontoGedeckt(double betrag) {
		if ((getKontostand() + rahmen - betrag) >= 0) return true; else return false;
	}
	
	/**
	 * Eine Bildschirmrepräsentation eines Girokontos. Die entsprechende
	 * Methode der Superklasse Konto wird dazu auch genutzt.
	 */
	public String toString() {
		//return "GIROKONTO:\t" + super.toString() + "\tRahmen: ";
		return String.format("GIROKONTO: \t %s \t Rahmen: %,.2f", super.toString(), rahmen);
	}

}
