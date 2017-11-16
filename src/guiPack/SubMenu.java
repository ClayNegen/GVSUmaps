package guiPack;

import java.awt.*;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.*;


public class SubMenu implements ActionListener {

	private JFrame subFrame = new JFrame();
	
	private JPanel mainPanel = new JPanel();
	
	private JLabel label;
	
	private JButton finalize = new JButton("Get Directions");
	
	private JButton signUp = new JButton("Sign up");
	
	private JButton signOn = new JButton("Sign On");
	
	private JComboBox choices;
	
	private GUI gui;
	
	private List<MapNode> options;

	private Vector<String> temp;
	
	
	public SubMenu(GUI instance, String type) {
		gui = instance;
		subFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		subFrame.setBounds(500, 100, 400, 200);;
		subFrame.setResizable(false);
		subFrame.setVisible(true);
		
		switch (type) {
        case "classes":  	
        	options = getUserClassList();
        	
        	for (int i = 0; i < options.size(); i++) {
        		temp.add(options.get(i).getNodeInfo());
        	}
        	
        	label = new JLabel("Please select a class to navigate to.");
        	choices = new JComboBox(temp);
    		initMainPanel();
        	break;
        case "favorites":	
        	options = null;
        	break;
        case "foods":		
        	options = null;
        	break;
        case "login":
        	options = null;
        	break;
        default: options = null;
                 break;
    }

    	initLoginPanel();
	}
	
	private List<MapNode> getUserClassList() {
			return gui.userController.loadedUser.getClassList();
		}

	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		SubMenu tempMenu;

		if (source == finalize) {
			try {
				gui.directionsController.reset();
			} catch (IOException e1){
			}
			gui.directionsController.getDirections(
					choices.getSelectedItem().toString(),"Alumni House");
			gui.reDrawMap();
			subFrame.dispose();
		}

		if (source == signUp) {
			String name = null;
			String pass = null;

			boolean flag = true;
			while (flag) {
				try {
					name = JOptionPane.showInputDialog(subFrame,
							"Enter the desired username");
					if (gui.userController.loadUser(name, null, 1)) {
						throw new Exception();
					}
					else if (name != null) {
						flag = false;
					}
				}
				catch (Exception k) {
					JOptionPane.showMessageDialog(subFrame,
							"Username is already taken");
				}
				
				if (flag == false) {
					while (pass == null || pass.equals("")) {
						pass = JOptionPane.showInputDialog(subFrame,
								"Enter the desired password");
					}
					try {
						gui.userController.newUser(name, pass);
					}
					catch (Exception l){
						
					}
					JOptionPane.showMessageDialog(subFrame,
							"Account for " + name + " has been created.");

				}
			}
		}

		if (source == signOn) {
			String name = null;
			String pass = null;

			boolean flag = true;
			while (flag) {
				try {
					name = JOptionPane.showInputDialog(subFrame,
							"Enter your username");
					pass = JOptionPane.showInputDialog(subFrame,
							"Enter your password");
					System.out.println(pass);
					System.out.println(name);
					if (!gui.userController.loadUser(name, pass, 0)) {
						throw new Exception();
					}
					else if (name != null) {
						flag = false;
					}
				}
				catch (Exception k) {
					JOptionPane.showMessageDialog(subFrame,
							"Either the username or password is inccorect");
				}
				if (flag == false) {
					try {
						gui.userController.loadUser(name, pass, 0);
						gui.enableButtons();
					}
					catch (Exception l){

					}
					JOptionPane.showMessageDialog(subFrame,
							"Succesful login " + name);

				}
			}
		}
	}

	private void initLoginPanel() {
		mainPanel.setLayout(new BorderLayout());
		mainPanel.setBackground(new Color(51, 51, 255));

		signUp.addActionListener(this);
		signOn.addActionListener(this);
		signUp.setBounds(50, 60, 100, 30);
		signOn.setBounds(250, 60, 100, 30);

		subFrame.add(signOn);
		subFrame.add(signUp);
		subFrame.add(gui.getLogo());
	}

	private void initMainPanel() {
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Serif", Font.BOLD, 16));

		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setBackground(Color.DARK_GRAY);

		Box testBox = new Box(BoxLayout.LINE_AXIS);
		testBox.setAlignmentX(Box.RIGHT_ALIGNMENT);
		Box testBox1 = new Box(BoxLayout.LINE_AXIS);
		testBox1.setAlignmentX(Box.RIGHT_ALIGNMENT);
		Box testBox2 = new Box(BoxLayout.LINE_AXIS);
		testBox2.setAlignmentX(Box.LEFT_ALIGNMENT);


		testBox.add(Box.createRigidArea(new Dimension(10, 1)));
		testBox.add(label);


		mainPanel.add(Box.createRigidArea(new Dimension(1, 6)));
		mainPanel.add(testBox);




		choices.setMaximumSize(new Dimension(200, 18));
		testBox1.add(choices);
		testBox1.add(Box.createRigidArea(new Dimension(32, 1)));



		mainPanel.add(Box.createRigidArea(new Dimension(1, 6)));	
		mainPanel.add(testBox1);

		testBox2.add(Box.createRigidArea(new Dimension(25, 1)));
		finalize.addActionListener(this);
		testBox2.add(finalize);




		mainPanel.add(Box.createRigidArea(new Dimension(1, 78)));
		mainPanel.add(testBox2);
		subFrame.add(mainPanel);
	}
}
