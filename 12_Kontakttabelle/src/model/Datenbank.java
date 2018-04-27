package model;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

/**
 * Die Klasse stellt die Speicher- und Lesemethoden zum Persistieren bzw.
 * Einlesen der Kontaktdaten auf/von Festplatte zur Verfügung.
 */
public class Datenbank {
	
	/**
	 * Die URL zur Verbindung mit der Datenbank
	 */
	private static String dbURL = "jdbc:mysql://localhost:3306/contact_db?user=student&password=htl";
	
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
	
	/**
	 * SQL Befehl Vorlage, um die Kontaktdaten in die Datenbank zu speichern
	 */
	private static final String SQL_KONTAKTDATEN_SPEICHERN = "INSERT INTO kontakte "
			+ "(Nachname, Vorname, Telefon, Email, Strasse, Ort) VALUES "
			+ "(?, ?, ?, ?, ?, ?);";
	
	/**
	 * Schreibt die Daten der Tabelle in die Datenbank
	 * @param tabelle Tabelle von der Getter Methode der GUI
	 */
	public static void writeKontakteToDatabase(ArrayList<String[]> tabelle) {

		// Tabelle loeschen und neu aufbauen anstatt zu updaten
		// Man koennte auch den Inhalt loeschen und dem Primary Key resetten (siehe Methode: deleteKontakteInhalt())
		deleteKontakteTabelle();
		// Tabelle erstellen
		createKontakteTabelle();


        try (Connection connection = 
                DriverManager.getConnection(dbURL);) {
        	
    				PreparedStatement pStatement = null;
        	
        			// Schleife durch alle Reihen (= Zeilen) in der Tabelle
        			for(String[] zeilen : tabelle) {
        				
        				// Neue SQL "Vorlage" erstellen
        				pStatement = connection.prepareStatement(SQL_KONTAKTDATEN_SPEICHERN);
        				
        				// Jede Zeile hat hat mehrere Werte (= Zellen)
        				for(int i = 0; i < zeilen.length; i++) {
        						// Jeden Wert in die "Vorlage" einsetzen
        						pStatement.setString(i+1, zeilen[i]);
        				}
        				// SQL-Befehl mit den in der Vorlage eingesetzten Werten ausfuehren
        				pStatement.executeUpdate();
        			}
        		
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
	}
	
	/**
	 * SQL Befehl um die Kontaktdaten aus der Datenbank zu laden
	 */
	private static final String SQL_KONTAKTDATEN_LADEN = "SELECT Nachname, Vorname, Telefon, Email, Strasse, Ort "
			+ "FROM kontakte ";
	
	/**
	 * Ladet alle Kontaktdaten von der Datenbank herunter und gibt
	 * sie in Form einer ArrayListe (Spalten) bestehend aus String-Arrays (Zeilen) zurueck
	 * @return
	 */
	public static ArrayList<String[]> loadKontakteFromDatabase() {
		
		ArrayList<String[]> tabelle = new ArrayList<String[]>();
		
		try (Connection connection =
				DriverManager.getConnection(dbURL);
				PreparedStatement pStatement = connection.prepareStatement(SQL_KONTAKTDATEN_LADEN);
				ResultSet resultat = pStatement.executeQuery();) {
			
				// TODO: 6 nicht hardcoden
				String[] zeile = new String[6];
				while(resultat.next()) {
					// ACHTUNG: immer neues Array erstellen! Weil call by reference
					zeile = new String[6];
					for(int i = 0; i < zeile.length; i++) {
						
						zeile[i] = resultat.getString(i+1);
					}
					tabelle.add(zeile);
				}
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return tabelle;
	}
	
	/**
	 * SQL Befehl fuer die Erstellung der Kontakttabelle
	 */
	private static final String SQL_KONTAKTTABELLE_ERSTELLEN = "CREATE TABLE IF NOT EXISTS kontakte ("
			+ "ID int(10) auto_increment PRIMARY KEY, "
			+ "Nachname varchar(30), "
			+ "Vorname varchar(30), "
			+ "Telefon varchar(30), "
			+ "Email varchar(30), "
			+ "Strasse varchar(50), "
			+ "Ort varchar(50)"
			+ ");";
	
	/**
	 * Erstellt Kontakttabelle in der Datenbank
	 */
	private static void createKontakteTabelle() {
		
        try (Connection connection = 
                DriverManager.getConnection(dbURL);
                Statement statement = connection.createStatement()) {
        	
            statement.execute(SQL_KONTAKTTABELLE_ERSTELLEN);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
	}
	
	/**
	 * SQL Befehl, um die Kontakttabelle zu leeren
	 */
	private static final String SQL_KONTAKTTABELLE_LEEREN = "DELETE FROM kontakte;";
	/**
	 * SQL Befehl um den Primary Key zurueckzusetzten (wegen auto_increment)
	 */
	private static final String SQL_PRIMARYKEY_RESET = "ALTER TABLE kontakte MODIFY ID int(10) PRIMARY KEY auto_increment";
	/**
	 * SQL Befehl um Kontakttabelle zu loeschen
	 */
	private static final String SQL_KONTAKTTABELLE_LOESCHEN = "DROP TABLE kontakte";
	
	/**
	 * Loescht die Kontakttabelle in der Datenbank
	 */
	private static void deleteKontakteTabelle() {
		
        try (Connection connection = 
                DriverManager.getConnection(dbURL);
                Statement statement = connection.createStatement()) {
        	
            statement.execute(SQL_KONTAKTTABELLE_LOESCHEN);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
	}
	
	private static void deleteKontakteInhalt() {
		
        try (Connection connection = 
                DriverManager.getConnection(dbURL);
                Statement statement = connection.createStatement()) {
        	
            statement.execute(SQL_KONTAKTTABELLE_LEEREN);
            statement.execute(SQL_PRIMARYKEY_RESET);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
	}
}

