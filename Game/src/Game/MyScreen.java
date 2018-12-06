package Game;
import javax.swing.JFrame;
public class MyScreen extends JFrame {
/**
 * MyScreen basic window for game, inherits properties and methods from JFrame
 * @author cole.parsons
 * @since Oct. 9, 2018
 */
	
	/**
	 * MyScreen default constructor
	 * @param none, default constructor
	 */
	public MyScreen() {
		// This is a current instance, setters are mutoator methods which
		this.setSize(1000,800);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	public static void main(String [] args) {
		MyScreen screen = new MyScreen();
		MyCanvas canvas = new MyCanvas();
		screen.getContentPane().add(canvas);
	}
}

