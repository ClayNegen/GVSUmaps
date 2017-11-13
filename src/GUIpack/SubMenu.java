package GUIpack;

import java.awt.Color;
import java.awt.event.*;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;

public class SubMenu implements WindowListener {

	private JFrame subFrame;
	
	private GUI gui;
	
	private Map<MapNode, String> options;

	public SubMenu(GUI instance, String type) {
		gui = instance;
		
		switch (type) {
        case "classes":  	options = gui.getUserClassList();
        		subFrame = new JFrame("Select a Class");
        		break;
        case "favorites":	options = gui.getUserFavoriteList();
        default: options = null;
                 break;
    }
		
		instance.setVisibility(false);
		subFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		subFrame.setBounds(700, 100, 400, 200);
		subFrame.addWindowListener(this);
		subFrame.setResizable(false);
		subFrame.setVisible(true);
	}

	public void windowClosing(WindowEvent e) {
		gui.setVisibility(true);
		subFrame.dispose();
	}
}  
