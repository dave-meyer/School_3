/**
 * @author David Meyer
 * @version 1.0
 * 
 * In dieser Klasse stehen alle Attribute, die ein Grundstueck beschreiben 
 * und alle Methoden, die Grundstueckspezifische Daten berechnen, Variabeln setzen oder Variabeln abfragen
 */
public class Grundstuecke {

	/** Der Besitzer des Grundstuecks */
	private String besitzer;
	/** Die Laenge des Grundstuecks */
	private double laenge;
	/** Die Breite des Grundstuecks */
	private double breite;
	/** Der Preis fuer einen Meter Zaun (mit Einheit)*/
	private double preismeterzaun;
	/** Der Preis fuer einen Quadratmeter Kunstrasen*/
	private double preisqmeterrasen; 
	
	/** Die Form des Grundstuecks (Rechteck oder Quadrat)*/
	private String form;
	/** Anzahl der erstellten Grundstuecke */
	public static int anzahl = 0;
	
	// Konstruktoren
	/** 1 Konstruktor fuer rechteckige Grundstuecke 
	 * @param besitzer_ Auf diesen Wert wird die Variabel 'besitzer' nach einer Fehlerabfrage gesetzt
	 * @param laenge_ Auf diesen Wert wird die Variabel 'laenge' nach einer Fehlerabfrage gesetzt
	 * @param breite_ Auf diesen Wert wird die Variabel 'breite' nach einer Fehlerabfrage gesetzt
	 * 
	 * Die Anzahl der Grundstuecke wird um 1 erhoeht
	 * Die Variabel 'form' wird auf 'Rechteck' gesetzt
	 * Der Konstruktor wird von einem zweiten Konstruktur fuer quadratische Grundstuecke ueberladen
	 * */
	public Grundstuecke(String besitzer_, double laenge_, double breite_) {
		setBesitzer(besitzer_);
		setLaenge(laenge_);
		setBreite(breite_);
		form = "Rechteck";
		anzahl++;
	}
	
	/** 2 Konstruktor fuer quadratische Grundstuecke 
	 * @param besitzer_ Auf diesen Wert wird die Variabel 'besitzer' nach einer Fehlerabfrage gesetzt
	 * @param seite Auf diesen Wert wird die Variabel 'laenge' und 'breite' nach einer Fehlerabfrage gesetzt
	 * 

	 * 
	 * Die Anzahl der Grundstuecke wird um 1 erhoeht
	 * Die Variabel 'form' wird auf 'Quadrat' gesetzt
	 * Der Konstruktor wird von einem zweiten Konstruktur fuer rechteckige Grundstuecke ueberladen
	 * */
	public Grundstuecke(String besitzer_, double seite) {
		setBesitzer(besitzer_);
		setLaenge(seite);
		setBreite(seite);
		form = "Quadrat";
		anzahl++;
	}
	
	// Get Methoden
	/** @return Gibt die Laenge des Grundstuecks zurueck */
	public double getLaenge() {
		return laenge;
	}
	/** @return Gibt die Laenge des Grundstuecks zurueck */
	public double getBreite() {
		return breite;
	}
	/** @return Gibt den Besitzer des Grundstuecks zurueck */
	public String getBesitzer() {
		return besitzer;
	}
	/** @return Gibt den Preis pro Meter Zaun des Grundstuecks zurueck */
	public double getPreismeterzaun() {
		return preismeterzaun;
	}
	/** @return Gibt den Preis pro Quadratmeter Kunstrasen des Grundstuecks zurueck */
	public double getPreisqmeterrasen() {
		return preisqmeterrasen;
	}
	

	/** @return Gibt den Umfang des Grundstuecks zurueck */
	public double getUmfang() {
		return 2*(laenge + breite);
	}
	/** @return Gibt die Flaeche des Grundstuecks zurueck */
	public double getFlaeche() {
		return laenge * breite;
	}
	/** @return Gibt den Umsatz fuer die verkauften Meter Zaun zurueck */
	public double getUmsatzZaun() {
		return getUmfang() * preismeterzaun;
	}
	/** @return Gibt den Umsatz fuer die verkauften Quadratmeter Kunstrasen zurueck */
	public double getUmsatzRasen() {
		return getFlaeche() * preisqmeterrasen;
	}
	
	
	/** Gibt die Daten/Attribute an die Konsole aus */
	public void printDaten() {
		System.out.printf("Von " + besitzer + " (" + form + ") \t Laenge: %,.2f \t Breite: %,.2f \t Zaunumsatz: %,.2f Rasenumsatz: %,.2f \t Gesamtumsatz: %,.2f \n", laenge, breite, getUmsatzZaun(), getUmsatzRasen(), getUmsatzZaun() + getUmsatzRasen());
	}
	
	
	// Set Methoden
	/** @param bes Auf diesen Wert wird die Variabel 'besitzer' nach einer Fehlerabfrage gesetzt. Ansonsten wird die Variabel auf '?' gesetzt */
	public void setBesitzer(String bes) {
		if(bes == "") {
			besitzer = "?";
		}
		else {
			besitzer = bes;
		}
	}
	/** @param laenge_ Auf diesen Wert wird die Variabel 'laenge' nach einer Fhlerabfrage gesetzt. Ansonsten wird eine Fehlermeldung ausgegeben */
	public void setLaenge(double laenge_) {
		if(laenge_ > 0){
			laenge = laenge_;
		}
		else {
			System.out.println("ERROR: Die 'Laenge' hat einen ungueltigen Wert!");
		}
	}
	/** @param breite_ Auf diesen Wert wird die Variabel 'breite' nach einer Fehlerabfrage gesetzt. Ansonsten wird eine Fehlermeldung ausgegeben */
	public void setBreite(double breite_) {
		if(breite_ > 0){
			breite = breite_;
		}
		else {
			System.out.println("ERROR: Die 'Breite' hat einen ungueltigen Wert!");
		}
	}
	/** @param preis Auf diesen Wert wird die Variabel 'preismeterzaun' nach einer Fehlerabfrage gesetzt. Ansonsten wird eine Fehlermeldung ausgegeben */
	public void setPreismeterzaun(double preis) {
		if(preis > 0){
			preismeterzaun = preis;
		}
		else {
			System.out.println("ERROR: Der 'Preis pro Meter Zaun' hat einen ungueltigen Wert!");
		}
	}
	/** @param preis Auf diesen Wert wird die Variabel 'preisqmeterrasen' nach einer Fehlerabfrage gesetzt. Ansonsten wird eine Fehlermeldung ausgegeben */
	public void setPreisqmeterrasen(double preis) {
		if(preis > 0){
			preisqmeterrasen = preis;
		}
		else {
			System.out.println("ERROR: Der 'Preis pro Quadratmeter Rasen' hat einen ungueltigen Wert!");
		}
	}
}
