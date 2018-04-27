package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;

import datenbank.Datenbank;
import view.WordPad;

/**
 * Wenn eine Datei normal gespeichert werden soll
 * @author David Meyer
 * @version 1.0
 */
public class NormalSpeichernListener implements ActionListener {
	
	WordPad wordpad;
	public NormalSpeichernListener(WordPad _wordpad) {
		wordpad = _wordpad;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(wordpad.getFileChooser().showSaveDialog(wordpad) == JFileChooser.APPROVE_OPTION){
			
			Datenbank.ordinarySave(wordpad.getTextArea().getText(), wordpad.getFileChooser().getSelectedFile());
			wordpad.setOpenedFileText(wordpad.getFileChooser().getSelectedFile().getAbsolutePath());
			System.out.println("Die Datei " + wordpad.getFileChooser().getSelectedFile().getAbsolutePath() + " wurde normal gespeichert");
			wordpad.savedchanges = true;
		}
	}
}