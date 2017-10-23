package GUIpack;
import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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
	
	public static String IMG_PATH = "src/gvsuMaps.jpg";
	public static String IMG_PATH1 = "src/GVmaps.png";
	
		public GUI() throws IOException {
			BufferedImage img = ImageIO.read(new File(IMG_PATH));
	        ImageIcon icon = new ImageIcon(img);
	        BufferedImage logo = ImageIO.read(new File(IMG_PATH1));
	        ImageIcon logoIcon = new ImageIcon(logo);
			
	        JFrame frame = new JFrame("GVSU Maps");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setSize(688, 600);
			frame.setVisible(true);
			
			JPanel panel = new JPanel();
			panel.setLayout(new BorderLayout());
			panel.setOpaque(true);
			panel.setBackground(Color.blue);
			
			JPanel items = new JPanel();
			items.setLayout(new GridLayout(4, 1, 5, 5));
			items.setOpaque(true);
			items.setBackground(Color.blue);
			JTextPane logoPicture = new JTextPane();
			logoPicture.insertIcon ( new ImageIcon ( getScaledImage(logo, 245, 126) ) );
			items.add(logoPicture);
			items.add(one);
			items.add(two);
			items.add(three);
			one.setFont(new Font("Sans Serrif", Font.BOLD, 25));
			two.setFont(new Font("Sans Serrif", Font.BOLD, 25));
			three.setFont(new Font("Sans Serrif", Font.BOLD, 25));
			panel.add(items, BorderLayout.EAST);
			
			JTextPane txtpnPicture = new JTextPane();
			txtpnPicture.insertIcon ( new ImageIcon ( IMG_PATH ) );
			panel.add(txtpnPicture, BorderLayout.WEST);
			
			three.addActionListener(this);
			frame.add(panel);
		}

		/************************************************************
		Main Method
		initializes and runs GUI
		 * @throws IOException 
		************************************************************/
		public static void main(String[] args) throws IOException {
			GUI gui = new GUI();
			gui.setVisible(true);
			
			//Some preliminary code to test the algorithim
			//ignore for now
			List<MapNode> nodes = new ArrayList<MapNode>();
			
//			for(int b = 0; b < 5; b++){
//				MapNode location = new MapNode(0, b);
//				MapNode location2 = new MapNode(1, b);
//				MapNode location3 = new MapNode(2, b);
//				nodes.add(location);
//				nodes.add(location2);
//				nodes.add(location3);
//			}
//			
//			
//			GVSUMap testMap = new GVSUMap(nodes);
//			testMap.addLane("00", "12");
//			testMap.addLane("00", "03");
//			testMap.addLane("00", "22");
//			testMap.addLane("00", "02");
//			testMap.addLane("03", "24");
//			testMap.addLane("11", "24");
//			testMap.addLane("12", "21");
//			testMap.addLane("11", "10");
//			testMap.addLane("04", "12");
//			
//			testMap.addLane("00", "01");
//			testMap.addLane("01", "11");
//			
//			DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(testMap);
//	        dijkstra.execute(nodes.get(0));
//	        LinkedList<MapNode> path = dijkstra.getPath(nodes.get(4));
//
//	        for (MapNode node : path) {
//	            System.out.println(node);
//	        }
//			
//			System.out.println("Got this far");
		}
			System.out.println("Doing good Captain");
		}
		/************************************************************
		Action performed Method
		Declares what happens upon button presses
		************************************************************/
		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();
			Info info = new Info();
			info.setVisible(false);
			
			if (source == three){
				info.setVisible(true);
			}
		} 
		
		public class Info extends JPanel implements ActionListener{
			JLabel Class1 = new JLabel("Class One:");
			JTextArea Class01 = new JTextArea("");
			JLabel Class2 = new JLabel("Class Two:");
			JTextArea Class02 = new JTextArea("");
			JLabel Class3 = new JLabel("Class Three:");
			JTextArea Class03 = new JTextArea("");
			JLabel Class4 = new JLabel("Class Four:");
			JTextArea Class04 = new JTextArea("");
			JLabel Class5 = new JLabel("Class Five:");
			JTextArea Class05 = new JTextArea("");
			JLabel Class6 = new JLabel("Class Six:");
			JTextArea Class06 = new JTextArea("");
			JButton submit = new JButton("Submit");
			JLabel blank1 = new JLabel("Please Enter 3 Letter" + "\n Building abbreviation");
			
			
			public Info(){
				JFrame frame = new JFrame("Your Information");
				frame.setVisible(true);
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frame.setSize(300, 400);
				JPanel panel = new JPanel();
				panel.setLayout(new GridLayout(7, 2, 5, 10));
				panel.add(Class1);
				panel.add(Class01);
				panel.add(Class2);
				panel.add(Class02);
				panel.add(Class3);
				panel.add(Class03);
				panel.add(Class4);
				panel.add(Class04);
				panel.add(Class5);
				panel.add(Class05);
				panel.add(Class6);
				panel.add(Class06);
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
					MapNode location = new MapNode(0, 1);
					MapNode location2 = new MapNode(1, 2);
					MapNode location3 = new MapNode(2, 3);
					
					if (c1.length() > 0){
						Location first = new Location(location, location.getX(), location.getY());
						user.setClass1(first);
					}
					if (c2.length() > 0){
						Location second = new Location(location2, location2.getX(), location2.getY());
						user.setClass1(second);
					}
				}
			}
		}
		Image getScaledImage(Image Img, int wt, int ht) {
		    BufferedImage resizedImg = new BufferedImage(wt, ht, BufferedImage.TYPE_INT_ARGB);
		    Graphics2D g2 = resizedImg.createGraphics();

		    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		    g2.drawImage(Img, 0, 0, wt, ht, null);
		    g2.dispose();

		    return resizedImg;
		}
		}
