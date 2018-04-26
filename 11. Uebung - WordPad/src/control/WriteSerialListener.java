package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import model.Datenbank;
import view.WordPad;

/**
 * Der in der GUI/View eingegebene Text soll auf Festplatte als einziges StringOBJEKT gespeichert werden.
 * Zur Fileauswahl wird die View aufgefordert das entsprechende File-Objekt zum Speichern zu liefern.
 * Dann wird der zu speichernde Text aus der View geholt und dieser mit dem File-Objekt an das Model
 * zum Speichern in der "Datenbank" weitergereicht.
 * 
 * Hier sieht man schön wie unabhängig von konkreten View- oder Model-Implementierungen diese Klasse ist.
 * Die View könnte z.B. von SWING in JAVA-FX umprogrammiert werden, ohne dass diese Klasse geändert 
 * werden müsste. Auch könnte die Datenbank ganz anders implementiert werden - z.B. die Daten
 * in einer "wirklichen" Datenbank abzuspeichern - auch da würde diese Klasse nichts "mitbekommen"! Durch
 * diese Unabhängigkeit werden wartungsfreundliche Anwendungen geschrieben.
 */
public class WriteSerialListener implements ActionListener {

	private WordPad gui;
	
	public WriteSerialListener(WordPad gui) {
		this.gui = gui;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// View auffordern File-Objekt zu liefern unter dessen Namen der Text abgespeichert werden soll
		File file = gui.getFileToWrite(); //kann auch "null" sein, wenn Dialog abgebrochen wurde
		if (file != null) {
			//Text der gepeichert werden soll aus View holen
			String text = gui.getAreaText();
			// File-Objekt und Text an Model übergeben, dass dieser in "Datenbank" gespeichert wird.
			Datenbank.writeTextSerial(file, text);
		}
	}

}
