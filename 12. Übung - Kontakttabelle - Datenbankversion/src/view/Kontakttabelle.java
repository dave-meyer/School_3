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
	private JButton btnUebernehmen;
	private JTextField tfStrasse;
	private JTextField tfOrt;
	private Kontakttabelle isoewa; 	// um JFrame (Kontakttabelle) in internen Listenerklassen referenzieren zu können
									// z.B. dass JOptionPane mittig zum Frame angezeigt wird.

	public Kontakttabelle() {
		initialize();
		isoewa = this;
	}

	/**
	 * Die Methode baut die gesamte GUI auf und definiert das benötigte
	 * Eventhandling. Im Wesentlichen wurde diese Methode mit Hilfe des
	 * Windwobuilder erstellt.
	 */
	private void initialize() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setTitle("Kontakttabelle");
		setBounds(200, 200, 1000, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane
				.setLayout(new MigLayout(
						"",
						"[][][131px,grow][13px][132px,grow][10px][120px,grow][9px][89px,grow]",
						"[21.00px][20px][21.00px][23px][21.00px][20px][31.00px][100px,grow][23px]"));

		JLabel lblA = new JLabel("");
		lblA.setIcon(new ImageIcon(Kontakttabelle.class
				.getResource("/view/images/logo2.JPG")));
		contentPane.add(lblA, "cell 0 0 1 6");

		JLabel lblNachname = new JLabel("Nachname");
		contentPane.add(lblNachname, "cell 2 0,growx,aligny top");

		JLabel lblVorname = new JLabel("Vorname");
		contentPane.add(lblVorname, "cell 6 0,alignx left,aligny top");

		tfNachname = new JTextField();
		contentPane.add(tfNachname, "cell 2 1 3 1,growx,aligny top");
		tfNachname.setColumns(10);

		tfVorname = new JTextField();
		contentPane.add(tfVorname, "cell 6 1 3 1,growx,aligny top");
		tfVorname.setColumns(10);

		JLabel lblEmail = new JLabel("EMail");
		contentPane.add(lblEmail, "cell 2 2,growx,aligny top");

		JLabel lblTelefon = new JLabel("Telefon");
		contentPane.add(lblTelefon, "cell 6 2,growx,aligny top");

		tfEmail = new JTextField();
		contentPane.add(tfEmail, "cell 2 3 3 1,growx,aligny center");
		tfEmail.setColumns(10);

		btnUebernehmen = new JButton("\u00DCbernehmen >>");
		UebernehmenListener uebList = new UebernehmenListener();	//geändert - dass Listener auch 
																	//von letztem Textfeld (Ort) mit Entertaste 
																	//genutzt werden kann
		btnUebernehmen.addActionListener(uebList);

		tfTelefon = new JTextField();
		contentPane.add(tfTelefon, "cell 6 3 3 1,growx,aligny top");
		tfTelefon.setColumns(10);

		JLabel lblStrasse = new JLabel("Stra\u00DFe");
		contentPane.add(lblStrasse, "cell 2 4");

		JLabel lblOrt = new JLabel("Ort");
		contentPane.add(lblOrt, "cell 6 4");

		tfStrasse = new JTextField();
		contentPane.add(tfStrasse, "cell 2 5 3 1,growx");
		tfStrasse.setColumns(10);

		tfOrt = new JTextField();
		tfOrt.addActionListener(uebList);							//Selbes Listenerobjekt wie für Übernehmenbutton
		contentPane.add(tfOrt, "cell 6 5 3 1,growx");
		tfOrt.setColumns(10);
		JLabel lblKontaktliste = new JLabel("Kontaktliste");
		contentPane.add(lblKontaktliste, "cell 0 6,growx,aligny center");

		JButton btnLeeren = new JButton("Eingabefelder leeren");
		btnLeeren.addActionListener(new LeerenActionListener());
		contentPane.add(btnLeeren, "cell 2 6 3 1,growx");
		contentPane.add(btnUebernehmen, "cell 6 6 3 1,growx");

		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, "cell 0 7 9 1,grow");

		table = new JTable();
		table.addMouseListener(new LoeschListenerMouse());
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Nachname", "Vorname", "Telefon", "EMail",
						"Stra\u00DFe", "Ort" }));

		btnEnde = new JButton("Ende");
		btnEnde.addActionListener(new EndeListener(this));

		JButton btnLoeschen = new JButton("markierten Eintrag l\u00F6schen");
		btnLoeschen.addActionListener(new LoeschListenerButton());
		contentPane.add(btnLoeschen, "cell 0 8,alignx center,aligny top");
		contentPane.add(btnEnde, "cell 8 8,growx,aligny top");
	}

	// ----------------- Benötigte Getters und Setters für Controller ---------

	/**
	 * Die vorhandene Tabelle von Kontaktdaten wird in Form einer ArrayList von
	 * String-Arrays retourniert (1 String-Array mit 4 Einträgen pro Zeile)
	 * 
	 * @return Die ArrayList von Kontaktdaten.
	 */
	public ArrayList<String[]> getTabelle() {
		DefaultTableModel m = (DefaultTableModel) table.getModel();
		int anzZeilen = m.getRowCount();
		int anzSpalten = m.getColumnCount();
		String[] zeile = new String[anzSpalten];
		ArrayList<String[]> tabelle = new ArrayList<String[]>();
		for (int i = 0; i < anzZeilen; i++) {
			for (int j = 0; j < anzSpalten; j++) {
				if (m.getValueAt(i, j) != null) {
					zeile[j] = m.getValueAt(i, j).toString();
				} else {
					zeile[j] = " ";
				}
			}
			// Zeile der ArrayList hinzufügen
			tabelle.add(zeile);
			zeile = new String[anzSpalten]; // Werte leeren
		}
		return tabelle;
	}

	/**
	 * Beim Programmstart werden ev. vorhandene Kontaktdaten von Controller und
	 * Model bereitgestellt. Diese sollen nun in der View angezeigt werden.
	 * 
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
				JOptionPane.showMessageDialog(isoewa, "kein Name angegeben!");
				tfNachname.requestFocus();
			} else {
				DefaultTableModel m = (DefaultTableModel) table.getModel();
				m.addRow(new String[] { tfNachname.getText(),
						tfVorname.getText(), tfTelefon.getText(),
						tfEmail.getText(), tfStrasse.getText(), tfOrt.getText() });
			}
		}
	}

	/**
	 * Die selektierte Zeile in der Kontakttabelle soll mittels Button gelöscht
	 * werden.
	 * 
	 * Da es sich hier wieder um eine reine interne GUI-Steuerung handelt, wird
	 * diese nicht im Controllerpackage implementiert.
	 */
	public class LoeschListenerButton implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (table.getSelectedRow() >= 0) {
				DefaultTableModel m = (DefaultTableModel) table.getModel();
				m.removeRow(table.getSelectedRow());
			}
		}
	}

	/**
	 * Die selektierte Zeile in der Kontakttabelle soll mittels rechter
	 * Maustaste gelöscht werden.
	 * 
	 * Da es sich hier wieder um eine reine interne GUI-Steuerung handelt, wird
	 * diese nicht im Controllerpackage implementiert.
	 */
	private class LoeschListenerMouse extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getButton() == MouseEvent.BUTTON3) { // rechte Maustaste
				int zeilennr = table.getSelectedRow();
				// ev. keine Zeile selektiert?
				if (zeilennr < 0) {
					JOptionPane
							.showMessageDialog(table,
									"Sie müssen mit der linken Maustaste zuerst eine Zeile selektieren!!");
					return;
				}
				String nn = (String) table.getModel().getValueAt(zeilennr, 0);
				String vn = (String) table.getModel().getValueAt(zeilennr, 1);
				int yesno = JOptionPane.showConfirmDialog(table, "Wollen Sie '"
						+ nn + " " + vn + "' " + "wirklich entfernen?",
						"Wirklich????", JOptionPane.YES_NO_OPTION);
				if (yesno == JOptionPane.YES_OPTION) {
					DefaultTableModel m = (DefaultTableModel) table.getModel();
					m.removeRow(table.getSelectedRow());
				}
			}
		}
	}

	/**
	 * Die Eingabefelder sind zu leeren.
	 * 
	 * Da es sich hier wieder um eine reine interne GUI-Steuerung handelt, wird
	 * diese nicht im Controllerpackage implementiert.
	 */
	private class LeerenActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			tfNachname.setText("");
			tfVorname.setText("");
			tfTelefon.setText("");
			tfEmail.setText("");
			tfStrasse.setText("");
			tfOrt.setText("");
		}
	}

}
