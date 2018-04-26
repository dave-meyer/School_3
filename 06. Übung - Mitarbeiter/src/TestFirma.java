import java.util.*;
import interfaces.*;
import unternehmen.*;

/**
 * Die Testklasse erzeugt verschiedene Mitarbeiter und gibt div. Eigenschaften
 * und Berechnungen am BS aus.
 * 
 * @author DI Martin Kampenhuber
 * @version 1.0
 */
public class TestFirma {
	
	public static void main(String args[]) {
		Arbeiter 		ar1, ar2;
		Buckler			bu1, bu2;
		Schlipstraeger	sl1, sl2;
		
		ar1 = new Arbeiter("Hugo", 10.0);
		ar2 = new Arbeiter("Maxi", 11.0);
		bu1 = new Buckler("Franzi", 2000.0);
		bu2 = new Buckler("Kurti", 2200.0);
		sl1 = new Schlipstraeger("Resi", 2500.0, "rot");
		sl2 = new Schlipstraeger("Rosi", 2500.0, "schwarz");
		
		ArrayList<IMitarbeiter> firma = new ArrayList<IMitarbeiter>();
		firma.add(ar1); firma.add(ar2);
		firma.add(bu1); firma.add(bu2);
		firma.add(sl1); firma.add(sl2);
		
		double summe = 0.0;
		for (IMitarbeiter ma : firma) {
			summe += ma.getMonatsauszahlung();
			System.out.println(ma);
		}
		System.out.println("monatliche Auszahlungssumme in der Firma: " + summe + "\n");
		
		ArrayList<IAngestellter> angestellte = new ArrayList<IAngestellter>();
		for (IMitarbeiter ma : firma) {
			if (ma instanceof IAngestellter) angestellte.add((IAngestellter)ma);
		}
		for (IAngestellter ang : angestellte) {
			//Mitarbeitermethoden zugreifbar machen....
			IMitarbeiter ma = (IMitarbeiter)ang; 
			System.out.println("Name: " + ma.getName() +
							   "\tSesselabwetztiefe: " + ang.getAbwetztiefe()); 
		}
	}
}
