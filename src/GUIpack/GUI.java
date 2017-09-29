package GUIpack;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class GUI extends JPanel implements ActionListener {
	JButton button = new JButton();

		public GUI() {
			JFrame frame = new JFrame("GVSU Maps");
			frame.setVisible(true);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setSize(300, 400);
			JPanel panel = new JPanel();
			panel.setLayout(new GridLayout(4, 3, 5, 10));
			
		}
		/************************************************************
		Main Method
		initializes and runs GUI
		************************************************************/
		public static void main(String[] args) {
			GUI gui = new GUI();
			gui.setVisible(true);

		}
		/************************************************************
		Action performed Method
		Declares what happens upon button presses
		************************************************************/
		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();

		}
}
