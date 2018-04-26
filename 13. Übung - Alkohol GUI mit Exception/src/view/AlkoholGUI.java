package view;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

import java.awt.event.*;
import java.util.*;

import model.*;
import exceptions.*;

public class AlkoholGUI extends JFrame {

	private JPanel contentPane;
	private JLabel label;
	private JLabel lblGetrnkebezeichnung;
	private JLabel lblAlkoholgehaltInProzent;
	private JLabel lblKonsumierteMengeIn;
	private JTextField bezeichnung_TF;
	private JTextField alkohol_TF;
	private JTextField liter_TF;
	private JButton uebButton;
	private JScrollPane scrollPane;
	private JTable table;
	private JLabel lblNewLabel;
	private JTextField gewicht_TF;
	private JSeparator separator;
	private JLabel lblKonsumierteMengeReinen;
	private JLabel mengeAlkohol_L;
	private JLabel lblPromilleAlkoholIm;
	private JLabel promilleBlut_L;
	private static final int MAX_GETRAENKE = 10;	//max. dürfen 10 Getränke konsumiert werden

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AlkoholGUI frame = new AlkoholGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AlkoholGUI() {
		initialize();
	}
	private void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 745, 448);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		label = new JLabel("");
		label.setIcon(new ImageIcon(AlkoholGUI.class.getResource("/view/images/Schnapsnase.JPG")));
		label.setBounds(10, 11, 171, 118);
		contentPane.add(label);
		
		lblGetrnkebezeichnung = new JLabel("Getr\u00E4nkebezeichnung: ");
		lblGetrnkebezeichnung.setBounds(215, 60, 180, 24);
		contentPane.add(lblGetrnkebezeichnung);
		
		lblAlkoholgehaltInProzent = new JLabel("Alkoholgehalt in Prozent (%): ");
		lblAlkoholgehaltInProzent.setBounds(215, 83, 180, 24);
		contentPane.add(lblAlkoholgehaltInProzent);
		
		lblKonsumierteMengeIn = new JLabel("Konsumierte Menge in Liter (l): ");
		lblKonsumierteMengeIn.setBounds(215, 105, 180, 24);
		contentPane.add(lblKonsumierteMengeIn);
		
		bezeichnung_TF = new JTextField();
		bezeichnung_TF.addActionListener(new Bezeichnung_AL());
		bezeichnung_TF.setEditable(false);
		bezeichnung_TF.setBounds(460, 62, 248, 20);
		contentPane.add(bezeichnung_TF);
		bezeichnung_TF.setColumns(10);
		
		alkohol_TF = new JTextField();
		alkohol_TF.addActionListener(new Alkohol_AL());
		alkohol_TF.setEditable(false);
		alkohol_TF.setBounds(460, 85, 47, 20);
		contentPane.add(alkohol_TF);
		alkohol_TF.setColumns(10);
		
		liter_TF = new JTextField();
		liter_TF.addActionListener(new Liter_AL());
		liter_TF.setEditable(false);
		liter_TF.setBounds(460, 107, 47, 20);
		contentPane.add(liter_TF);
		liter_TF.setColumns(10);
		
		uebButton = new JButton("Getr\u00E4nk \u00FCbernehmen");
		uebButton.addActionListener(new UebButtonActionListener());
		uebButton.setEnabled(false);
		uebButton.setBounds(537, 106, 171, 23);
		contentPane.add(uebButton);
		this.getRootPane().setDefaultButton(uebButton);	//damit "Enter" beim Button funktioniert 
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 168, 709, 169);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setEnabled(false);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
			},
			new String[] {
				"Getr\u00E4nk", "Alkohol (%)", "Menge (l)", "Getrunkener Alkohol (l)"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(3).setPreferredWidth(130);
		scrollPane.setViewportView(table);
		
		lblNewLabel = new JLabel("K\u00F6rpergewicht in Kilogramm (kg):");
		lblNewLabel.setBounds(215, 11, 191, 24);
		contentPane.add(lblNewLabel);
		
		gewicht_TF = new JTextField();
		gewicht_TF.addActionListener(new Gewicht_AL());
		gewicht_TF.setBounds(460, 11, 86, 20);
		contentPane.add(gewicht_TF);
		gewicht_TF.setColumns(10);
		
		separator = new JSeparator();
		separator.setBounds(215, 40, 493, 13);
		contentPane.add(separator);
		
		lblKonsumierteMengeReinen = new JLabel("Konsumierte Menge reiner Alkohol in Liter: ");
		lblKonsumierteMengeReinen.setBounds(10, 348, 260, 50);
		contentPane.add(lblKonsumierteMengeReinen);
		
		mengeAlkohol_L = new JLabel("0.00");
		mengeAlkohol_L.setForeground(Color.BLUE);
		mengeAlkohol_L.setFont(new Font("Tahoma", Font.BOLD, 14));
		mengeAlkohol_L.setBounds(268, 348, 67, 50);
		contentPane.add(mengeAlkohol_L);
		
		lblPromilleAlkoholIm = new JLabel("Promille Alkohol im Blut nach Widmarkformel: ");
		lblPromilleAlkoholIm.setBounds(361, 348, 281, 50);
		contentPane.add(lblPromilleAlkoholIm);
		
		promilleBlut_L = new JLabel("0.00");
		promilleBlut_L.setForeground(Color.RED);
		promilleBlut_L.setFont(new Font("Tahoma", Font.BOLD, 14));
		promilleBlut_L.setBounds(641, 348, 67, 50);
		contentPane.add(promilleBlut_L);
	}
	
	/**
	 * Eingabe des Körpergewichts - Neben der Speicherung des Gewichts
	 * sind die GUI-Elemente zur Getränkeerfassung zu enablen.
	 * 
	 * Eine Falscheingabe ist abzufangen!!!
	 */
	private class Gewicht_AL implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String kiloS = gewicht_TF.getText();
			try {
				ErrorHandling.checkKilo(kiloS);
			} catch (WrongInputException e1) {
				gewicht_TF.setText("");
				return;
			}
			//Alles O.K.
			Trinker.setGewicht(new Double(kiloS));
			//Eingabefelder und Button enablen; Kilogramm disablen
			bezeichnung_TF.setEditable(true);
			alkohol_TF.setEditable(true);
			liter_TF.setEditable(true);
			uebButton.setEnabled(true);
			gewicht_TF.setEditable(false);
			bezeichnung_TF.requestFocus();
		}
	}
	
	/**
	 * Neue Getränkedaten wurden erfasst und müssen nun gespeichert und
	 * angezeigt werden.
	 * 
	 * Falscheingaben sind einzufangen!!!
	 */
	private class UebButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String bezS = bezeichnung_TF.getText();
			String alkS = alkohol_TF.getText();
			String literS = liter_TF.getText();
			//Die eingegebenen Werte sind zu prüfen
			try {
				ErrorHandling.checkAlkohol(alkohol_TF.getText());
			} catch (WrongInputException ex) {
				alkohol_TF.setText("");
				return;
			}
			try {
				ErrorHandling.checkLiter(liter_TF.getText());
			} catch (WrongInputException ex) {
				liter_TF.setText("");
				return;
			}
			//Alle Werte sind korrekt
			Getraenk getraenk = new Getraenk(bezS, new Double(alkS), new Double(literS));
			//Das neue Getränk ist im Model abzuspeichern und die Tabelle zu aktualisieren.
			//Es können maximal 10 Getränke konsumiert werden
			try {
				ErrorHandling.checkAnzahlGetraenke(MAX_GETRAENKE);
			} catch (WrongInputException ex) {
				return;	//wird einfach nicht weiter fortgefahren
			}
			Trinker.addGetraenk(getraenk);
			//Alle Getränke holen und Model aktualisieren
			TableModel model = table.getModel();
			ArrayList<Getraenk> getraenke = Trinker.getGetraenke();
			for (int i = 0; i < Trinker.getGetraenke().size(); i++) {
				Getraenk g = getraenke.get(i);
				model.setValueAt(g.getBezeichnung(), i, 0);
				model.setValueAt(g.getAlkohol(), i, 1);
				model.setValueAt(g.getLiter(), i, 2);
				model.setValueAt(g.getAlkoholLiter(), i, 3);
			}
			//Eingabefelder leeren
			bezeichnung_TF.setText("");
			alkohol_TF.setText("");
			liter_TF.setText("");
			bezeichnung_TF.requestFocus();
			//Aktualisierte Gesamtwerte anzeigen
			mengeAlkohol_L.setText(""+Trinker.getGesamtAlkoholLiter());
			promilleBlut_L.setText(""+Trinker.getPromille());
		}
	}
	
	/**
	 * Eingabe Getränkebezeichnung
	 * 
	 * Keine besondere Validierung
	 */
	private class Bezeichnung_AL implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			alkohol_TF.requestFocus();
		}
	}
	
	/**
	 * Eingabe Alkohol in %
	 * 
	 * Validierung auf numerisch
	 */
	private class Alkohol_AL implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			try {
				ErrorHandling.checkAlkohol(alkohol_TF.getText());
			} catch (WrongInputException ex) {
				alkohol_TF.setText("");
				return;
			}
			liter_TF.requestFocus();
		}
	}
	
	/**
	 * Eingabe Menge in Liter
	 * 
	 * Validierung auf numerisch
	 */
	private class Liter_AL implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			try {
				ErrorHandling.checkLiter(liter_TF.getText());
			} catch (WrongInputException ex) {
				liter_TF.setText("");
				return;
			}
			uebButton.requestFocus();
		}
	}
}
