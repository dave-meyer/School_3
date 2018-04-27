package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.Farbenauswahl;
import view.Farbenauswahl.Farbenart;
import view.WordPad;

/**
 * Wenn die Farbe eines Components geandert werden soll
 * Erhaehlt per Konstruktor, um welchen Farbentyp es sich handelt und welcher
 * Component geandert werden soll
 * 
 * @author David Meyer
 * @version 1.0
 */
public class FarbeAendernListener implements ActionListener {

	Farbenart farbenart;
	WordPad wordpad;
	
	public FarbeAendernListener(WordPad _wordpad, Farbenart _farbenart) {
		
		farbenart = _farbenart;
		wordpad = _wordpad;
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if(WordPad.farbenauswahl == null) {
			
			WordPad.farbenauswahl = new Farbenauswahl(wordpad.getTextArea(),farbenart);
		}
	}
	
}
