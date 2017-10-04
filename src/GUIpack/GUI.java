package GUIpack;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.text.*;
public class GUI extends JPanel implements ActionListener {
	JButton one = new JButton("Classes");
	JButton two = new JButton("Bus Stops");
	JButton three = new JButton("Update Information");
	JLabel blank1 = new JLabel("");
	JLabel blank2 = new JLabel("");
	JLabel blank3 = new JLabel("");

		public GUI() {
			JFrame frame = new JFrame("GVSU Maps");
			frame.setVisible(true);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setSize(300, 400);
			JPanel panel = new JPanel();
			panel.setLayout(new GridLayout(3, 2, 5, 10));
			panel.add(blank1);
			panel.add(one);
			panel.add(blank2);
			panel.add(two);
			panel.add(blank3);
			panel.add(three);
			frame.add(panel);
			three.addActionListener(this);
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
			Info info = new Info();
			
			if (source == three){
				info.setVisible(true);
			}
			/*if (source == Info.submit){
				String c1 = Info.Class1.getText();
				String c2 = Info.Class2.getText();
				if (c1.length() > 0){
					//update class 1
				}
				if (c2.length() > 0){
					//update class 2
				}
				info.setVisible(false);
			} */
		} 
		
		public class Info extends JPanel implements ActionListener{
			JLabel Class1 = new JLabel("Class One:");
			JTextArea Class01 = new JTextArea("");
			JLabel Class2 = new JLabel("Class Two:");
			JTextArea Class02 = new JTextArea("");
			JLabel Class3 = new JLabel("Class Three:");
			JTextArea Class03 = new JTextArea("");
			JButton submit = new JButton("Submit");
			JLabel blank1 = new JLabel();
			
			
			public Info(){
				JFrame frame = new JFrame("Your Information");
				frame.setVisible(true);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setSize(300, 400);
				JPanel panel = new JPanel();
				panel.setLayout(new GridLayout(4, 2, 5, 10));
				panel.add(Class1);
				panel.add(Class01);
				panel.add(Class2);
				panel.add(Class02);
				panel.add(Class3);
				panel.add(Class03);
				panel.add(submit);
				panel.add(blank1);
				frame.add(panel);
				
				submit.addActionListener(this);
			}
			public void actionPerformed(ActionEvent e) {
				Object source = e.getSource();
				
				if (source == submit){
					String c1 = Class1.getText();
					String c2 = Class2.getText();
					if (c1.length() > 0){
						//update class 1
					}
					if (c2.length() > 0){
						//update class 2
					}
					System.exit(0);
					//This closes everything. Idk how to make it only close
					//the information window. I think you have to use 
					//what i wrote above: info.setVisible(false)
					//but i can't call info from this class
					
					//GUI.info.setVisible(false);
				}
			}
		}
		}
