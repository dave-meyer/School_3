package control;

import view.*;

import java.awt.event.*;

import javax.swing.JOptionPane;

/**
 * Eine neue Textdatei soll erstellt werden.
 * Sollte sich bereits Inhalt in der GUI befinden, muss nachgefragt werden, ob dieser Inhalt
 * gelöscht werden darf.
 * 
 * Dieser einfache GUI-Teil sollte auch im control-Package zulässig sein - auch wenn es nicht
 * ganz "sauber" ist.
 */

public class DateiNeuListener implements ActionListener {

	private WordPad gui;
	
	public DateiNeuListener(WordPad gui) {
		this.gui = gui;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		String inhalt = gui.getAreaText();
		if (!inhalt.equals("")) {
			String meldung = "Der Textbereich ist nicht leer! \nWenn sie eine neue " +
					 "Textdatei erstellen wollen, so geht dieser Inhalt verloren! \n" +
					 "Wollen sie wirklich eine neue Datei erstellen?";
			int wahl = JOptionPane.showConfirmDialog(gui.getFrame(), meldung, "Rückfrage", JOptionPane.YES_NO_OPTION);
			if (wahl == JOptionPane.NO_OPTION) {
				return;
			}
		}
		gui.setAreaText("");
	}

}
