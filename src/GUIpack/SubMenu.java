package GUIpack;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.*;
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
        	options = gui.getUserClassList();
        	mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        	
        	for (int i = 0; i < options.size(); i++) {
        		temp.add(options.get(i).getNodeInfo());
        	}
        	
        	label = new JLabel("Please select a class to navigate to.",
        			JLabel.LEFT);
        	choices = new JComboBox(temp);
        	
        	finalize.addActionListener(this);
        	
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
		mainPanel.setBackground(Color.DARK_GRAY);
		
		
		
		HorizontalBox b1 = new HorizontalBox();
		mainPanel.add(label);
		label.setAlignmentX(Component.LEFT_ALIGNMENT);
		
//		mainPanel.add(Box.createRigidArea(new Dimension(1, 6)));
//		
//		mainPanel.add(choices);
//		mainPanel.add(Box.createRigidArea(new Dimension(1, 90)));
//		mainPanel.add(finalize);
		
		subFrame.add(mainPanel);
	}

	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		SubMenu tempMenu;
		
		if (source == finalize) {
			System.out.println(choices.getSelectedItem());
		}
	}
	
	public void windowClosing(WindowEvent e) {
		gui.setVisibility(true);
		subFrame.dispose();
	}
}  
