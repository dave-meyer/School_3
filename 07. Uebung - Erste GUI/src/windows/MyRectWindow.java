package windows;
import java.awt.*;
import javax.swing.*;

/**
 * MyRectWindow zeichnet ein Rechteck in ein JWindow.
 */
public final class MyRectWindow extends JWindow {

	@Override
	public void paint(Graphics g) {
		g.setColor(Color.orange);
		g.fillRect(20, 50, 100, 100);
	}
}
