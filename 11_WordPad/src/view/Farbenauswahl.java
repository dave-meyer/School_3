package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Ein Fenster, welches verschiedene Farben zur Auswahl zeigt, mit denen man die
 * Hintergrund- oder Vordergrundfarbe eines Components aendern kann
 * @author David Meyer
 * @version 1.0
 *
 */
public class Farbenauswahl extends JFrame {

	private JPanel contentPane;
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private JButton btnNewButton_3;
	private JButton btnNewButton_4;
	private JButton btnNewButton_5;
	private JButton btnNewButton_6;
	private JButton btnNewButton_7;
	private JButton btnNewButton_8;
	private JButton btnNewButton_9;
	private JLabel lblNewLabel;
	private JButton btnNewButton_10;
	private JButton button;
	
	public enum Farbenart {
		VORDERGRUNDFARBE, HINTERGRUNDFARBE
	}
	
	/**
	 * Handelt es sich bei der zu aendernden Farbe um
	 * eine Vordergrundfarbe oder um eine Hintergrundfarbe?
	 */
	private Farbenart farbenart;
	
	/**
	 * Handelt es sich bei der zu aendernden Farbe um
	 * eine Vordergrundfarbe oder um eine Hintergrundfarbe?
	 *  1: Foreground
	 *  2: Background
	 */
	public static int farbenart_alt;
	/**
	 * Das Component, welches die Farbe geaendert bekommen soll
	 */
	public static Component zielcomponent;

	/**
	 * Konstruktor
	 * @param _zielcomponent Der Component, welcher die Farbe geandert bekommen soll
	 * @param _farbenart Welche Farbe geaendert werden soll (1: Vordergrund // 2: Hintergrund)
	 */
	public Farbenauswahl(Component _zielcomponent, Farbenart _farbenart) {
		zielcomponent = _zielcomponent;
		farbenart = _farbenart;
		
		initialize();
	}
	private void initialize() {
		
		this.setVisible(true);
		setBounds(100, 100, 450, 300);
		// Wird nicht normal geschlossen, sondern disposed wenn auf das X geklickt wird
		// damit die ueberschriebene Dispose Methode verwendet wird
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		
		lblNewLabel = new JLabel("");
		panel.add(lblNewLabel);
		
		switch(farbenart) {
			case VORDERGRUNDFARBE: this.setTitle("Schriftfarbe veraendern"); lblNewLabel.setText("Farbe des Textes veraendern"); break;
			case HINTERGRUNDFARBE: this.setTitle("Hintergrundfarbe veraendern"); lblNewLabel.setText("Farbe des Hintergrunds veraendern"); break;
		}
		System.out.println("Farbe wurde geaendert");
		
		panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		
		button = new JButton("        ");
		button.setEnabled(false);
		panel_1.add(button);
		
		btnNewButton_10 = new JButton("Farbe bestaetigen");
		btnNewButton_10.addActionListener(new BestaetigenbuttonActionListener());
		panel_1.add(btnNewButton_10);
		
		panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new GridLayout(5, 5, 0, 0));
		
		btnNewButton_1 = new JButton("");
		btnNewButton_1.setBackground(Color.BLACK);
		btnNewButton_1.addActionListener(new FarbbuttonActionListener());
		panel_2.add(btnNewButton_1);
		
		btnNewButton_9 = new JButton("");
		btnNewButton_9.setBackground(Color.WHITE);
		btnNewButton_9.addActionListener(new FarbbuttonActionListener());
		panel_2.add(btnNewButton_9);
		
		btnNewButton_8 = new JButton("");
		btnNewButton_8.setBackground(Color.RED);
		btnNewButton_8.addActionListener(new FarbbuttonActionListener());
		panel_2.add(btnNewButton_8);
		
		btnNewButton_7 = new JButton("");
		btnNewButton_7.setBackground(Color.ORANGE);
		btnNewButton_7.addActionListener(new FarbbuttonActionListener());
		panel_2.add(btnNewButton_7);
		
		btnNewButton_6 = new JButton("");
		btnNewButton_6.setBackground(Color.YELLOW);
		btnNewButton_6.addActionListener(new FarbbuttonActionListener());
		panel_2.add(btnNewButton_6);
		
		btnNewButton_5 = new JButton("");
		btnNewButton_5.setBackground(Color.MAGENTA);
		btnNewButton_5.addActionListener(new FarbbuttonActionListener());
		panel_2.add(btnNewButton_5);
		
		btnNewButton_4 = new JButton("");
		btnNewButton_4.setBackground(Color.BLUE);
		btnNewButton_4.addActionListener(new FarbbuttonActionListener());
		panel_2.add(btnNewButton_4);
		
		btnNewButton_3 = new JButton("");
		btnNewButton_3.setBackground(Color.CYAN);
		btnNewButton_3.addActionListener(new FarbbuttonActionListener());
		panel_2.add(btnNewButton_3);
		
		btnNewButton_2 = new JButton("");
		btnNewButton_2.setBackground(Color.GREEN);
		btnNewButton_2.addActionListener(new FarbbuttonActionListener());
		panel_2.add(btnNewButton_2);
		
		btnNewButton = new JButton("");
		btnNewButton.setBackground(Color.PINK);
		btnNewButton.addActionListener(new FarbbuttonActionListener());
		panel_2.add(btnNewButton);
	}
	
	@Override
	/**
	 * Methode wird auch beim Klicken auf das 'X' in der rechten oberen Ecke des Frames ausgefuehrt
	 * 
	 * Loescht das Farbenauswahl-Fenster aus der farbenauswahl Variable von dem Hauptfenster,
	 * damit Hauptfenster wieder ein neues Farbenauswahlfenster oeffnen kann
	 * Zusaetzlich wird die normale dispose Methode ausgefuehrt
	 */
	public void dispose() {
		
		WordPad.farbenauswahl = null;
		super.dispose();
	}

	/**
	 * Wenn man den Farbbutton klickt
	 * Ein Farbbutton hat keinen Text, nur eine Hintergrundfarbe, die verwendet werden soll
	 * 
	 * Bei einem Klick auf einen Farbbutton soll seine Farbe ausgewaehlt werden
	 * Auswaehlen heisst, dass der Bestaetigungsbutton die Farbe erhaelt
	 * @author Dave
	 *
	 */
	private class FarbbuttonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			button.setBackground(((JButton) e.getSource()).getBackground());
		}
	}
	/**
	 * Die ausgewaehlte Farbe als Hintergrund-/Vordergrundfarbe einstellen
	 * Die ausgewaehlte Farbe ist die Farbe des Bestaetigungsbuttons
	 * 
	 * Danach wird der Farbenauswahlframe geschlossen
	 * 
	 * @author David Meyer
	 *
	 */
	private class BestaetigenbuttonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			switch(farbenart) {
				case VORDERGRUNDFARBE: zielcomponent.setForeground(button.getBackground()); break;
				case HINTERGRUNDFARBE: zielcomponent.setBackground(button.getBackground()); break;
			}
			// Frame zerstoeren
			Farbenauswahl.this.dispose();
		}
		
	}
}
