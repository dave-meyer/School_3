package model;

import java.sql.*;
import java.util.*;

/**
 * Die Klasse stellt die Speicher- und Lesemethoden zum Persistieren bzw.
 * Einlesen der Kontaktdaten auf/von einer MySQL-Datenbank zur Verfügung.
 * 
 * Folgende Voraussetzungen sind vor der Programmnutzung zu erfüllen:
 * 	- Einbinden des DB-Treibers 'mysql-connector-java-5.1.38-bin.jar'
 * 	- Anlegen einer leeren Datenbank 'Contact_DB' - am einfachsten mit 'phpMyAdmin'
 * 	- Einrichten eines Users/PW - hier user 'student' PW 'htl' - mit allen Rechten
 */
public class Datenbank {
	
	private static String dbUrl = "jdbc:mysql://localhost:3306/Contact_DB?user=student&password=htl";
	private static final int ANZ_SPALTEN = 6;	// Anzahl der Spalten in der Kontakttabelle

	/**
	 * Die Kontakttabelle wird in der Datenbank angelegt - soferne sie nicht schon vorhanden ist.
	 */
	private static void createKontakte() {
		String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS contacts (nachname VARCHAR(100), vorname VARCHAR(100),"
																	 + "email VARCHAR(100), telefon VARCHAR(100), "
																	 + "strasse VARCHAR(100), ort VARCHAR(100))";
		try (Connection connection = DriverManager.getConnection(dbUrl);
				Statement statement = connection.createStatement()) {
			statement.execute(CREATE_TABLE_SQL);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} 
	}	
	
	/**
	 * Die vorhandene Kontakttabelle wird gelöscht.
	 */
	private static void dropKontakte() {
		String DROP_TABLE_SQL = "DROP TABLE contacts";
		try (Connection connection = DriverManager.getConnection(dbUrl);
				Statement statement = connection.createStatement()) {
			statement.execute(DROP_TABLE_SQL);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * Die Daten werden aus der Datenbank geholt. Sollte die Kontakttabelle
	 * noch nicht existieren, so wird sie neu angelegt.
	 * 
	 * @return die eingelesenen Kontaktdaten
	 */
	public static ArrayList<String[]> readKontakte() {
		ArrayList<String[]> tabelle = new ArrayList<String[]>();
		//Bei erstmaliger Nutzung - Tabelle neu erstellen.
		createKontakte();
		//Tabellenzeilen aus Datenbank einlesen
		String READ_DATA_SQL = "SELECT nachname, vorname, email, telefon, strasse, ort FROM contacts";
		try (Connection connection = DriverManager.getConnection(dbUrl);
			  PreparedStatement pStatement = connection.prepareStatement(READ_DATA_SQL);
              ResultSet resultSet = pStatement.executeQuery()) {
			while (resultSet.next()) {
				String[] zeile = new String[ANZ_SPALTEN];
				System.out.print("Gelesen wurde: ");
				for (int i = 0; i < ANZ_SPALTEN; i++) {
					zeile[i] = resultSet.getString(i+1);
					System.out.print(" '" + zeile[i] + "'");	//zur Kontrolle
				}
				tabelle.add(zeile);
				System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return tabelle;
	}
	
	/**
	 * Die übergebenen Tabellendaten werden in der Datenbank persistiert. Die vorhandene
	 * alte Datenbanktabelle wird zuvor einfach gelöscht.
	 * 
	 * @param tabelle
	 * @return boolean erfolgreich
	 */
	public static boolean writeKontakte(ArrayList<String[]> tabelle) {
		boolean erfolg = true;
		//"alte" Datenbanktabelle wird einfach gelöscht und wieder neu angelegt
		dropKontakte();
		createKontakte();
		//Neue Daten in Datebantabelle schreiben.
		String INSERT_DATA_SQL = "INSERT INTO contacts (nachname, vorname, email, telefon, strasse, ort) VALUES (?, ?, ?, ?, ?, ?)";
		try (Connection connection = DriverManager.getConnection(dbUrl);
			  PreparedStatement pStatement = connection.prepareStatement(INSERT_DATA_SQL);) {
			for (String[] zeile : tabelle) {
				for (int i = 1; i <= ANZ_SPALTEN; i++) {
					pStatement.setString(i, zeile[i-1]);
				}
				pStatement.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			erfolg = false;
		}
		return erfolg;
	}	

}

