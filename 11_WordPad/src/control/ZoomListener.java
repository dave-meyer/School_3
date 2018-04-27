package control;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import view.*;

/**
 * Einzoomen (Schriftgroesse um Zoomfaktor erhoehen/verringern, je nach Vorzeichen des Faktors)
 * @author David Meyer
 *
 */
public class ZoomListener implements ActionListener {

	float zoomfactor;
	WordPad wordpad;
	public ZoomListener(int _zoomfactor, WordPad _wordpad) {
		
		zoomfactor = _zoomfactor;
		wordpad = _wordpad;
	}

	public void actionPerformed(ActionEvent event) {
			
		wordpad.increaseFontSize(zoomfactor);
	}
}