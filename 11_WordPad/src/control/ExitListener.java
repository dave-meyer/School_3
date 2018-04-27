package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import view.WordPad;

/**
 * Wenn ein Schliessen-Button gedrueckt wurde
 * @author David Meyer
 * @version 1.0
 */
public class ExitListener implements ActionListener {

	WordPad wordpad;
	
	public ExitListener(WordPad _wordpad) {
		wordpad = _wordpad;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		wordpad.dispose();
	}
}