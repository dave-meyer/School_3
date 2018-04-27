import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.*;

/**
 * Diese Klasse ist ein JWindow, welches ein Zufallsbild
 * auf orangen Hintergrund 
 * @author David Meyer
 * @version 1.0
 *
 */
public class MyImageWindow extends JWindow {

	/**
	 * Die paint Methode wird nicht vom Endbenutzer, sondern vom Programm 
	 * selbst ausgefuehrt, welches den Parameter uebergibt
	 * 
	 * @param g Ist eigentlich ein Graphics2D Objekt, mit welchen gezeichnet wird
	 */
	public void paint(Graphics g) {
		
		/**
		 * Beinhaltet die Pfaede fuer die Zufallsbilder
		 */
		ArrayList<String> bilder = new ArrayList<String>();
		
		bilder.add("blobfish.jpg");
		bilder.add("typ.jpg");
		bilder.add("Sauerkraut_unofficial-Logo_weiss");
		bilder.add("Bear.png");
		bilder.add("optical-illusion.jpg");
		
		Random zufall = new Random();
		int zufallszahl = zufall.nextInt(bilder.size());
		
		g.setColor(Color.ORANGE);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		
		ImageIcon bild = new ImageIcon("src/Bilder/"+bilder.get(zufallszahl));
		g.drawImage(bild.getImage(), 0, 0, this);
		
		
		
	}
}
