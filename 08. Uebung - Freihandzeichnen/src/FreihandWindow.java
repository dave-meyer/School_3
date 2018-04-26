import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Das Window implementiert den MouseMotionListener. Immer wenn die Maus
 * gedragged wird, wird die aktuelle x,y-Position ermittelt und das
 * Windwo aufgefordert sich neu zu zeichnen (repaint). Dadurch wird an
 * der soeben ermittelten x,y-Position ein kleiner Kreis gezeichnet...
 * Viele kleine Kreise ergeben somit eine "dicke" Linie.
 * 
 * @author DI Martin Kampenhuber
 *
 */
public class FreihandWindow extends JWindow implements MouseMotionListener {
	private int x = -100; // Startkoordinaten des Kreises - zu beginn
	private int y = -100; // außerhalb des Panels, dass unsichtbar
	boolean firstpaint = true;

	public FreihandWindow() {
		this.addMouseMotionListener(this);
	}

	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		Color startColor = Color.red;
		Color endColor = Color.blue;
		GradientPaint gradient =
				new GradientPaint(70, 70, startColor, 300, 300, endColor);
        g2d.setPaint(gradient);
        if (firstpaint) {
           	g.fillRect(0, 0, this.getWidth(), this.getHeight());
           	firstpaint = false;
        }
        Color kreiscolor = Color.green;
        g.setColor(kreiscolor);
        // kleiner Kreis wird an aktueller x,y-Position gezeichnet
        g2d.fillOval(x, y, 20, 20);
	}

	/**
	 * Ermittlung der aktuellen x,y-Position und Aufforderung, dass sich
	 * Window neu zeichnet.
	 */
	public void mouseDragged(MouseEvent evt) {
		x = evt.getX();
		y = evt.getY();
		repaint();
	}

	public void mouseMoved(MouseEvent arg0) {
		// wird nicht benötigt
	}
} // END Class ZeichenPanel
