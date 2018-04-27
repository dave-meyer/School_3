package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;

import datenbank.Datenbank;
import view.WordPad;

/**
 * Wenn eine Datei normal geoeffnet werden soll
 * @author David Meyer
 * @version 1.0
 */
public class NormalOeffnenListener implements ActionListener {
	
	WordPad wordpad;
	public NormalOeffnenListener(WordPad _wordpad) {
		wordpad = _wordpad;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(wordpad.getFileChooser().showSaveDialog(wordpad) == JFileChooser.APPROVE_OPTION){
			
			wordpad.getTextArea().setText(Datenbank.ordinaryLoad(wordpad.getFileChooser().getSelectedFile()));
			wordpad.setOpenedFileText(wordpad.getFileChooser().getSelectedFile().getAbsolutePath());
			wordpad.savedchanges = true;
		}
	}
}