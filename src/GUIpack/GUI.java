package GUIpack;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.text.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class GUI extends JPanel implements ActionListener {
	JButton one = new JButton("Classes");
	JButton two = new JButton("Bus Stops");
	JButton three = new JButton("Update Information");
	JLabel blank1 = new JLabel("");
	JLabel blank2 = new JLabel("");
	JLabel blank3 = new JLabel("");
	User user = new User();
	
	//Test Variables for testing djikstras algorithim before coding and 
	//loading the graph


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
			//GUI gui = new GUI();
			//gui.setVisible(true);
			
			//Some preliminary code to test the algorithim
			//ignore for now
			List<MapNode> nodes = new ArrayList<MapNode>();
			
			for(int b = 0; b < 5; b++){
				MapNode location = new MapNode(0, b);
				MapNode location2 = new MapNode(1, b);
				MapNode location3 = new MapNode(2, b);
				nodes.add(location);
				nodes.add(location2);
				nodes.add(location3);
			}
			
			
			GVSUMap testMap = new GVSUMap(nodes);
			testMap.addLane("00", "12");
			testMap.addLane("00", "03");
			testMap.addLane("00", "22");
			testMap.addLane("00", "02");
			testMap.addLane("03", "24");
			testMap.addLane("11", "24");
			testMap.addLane("12", "21");
			testMap.addLane("11", "10");
			testMap.addLane("04", "12");
			
			//testMap.addLane("00", "01");
			//testMap.addLane("01", "11");
			
			DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(testMap);
	        dijkstra.execute(nodes.get(0));
	        LinkedList<MapNode> path = dijkstra.getPath(nodes.get(4));

	        for (MapNode node : path) {
	            System.out.println(node);
	        }
			
			System.out.println("Got this far");
			
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
					String c1 = Class01.getText();
					String c2 = Class02.getText();
					if (c1.length() > 0){
						Location first = new Location(c1, 1, 1);
						user.setClass1(first);
					}
					if (c2.length() > 0){
						Location second = new Location(c2, 1, 1);
						user.setClass1(second);
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
