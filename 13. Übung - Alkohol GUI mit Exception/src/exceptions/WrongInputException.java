package exceptions;
import javax.swing.*;
import view.*;

public class WrongInputException extends Exception {
	public WrongInputException(String error) {
		JOptionPane.showMessageDialog(null, 
				  error, 
				  "E R R O R", 
				  JOptionPane.ERROR_MESSAGE);
	}
}
