package model;

import java.util.ArrayList;

public class Trinker {
	private static double gewicht = 0;
	private static ArrayList<Getraenk> getraenke = new ArrayList<Getraenk>();
	
	public static void setGewicht(double g) {
		gewicht = g;
	}
	
	/**
	 * Ein neues Getraenk wurde getrunken und ist aufzunehmen
	 * @param g das getrunkene Getraenk
	 */
	public static void addGetraenk(Getraenk g) {
		getraenke.add(g);
	}
	
	/**
	 * Die gesamten konsumierten Getr�nke werden retourniert
	 * @return die getrunkenen Getr�nke
	 */
	public static ArrayList<Getraenk> getGetraenke() {
		return getraenke;
	}
	
	/**
	 * Gesamte konsumierte Menge Alkohol in Liter
	 * @return Liter Alkohol
	 */
	public static double getGesamtAlkoholLiter() {
		double liter = 0.0;
		for (Getraenk g : getraenke) {
			liter += g.getAlkoholLiter();
		}
		return liter;
	}
	
	/**
	 * Die Menge Alkohol im Blut wird berechnet. Dazu wird die 
	 * Widmarkformel und die zugeh�rige Toolklasse der vergangenen �bung
	 * verwendet.
	 * @return Promille Alkohol im Blut
	 */
	public static double getPromille() {
		double promille = Tool.getPromilleAlkoholWidmark(getGesamtAlkoholLiter(), gewicht);
		return promille;
	}
	
}
