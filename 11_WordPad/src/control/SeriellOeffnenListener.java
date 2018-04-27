package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;

import datenbank.Datenbank;
import view.WordPad;

/**
 * Wenn eine Datei seriell geoeffnet werden soll
 * @author David Meyer
 * @version 1.0
 */
public class SeriellOeffnenListener implements ActionListener {
	
	WordPad wordpad;
	public SeriellOeffnenListener(WordPad _wordpad) {
		wordpad = _wordpad;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(wordpad.getFileChooser().showOpenDialog(wordpad) == JFileChooser.APPROVE_OPTION){

			wordpad.getTextArea().setText(Datenbank.serialLoad(wordpad.getFileChooser().getSelectedFile()));
			wordpad.setOpenedFileText(wordpad.getFileChooser().getSelectedFile().getAbsolutePath());
			System.out.println("Die Datei " + wordpad.getFileChooser().getSelectedFile().getAbsolutePath() + " wurde seriell geoeffnet");
			wordpad.savedchanges = true;
		}
	}
}