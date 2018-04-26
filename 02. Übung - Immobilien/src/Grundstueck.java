/**
 * Die Klasse dient als Vorlage für Grundstückobjekte. Die Eigenschaften
 * werden mit 'Getters und Setters' ausgelesen bzw. festgelegt.
 * 
 * Berechnet wird die Grundstücksfläche und der Wert des Grundstücks.
 * 
 * @author DI Martin Kampenhuber
 * @version 1.0
 */
public class Grundstueck {

	private double preisProQM;
	private double laengeInMeter;
	private double breiteInMeter;
	private String bezeichnung;

	public void setPreisProQM(double preisProQM) {
		this.preisProQM = preisProQM;
	}
	
	public double getPreisProQM() {
		return preisProQM;
	}
	
	public void setLaengeInMeter(double laengeInMeter) {
		this.laengeInMeter = laengeInMeter;
	}
	
	public double getLaengeInMeter() {
		return laengeInMeter;
	}
	
	public double getBreiteInMeter() {
		return breiteInMeter;
	}
	
	public void setBreiteInMeter(double breiteInMeter) {
		this.breiteInMeter = breiteInMeter;
	}
	
	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}
	
	public String getBezeichnung() {
		return bezeichnung;
	}
	
	public double getFlaeche() {
		return laengeInMeter * breiteInMeter;
	}
	
	public double getWert() {
		return getFlaeche() * preisProQM;
	}
}
