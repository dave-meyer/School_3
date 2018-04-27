package server;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;

import client.Client;

/**
 * Server, der eingehenden Clients bei Anforderung
 * ein Zufaelliges Bild aus dem eingegebenen Bildverzeichnis sendet
 * 
 * Neue Clients werden einem neuen @see ClientHandler in einem neuen Thread zugewiesen,
 * der sich um den Client kuemmert
 * 
 * @version 0.7
 * @author David Meyer
 */
public class Server {
	
	public ServerSocket serversocket;
	/** Port-Nummer des Servers (auf der lokalen Adresse) */
	private int port;
	/** Lokales Verzeichnis, in dem die Bilder gespeichert sind */
	private String bildverzeichnis;
	boolean gestartet = false;
	/** Zum Lesen von Daten vom Client (werden aber noch vor dem Lesen gepuffert)*/
	InputStreamReader inputstream;
	/** Zum Lesen von gepuffertet Daten, die vom Client gesendet wurden */
	BufferedReader reader;
	/** Zum Senden von Objekten zu dem Client */
	ObjectOutputStream outputstream;
	File verzeichnis;
	/** Zufallsgenerator */
	private Random zufall;
	/** Alle Sockets, die mit den Clients verbunden sind */
	ArrayList<Socket> clientsockets = new ArrayList<Socket>();
	
	/** Anzahl der Clients, die sich in dieser gesamten Session an 
	 * allen Servern (falls mehrere vorhanden) eingeloggt haben
	 * (erster Client = 1)
	 * */
	private static int clientanzahl;
	
	/** Prefix (Praefix) der vor der Nachricht in der Konsole angezeigt wird wird */
	private String prefix;
	
	/**
	 * Erstellt einen neuen Server mit den Laufzeitparametern
	 * @param args Portnummer und Bildverzeichnis
	 */
	public static void main(String[] args) {
		
		clientanzahl = 0;
		
		Client.main(null);
		
		// Standardwerte vergeben
		int portnr = 0;
		String bildverzeichnis = "";
		
		// Falls ein falscher Port angegeben wurde -> Programm schliessen 
		// (noch bevor eine Serverinstanz erstellt werden konnte)
		try {
			
			portnr = Integer.parseInt((args[0]));
			bildverzeichnis = args[1];
		}
		catch(NumberFormatException ex) {
			
			ex.printStackTrace();
			System.exit(0);
		}
		
		Server server = new Server(portnr, bildverzeichnis);
		
	}
	
	/**
	 * Erstellt einen Serversocket (an den eingegebenen Port) 
	 * der dann durchgehend nach Clients sucht
	 * 
	 * @param _port Port-Nummer, an dem der Server auf der lokalen Adresse laufen soll
	 * @param _bildverzeichnis Verzeichnis, an dem alle Bilder, die an den Client gesendet werden sollen, gespeichert sind
	 */
	public Server(int _port, String _bildverzeichnis) {
		
		prefix = "[Server] ";
		this.port = _port;
		this.bildverzeichnis = _bildverzeichnis;
		
		try {
			
			serversocket = new ServerSocket(port);
			
			verzeichnis = new File(bildverzeichnis);
			zufall = new Random();
			
			gestartet = true;
			System.out.println(prefix + "++++++++++ Der Server wurde gestartet ++++++++++");
			System.out.println(prefix + "Verzeichnis: " + verzeichnis);
			System.out.println(prefix + verzeichnis.getAbsolutePath());
			System.out.println(prefix + verzeichnis.getPath());
			run();
		}
		catch (IOException e) {
			
			e.printStackTrace();
		}
	
	}
	
	/**
	 * ACHTUNG: nicht verwechseln mit Methode run des Interfaces @see Runnable
	 * Sucht durchgehend (wenn er ueberhaupt suchen soll) nach neuen Clients, die sich verbinden wollen
	 * Neue Clients werden einem neuen, eigenen Thread zugewiesen,
	 * der sich um sie kuemmert @see ClientHandler
	 */
	public void run() {
		
		Socket socket = null;
		
		while(gestartet) {
				
			try {
				System.out.println(prefix + "Warten auf weitere Clients ...");
				socket = serversocket.accept();
				System.out.println(prefix + "Ein neuer Client hat sich mit dem Server verbunden");
				
				Thread t = new Thread(new ClientHandler(socket, this));
				// TODO: wird clientsocket gebraucht?
				clientsockets.add(socket);
				clientanzahl++;
				t.start();
				
			}
			catch (IOException e) {
				e.printStackTrace();
			}

		}
		
	}
		
	/** Holt ein zufaelliges Bild aus dem angegebenen Verzeichnis des Servers
	 * Es werden nur Dateitypen bzw Dateinamenerweiterungen verwendet, die im @see BildFilter
	 * in der Liste iengetragen sind
	 * */
	public ImageIcon getZufallsbild() {
		
		try {
			
			System.out.println(prefix + verzeichnis.isDirectory());
			String[] dateinamen = verzeichnis.list(new BildFilter());
			int zufallszahl = zufall.nextInt(dateinamen.length);
			
			//TODO: grosse Bilder skalieren
			
			// URL als Pfad bsp: B://Meine Dateien//Bilder//
			String pfad = "File:///" + bildverzeichnis + dateinamen[zufallszahl];
			System.out.println(prefix + pfad);
			return new ImageIcon(new URL(pfad));
		}
		catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		}
	}

	/** Port-Nummer des Servers (auf der lokalen Adresse) */
	public int getPort() {
		return this.port;
	}
	
	/** Lokales Verzeichnis, in dem die Bilder gespeichert sind */
	public String getBildverzeichnis() {
		return this.bildverzeichnis;
	}
	
	/** Lokales Verzeichnis, in dem die Bilder gespeichert sind */
	public void setBildverzeichnis(String _bildverzeichnis) {

		this.bildverzeichnis = _bildverzeichnis;
	}
	
	/** Anzahl der Clients, die sich in dieser gesamten Session an 
	 * allen Servern (falls mehrere vorhanden) eingeloggt haben
	 * (erster Client = 1)
	 * */
	public static int getClientanzahl() {
		return clientanzahl;
	}

}