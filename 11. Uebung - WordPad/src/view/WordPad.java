package view;

import control.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;

/**
 * Die Klasse stellt einen einfachen Editor zur Verfügung.
 * Der eingegebene Text kann als Textform oder als Objekt gespeichert bzw.
 * eingelesen werden.
 * 
 * @author DI Martin Kampenhuber
 */
public class WordPad {

	private JFrame frame;
	private JMenuBar menuBar;
	private JMenu mnDatei;
	private JMenu mnffnen;
	private JMenuItem mntmTextdatei;
	private JMenuItem mntmSerialisiertesObjekt;
	private JMenu mnSpeichern;
	private JMenuItem mntmTextdatei_1;
	private JMenuItem mntmSerialisiertesObjekt_1;
	private JSeparator separator;
	private JMenuItem mntmWordpadBeenden;
	private JMenuItem mntmNeueDatei;
	private JScrollPane scrollPane;
	
	private JTextArea textArea;	//händisch hinzugefügt, da sich jTextArea
								//im WindowBuilder nicht mit jScrollPane
								//verbinden ließ	
	private JToolBar toolBar;
	private JButton button;
	private JButton button_1;
	private JButton button_2;
	private JButton button_3;
	private JButton button_4;
	private JSeparator separator_1;
	private JButton button_5;
	private File einstieg = new File("C:/KamM");	//Einstieg f. Filechooser
		
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WordPad window = new WordPad();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public WordPad() {
		initialize();
		textArea = new JTextArea();				//händisch hinzugefügt
		textArea.setLineWrap(true);				//händisch hinzugefügt
		scrollPane.setViewportView(textArea);	//händisch hinzugefügt
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 801, 603);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		mnDatei = new JMenu("Datei...");
		menuBar.add(mnDatei);
		
		mntmNeueDatei = new JMenuItem("Neue Datei");
		mntmNeueDatei.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
		mntmNeueDatei.addActionListener(new DateiNeuListener(this));
		mntmNeueDatei.setIcon(new ImageIcon(WordPad.class.getResource("/view/images/New16.gif")));
		mnDatei.add(mntmNeueDatei);
		
		mnffnen = new JMenu("\u00D6ffnen...");
		mnffnen.setIcon(new ImageIcon(WordPad.class.getResource("/view/images/Open16.gif")));
		mnDatei.add(mnffnen);
		
		mntmTextdatei = new JMenuItem("Textdatei");
		mntmTextdatei.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
		mntmTextdatei.addActionListener(new OpenTextListener(this));
		mnffnen.add(mntmTextdatei);
		
		mntmSerialisiertesObjekt = new JMenuItem("Serialisiertes Objekt");
		mntmSerialisiertesObjekt.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.ALT_MASK));
		mntmSerialisiertesObjekt.addActionListener(new OpenSerialListener(this));
		mnffnen.add(mntmSerialisiertesObjekt);
		
		mnSpeichern = new JMenu("Speichern...");
		mnSpeichern.setIcon(new ImageIcon(WordPad.class.getResource("/view/images/Save16.gif")));
		mnDatei.add(mnSpeichern);
		
		mntmTextdatei_1 = new JMenuItem("Textdatei");
		mntmTextdatei_1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		mntmTextdatei_1.addActionListener(new WriteTextListener(this));
		mnSpeichern.add(mntmTextdatei_1);
		
		mntmSerialisiertesObjekt_1 = new JMenuItem("Serialisiertes Objekt");
		mntmSerialisiertesObjekt_1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.ALT_MASK));
		mntmSerialisiertesObjekt_1.addActionListener(new WriteSerialListener(this));
		mnSpeichern.add(mntmSerialisiertesObjekt_1);
		
		separator = new JSeparator();
		mnDatei.add(separator);
		
		mntmWordpadBeenden = new JMenuItem("WordPad beenden");
		mntmWordpadBeenden.addActionListener(new BeendenListener());
		mntmWordpadBeenden.setIcon(new ImageIcon(WordPad.class.getResource("/view/images/Stop16.gif")));
		mnDatei.add(mntmWordpadBeenden);
		
		scrollPane = new JScrollPane();
		frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		toolBar = new JToolBar();
		scrollPane.setColumnHeaderView(toolBar);
		
		button_2 = new JButton("");
		button_2.addActionListener(new DateiNeuListener(this));
		button_2.setIcon(new ImageIcon(WordPad.class.getResource("/view/images/New24.gif")));
		toolBar.add(button_2);
		
		button = new JButton("");
		button.addActionListener(new OpenTextListener(this));
		button.setIcon(new ImageIcon(WordPad.class.getResource("/view/images/Open24.gif")));
		toolBar.add(button);
		
		button_1 = new JButton("");
		button_1.addActionListener(new OpenSerialListener(this));
		button_1.setIcon(new ImageIcon(WordPad.class.getResource("/view/images/Open24 - Kopie.gif")));
		toolBar.add(button_1);
		
		button_3 = new JButton("");
		button_3.addActionListener(new WriteTextListener(this));
		button_3.setIcon(new ImageIcon(WordPad.class.getResource("/view/images/Save24.gif")));
		toolBar.add(button_3);
		
		button_4 = new JButton("");
		button_4.addActionListener(new WriteSerialListener(this));
		button_4.setIcon(new ImageIcon(WordPad.class.getResource("/view/images/Save24 - Kopie.gif")));
		toolBar.add(button_4);
		
		separator_1 = new JSeparator();
		toolBar.add(separator_1);
		
		button_5 = new JButton("");
		button_5.addActionListener(new BeendenListener());
		button_5.setIcon(new ImageIcon(WordPad.class.getResource("/view/images/Stop24.gif")));
		toolBar.add(button_5);
	}

	//++++++++ benötigte Getter und Setter für Controler (= ext. Listenerklassen in control-package) +++++++++++++++++++

	public String getAreaText() {
		return textArea.getText();
	}
	
	public void setAreaText(String text) {
		textArea.setText(text);
	}
	
	public JFrame getFrame() {
		return frame;
	}
	
	/**
	 * Ein File-Objekt soll ausgewählt werden - zum Einlesen der entsprechenden
	 * Textdaten. 
	 * 
	 * @return das benötigte File-Objekt
	 */
	public File getFileToRead() {
		JFileChooser chooser = new JFileChooser(einstieg);
		File file = null;
		int retVal = chooser.showOpenDialog(frame);
		if (retVal == JFileChooser.APPROVE_OPTION) {
			file = chooser.getSelectedFile();
		}
		return file;
	}
	
	/**
	 * Ein File-Objekt soll ausgewählt werden - zum Schreiben eines Textes
	 * auf Festplatte. 
	 * 
	 * @return das benötigte File-Objekt
	 */
	public File getFileToWrite() {
		JFileChooser chooser = new JFileChooser(einstieg);
		File file = null;
		int retVal = chooser.showSaveDialog(frame);
		if (retVal == JFileChooser.APPROVE_OPTION) {
			file = chooser.getSelectedFile();
		}
		return file;
	}	
	
}
