import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JWindow;

/**
 * Diese Klasse ist ein JWindow, welches ein rotes Rechteck
 * auf transparenten Hintergrund anzeigt
 * 
 * @author David Meyer
 * @version 1.0
 */
public class MyRectWindow extends JWindow{

	/**
	 * Die paint Methode wird nicht vom Endbenutzer, sondern vom Programm 
	 * selbst ausgefuehrt, welches den Parameter uebergibt
	 * 
	 * @param g Ist eigentlich ein Graphics2D Objekt, mit welchen gezeichnet wird
	 */
	public void paint(Graphics g) {
		
		// Die Farbe 'rot' wird eingestellt
		g.setColor(Color.red);
		g.fillRect(5, 5, 100, 100);
	}
}