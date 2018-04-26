package model;

import java.io.*;
import java.util.*;

/**
 * Die Klasse stellt die Speicher- und Lesemethoden zum Persistieren bzw.
 * Einlesen der Kontaktdaten auf/von Festplatte zur Verfügung.
 */
public class Datenbank {
	
	/**
	 * Die Daten werden im csv-Format abgespeichert - d.h. zeilenweise - zwischen den Werten ein ";"
	 * @param datei
	 * @param tabelle
	 * @return boolean erfolgreich
	 */
	public static boolean writeKontakte(File datei, ArrayList<String[]> tabelle) {
		boolean erfolg = true;
		BufferedWriter out = null;
		try {
			out = new BufferedWriter(new FileWriter(datei));
			for (String[] zeile : tabelle) {
				for (String wert : zeile) {
					out.write(wert + ";");
				}
				out.newLine();
			}
			out.close();
		} catch (Exception ex) {
			ex.printStackTrace();
			erfolg = false;
		}
		return erfolg;
	}

	/**
	 * Die Daten werden von Festplatte eingelesen und als ArrayList<String[]> retourniert
	 * @param datei
	 * @return die eingelesenen Kontaktdaten
	 */
	public static ArrayList<String[]> readKontakte(File datei) {
		BufferedReader in = null;
		ArrayList<String[]> tabelle = new ArrayList<String[]>();
		try {
			in = new BufferedReader(new FileReader(datei));
			String zeile = null;
			while ((zeile = in.readLine()) != null) {
				String[] zeileSplit = zeile.split(";");
				tabelle.add(zeileSplit);
			}
			in.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return tabelle;
	}
}

