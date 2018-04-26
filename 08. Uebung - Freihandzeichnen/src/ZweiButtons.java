
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Das Programm erweitert das Buchbeispiel "2 Buttons" um die M�glichkeit
 * einer Freihandzeichnung
 * 
 * @author DI Martin Kampenhuber
 *
 */
public class ZweiButtons {
	
	JFrame frame;
	JLabel label;
	MeinZeichenPanel zeichenPanel;
	
	public static void main (String[] args) {
		ZweiButtons gui = new ZweiButtons ();
		gui.los();
	}
	
	/**
	 * die Methode baut den Hauptframe auf und definiert das Eventhandling
	 */
	public void los() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
		JButton labelButton = new JButton("�ndere Label");
		labelButton.addActionListener(new LabelListener());
		
		JButton colorButton = new JButton("Starte Animation");
		colorButton.addActionListener(new ColorListener());
		
		JButton freihandButton = new JButton("Starte Freihandzeichnen");
		freihandButton.addActionListener(new FreihandListener());
		
		label = new JLabel("Ich bin ein Label");
		zeichenPanel = new MeinZeichenPanel();
		
		frame.getContentPane().add(BorderLayout.SOUTH, colorButton);
		frame.getContentPane().add(BorderLayout.CENTER, zeichenPanel);
		frame.getContentPane().add(BorderLayout.EAST, labelButton);
		frame.getContentPane().add(BorderLayout.WEST, label);
		frame.getContentPane().add(BorderLayout.NORTH, freihandButton);
		
		frame.setSize(420,300);
		frame.setLocation(100, 200); //ein wenig vom Bildschirmrand reinr�cken
		frame.setVisible(true);
	}

	/**
	 * 
	 * Innere Klasse f�r Eventhandling des Buttons zum �ndern des Textes
	 * 
	 * @author DI Martin Kampenhuber
	 *
	 */
	class LabelListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			label.setText("Autsch!");
		}
	} // innere Klasse schlie�en
	
	/**
	 * 
	 * Innere Klasse zum Eventhandling des Buttons zum Starten der Animation
	 * 
	 * @author DI Martin Kampenhuber
	 *
	 */
	class ColorListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			zeichenPanel.doAnimation();
		}
	} // innere Klasse schlie�en
	
	/**
	 * 
	 * Innere Klasse f�r Eventhandling des Buttons zum Starten des 
	 * Freihandwindows
	 * 
	 * @author DI Martin Kampenhuber
	 *
	 */
	class FreihandListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			FreihandWindow anim = new FreihandWindow();
			anim.setSize(500,500);
			anim.setLocation(600, 200);
			anim.setVisible(true);
		}
	}
}       