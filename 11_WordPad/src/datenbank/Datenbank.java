package datenbank;

import java.io.*;

/**
 * Enthaelt statische Methoden um Dateien seriell oder als Text zu speichern und zu lesen
 * 
 * @author David Meyer
 * @see WordPad
 * @see NormalOeffnenListener
 * @see NormalSpeichernListener
 * @see SeriellOeffnenListener
 * @see SeriellSpeichernListener
 */
public class Datenbank {

	/**
	 * Speichert seriell einen String-Text in eine Datei
	 * @param text Der zu speichernde Text
	 * @param datei Eine Referenz einer Datei, in der der Text gespeichert werden soll
	 */
	public static void serialSave(String text, File datei) {
		
		try {
			
			FileOutputStream filestream = new FileOutputStream(datei.getAbsolutePath());
			ObjectOutputStream objectstream = new ObjectOutputStream(filestream);
			
			objectstream.writeObject(text);
			objectstream.close();
			
		}
		catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	/**
	 * Liest seriell einen String-Text aus einer Datei
	 * @param datei Eine Referenz einer Datei, aus der der Text gelesen werden soll
	 * @return der aus der Datei gelesene Text, bei Fehlern ein Fehlertext
	 */
	public static String serialLoad(File datei) {
		
		try {
			
			FileInputStream filestream = new FileInputStream(datei.getPath());
			ObjectInputStream objectstream = new ObjectInputStream(filestream);
			
			String text = (String) objectstream.readObject();
			objectstream.close();
			return text;
			
		} catch (Exception e) {
			
			e.printStackTrace();
			return "FAILED TO LOAD";
		}	
	}
	
	/**
	 * Speichert einen String-Text als String-Text in einer Datei
	 * @param text Der zu speichernde Text
	 * @param datei Eine Referenz einer Datei, in der der Text gespeichert werden soll
	 */
	public static void ordinarySave(String text, File datei) {
		
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(datei));
			
			text += " ";
			System.out.println(text);
			String[] textstuecke = text.split("\n");
			
			for(String stueck : textstuecke) {
				writer.write(stueck);
				writer.newLine();
			}
			writer.close();
		} 
		catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	/**
	 * Liest einen String-Text aus einer Datei, in der ein String-Text steht
	 * @param datei Eine Referenz einer Datei, aus der der Text gelesen werden soll
	 * @return der aus der Datei gelesene Text, bei Fehlern ein Fehlertext
	 */
	public static String ordinaryLoad(File datei) {
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(datei));
			String loadedtext = "";
			String nextline;
			while ((nextline = reader.readLine()) != null) {
				loadedtext += nextline;
				loadedtext += "\n";
			}
			reader.close();
			return loadedtext;
		} catch (Exception e) {
			
			e.printStackTrace();
			return "FAILED TO LOAD";
		}
		
	}
}