import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

/**
 * Ein Panel, in dem ein roter Kreis gezeichnet ist,
 * welcher auf Knopfdruck von der linken oberen Ecke des Panels
 * in die rechte untere Ecke des Panels wandert, 
 * und beim naechsten Knopfdruck wieder andersherum
 * 
 * Der Knopf ist im dem Frame, in dem dieses Panel auch eingebettet ist
 * 
 * @author David Meyer 
 * @version 1.0
 */
public class KreisPanel extends JPanel {

	/**
	 * X Koordinate des zu zeichnenden Kreises
	 * Die Startkoordinate ist 20
	 */
	int x = 20;
	/**
	 * Y Koordinate des zu zeichnenden Kreises
	 * Die Startkoordinate ist 20
	 */
	int y = 20;
	
	/**
	 *  um diese Zahl wird die X und Y Koordinate pro Animationsschritt erhöht/vermindert
	 */
	int summand = 1;
	
	/**
	 * Ein roter Kreis wird auf schwarzem Hintergrund gezeichnet
	 */
	public void paint(Graphics g) {
		
		// schwarzer Hintergrund an Stelle 0,0 mit den Massen des Frames zeichnen
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		// Rote Farbe einstellen
		g.setColor(Color.red);
		// Kreis an gegebene Stelle zeichnen (Radius = 50)
		g.fillOval(x, y, 50, 50);
	}
	
	/**
	 * Hier wird der Kreis quer ueber das innere Fenster des Gesmatframes bewegt
	 * 
	 * 100 mal den Kreis um 1 nach links (oder rechts) und oben (oder unten) bewegen, 
	 * zwischendurch wird 50 ms lang gewartet, damit man die Animation auch sieht
	 */
	public void kreisAnimieren() {
		
		// Folgendes 100 mal wiederholen
		for(int i = 0; i <= 100; i++) {
			
			// X- und Y Koordinate incrementieren/decrementieren
			x += summand;
			y += summand;
			
			// gleiche wie this.repaint();
			this.update(this.getGraphics());
			
			// Versuchen, 50 ms lang nichts zu tun
			try {
				Thread.sleep(50);
			}
			// Falls es fehlschlagen sollte (interrupted), wird eine Fehlermeldung ausgegeben
			catch (InterruptedException e) {
				e.printStackTrace();
			}		
		}
		
		// Das Vorzeichen des Animationsschrittes umkehren => Richtung umkehren
		summand*=(-1);
				
	}
}