import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.*;

/**
 * Diese Klasse erstellt einen Frame mit 3 Buttons, einem Label und einem Panel,
 * auf dem ein Kreis angezeigt wird 
 * 
 * Ein Button aendert den Text des Labels (im Westen des Frames)
 * Der andere laesst den Kreis im Panel animieren (im Zentrum des Frames)
 * Und der letzte oeffnet ein neues Window zum Freiandzeichnen (im Norden des Frames)
 * Der Label ist im Osten des Frames
 * 
 * @author David Meyer
 * @version 1.0
 */
public class DreiButtons {

	JFrame frame;
	JLabel label;
	KreisPanel panel;
	
	/**
	 * Wird bei Programmstart ausgefuehrt
	 * Ein neues Objekt dieser Klasse wird instanziiert,
	 * darauf wird die los-Methode ausgefuehrt
	 * 
	 * @param args Laufzeitparameter - hier keine
	 */
	public static void main(String[] args) {
		
		DreiButtons gui = new DreiButtons ();
		gui.los();
	}
	
	/**
	 * 3 neue Buttons werden erstellt, in welche ein neues Objekt aus einer inneren Klasse in 
	 * die ActionEvent-Liste eingetragen wird
	 */
	public void los() {
			
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		
		JButton buttonnorth = new JButton("Freihandzeichnen");
		buttonnorth.addActionListener(new buttonNorthListener());
		
		JButton buttonsouth = new JButton("Kreis animieren");
		buttonsouth.addActionListener(new buttonSouthListener());
		
		JButton buttoneast = new JButton("Label ändern");
		buttoneast.addActionListener(new buttonEastListener());
		
		label = new JLabel("Ich bin ein Label");
		
		panel = new KreisPanel();
		
		// Komponenten (Buttons/Label/Panel) geordnet an den Frame haengen
		frame.getContentPane().add(BorderLayout.SOUTH, buttonsouth);
		frame.getContentPane().add(BorderLayout.EAST, buttoneast);
		frame.getContentPane().add(BorderLayout.NORTH, buttonnorth);
		frame.getContentPane().add(BorderLayout.WEST, label);
		frame.getContentPane().add(BorderLayout.CENTER, panel);
		
		frame.setSize(450, 300);
		frame.setVisible(true);
		
	}
	
	// Innere Klassen (Listener-Klassen)
		/**
		 *  Listener fuer "Label aendern" Button (buttonEast)
		 * @author David Meyer
		 * @version 1.0
		 */
	class buttonEastListener implements ActionListener  {

		@Override
		/**
		 * Wenn der "Label aendern" Button gedrueckt wurde, wird der Text des Labels geaendert
		 */
		public void actionPerformed(ActionEvent event) {
			label.setText("Der Knopf wurde gedrueckt!");
			
		}
	}
	/**
	 *  Listener fuer "Kreis animieren" (buttonSouth)
	 * @author David Meyer
	 * @version 1.0
	 */
	class buttonSouthListener implements ActionListener {

		@Override
		/**
		 * Wenn der "Kreis animieren" Button gedrueckt wurde, wird der Kreis innerhalb des Panels animiert
		 */
		public void actionPerformed(ActionEvent e) {
			
			panel.kreisAnimieren();
		}
	}
	/**
	 * Listener fuer "Freihandzeichnung" (buttonNorth) 
	 * @author David Meyer
	 * @version 1.0
	 */
	class buttonNorthListener implements ActionListener {

		@Override
		/**
		 * Wenn der "Freihandzeichnung" Button gedrueckt wurde, wird ein neues Fenster zum Freihandzeichnen geoeffnet
		 */
		public void actionPerformed(ActionEvent e) {
			
			ZeichenWindow freihandwindow = new ZeichenWindow();
			freihandwindow.setSize(500, 500);
			freihandwindow.setVisible(true);
		}		
	}
}