
import java.awt.*;
import javax.swing.*;

public class MeinZeichenPanel extends JPanel {

	private int x = 70, y = 70;

	public void doAnimation() {
		x = 70;
		y = 70;
		for (int i = 0; i < 130; i++) {
			x++;
			y++;
			//repaint() hat leider nicht genügt, das Panel muss 
			//explizit mit "update" neu gezeichnet werden.
			this.update(this.getGraphics());
			try {
				Thread.sleep(10);
			} catch (Exception ex) {
			}
		}
	}		
	
	public void paintComponent(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
        g.setColor(Color.green);
        g.fillOval(x, y, 40, 40);
     }
}
