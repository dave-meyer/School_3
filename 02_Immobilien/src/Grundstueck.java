/**
 * Klasse 'Grundstueck' enthaelt Attribute, die ein Grundstueck beschreiben
 * und Methoden, die Grundstueckspezifische Daten ausrechnet
 * 
 * @author David Meyer
 * @version 1.1
 */
public class Grundstueck {
	/**
	 * Preis pro Quadratmeter Grundstuecksflaeche
	 */
	double preisproqm;
	/**
	 * Die Leange des Grundstuecks (in Metern)
	 */
	double laengeinmeter;
	/**
	 * Die Breite des Grundsteucks (in Metern)
	 */
	double breiteinmeter;
	/**
	 * Die Bezeichnung des Grundstuecks
	 */
	String bezeichnung;

	/**
	 * Anzahl der Grundstuecke
	 */
	static int grundanzahl = 0;
	
	/**
	 * @return  Gibt den Wert der Variabel 'preisproqm' zurueck 
	 */
	public double getPreisProQM() {
		return preisproqm;
	}
	
	/**
	 * @return  Gibt den Wert der Variabel 'laengeinmeter' zurueck 
	 */
	public double getLaengeInMeter() {
		return laengeinmeter;
	}
	
	/**
	 * @return  Gibt den Wert der Variabel 'breiteinmeter' zurueck 
	 */
	public double getBreiteInMeter() {
		return breiteinmeter;
	}
	
	/**
	 * @return  Gibt den Wert der Variabel 'bezeichnung' zurueck 
	 */
	public String getBezeichnung() {
		return bezeichnung;
	}
	
	/**
	 * @return  Gibt die Flaeche des Grundstuecks zurueck 
	 */
	public double getFlaeche() {
		return laengeinmeter * breiteinmeter;
	}
	
	/**
	 * @return  Gibt den Wert des Grundstuecks zurueck 
	 */
	public double getWert() {
		return getFlaeche() * preisproqm;
	}
}