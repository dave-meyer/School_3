package view;

import net.miginfocom.swing.MigLayout;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import exceptions.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import view.AlkoholGUI.masseeinheiten;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import view.AlkoholGUI.volumseinheiten;

/**
 * Ein Programm, mit welchem man seine Promille Alkohol im Blut berechnen kann
 * 
 * @author David Meyer
 * @version 1.0
 * @see exceptions.WronInputException
 * @see exceptions.ErrorHandling
 */
public class AlkoholGUI extends JFrame {

	/** Tabelle mit getrunkenen Getraenken mit den Spalten <ul>
	 * <li>Getraenkbezeichnung</li>
	 * <li>Alkoholgehalt [in %]</li>
	 * <li>Menge [in Liter]</li>
	 * <li>Getrunkener Alkohol [in Liter]</li>
	 * </ul>
	 */
	protected JTable tabelle;
	/** Enthaelt die Bezeichnung des Getraenks */
	protected JTextField tfbez;
	/** Enthaelt die Masse (~Gewicht) der Person */
	protected JTextField tfmasse;
	/** Enthaelt den Alkoholgehalt in % des Getraenks */
	protected JTextField tfgehalt;
	/** Enthaelt die getrunkenen Liter des Getraenks */
	protected JTextField tfmenge;
	/** Enthaelt die derzeitigen getrunkenen Liter reinen Alkohol */
	protected JLabel lblgetrunken;
	/** Enthaelt die derzeitigen Promille Alkohol im Blut */
	protected JLabel lblpromille;
	/** Enthaelt Auswahlmoeglichkeiten fuer verschiedene Einheiten der Masse */
	protected JComboBox comboboxmasse;
	/** Enthaelt Auswahlmoeglichkeiten fuer verschiedene Einheiten des Volumens */
	protected JComboBox comboboxvolumen;
	
	/** Maximale Anzahl an eintragbaren Getraenken */
	public static final int MAX_GETRAENKE = 10;
	
	/** 
	 * Enthaelt mehrere Einheiten fuer die Masse mit ihren Umrechnungsfaktoren
	 * auf Kilogramm, da die Formel zur Berechnung der Promille Alkohol im Blut
	 * die Masse in der Einheit Kilogramm benoetigt
	 * 
	 * @author David Meyer
	 * @version 1.0
	 */
	public static enum masseeinheiten {
		
		kg (1),
		lbs (0.453592);
		
		/** Umrechnungsfaktor auf kg */
		public final double umrechnungsfaktor;
		
		/** @param der Umrechnungsfaktor dieser Einheit auf kg */
		masseeinheiten(double _umrechnungsfaktor) {
			umrechnungsfaktor = _umrechnungsfaktor;
		}
	}
		
	/** 
	 * Enthaelt mehrere Einheiten fuer das Volumen von getrunkenen Alkohol mit ihren Umrechnungsfaktoren
	 * auf Liter, da die Formel zur Berechnung der Promille Alkohol im Blut
	 * das Volumen in der Einheit Liter benoetigt
	 * 
	 * @author David Meyer
	 * @version 1.0
	 */
	public static enum volumseinheiten {
			
		Liter (1),
		Deziliter (0.001),
		Centiliter (0.000001),
		Milliliter (0.000000001);
			
		/** Umrechnungsfaktor auf Liter */
		public final double umrechnungsfaktor;
			
		/** @param der Umrechnungsfaktor dieser Einheit auf kg */
		volumseinheiten(double _umrechnungsfaktor) {
				
			umrechnungsfaktor = _umrechnungsfaktor;
		}
	}
	
	/**
	 * neues Alkoholrechner Fenster erstellen
	 * @param args Laufzeitparameter (hier keine)
	 */
	public static void main(String[] args) {
		
		/** Alkoholrechnerfenster (dort spielt sich alles ab) */
		AlkoholGUI frame = new AlkoholGUI();
	}
	
	/**
	 * Alkoholrechner GUI aufbauen
	 */
	public AlkoholGUI() {
		this.frameErstellen();
	}
	
	/**
	 * Die Alkoholrechner GUI wird aufgebaut
	 */
	public void frameErstellen() {
		
		this.setTitle("Alkoholrechner");
		this.setBounds(100, 100, 800, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
		/**
		 * Layout des ganzes Frames, an dem sich die Komponenten anpassen
		 */
		MigLayout mig = new MigLayout("",
				"[100][10][100][10][100][10][100][10][100,grow][10][100][10]",
				"[20][10][20][5][20][5][20][5][20][5][100][5][20][5][20][5][20][5][20][5][20][5][20][5]"
				);
		
		getContentPane().setLayout(mig);
		
		/** Enthaelt nur ein Bild */
		Icon icon = new ImageIcon(this.getClass().getResource("blurry.gif"));
		((ImageIcon) icon).setImage(((ImageIcon) icon).getImage().getScaledInstance(260, 150, Image.SCALE_DEFAULT)); 
		JLabel lblbild = new JLabel(icon);
		getContentPane().add(lblbild, "cell 0 0 3 9");
		
		/** Beschreibung des Textfeldes fuer die Koerpermasse */
		JLabel lblmasse = new JLabel("Koerpermasse: ");
		lblmasse.setToolTipText("Umgangssprachlich auch als Gewicht bezeichnet");
		getContentPane().add(lblmasse, "cell 4 0, growx");

		tfmasse = new JTextField();
		tfmasse.addFocusListener(new TfmasseFocusListener());
		getContentPane().add(tfmasse, "cell 6 0, growx");
		
		/** Eine horizontale Line zum Aufteilen von 2 Komponentengruppen */
		JSeparator separator = new JSeparator();
		separator.setVisible(true);
		getContentPane().add(separator, "cell 4 2 6 0, pushx, growx");
		
		comboboxmasse = new JComboBox();
		comboboxmasse.addItemListener(new ComboboxmasseItemListener());
		comboboxmasse.setModel(new DefaultComboBoxModel(masseeinheiten.values()));
		getContentPane().add(comboboxmasse, "cell 8 0,growx");
		
		/** Beschreibung des Textfeldes fuer die Getraenkbezeichnung */
		JLabel lblbez = new JLabel();
		lblbez.setText("Getraenkebezeichnung: ");
		getContentPane().add(lblbez, "cell 4 4");
		
		tfbez = new JTextField();
		getContentPane().add(tfbez, "cell 6 4 3 1, growx");
		
		/** Beschreibung des Textfeldes fuer den Alkoholgehalt */
		JLabel lblgehalt = new JLabel();
		lblgehalt.setText("Alkoholgehalt [in %]: ");
		getContentPane().add(lblgehalt, "cell 4 6, growx");
		
		tfgehalt = new JTextField();
		getContentPane().add(tfgehalt, "cell 6 6, growx");
		
		JLabel lblmenge = new JLabel();
		lblmenge.setText("Konsummenge: ");
		getContentPane().add(lblmenge, "cell 4 8, growx");
		
		tfmenge = new JTextField();
		tfmenge.addKeyListener(new TfmengeKeyListener());
		getContentPane().add(tfmenge, "cell 6 8, growx");
		
		JButton btnuebernehmen = new JButton("Getraenk uebernehmen");
		getContentPane().add(btnuebernehmen, "cell 8 6 2 1, growx");
		btnuebernehmen.addActionListener(new GetraenkUebernehmenListener());	
		
		/** Scrollbereich fuer die Tabelle mit den Getraenken */
		JScrollPane sp = new JScrollPane();
		sp.addMouseListener(new SpMouseListener());
		
		comboboxvolumen = new JComboBox();
		comboboxvolumen.setModel(new DefaultComboBoxModel(volumseinheiten.values()));
		getContentPane().add(comboboxvolumen, "cell 8 8,growx");
		getContentPane().add(sp, "cell 0 12 12 7, growx");
		
		tabelle = new JTable();
		tabelle.addKeyListener(new TabelleKeyListener());
		sp.setViewportView(tabelle);
		tabelle.setVisible(true);
		tabelle.setModel(new DefaultTableModel(new Object[][] {},
				new String[]{"Getraenkbezeichnung", "Alkoholgehalt [in %]", "Menge [in Liter]", "Getrunkener Alkohol [in Liter]"}));
		
		/** Beschreibung des Textfeldes fuer die konsumierte Menge an reinen Alkohol */
		JLabel lblgetrunkentext = new JLabel("Konsumierte Menge Alkohol [in Liter]: ");
		getContentPane().add(lblgetrunkentext, "cell 0 20, growx");
		
		lblgetrunken = new JLabel("0,00000");
		lblgetrunken.setForeground(Color.BLUE);
		getContentPane().add(lblgetrunken, "cell 2 20, growx");
		
		/** Beschreibung des Textfeldes fuer die Promille Alkohol im Blut */
		JLabel lblpromilletext = new JLabel("Promille Alkohol im Blut nach Widmarkformel: ");
		getContentPane().add(lblpromilletext, "cell 4 20 3 1,growx");
		
		lblpromille = new JLabel("0,00000");
		lblpromille.setForeground(Color.RED);
		getContentPane().add(lblpromille, "cell 8 20, growx");
		
		this.validate();
	}
	/**
	 * Berechnet Promille & Menge reiner Alkohol 
	 * und zeigt sie (auf 5 Stellen nach dem Komma gerundet) im GUI in den Labels an
	 * Es wird aus den derzeitig vorhandenen Werten in der GUI berechnet
	 */
	public void promilleBerechnen() {
		
		try {
			
			ErrorHandling.checkKilo(tfmasse.getText());
		}
		catch(WrongInputException ex) {
			
			ex.showOptionPane(AlkoholGUI.this);
			ex.fehlermeldungAusgeben();
			
			return;
		}
		
		double masse = Double.parseDouble(tfmasse.getText()) * ((masseeinheiten) comboboxmasse.getSelectedItem()).umrechnungsfaktor;
		double alkmenge = 0;
		for(int i = 0; i < tabelle.getRowCount(); i++) {
			alkmenge += (double) tabelle.getValueAt(i, 3);
		}
		
		// Werte auf 5 Stellen runden
		lblgetrunken.setText(String.format("%.5f", alkmenge));
		
		lblpromille.setText(String.format("%.5f", ( (alkmenge*800/(masse*0.7) ) * 0.85) ));
		validate();
	}
	
	/**
	 * Loescht alle selektierten Zeilen in der Getraenketabelle
	 */
	public void ausgewaehlteLoeschen() {
		
		// das muss so seltsam sein, da wenn man eine Reihe wegloescht, veraendern sich die Indexe der anderen Reihen
		// loescht immer die erste ausgewaehlte Zeile, solange bis keine ausgewaehlten Zeilen uebrig sind 
		while(tabelle.getSelectedRowCount() != 0) {
			
			int[] ausgewaehlteReihen = tabelle.getSelectedRows();
			((DefaultTableModel) AlkoholGUI.this.tabelle.getModel()).removeRow(ausgewaehlteReihen[0]);
		}
		
		promilleBerechnen();
	}
	
	/**
	 * Wenn dr Uebernehmen-Button gedrueckt wird, soll das eingegebene
	 * Getraenk in die Liste aufgenommen werden
	 * Vorher wird abgefragt, ob die Textboxen mit sinnvollen Werten gefuellt sind
	 * mit sinvollen Werten wird das Getraenk in die Tabelle eingetragen und
	 * die Menge an reinen Alkohol und die Promille Alkohol im Blut berechnet und ausgegeben
	 * mit nicht sinnvollen Werten wird ein Popup Fenster mit Fehlermeldung geoeffnet
	 * 
	 * @author David Meyer
	 * @version 1.0
	 */
	public void getraenkUebernehmen() {
			
			try{
				ErrorHandling.checkAlkohol(tfgehalt.getText());
				ErrorHandling.checkLiter(tfmenge.getText());
				ErrorHandling.checkAnzahlGetraenke(tabelle.getRowCount() + 1);
			}
			catch(WrongInputException ex) {
				
				// Fehlermeldung in Konsole anzeigen
				ex.fehlermeldungAusgeben();
				// Fehlermeldung in OptionPane anzeigen
				ex.showOptionPane(AlkoholGUI.this);

				// weiteren Ablauf der Methode stoppen, damit 
				// alles nach catch (Berechnung, in Liste aufnehmen, etc)
				// nicht mehr ausgefuehrt wird
				// (man koennte auch Berechnungen, etc in den try-Bereich schreiben, da 
				// wenn ein Fehler auftritt, der try-Bereich nicht mehr weiter ausgefuehrt wird)
				return;
			}
			
			String gehalts = tfgehalt.getText();
			String menges = tfmenge.getText();
			double gehalt = Double.parseDouble(gehalts);
			double menge = Double.parseDouble(menges) * ((volumseinheiten) comboboxvolumen.getSelectedItem()).umrechnungsfaktor;
		
			// TODO: automatisc addieren? :D
			((DefaultTableModel) tabelle.getModel()).addRow(new Object[] {tfbez.getText(), gehalt, menge, (gehalt/100)*menge});
			
			this.promilleBerechnen();
	}
	
	/**
	 * Wenn der Button gedrueckt wurde, sollen die eingegebenen Getraenkdaten
	 * in die Tabelle uebernommen werden
	 * @author David Meyer
	 * @version 1.0
	 */
	class GetraenkUebernehmenListener implements ActionListener {

		@Override
		/**
		 * Getraenk in Tabelle uebernehmen
		 */
		public void actionPerformed(ActionEvent arg0) {
			
			getraenkUebernehmen();
		}
	}
	
	/**
	 * Wenn ENTER gedrueckt wurde, sollen die eingegebenen Getraenkdaten
	 * in die Tabelle uebernommen werden
	 * @author David Meyer
	 * @version 1.0
	 */
	private class TfmengeKeyListener extends KeyAdapter {
		@Override
		/**
		 * Wenn ein Key wieder losgelassen wird (und dieser Key die ENTER Taste ist)
		 * wird das Getraenk zur Tabelle hinzugefuegt
		 */
		public void keyReleased(KeyEvent event) {
			
			if(event.getKeyCode() == 10) {
				
				getraenkUebernehmen();
			}
		}
	}
	/**
	 * Wenn die rechte Maustaste auf der Scrollpane mit der Tabelle 
	 * gedrueckt wurde, sollen
	 * die ausgewaehlten Zeilen entfernt werden
	 * Auch bei drücken der DELETE Taste werden ausgewaehlten Zeilen geloescht
	 * @author David Meyer
	 * @version 1.0
	 * @see TabelleKeyListener
	 */
	private class SpMouseListener extends MouseAdapter {
		@Override
		/** 
		 * Wenn gedrueckte Maustaste die rechte Maustaste ist,
		 * ausgewaehlte Zeilen loeschen
		 */
		public void mouseClicked(MouseEvent event) {
			
			if(event.getButton() == MouseEvent.BUTTON3) {
				
				ausgewaehlteLoeschen();
			}
		}
	}

	/**
	 * Wenn die ENTFERNEN (DELETE) Taste gedrueckt wurde, sollen 
	 * die ausgewaehlten Zeilen entfernt werden
	 * Auch bei Rechtsklick werden ausgewahelte Zeilen geloescht
	 * @author David Meyer
	 * @version 1.0
	 * @see SpMouseListener
	 */
	private class TabelleKeyListener extends KeyAdapter {
		@Override
		/**
		 * Wenn DELTE Taste wieder losgelassen wird, sollen ausgewaehlte Zeilen geloescht werden
		 */
		public void keyReleased(KeyEvent event) {
			// TODO: tabelle darf nicht veraenderbar sein
			if(event.getKeyCode() == 127) {
				
				ausgewaehlteLoeschen();
			}
		}
	}

	/**
	 * Wenn das Textfeld fuer die Masse den Fokus verliert
	 * (wenn andere Komponente ausgewaehlt wird) soll die Promille 
	 * neue berechnet werden
	 * @author David Meyer
	 * @versio 1.0
	 */
	private class TfmasseFocusListener extends FocusAdapter {
		@Override
		/**
		 * Promille neu berechnen
		 */
		public void focusLost(FocusEvent event) {
			
			promilleBerechnen();
		}
	}
	
	
	/**
	 * Wenn die Einheit der eingegebenen Masse geaendert wurde, muss
	 * die Promille neu berechnet werden
	 * @author David Meyer
	 * @version 1.0
	 */
	private class ComboboxmasseItemListener implements ItemListener {
		/** 
		 * Berechnet Promille neu
		 * */
		public void itemStateChanged(ItemEvent event) {
			
			promilleBerechnen();
		}
	}
}