package windows;
import java.awt.*;
import java.util.*;

import javax.swing.*;

/**
 * MyImageWindow zeigt Zufallsbilder in einem JWindow an. 
 */
public final class MyImageWindow extends JWindow {
	
	private String pfad;
	private ArrayList<Image> bilder = new ArrayList<Image>();
	
	//Beim Konstruieren des Windows wird Pfad ermittelt und gemerkt
	//Weiters werden die im Verzeichnis vorhandenen Images in einer ArrayList gespeichert
	public MyImageWindow() {
		//Pfad ermitteln
		pfad = System.getProperty("user.dir") + "\\bin\\windows\\images\\";
		//Bilder in ArrayList aufnehmen
		for (int i = 1; i <= 4; i++) {
			String bildname = "Catzilla" + i + ".jpg";
			Image image = new ImageIcon(pfad + bildname).getImage();
			bilder.add(image);
		}
	}
	
	@Override
	public void paint(Graphics g) {
		Random zufi = new Random();
		int z = zufi.nextInt(4); 	// 0 bis 3
		Image image = bilder.get(z);
		g.drawImage(image,3,4,this);
	}
}
