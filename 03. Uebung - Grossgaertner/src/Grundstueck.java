/**
 * Ein Grundstueck besitzt eine L�nge und eine Breite. Diese Attribute werden
 * �ber 2 Konstruktoren festgelegt - entweder mit unterschiedlichen Werten f�r
 * L�nge und Breite, oder f�r ein quadratisches Grundst�ck mit dem Wert f�r die
 * Seitenl�nge. Weiters wird ueber den Konstruktor festgelegt, wem das Grundstueck
 * gehoert.
 * 
 * Weiters wird f�r ein Grundst�ck der Preis f�r 1 Meter Zaun und f�r
 * 1 Quadratmeter Fertigrasen festgelegt. Dies erfolgt mit den entsprechenden 
 * "Setters".
 *  
 * @author DI Martin Kampenhuber
 * @version 1.0
 */
public class Grundstueck {
	
	private String besitzer = "";
	private double laenge = 0.0;
	private double breite = 0.0;
	private double preismeterzaun = 0.0;
	private double preisqmeterrasen = 0.0;
	
	/**
	 * Konstruktor f�r ein rechteckiges Grundstueck.
	 * @param l - Laenge des Grundstuecks
	 * @param b - Breite des Grundstuecks
	 */
	public Grundstueck(String bes, double l, double b) {
		besitzer = bes;
		laenge = l;
		breite = b;
	}
	
	/**
	 * Konstruktor f�r ein quadratisches Grundstueck
	 * @param seite - Seitenlaenge des Grundstuecks.
	 */
	public Grundstueck(String besitzer, double seite) {
		this(besitzer, seite, seite);
	}

	/**
	 * Der Preis f�r 1 Meter Zaun des Grundstuecks wird festgelegt - 
	 * allerdings nur dann, wenn auch ein vern�nftiger Preis (groesser 0)
	 * eingegeben wurde.
	 * 
	 * @param preismeterzaun - Preis fuer 1 Meter Zaun fuer dieses Grundstueck
	 */
	public void setPreismeterzaun(double preismeterzaun) {
		if (preismeterzaun > 0) {
			this.preismeterzaun = preismeterzaun;
		} else {
			System.out.println("ERROR - Ung�ltiger Preis f�r Zaun!!");
		}
	}
	
	/**
	 * Der Preis f�r 1 Quadratmeter Fertigrase des Grundstuecks wird festgelegt - 
	 * allerdings nur dann, wenn auch ein vern�nftiger Preis (groesser 0)
	 * eingegeben wurde.
	 * 
	 * @param preisqmeterrasen - Preis fuer 1 Quadratmeter Fertigrasen fuer dieses Grundstueck
	 */
	public void setPreisqmeterrasen(double preisqmeterrasen) {
		if (preisqmeterrasen > 0) {
			this.preisqmeterrasen = preisqmeterrasen;
		} else {
			System.out.println("ERROR - Ung�ltiger Preis f�r Rasen!!");
		}
	}
		
	/**
	 * Flaechenberechnung des Grundstuecks
	 * 
	 * @return - die Flaeche des Grundstuecks
	 */
	public double getFlaeche() {
		return laenge * breite;
	}
	
	/**
	 * Umfangberechnung des Grundstuecks
	 * 
	 * @return - der Umfang des Grundstuecks
	 */
	public double getUmfang() {
		return 2 * (laenge + breite);
	}
	
	/**
	 * Der Zaunumsatz f�r dieses Grundstueck wird berechnet
	 * 
	 * @return Der Zaunumsatz f�r dieses Grundst�ck
	 */
	public double getUmsatzZaun() {
		return getUmfang() * preismeterzaun;
	}
	
	/**
	 * Der Rasenumsatz f�r dieses Grundst�ck wird berechnet
	 * 
	 * @return Der Rasenumsatz f�r dieses Grundst�ck
	 */
	public double getUmsatzRasen() {
		return getFlaeche() * preisqmeterrasen;
	}
	
	/**
	 * Eine sch�ne einzeilige Ausgabe der Grundstuecksdaten
	 */
	public void printDaten() {
		System.out.printf("%s:\tLaenge: %,.2f\tBreite: %,.2f\tZaunums.: %,.2f\tRasenums.: %,.2f\tGesamtums.: %,.2f\n", 
	              besitzer, laenge, breite, getUmsatzZaun(), getUmsatzRasen(), getUmsatzZaun() + getUmsatzRasen());
	}
}
