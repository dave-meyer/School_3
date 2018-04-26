package control;

import java.awt.event.*;

/**
 * Durch Auswahl des entsprechenden Menüpunktes oder ToolBarIcons wird Programm beendet.
 */
public class BeendenListener implements ActionListener {

	public void actionPerformed(ActionEvent arg0) {
		System.exit(0);
	}
	
}
