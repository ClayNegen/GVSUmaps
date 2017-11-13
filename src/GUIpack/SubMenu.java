package GUIpack;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.*;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SubMenu implements WindowListener {

	private JFrame subFrame = new JFrame();
	
	private JPanel mainPanel = new JPanel();
	
	private JLabel label;
	
	private JComboBox choices;
	
	private GUI gui;
	
	private List<MapNode> options;

	
	
	public SubMenu(GUI instance, String type) {
		gui = instance;
		
		switch (type) {
        case "classes":  	
        	options = gui.getUserClassList();
        	mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        	
        	Vector<String> temp = new Vector<String>();
        	
        	for (int i = 0; i < options.size(); i++) {
        		temp.add(options.get(i).getNodeInfo());
        	}
        	
        	label = new JLabel("Please select a class to navigate to");
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
		subFrame.setBounds(700, 100, 400, 200);
		subFrame.addWindowListener(this);
		subFrame.setResizable(false);
		subFrame.setVisible(true);
		
		//probably dont need this
		//choices.setPreferredSize();
		choices.setMaximumSize(new Dimension(120, 20));
		mainPanel.add(label);
		
		mainPanel.add(choices);
		subFrame.add(mainPanel);
	}

	
	public void windowClosing(WindowEvent e) {
		gui.setVisibility(true);
		subFrame.dispose();
	}
}  
