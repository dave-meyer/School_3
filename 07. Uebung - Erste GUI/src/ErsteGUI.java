
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

import windows.*;

/**
 * Die Klasse stellt einen JFrame mit 3 Buttons dar. Mit den
 * 3 Buttons werden JWindows angezeigt.
 *  
 * @author DI Martin Kampenhuber
 */
public class ErsteGUI implements ActionListener {

	JButton button1, button2, button3;
	
	public static void main(String[] args) {
		ErsteGUI gui = new ErsteGUI();
		gui.los();
	}
	
	public void los() {
		JFrame frame = new JFrame();
		frame.setLayout(new FlowLayout());
		
		button1 = new JButton("Quadrat zeichnen");
		button2 = new JButton("Bild darstellen");
		button3 = new JButton("Zufallskreis zeichnen");
		
		button1.addActionListener(this);
		button2.addActionListener(this);
		button3.addActionListener(this);
		
		frame.getContentPane().add(button1);
		frame.getContentPane().add(button2);
		frame.getContentPane().add(button3);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(450,70);
		frame.setLocation(400,300);
		frame.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent ae) {
		//Da in einer Klasse nur eine actionPerformed-Methode sein kann, muss 
		//abgefragt werden, welcher Button gedrückt wurde
		JButton gedruckt = (JButton)ae.getSource();
		if (gedruckt == button1) {
			// Es wird ein JWindow instanziiert und darin ein Quadrat gezeichnet.
			MyRectWindow window = new MyRectWindow();
			window.setSize(400,500);
			window.setVisible(true);
		}
		if (gedruckt == button2) {
			//Es wird ein JWindow instanziiert und darin ein Zufallsbilde angezeigt.
			MyImageWindow window = new MyImageWindow();
			window.setSize(400,500);
			window.setVisible(true);
		}
		if (gedruckt == button3) {
			// Es wird ein JWindow instanziiert und darin Kreis mit Zufallsfarbe gezeichnet.
			MyCircleWindow window = new  MyCircleWindow();
			window.setSize(400,500);
			window.setVisible(true);
		}
	}
}