package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JOptionPane;

import view.WordPad;

/**
 * Wenn ein neues Dokument erstellt werden soll
 * @author David Meyer
 * @version 1.0
 */
public class NeuesDokumentListener implements ActionListener {

	WordPad wordpad;
	
	public NeuesDokumentListener(WordPad _wordpad) {
		
		wordpad = _wordpad;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		if(wordpad.savedchanges) {
			
			newFile();
		}
		else {
			
			if(JOptionPane.showConfirmDialog(wordpad, "Die Aenderungen an \"" + wordpad.getFileChooser().getSelectedFile().getAbsolutePath() + "\" wurden noch nicht gespeichert. Moechten Sie trotzdem fortfahren?",
					"Neues Dokument erstellen", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == 0) {
				
				newFile();
			}
		}
		
	}
	
	/**
	 * Erstellt eine neue Datei-Referenz mit dem Namen "Unbenannt"
	 * und loescht den aktuellen Text in der textArea 
	 */
	public void newFile() {
		
		wordpad.getFileChooser().setSelectedFile(new File("Unbenannt"));
		
		wordpad.getTextArea().setText("");
		
		wordpad.savedchanges = true;
	}

	
}