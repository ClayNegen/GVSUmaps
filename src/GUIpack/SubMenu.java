package GUIpack;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.*;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SubMenu implements WindowListener, ActionListener {

	private JFrame subFrame = new JFrame();
	
	private JPanel mainPanel = new JPanel();
	
	private JLabel label;
	
	private JButton finalize = new JButton("Get Directions");
	
	private JComboBox choices;
	
	private GUI gui;
	
	private List<MapNode> options;
	
	private Vector<String> temp = new Vector<String>();

	
	
	public SubMenu(GUI instance, String type) {
		gui = instance;
		
		switch (type) {
        case "classes":  	
        	//options = gui.getUserClassList();
        	
        	for (int i = 0; i < options.size(); i++) {
        		temp.add(options.get(i).getNodeInfo());
        	}
        	
        	label = new JLabel("Please select a class to navigate to.");
        	choices = new JComboBox(temp);
        	break;
        case "favorites":	
        	options = null;
        	break;
        case "foods":		options = null;
        		break;
        default: options = null;
                 break;
    }
		
		
		instance.setVisibility(false);
		subFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		subFrame.setBounds(500, 100, 400, 200);
		subFrame.addWindowListener(this);
		subFrame.setResizable(false);
		subFrame.setVisible(true);
		
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Serif", Font.BOLD, 16));
		
		initMainPanel();
		subFrame.add(mainPanel);
	}

	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		SubMenu tempMenu;
		
		if (source == finalize) {
			System.out.println(choices.getSelectedItem());
			try {
				gui.directionsController.reset();
			} catch (IOException e1){
				System.out.println("Problem reseting controller during finalize"
						+ "button press");
			}
			gui.directionsController.getDirections(choices.getSelectedItem().toString(),
					"Alumni House");
			gui.reDrawMap();
			gui.setVisibility(true);
			subFrame.dispose();
		}
	}
	
	public void windowClosing(WindowEvent e) {
		gui.setVisibility(true);
		subFrame.dispose();
	}
	
	private void initMainPanel() {
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
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
}  
