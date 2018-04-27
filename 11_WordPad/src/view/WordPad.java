package view;

import java.io.File;
import org.eclipse.wb.swing.FocusTraversalOnArray;

import java.awt.Component;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.*;

import control.*;
import view.Farbenauswahl.Farbenart;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.KeyAdapter;

/**
 * Ein WordPad mit Texteingabe, Speichern/Laden Funktionen und diverse Zusatzfunktionen
 * @author David Meyer
 * @version 1.0
 *
 */
public class WordPad extends JFrame {

	private JPanel contentPane;
	private JMenuBar menuBar;
	private JMenu mnDatei;
	private JMenuItem mntmffnen;
	private JMenuItem mntmSpeichern;
	private JMenuItem mntmBeenden;
	private JSeparator separator;
	private JToolBar toolBar;
	private JScrollPane scrollPane;
	private JTextArea textArea;
	private JMenu mnAnsicht;
	private JMenuItem mntmZoom;
	private JMenuItem mntmZoom_1;
	
	private JMenuItem mntmHintergrundfarbeAendern;
	private JMenuItem mntmTextfarbeVeraendern;
	private JSeparator separator_1;
	
	public static Farbenauswahl farbenauswahl;
	private JMenuItem mntmNeu;
	private JMenuItem mntmSeriellSpeichern;
	public javafx.stage.Window wind;

	public boolean savedchanges;
	private JMenuItem mntmSeriellffnen;
	
	private JPanel panel;
	private JLabel lblOpenedFile;
	private JMenu mnSpeichern;
	private JMenu mnNewMenu;
	
	private JFileChooser fc;
	
	public static WordPad wordpad;
	private JButton btnOeffnen;
	private JButton btnSeriellOeffnen;
	private JButton btnSpeichern;
	private JButton btnSeriellSpeichern;
	private JButton btnBeenden;
	private JButton btnNeu;
	private JSeparator separator_2;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WordPad frame = new WordPad();
					wordpad = frame;
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
	public WordPad() {
		initialize();
	}
	/**
	 * Alle Variablen zuweisen und UI Components erstellen und laden
	 */
	private void initialize() {
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(WordPad.class.getResource("/icons/general/Edit16.gif")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("WordPad");
		setBounds(100, 100, 700, 440);
		
		savedchanges = true;
		
		fc = new JFileChooser();
		fc.setSelectedFile(new File("Unbenannt"));
		fc.addChoosableFileFilter(new FileNameExtensionFilter("Textdateien", "txt"));
		fc.addChoosableFileFilter(new FileNameExtensionFilter("Serialisierte Textdateien", "ser"));
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnDatei = new JMenu("Datei");
		menuBar.add(mnDatei);
		
		mntmNeu = new JMenuItem("Neu");
		mntmNeu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
		mntmNeu.setIcon(new ImageIcon(WordPad.class.getResource("/icons/general/New16.gif")));
		mnDatei.add(mntmNeu);
		mntmNeu.addActionListener(new NeuesDokumentListener(this));
		
		mnNewMenu = new JMenu("\u00D6ffnen");
		mnDatei.add(mnNewMenu);
		
		mntmSeriellffnen = new JMenuItem("Seriell \u00D6ffnen ...");
		mntmSeriellffnen.setIcon(new ImageIcon(WordPad.class.getResource("/icons/general/Open16.gif")));
		mntmSeriellffnen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.ALT_MASK));
		mnNewMenu.add(mntmSeriellffnen);
		
		mntmffnen = new JMenuItem("\u00D6ffnen ...");
		mnNewMenu.add(mntmffnen);
		mntmffnen.setIcon(new ImageIcon(WordPad.class.getResource("/icons/general/Open16.gif")));
		mntmffnen.addActionListener(new NormalOeffnenListener(this));
		mntmffnen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
		mntmSeriellffnen.addActionListener(new SeriellOeffnenListener(this));
		
		mnSpeichern = new JMenu("Speichern");
		mnDatei.add(mnSpeichern);
		
		mntmSeriellSpeichern = new JMenuItem("Seriell Speichern ...");
		mntmSeriellSpeichern.setIcon(new ImageIcon(WordPad.class.getResource("/icons/general/Save16.gif")));
		mntmSeriellSpeichern.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.ALT_MASK));
		mnSpeichern.add(mntmSeriellSpeichern);
		mntmSeriellSpeichern.addActionListener(new SeriellSpeichernListener(this));
		
		mntmSpeichern = new JMenuItem("Speichern...");
		mnSpeichern.add(mntmSpeichern);
		mntmSpeichern.addActionListener(new NormalSpeichernListener(this));
		mntmSpeichern.setIcon(new ImageIcon(WordPad.class.getResource("/icons/general/Save16.gif")));
		mntmSpeichern.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		
		separator = new JSeparator();
		mnDatei.add(separator);
		
		mntmBeenden = new JMenuItem("Beenden");
		mntmBeenden.setIcon(new ImageIcon(WordPad.class.getResource("/icons/general/Stop16.gif")));
		mntmBeenden.addActionListener(new ExitListener(this));
		mnDatei.add(mntmBeenden);
		
		mnAnsicht = new JMenu("Ansicht");
		menuBar.add(mnAnsicht);
		
		mntmZoom = new JMenuItem("Einzoomen");
		mntmZoom.setIcon(new ImageIcon(WordPad.class.getResource("/icons/general/ZoomIn16.gif")));
		mntmZoom.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_PLUS, InputEvent.CTRL_MASK));
		mntmZoom.addActionListener(new ZoomListener(2, this));
		mnAnsicht.add(mntmZoom);
		
		mntmZoom_1 = new JMenuItem("Auszoomen");
		mntmZoom_1.setIcon(new ImageIcon(WordPad.class.getResource("/icons/general/ZoomOut16.gif")));
		mntmZoom_1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_MINUS, InputEvent.CTRL_MASK));
		mntmZoom_1.addActionListener(new ZoomListener(-2, this));
		mnAnsicht.add(mntmZoom_1);
		
		separator_1 = new JSeparator();
		mnAnsicht.add(separator_1);
		
		mntmHintergrundfarbeAendern = new JMenuItem("Hintergrundfarbe aendern");
		mntmHintergrundfarbeAendern.addActionListener(new FarbeAendernListener(this, Farbenart.HINTERGRUNDFARBE));
		mnAnsicht.add(mntmHintergrundfarbeAendern);
		
		mntmTextfarbeVeraendern = new JMenuItem("Textfarbe veraendern");
		mntmTextfarbeVeraendern.addActionListener(new FarbeAendernListener(this, Farbenart.VORDERGRUNDFARBE));
		mnAnsicht.add(mntmTextfarbeVeraendern);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		// TOOLBAR
		toolBar = new JToolBar();
		contentPane.add(toolBar, BorderLayout.NORTH);
		
		btnNeu = new JButton("Neu");
		btnNeu.setIcon(new ImageIcon(WordPad.class.getResource("/icons/general/New24.gif")));
		btnNeu.setSelectedIcon(null);
		toolBar.add(btnNeu);
		btnNeu.addActionListener(new NeuesDokumentListener(this));
		
		btnOeffnen = new JButton("\u00D6ffnen");
		btnOeffnen.setIcon(new ImageIcon(WordPad.class.getResource("/icons/general/Open24.gif")));
		btnOeffnen.addActionListener(new NormalOeffnenListener(this));
		toolBar.add(btnOeffnen);
		
		btnSeriellOeffnen = new JButton("Seriell \u00D6ffnen");
		btnSeriellOeffnen.setIcon(new ImageIcon(WordPad.class.getResource("/icons/general/Open24.gif")));
		btnSeriellOeffnen.addActionListener(new SeriellOeffnenListener(this));
		toolBar.add(btnSeriellOeffnen);
		
		btnSpeichern = new JButton("Speichern");
		btnSpeichern.setIcon(new ImageIcon(WordPad.class.getResource("/icons/general/Save24.gif")));
		btnSpeichern.addActionListener(new NormalSpeichernListener(this));
		toolBar.add(btnSpeichern);
		
		btnSeriellSpeichern = new JButton("Seriell Speichern");
		btnSeriellSpeichern.setIcon(new ImageIcon(WordPad.class.getResource("/icons/general/Save24.gif")));
		btnSeriellSpeichern.addActionListener(new SeriellSpeichernListener(this));
		toolBar.add(btnSeriellSpeichern);
		
		separator_2 = new JSeparator();
		toolBar.add(separator_2);
		
		btnBeenden = new JButton("Beenden");
		btnBeenden.setIcon(new ImageIcon(WordPad.class.getResource("/icons/general/Stop24.gif")));
		btnBeenden.addActionListener(new ExitListener(this));
		toolBar.add(btnBeenden);
		
		// TOOLBAR ENDE
		
		panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		
		lblOpenedFile = new JLabel("Unbenannt");
		panel.add(lblOpenedFile);
		scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		textArea = new JTextArea();
		textArea.addKeyListener(new TextAreaKeyListener(this));
		textArea.setFont(new java.awt.Font("Monospaced", java.awt.Font.PLAIN, 13));
		scrollPane.setViewportView(textArea);
		
		contentPane.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{toolBar, scrollPane, textArea}));
	}
	
	/**
	 * 
	 * @return Die Haupt-JTextArea des WordPads (dort steht der ganze geschriebene/geladene Text drin)
	 */
	public JTextArea getTextArea() {
		return textArea;
	}
	
	/**
	 * 
	 * @return JFileChooser fuer Oeffnen/Speichern von Dateien
	 */
	public JFileChooser getFileChooser() {
		return fc;
	}
	
	public void setOpenedFileText(String text) {
		this.lblOpenedFile.setText(text);
	}
	
	/**
	 * Erhoeht/Verringert die Schriftgroesse des Textes in der Haupt-JTextArea
	 * @param zoomfactor Anzahl der Points, um die die Schriftgroesse verringert (Zahll negativ) oder erhoeht (Zahl positiv) werden soll
	 */
	public void increaseFontSize(float zoomfactor) {
		
		if((textArea.getFont().getSize() + zoomfactor < 100) && (textArea.getFont().getSize() + zoomfactor > 0)) {
			
			textArea.setFont(textArea.getFont().deriveFont(textArea.getFont().getSize() + zoomfactor));
			System.out.println("Es wurde auf " + textArea.getFont().getSize() + " gezoomt");
		}
	}

	/**
	 * Fuehrt die dispose Methode der Superklasse aus, nach einer Abfrage,
	 * ob das aktuelle Dokument gespeichert wurde
	 * Wenn nicht gespeichert wurde: Popup mit Abfrage
	 */
	@Override
	public void dispose() {
		if(wordpad.savedchanges) super.dispose();
		else {
			
			if(JOptionPane.showConfirmDialog(wordpad, "Sie haben die Aenderungen an der Datei noch nicht gespeichert. Moechten Sie trotzdem das Programm schliessen?",
					"Programm schliessen", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == 0) {
				
				super.dispose();
			}

		}
	}
}