package client;
import java.net.Socket;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Ein Frame, der eine Verbindung zu einem Server aufbauen kann
 * und von dort Zufallsbilder anfordert, welche er dann an die Bildschirmgroesse
 * angepasst anzeigt
 * 
 * @author David Meyer
 * @version 0.7
 */
public class Client extends JFrame {

	Socket socket;
	PrintWriter writer;
	boolean bildabfragen = false;
	ObjectInputStream inputstream;
	
	private JPanel panelNorth;
	private JButton btnNaechstesBild;
	private JScrollPane scrollPane;
	private JPanel platzhalter_1;
	private JPanel platzhalter_2;
	private JLabel lblIPAdresse;
	private JLabel lblPort;
	private JTextField tfIPAdresse;
	private JTextField tfPort;
	private JLabel lblBild;
	
	/**
	 * Erstellt einen neuen Clienten
	 * @param args Laufzeitparameter - hier keine
	 */
	public static void main(String[] args) {
		
		new Client();
	}
	/** Frame soll aufgebaut werden */
	public Client() {
		
		initialize();
	}
	
	/**  Verbindet sich mit dem Server
	 * ueber die eingegebene IP Adresse und den eingegebenen Port
	 * und baut Streams auf
	 * */
	public void serverVerbinden() {
		
		try {
			
			// Neue Verbindung zum Server mit Daten aus den Textfeldern aufbauen
			System.out.println("Versuchen, sich mit " + tfIPAdresse.getText() + " auf dem Port " + tfPort.getText() + " zu verbinden");
			socket = new Socket(tfIPAdresse.getText(), Integer.parseInt(tfPort.getText()));
			
			System.out.println("Mit Server verbunden");
			writer = new PrintWriter(socket.getOutputStream());
			inputstream = new ObjectInputStream(socket.getInputStream());
			System.out.println("Streams wurden aufgebaut");
			bildabfragen = true;
			
		}
		catch (IOException ex) {
			
			ex.printStackTrace();
			JOptionPane.showMessageDialog(this, "Aufbauen der Verbindung zum Server ist fehlgeschlagen", "ERROR", JOptionPane.ERROR_MESSAGE, null);
		}
		catch(NumberFormatException ex) {
			
			ex.printStackTrace();
			JOptionPane.showMessageDialog(this, "Der eingegebene Port ist ungueltig", "ERROR", JOptionPane.ERROR_MESSAGE, null);
		}
	}
	
	/** Sendet Anfrage  */
	public void bildAnfragen() {
		
		System.out.println("Bild wird angefragt");
		writer.println("hallo");
		writer.flush();
		System.out.println("Bild wurde angefragt");
		
		ImageIcon bild = null;
		try {
			
			System.out.println("Auf Bild warten");
			bild = (ImageIcon) inputstream.readObject();
			
			System.out.println("Bild wurde empfangen");
			lblBild.setIcon(bild);
			bildAnpassen();
			
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
	
	/** 
	 * Falls das Bild zu groß fuer die aktuelle Framegroesse ist, wird es nach dem Bildverhaeltnis skaliert
	 * damit es nicht verzerrt wird
	 * ein wenig scrollen laesst sich bei dieser Variante nicht vermeiden, was auch nicht
	 * der Sinn von dem Skalieren waere (siehe Screenshot der Aufgabenstellung: es wird eine Scrollbar angezeigt,
	 * das heisst, dass das Bild nicht ganz in den Frame/scrollpane passt)
	 */
	public void bildAnpassen() {
		
		int hoehe = lblBild.getIcon().getIconHeight();
		int breite = lblBild.getIcon().getIconWidth();

		double verhaeltnis = ((double) breite)/((double) hoehe);
		
		// Wenn das Bild zu hoch ist, hoehe auf Maximalhoehe (hoehe des Scrollpanes) setzen
		// und Breite proportional anpassen (damit Bild nicht verzerrt wird)
		if(hoehe > scrollPane.getHeight()) {
			
			System.out.println("zu hoch");
			lblBild.setIcon(new ImageIcon( ((ImageIcon) lblBild.getIcon()).getImage().getScaledInstance((int) (verhaeltnis*scrollPane.getHeight()), (scrollPane.getHeight()-20), 0) ));
			System.out.println("Breite/Hoehe: " + verhaeltnis);
			System.out.println("Bild auf " + (int) (verhaeltnis*scrollPane.getHeight()) + " X " +  (scrollPane.getHeight()-20) + " skaliert");
		}
		// Wenn das Bild nicht zu hoch, sondern zu breit ist, Breite auf Maximalbreite (Breite des Scrollpanes) setzen
		// und Hoehe proportional anpassen (damit Bild nicht verzerrt wird)
		else if(breite > scrollPane.getWidth()) {
			
			System.out.println("zu breit");
			lblBild.setIcon(new ImageIcon( ((ImageIcon) lblBild.getIcon()).getImage().getScaledInstance((scrollPane.getWidth()-20), (int) (scrollPane.getWidth()/verhaeltnis), 0) ));
			System.out.println("Breite/Hoehe: " + verhaeltnis);
			System.out.println("Bild auf " + (scrollPane.getWidth()-20) + " X " +  (int) (scrollPane.getWidth()/verhaeltnis) + " skaliert");
		}
	}
	
	/** Erstellt Frame und baut GUI auf */
	private void initialize() {
		
		setBounds(50, 50, 700, 500);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout(0, 0));
		setTitle("Zufallsbilder");
		
		panelNorth = new JPanel();
		getContentPane().add(panelNorth, BorderLayout.NORTH);
		panelNorth.setLayout(new GridLayout(2, 3, 0, 0));
		
		lblIPAdresse = new JLabel("Server IP-Adresse:");
		lblIPAdresse.setHorizontalAlignment(SwingConstants.CENTER);
		lblIPAdresse.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelNorth.add(lblIPAdresse);
		
		tfIPAdresse = new JTextField();
		tfIPAdresse.setText("127.0.0.1");
		panelNorth.add(tfIPAdresse);
		tfIPAdresse.setColumns(10);
		
		platzhalter_1 = new JPanel();
		panelNorth.add(platzhalter_1);
		
		lblPort = new JLabel("Server Port:");
		lblPort.setHorizontalAlignment(SwingConstants.CENTER);
		lblPort.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelNorth.add(lblPort);
		
		tfPort = new JTextField();
		tfPort.setText("5555");
		panelNorth.add(tfPort);
		tfPort.setColumns(10);
		
		platzhalter_2 = new JPanel();
		panelNorth.add(platzhalter_2);
		
		btnNaechstesBild = new JButton("Naechstes Bild");
		btnNaechstesBild.addActionListener(new BtnNaechstesBildActionListener());
		btnNaechstesBild.setFont(new Font("Calibri", Font.PLAIN, 16));
		getContentPane().add(btnNaechstesBild, BorderLayout.SOUTH);
		
		lblBild = new JLabel();
		
		scrollPane = new JScrollPane(lblBild);
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		validate();
	}
	
	/** 
	 * Listener fuer den "Naechstes Bild" Button 
	 *  Zuerst stellt er eine Verbindung zum Server her, dann holt er immer wieder neue Bilder vom Server
	 */
	private class BtnNaechstesBildActionListener implements ActionListener {
		/**
		 * Wenn Knopf gedrueckt wird (und keine Verbindung zum Server besteht) Verbindung zum Server aufbauen,
		 * ansonsten neues Bild vom Server anfordern 
		 * */
		public void actionPerformed(ActionEvent arg0) {
			
			// TODO:
			if(socket == null) {
				serverVerbinden();
			}
			else {
				bildAnfragen();
			}
			
		}
	}
}