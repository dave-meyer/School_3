package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import view.WordPad;

/**
 * Wenn der Text einer JTextArea geaendert wurde
 * @author Dave
 *
 */
public class TextAreaKeyListener extends KeyAdapter {

	WordPad wordpad;
	
	public TextAreaKeyListener(WordPad _wordpad) {
		
		wordpad = _wordpad;
	}
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		
		wordpad.savedchanges = false;
	}

}
