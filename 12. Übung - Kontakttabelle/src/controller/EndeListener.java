package controller;
import view.*;
import model.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;

/**
 * Das Programm soll beendet werden und davor die Kontaktdaten persistiert werden.
 */
public class EndeListener implements ActionListener {
	
	private Kontakttabelle gui;
	
	public EndeListener(Kontakttabelle gui) {
		this.gui = gui;
	}

	/**
	 * Diese Implementierung geht davon aus, dass das Model f�r die Persistierung ein
	 * File-Objekt ben�tigt unter dem die Kontaktdaten gespeichert werden sollen.
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		//View auffordern das File-Objekt zu bringen, unter dem persistiert werden soll
		File datei = gui.getFileToWrite();	//kann auch null sein!!!
		if (datei != null) {
			//Tabellendaten aus View holen - diese wird als ArrayList von String[]-Objekten zur Verf�gung gestellt.
			//Ein String[]-Array stellt eine Zeile mit 4 Eintr�gen dar.
			ArrayList<String[]> tabelle = gui.getTabelle();
			//Die Daten an das Model zum Persistieren weiterreichen und pr�fen ob dies erfolgreich war
			boolean erfolg = Datenbank.writeKontakte(datei, tabelle);
			if (erfolg == false) {
				System.out.println("ERROR: Abspeichern ging schief....");
			}
		}
		System.exit(0);
	}
}
