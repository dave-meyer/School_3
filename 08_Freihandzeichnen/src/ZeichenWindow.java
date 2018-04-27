import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JWindow;

/**
 * In diesem Fenster kann man auf einem bunten Hintergrund mit Farbverlauf
 * mit gedrueckter Maustaste und bewegten Mauszeiger etwas mit schwarzer Farbe zeichnen
 * 
 * Hierbei werden ganz viele Kreise nebeneinander gezeichnet, wodurch es so aussieht,
 * als ob man richtig gezeichnet haette
 * 
 * Die Klasse implementiert das MouseMotionListener Interface
 * um MouseMotion Events empfangen zu koennen
 * 
 * Diese Klasse ist ein JWindow
 * 
 * @author David Meyer
 * @version 1.0
 */
public class ZeichenWindow extends JWindow implements MouseMotionListener {

	/**
	 * X Koordinate des zu plazierenden Kreises
	 * Ein Standardwert wird zugewiesen, der eigentlich nicht verwendet wird
	 */
	int x = 250;
	/**
	 * Y Koordinate des zu plazierenden Kreises 
	 * Ein Standardwert wird zugewiesen, der eigentlich nicht verwendet wird
	 */
	int y = 250;
	
	/**
	 * Laenge der zu zeichnenden Elypse
	 */
	int leange = 30;
	/**
	 * Breite der zu zeichnenden Elypse
	 */
	int breite = 30;
	
	/**
	 * Diese Variable sorgt dafuer, dass ein bestimmter Codeteil
	 * nur einmal ausgefuehrt wird
	 */
	boolean first = true;
	
	/**
	 * Zeichnet einen Hintergrund (Farbverlauf) und, wenn der Mauszeiger gedragged wurde, an der Stelle des Mauszeigers ein Kreis 
	 * und Listener werden konfiguriert
	 */
	public void paint(Graphics g) {
		
		// Wenn paint Methode zum ersten Mal ausgeführt wurde:
		if(first) {
			
			// Dieses Objekt in die MotionListener Liste der Maus eintragen
			// Dieses Objekt wird benachrichtigt, wenn eine MouseMotion ausgefuehrt wurde
			this.addMouseMotionListener((MouseMotionListener) this);
			
			// Farbverlauf erstellen
			GradientPaint gradient = new GradientPaint(0, 0, Color.orange, 500, 500, Color.cyan);
			
			// g ist ein  Graphics2D Objekt in einer Graphics Variable
			// Die Methode setPaint() braucht man um ein Gradient als Farbe einzustellen
			// Die Methode setPaint() gibt es nur im Graphics2D Objekt, nicht im Graphics Objekt
			((Graphics2D) g).setPaint(gradient);
			// Den Hintergrund (Rechteck) an der Stelle 0,0 mit den Massen des Fensters zeichnen
			g.fillRect(0, 0, this.getWidth(), this.getHeight());
			
			// Diesen Codeteil nicht mehr ausfuehren
			first = false;
		}
		else {
			
			// Kreis an gegebener Stelle mit gegebener Groesse erstellen
			g.fillOval(x, y, leange, breite);
		}
	}

	@Override
	/**
	 * Wird ausgefuehrt, wenn die Maustaste gedrueckt und der Mauszeiger bewegt wurde 
	 */
	public void mouseDragged(MouseEvent event) {
		
		// event.getXOnScreen holt die X-Koordinate von dem Mauszeiger
		// -leange/2 damit der Kreis genau an der Stelle des Mauszeigers erstellt wird
		// ( Die Positionsangabe fuer das Kreiszeichnen bezieht sich auf die "linke obere Ecke" des Kreises )
		x = event.getX() - leange/2;
		
		// event.getXOnScreen holt die Y-Koordiante von dem Mauszeiger
		// -leange/2 damit der Kreis genau an der Stelle des Mauszeigers erstellt wird
		// ( Die Positionsangabe fuer das Kreiszeichnen bezieht sich auf die "linke obere Ecke" des Kreises )
		y = event.getY() - breite/2;
		
		// Neuen Kreis zeichnen
		this.update(this.getGraphics());	
	}

	@Override
	/**
	 * Wird ausgefuehrt, wenn der Mauszeiger bewegt wurde
	 */
	public void mouseMoved(MouseEvent e) {
	}
	
}