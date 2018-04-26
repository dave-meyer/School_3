package controller;
import view.*;
import model.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
	 * Im Gegensatz zur "File-Version" werden die Daten in einer Datenbank
	 * automatisch persistiert. Somit wird auch kein File-Objekt mehr ben�tigt.
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		//Tabellendaten aus View holen - diese wird als ArrayList von String[]-Objekten zur Verf�gung gestellt.
		ArrayList<String[]> tabelle = gui.getTabelle();
		//Die Daten an das Model zum Persistieren weiterreichen und pr�fen ob dies erfolgreich war
		boolean erfolg = Datenbank.writeKontakte(tabelle);
		if (erfolg == false) {
			System.out.println("ERROR: Abspeichern ging schief....");
		}
		System.exit(0);
	}
}
