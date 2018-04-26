package view;

import controller.*;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.awt.event.*;
import net.miginfocom.swing.MigLayout;
import java.io.*;
import java.util.*;

/**
 * Daptiert aus: "Programmieren lernen mit JAVA"
 * 
 * Die View ermöglicht die Erfassung von Kontaktdaten. Diese können in eine
 * Tabelle übernommen und auch wieder entfernt werden.
 * 
 * Beim Beenden der View wird die Möglichkeit zum Speichern der Kontaktdaten
 * automatisch angeboten.
 */
public class Kontakttabelle extends JFrame {

	private JPanel contentPane;
	private JTextField tfNachname;
	private JTextField tfVorname;
	private JTextField tfTelefon;
	private JTextField tfEmail;
	private JTable table;
	private JButton btnEnde;

	public Kontakttabelle() {
		initialize();
	}

	/**
	 * Die Methode baut die gesamte GUI auf und definiert das benötigte
	 * Eventhandling. Im Wesentlichen wurde diese Methode mit Hilfe
	 * des Windwobuilder erstellt.
	 */
	private void initialize() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setTitle("Kontakttabelle");
		setBounds(200, 200, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane
				.setLayout(new MigLayout(
						"",
						"[131px,grow][13px][132px,grow][10px][120px,grow][9px][89px,grow]",
						"[14px][20px][14px][23px][14px][105px,grow][23px]"));

		JLabel lblNachname = new JLabel("Nachname");
		contentPane.add(lblNachname, "cell 0 0,growx,aligny top");

		tfNachname = new JTextField();
		contentPane.add(tfNachname, "cell 0 1,growx,aligny top");
		tfNachname.setColumns(10);

		JLabel lblVorname = new JLabel("Vorname");
		contentPane.add(lblVorname, "cell 2 0,alignx left,aligny top");

		tfVorname = new JTextField();
		contentPane.add(tfVorname, "cell 2 1,growx,aligny top");
		tfVorname.setColumns(10);

		JLabel lblTelefon = new JLabel("Telefon");
		contentPane.add(lblTelefon, "cell 4 0,growx,aligny top");

		tfTelefon = new JTextField();
		contentPane.add(tfTelefon, "cell 4 1 3 1,growx,aligny top");
		tfTelefon.setColumns(10);

		JLabel lblEmail = new JLabel("EMail");
		contentPane.add(lblEmail, "cell 0 2,growx,aligny top");

		tfEmail = new JTextField();
		contentPane.add(tfEmail, "cell 0 3 3 1,growx,aligny center");
		tfEmail.setColumns(10);

		JButton btnUebernehmen = new JButton("\u00DCbernehmen >>");
		btnUebernehmen.addActionListener(new UebernehmenListener());
		contentPane.add(btnUebernehmen, "cell 4 3 3 1,growx,aligny top");

		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, "cell 0 5 7 1,grow");

		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] {
				"Nachname", "Vorname", "Telefon", "EMail" }));
		JLabel lblKontaktliste = new JLabel("Kontaktliste");
		contentPane.add(lblKontaktliste, "cell 0 4,growx,aligny top");

		btnEnde = new JButton("Ende");
		btnEnde.addActionListener(new EndeListener(this));
		contentPane.add(btnEnde, "cell 6 6,growx,aligny top");

		JButton btnLoeschen = new JButton("markierten Eintrag l\u00F6schen");
		btnLoeschen.addActionListener(new LoeschListener());
		contentPane.add(btnLoeschen, "cell 0 6 3 1,alignx left,aligny top");
	}
	
	// ----------------- Benötigte Getters und Setters für Controller ---------
	
	/**
	 * Die vorhandene Tabelle von Kontaktdaten wird in Form einer ArrayList
	 * von String-Arrays retourniert (1 String-Array mit 4 Einträgen pro Zeile)
	 * 
	 * @return Die ArrayList von Kontaktdaten.
	 */
	public ArrayList<String[]> getTabelle() {
		DefaultTableModel m = (DefaultTableModel)table.getModel();
		int anzZeilen = m.getRowCount();
		int anzSpalten = m.getColumnCount();
		String[] 			zeile 	= new String[anzSpalten];
		ArrayList<String[]> tabelle = new ArrayList<String[]>();
		for (int i = 0; i < anzZeilen; i++) {
			for (int j = 0; j < anzSpalten; j++) {
				if (m.getValueAt(i, j) != null) {
					zeile[j] = m.getValueAt(i, j).toString();
				} else {
					zeile[j] = " ";
				}
			}
			//Zeile der ArrayList hinzufügen
			tabelle.add(zeile);
			zeile = new String[anzSpalten];	//Werte leeren
		}
		return tabelle;
	}
	
	/**
	 * Die View wird aufgefordert einen Dialog zu führen und das File-Objekt zu 
	 * bestimmen unter dem die Daten gespeichert werden sollen.
	 * 
	 * @return das File-Objekt
	 */
	public File getFileToWrite() {
		File datei = null;
		JFileChooser fc = new JFileChooser("C:/KamM");
		int wahl = fc.showSaveDialog(this);
		if (wahl == JFileChooser.APPROVE_OPTION) {
			datei = fc.getSelectedFile();
		}
		return datei;
	}
	
	/**
	 * Die View wird aufgefordert einen Dialog zu führen und das File-Objekt zu 
	 * bestimmen von dem die Daten gelesen werden sollen.
	 * 
	 * @return das File-Objekt
	 */
	public File getFileToRead() {
		File datei = null;
		JFileChooser fc = new JFileChooser("C:/KamM");
		int wahl = fc.showDialog(this, "Datei einlesen...");
		if (wahl == JFileChooser.APPROVE_OPTION) {
			datei = fc.getSelectedFile();
		}
		return datei;		
	}
	
	/**
	 * Beim Programmstart werden ev. vorhandene Kontaktdaten von Controller und Model
	 * bereitgestellt. Diese sollen nun in der View angezeigt werden.
	 * @param tabelle
	 */
	public void showKontakte(ArrayList<String[]> tabelle) {
		DefaultTableModel m = (DefaultTableModel) table.getModel();
		for (String[] zeile : tabelle) {
			m.addRow(zeile);
		}
	}
	

	// +++++++++++++++++ Listener für GUI-Handling ++++++++++++++++++++++++++++

	/**
	 * Die in den Textfeldern eingegebenen Werte werden in die Tabellenliste
	 * übernommen.
	 * 
	 * Da es sich hier um eine reine interne GUI-Steuerung handelt, wurde zur
	 * Vereinfachung dieser Controller-Teil nicht in das Controllerpackage
	 * aufgenommen.
	 */
	public class UebernehmenListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (tfNachname.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "kein Name angegeben!");
				tfNachname.requestFocus();
			} else {
				DefaultTableModel m = (DefaultTableModel) table.getModel();
				m.addRow(new String[] { tfNachname.getText(),
						tfVorname.getText(), tfTelefon.getText(),
						tfEmail.getText() });
			}
		}
	}
	
	/**
	 * Die selektierte Zeile in der Kontakttabelle soll gelöscht werden.
	 * 
	 * Da es sich hier wieder um eine reine interne GUI-Steuerung handelt,
	 * wird diese nicht im Controllerpackage implementiert.
	 */
	public class LoeschListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (table.getSelectedRow() >= 0) {
				DefaultTableModel m = (DefaultTableModel) table.getModel();
				m.removeRow(table.getSelectedRow());
			}
		}
	}
	
}
