package model;

public class Getraenk {
	
	private String bezeichnung = "";
	private double alkohol = 0.0;	//Prozent
	private double liter = 0.0;
	private double alkoholLiter = 0.0;
	
	public Getraenk (String b, double a, double l) {
		bezeichnung = b;
		alkohol = a;
		liter = l;
		alkoholLiter = liter * alkohol / 100;
	}

	public String getBezeichnung() {
		return bezeichnung;
	}

	public double getAlkohol() {
		return alkohol;
	}

	public double getLiter() {
		return liter;
	}
	
	public double getAlkoholLiter() {
		return alkoholLiter;
	}
}
