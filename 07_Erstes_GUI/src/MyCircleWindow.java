import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.*;

/**
 * Diese Klasse ist ein JWindow, welches zwei Kreise mit zufaelliger Farbe
 * auf transparenten Hintergrund anzeigt
 * 
 * @author David Meyer
 * @version 1.0
 */
public class MyCircleWindow extends JWindow {

	/**
	 * Die paint Methode wird nicht vom Endbenutzer, sondern vom Programm 
	 * selbst ausgefuehrt, welches den Parameter uebergibt
	 * 
	 * @param g Ist eigentlich ein Graphics2D Objekt, mit welchen gezeichnet wird
	 */
	public void paint(Graphics g) {
		
		Random zufall = new Random();
		
		// Hintergrund transparent machen
		g.setColor(new Color(0, 0, 0, 0));
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		// Zufaellige Werte fuer Rot, Gruen und Blau erstellen und daraus eine Farbe kreieren
		Color farbe = new Color(zufall.nextInt(256), zufall.nextInt(256), zufall.nextInt(256));
		 
		// Farbe verwenden
		g.setColor(farbe);
		// Kreis erstellen
		g.fillOval(99, 182, 75, 75);
		g.fillOval(282, 186, 75, 75);
	}
}
