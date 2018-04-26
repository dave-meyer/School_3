package control;

import java.awt.event.*;
import java.io.*;

import view.*;
import model.*;

/**
 * Ein in Textform gespeicherter Text soll eingelesen werden.
 * Zur Fileauswahl wird die View aufgefordert das entsprechende File-Objekt zum Einlesen zu liefern.
 * Dann wird dieses File-Objekt an das Model weitergereicht um den Text aus der "Datenbank" zu
 * holen.
 * 
 * Hier sieht man schön wie unabhängig von konkreten View- oder Model-Implementierungen diese Klasse ist.
 * Die View könnte z.B. von SWING in JAVA-FX umprogrammiert werden, ohne dass diese Klasse geändert 
 * werden müsste. Auch könnte die Datenbank ganz anders implementiert werden - z.B. die Daten
 * aus einer "wirklichen" Datenbank zu lesen - auch da würde diese Klasse nichts "mitbekommen"! Durch
 * diese Unabhängigkeit werden wartungsfreundliche Anwendungen geschrieben.
 */
public class OpenTextListener implements ActionListener {

	private WordPad gui;
	
	public OpenTextListener(WordPad gui) {
		this.gui = gui;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// View auffordern zu öffnendes File-Objekt zu liefern
		File file = gui.getFileToRead(); //kann auch "null" sein, wenn Dialog abgebrochen wurde
		if (file != null) {
			// File-Objekt an Model übergeben, und Text aus "Datenbank" holen
			String text = Datenbank.readText(file);
			// Text der View zum Anzeigen zur Verfügung stellen
			gui.setAreaText(text);			
		}
	}
}
