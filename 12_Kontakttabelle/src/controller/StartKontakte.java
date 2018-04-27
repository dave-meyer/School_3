package controller;

import view.*;
import model.*;
import java.awt.*;
import java.io.*;
import java.util.*;

/**
 * Das Programm ermöglicht die Erfassung von Kontaktdaten. Diese können auf 
 * Festplatte persistiert und wieder eingelesen werden. 
 * 
 * Der JAVA-Code ist strikt in die 3 Bereiche Model, View und Controller getrennt.
 * Dies ermöglicht die unabhängige Wartung der entsprechenden Programmteile.
 */
public class StartKontakte {

	/**
	 * Die Main-Methode instanziiert die View (= Kontakttabelle) und fordert
	 * diese (den User) gleich auf einen Dialog zu starten um ein File-Objekt
	 * zu liefern unter diesem ev. Kontaktdaten auf Festplatte gespeichert sind.
	 * 
	 *  Wählt der User eine Datei aus, so wird das entsprechende File-Objekt
	 *  retourniert und an die "Datenbank" weitergereicht um die Daten von
	 *  Festplatte zu lesen.
	 *  
	 * @param args - wird nicht benötigt.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Kontakttabelle frame = new Kontakttabelle();
					frame.setVisible(true);
					
					/* ALT
					//Eventuell vorhandene Daten einlesen - View auffordern entspr. File-Objekt zu liefern.
					File datei = frame.getFileToRead(); //kann auch null sein
					
					if (datei != null) {
						//Daten aus Model holen
						ArrayList<String[]> tabelle = Datenbank.readKontakte(datei);
						//Daten an View zum Anzeigen weiter geben
						frame.showKontakte(tabelle);
					}
					*/
					
					ArrayList<String[]> tabelle; 
					if((tabelle = Datenbank.loadKontakteFromDatabase()) != null) {
						
						frame.showKontakte(tabelle);
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
