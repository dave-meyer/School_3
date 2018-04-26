package control;

import java.awt.event.*;
import javax.swing.*;
import view.*;

/**
 * Jeder Button hat seinen eigenen Listener. Wenn der Button gedrückt wurde,
 * ist zu prüfen, ob dessen Farbe mit der Hintergrundfarbe des Ratepanels
 * übereinstimmt. Die Statistikwerte sind dann entsprechend anzupassen.
 */
public class FarbButtonListener implements ActionListener 
{
	private FarbenRatenGUI gui;
	
	public FarbButtonListener(FarbenRatenGUI gui) 
	{
		this.gui = gui;
	}

	/**
	 * Aus dem ActionEvent wird der JButton ermittelt, der gedrückt
	 * wurde. Dessen Hintergrundfarbe wird mit dem Ratepanel 
	 * verglichen.
	 */
	public void actionPerformed(ActionEvent e) 
	{
		JButton button = (JButton)e.getSource();
		gui.setTries(gui.getTries() + 1);
		//richtige Farbe erwischt??
		if (button.getBackground().equals(gui.getColorPanel().getBackground())) 
		{
			gui.setHits(gui.getHits() + 1);
		} 
		gui.recalculate();	//Statistikzeile anzeigen
		gui.area();			//Buttons und zu erratende Hintergrundfarbe neu bestimmen
	}
}
