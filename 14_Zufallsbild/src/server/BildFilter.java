package server;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;

/**
 * @version 0.7
 * @author David Meyer
 *
 */
public class BildFilter implements FilenameFilter {

	/**
	 * Zugelassene Dateitypen fuer Bilder, die an den Client gesendet werden sollen
	 */
	public static ArrayList<String> dateitypen;
	
	/** nur voreingestellte Dateitypen zulassen */
	private void dateitypenSetzen() {
		
		System.out.println("Typen werden gesetzt");
		 dateitypen = new ArrayList<String>();
		 dateitypen.add(".jpg");
		 dateitypen.add(".png");
		 dateitypen.add(".tiff");
		 dateitypen.add(".gif");
		 dateitypen.add(".GIF");
		 dateitypen.add(".bmp");
		 dateitypen.add(".JPG");
		 dateitypen.add(".JPEG");
		System.out.println("Typen wurden gesetzt");
	}
	
	/**
	 * Bereitet den BildFilter vor
	 */
	public BildFilter() {
		System.out.println("Neuer Bild Filter");
		dateitypenSetzen();
	}
	
	@Override
	/**
	 * Wird fuer jedes File bei der Methode list() aufgerufen
	 * Ermoeglicht nur zugelassenen Dateitypen als Bild versendet zu werden
	 * @param dir Dateipfad
	 * @param name Dateiname
	 * @return true wenn Datei aufgelistet werden soll, false wenn nicht
	 *
	 */
	public boolean accept(File dir, String name) {
		
		for(String typ : dateitypen) {
			
			name = name.toLowerCase();
			
			if(name.endsWith(typ)) {
				
				System.out.println("Ends with " + typ);
				return true;
			}
		}
		return false;
	}
}