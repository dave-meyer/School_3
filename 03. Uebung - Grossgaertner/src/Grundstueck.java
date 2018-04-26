/**
 * Ein Grundstueck besitzt eine Länge und eine Breite. Diese Attribute werden
 * über 2 Konstruktoren festgelegt - entweder mit unterschiedlichen Werten für
 * Länge und Breite, oder für ein quadratisches Grundstück mit dem Wert für die
 * Seitenlänge. Weiters wird ueber den Konstruktor festgelegt, wem das Grundstueck
 * gehoert.
 * 
 * Weiters wird für ein Grundstück der Preis für 1 Meter Zaun und für
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
	 * Konstruktor für ein rechteckiges Grundstueck.
	 * @param l - Laenge des Grundstuecks
	 * @param b - Breite des Grundstuecks
	 */
	public Grundstueck(String bes, double l, double b) {
		besitzer = bes;
		laenge = l;
		breite = b;
	}
	
	/**
	 * Konstruktor für ein quadratisches Grundstueck
	 * @param seite - Seitenlaenge des Grundstuecks.
	 */
	public Grundstueck(String besitzer, double seite) {
		this(besitzer, seite, seite);
	}

	/**
	 * Der Preis für 1 Meter Zaun des Grundstuecks wird festgelegt - 
	 * allerdings nur dann, wenn auch ein vernünftiger Preis (groesser 0)
	 * eingegeben wurde.
	 * 
	 * @param preismeterzaun - Preis fuer 1 Meter Zaun fuer dieses Grundstueck
	 */
	public void setPreismeterzaun(double preismeterzaun) {
		if (preismeterzaun > 0) {
			this.preismeterzaun = preismeterzaun;
		} else {
			System.out.println("ERROR - Ungültiger Preis für Zaun!!");
		}
	}
	
	/**
	 * Der Preis für 1 Quadratmeter Fertigrase des Grundstuecks wird festgelegt - 
	 * allerdings nur dann, wenn auch ein vernünftiger Preis (groesser 0)
	 * eingegeben wurde.
	 * 
	 * @param preisqmeterrasen - Preis fuer 1 Quadratmeter Fertigrasen fuer dieses Grundstueck
	 */
	public void setPreisqmeterrasen(double preisqmeterrasen) {
		if (preisqmeterrasen > 0) {
			this.preisqmeterrasen = preisqmeterrasen;
		} else {
			System.out.println("ERROR - Ungültiger Preis für Rasen!!");
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
	 * Der Zaunumsatz für dieses Grundstueck wird berechnet
	 * 
	 * @return Der Zaunumsatz für dieses Grundstück
	 */
	public double getUmsatzZaun() {
		return getUmfang() * preismeterzaun;
	}
	
	/**
	 * Der Rasenumsatz für dieses Grundstück wird berechnet
	 * 
	 * @return Der Rasenumsatz für dieses Grundstück
	 */
	public double getUmsatzRasen() {
		return getFlaeche() * preisqmeterrasen;
	}
	
	/**
	 * Eine schöne einzeilige Ausgabe der Grundstuecksdaten
	 */
	public void printDaten() {
		System.out.printf("%s:\tLaenge: %,.2f\tBreite: %,.2f\tZaunums.: %,.2f\tRasenums.: %,.2f\tGesamtums.: %,.2f\n", 
	              besitzer, laenge, breite, getUmsatzZaun(), getUmsatzRasen(), getUmsatzZaun() + getUmsatzRasen());
	}
}
