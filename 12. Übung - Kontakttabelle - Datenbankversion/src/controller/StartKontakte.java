package controller;

import view.*;
import model.*;
import java.awt.*;
import java.io.*;
import java.util.*;

/**
 * Das Programm ermöglicht die Erfassung von Kontaktdaten.
 * 
 * Im Gegensatz zur "File-Version" wird eine MySQL-Datenbank genutzt. Beim erstmaligen
 * Aufruf des Programms wird eine neue Tabelle erstellt. Bei weiterer Nutzung des
 * Programms diese automatisch eingelesen und verwendet.
 * 
 * Der JAVA-Code ist strikt in die 3 Bereiche Model, View und Controller getrennt.
 * Dies ermöglicht die unabhängige Wartung der entsprechenden Programmteile.
 */
public class StartKontakte {

	/**
	 * Die Main-Methode instanziiert die View (= Kontakttabelle).
	 * 
	 * Im Gegensatz zur File-Version braucht keine Datei zum Einlesen/Speichern mehr
	 * ausgewählt werden.
	 *  
	 * @param args - wird nicht benötigt.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Kontakttabelle frame = new Kontakttabelle();
					frame.setVisible(true);
					//ev. vorhandene Daten aus Model (Datenbank) holen
					ArrayList<String[]> tabelle = Datenbank.readKontakte();
					//Daten an View zum Anzeigen weiter geben
					frame.showKontakte(tabelle);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
