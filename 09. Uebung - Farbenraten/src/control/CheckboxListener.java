package control;

import java.awt.event.*;
import javax.swing.*;

import view.*;

/**
 * Jeder RadioButton besitzt seinen Listener. Beim Instanziieren wird
 * der Wert für die Spielstärke bzw. die Dimension des ButtonPanels
 * festgelegt.
 */
public class CheckboxListener implements ItemListener 
{
	private FarbenRatenGUI gui;
	private int length;
	
	public CheckboxListener(FarbenRatenGUI gui, int length) 
	{
		this.gui = gui;
		this.length = length;
	}

	@Override
	public void itemStateChanged(ItemEvent ev) {
		JCheckBox cb = (JCheckBox)ev.getSource();
		if (cb.isSelected()) {
			gui.setSize(length);
			gui.setTries(0);
			gui.setHits(0);
			gui.area();
			gui.recalculate();			
		}
		
	}
}
