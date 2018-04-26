package control;

import java.awt.event.*;
import java.io.File;

import model.Datenbank;
import view.WordPad;

/**
 * Ein als StringOBJEKT gespeicherter Text soll eingelesen werden.
 * Zur Fileauswahl wird die View aufgefordert das entsprechende File-Objekt zum Einlesen zu liefern.
 * Dann wird dieses File-Objekt an das Model weitergereicht um den Text aus der "Datenbank" zu
 * holen.
 * 
 * Hier sieht man sch�n wie unabh�ngig von konkreten View- oder Model-Implementierungen diese Klasse ist.
 * Die View k�nnte z.B. von SWING in JAVA-FX umprogrammiert werden, ohne dass diese Klasse ge�ndert 
 * werden m�sste. Auch k�nnte die Datenbank ganz anders implementiert werden - z.B. die Daten
 * in einer "wirklichen" Datenbank abzuspeichern - auch da w�rde diese Klasse nichts "mitbekommen"! Durch
 * diese Unabh�ngigkeit werden wartungsfreundliche Anwendungen geschrieben.
 */
public class OpenSerialListener implements ActionListener {

	private WordPad gui;
	
	public OpenSerialListener(WordPad gui) {
		this.gui = gui;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// View auffordern zu �ffnendes File-Objekt zu liefern - muss serialisiertes String-Objekt sein.
		File file = gui.getFileToRead(); //kann auch "null" sein, wenn Dialog abgebrochen wurde
		if (file != null) {
			// File-Objekt an Model �bergeben, und Text aus "Datenbank" holen
			Object text = Datenbank.readTextSerial(file);
			// Text der View zum Anzeigen zur Verf�gung stellen
			gui.setAreaText((String)text);			
		}
	}
}
