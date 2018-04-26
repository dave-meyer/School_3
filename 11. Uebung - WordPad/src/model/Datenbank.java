package model;

import java.io.*;

public class Datenbank {

	/**
	 * Mit Hilfe des File-Objektes wird der gespeicherte Text von Festplatte
	 * geholt - der Text liegt in Textform auf Festplatte vor.
	 * 
	 * @return der gespeicherte Text als String
	 */
	public static String readText(File file) {
		String text = "";
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String zeile = "";
			while((zeile = reader.readLine()) != null) {
				text = text + zeile + "\n";	//Linefeed muss wieder eingefügt werden
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return text;
	}
	
	/**
	 * Mit Hilfe des File-Objektes wird der gespeicherte Text von Festplatte
	 * geholt - der Text liegt als StringOBJEKT auf Festplatte vor.
	 * 
	 * @return der gespeicherte Text als StringOBJEKT
	 */
	public static Object readTextSerial(File file) {
		Object text = null;
		try {
			ObjectInputStream is = new ObjectInputStream(new FileInputStream(file));
			text = is.readObject();
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return text;
	}	
	
	/**
	 * Mit Hilfe des File-Objektes soll der übergebene Text (String) auf Festplatte
	 * gespeichert werden.
	 */
	public static void writeText(File file, String text) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write(text);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Mit Hilfe des File-Objektes soll der übergebene Text (String) auf Festplatte
	 * als einziges StringOBJEKT gespeichert werden.
	 */
	public static void writeTextSerial(File file, String text) {
		try {
			ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(file));
			os.writeObject(text);
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
