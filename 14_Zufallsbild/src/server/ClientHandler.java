package server;

import java.awt.Image;
import java.io.*;
import java.net.Socket;

import javax.swing.ImageIcon;

/**
 * Ein @see Runnable fuer einen @see Thread
 * Ein Thread kuemmert sich um einen Client (Socket) 
 * und wartet, bis er ein Bild anfordert und 
 * sendet dann eines
 * 
 * @see Server
 * @author David Meyer
 * @version 0.7
 */
public class ClientHandler implements Runnable {

	boolean start = false;
	/** Socket fuer den Client */
	Socket socket;
	/** Sendet ImageIcons an den Client */
	ObjectOutputStream outputstream;
	/** Empfaengt Nachrichten vom Client */
	InputStreamReader inputstream;
	/** Empfaengt gepufferte Nachrichten (Requests) vom Client */
	BufferedReader reader;
	/** Der Server, fuer den dieser Handler arbeitet */
	Server server;
	
	/** Prefix (Praefix) der vor der Nachricht in der Konsole angezeigt wird wird */
	String prefix;
	
	/**
	 * Baut Streams zu den Socket/Client auf
	 * @param _socket der Socket zu dem Client
	 * @param _server Server von dem der Client empfangen wurde
	 */
	public ClientHandler(Socket _socket, Server _server) {
		
		Thread.currentThread().setName("Thread " + Server.getClientanzahl());
		prefix = "[" + Thread.currentThread().getName() + "] ";
		
		socket = _socket;
		server = _server;
		
		try {
			
			outputstream = new ObjectOutputStream(socket.getOutputStream());
			inputstream = new InputStreamReader(socket.getInputStream());
			reader = new BufferedReader(inputstream);
			System.out.println(prefix + "Streams zu Client " + socket.getLocalAddress() + " wurden aufgebaut");
			
			start = true;
		}
		catch (IOException e) {
			
			e.printStackTrace();
			System.out.println(prefix + "Streams zu Client " + socket.getLocalAddress() + " konnten nicht aufgebaut werden");
		}
	}
	
	@Override
	/**
	 * Vom Interface @see Runnable
	 * Wird 1x ausgefuehrt, wenn der Thread gestartet wird
	 * Es wartet durchgehend bis Client eine Nachricht sendet,
	 * dann antwortet der Server (dieser Thread / diese Methode) mit einem Zufallsbild
	 * und legt eine kurze Pause ein (eigentlich unnoetig?)
	 */
	public void run() {
		
		String line = "";
		
		while(start) {
			
			try {
				
				System.out.println(prefix + "Auf Nachricht vom Client " + socket.getLocalAddress() + " warten ...");
				
				// Ablauf bleibt stehen, bis Nachricht ankommt
				line = reader.readLine();
				System.out.println(prefix + "Client " + socket.getLocalAddress() + " fragt Bild an");
				
				
				ImageIcon bild = server.getZufallsbild();
				
				// Skalieren
				if(bild.getIconHeight() > 1080 && bild.getIconWidth() > 1920) {
					bild.setImage( bild.getImage().getScaledInstance(1920, 1080, Image.SCALE_SMOOTH));
				}
				
				outputstream.writeObject(bild);
				
				System.out.println(prefix + "Es wurde ein Bild dem Client gesendet");
				
				Thread.sleep(200);
			}
			catch(InterruptedException ex) {
				ex.printStackTrace();
			}
			catch(IOException ex) {
				ex.printStackTrace();
				System.out.println(prefix + "Verbindung zu " + socket.getLocalAddress() + " verloren");
				try {
					reader.close();
				} catch (IOException e) {
					
					e.printStackTrace();
				}
				start = false;
				return;
			}
		}
	}
}