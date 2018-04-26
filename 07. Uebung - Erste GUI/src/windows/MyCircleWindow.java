package windows;
import java.awt.*;
import javax.swing.*;

/**
 * MyCircleWindow zeichnet einen Kreis mit Zufallsfarbe. 
 */
public class MyCircleWindow extends JWindow {
	
	public void paint(Graphics g) {
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		int red = (int)(Math.random() * 255.0);
		int green = (int)(Math.random() * 255.0);
		int blue = (int)(Math.random() * 255.0);
		Color randomColor = new Color(red, green, blue);
		g.setColor(randomColor);
		g.fillOval(70, 70, 100, 100);
	}
}
