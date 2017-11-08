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
import java.util.EventObject;
import java.util.LinkedList;
import java.util.List;

/**
 * 
 * @author Louis Sullivan
 * @author Clay Negen
 * @author Douglas Wallim
 */

/************************************************************
@category Main Method
@see initializes and runs GUI
 @throws IOException 
************************************************************/

/************************************************************
@see Action performed Method; Declares what happens upon button presses
************************************************************/
public class GUI extends JPanel //implements ActionListener 
{
	Boolean THREEFRAME, JButtonClassesFrame;
	
	

	public static void main(String[] args) throws IOException {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI gui = new GUI();
					gui.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
		
//		
//		DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(testMap);
//        dijkstra.execute(nodes.get(0));
//        LinkedList<MapNode> path = dijkstra.getPath(nodes.get(4));
//
//        for (MapNode node : path) {
//            System.out.println(node);
//        }
//		
		System.out.println("Doing good Captain");
	}
	
	
	
	
	JButton JButtonClasses = new JButton("Add Classes");
	JButton two = new JButton("Bus Stops");
	JButton three = new JButton("Update Information");
	JLabel blank1 = new JLabel("");
	JLabel blank2 = new JLabel("");
	JLabel blank3 = new JLabel("");
	User user = new User();
	
	public static String IMG_PATH = "src/gvsuMaps.jpg";
	public static String IMG_PATH1 = "src/GVMaps.png";
	
	/**
	 * @return 
	 * @see GUI creates an interactive user experiance that allows user to click between menu, classes, buss stops and update information
	 * @throws IOException
	 */
	public Boolean getResetTHREEFRAME(Boolean tmp) {
		THREEFRAME = tmp;
		return THREEFRAME;
	}
	
	public Boolean getResetJButtonClassesFrame(Boolean tmp) {
		JButtonClassesFrame = tmp;
		return JButtonClassesFrame;
	}
	
		public GUI() throws IOException {
			 
			
			
			
			BufferedImage img = ImageIO.read(new File(IMG_PATH));
	        ImageIcon icon = new ImageIcon(img);
	        BufferedImage logo = ImageIO.read(new File(IMG_PATH1));
	        ImageIcon logoIcon = new ImageIcon(logo);
			
	        JFrame frame = new JFrame("GVSU Maps");
	        
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	       
	        
			
			JPanel panel = new JPanel();
			frame.setBounds(680, 600, 1090, 1000);
			frame.setVisible(true);
			panel.setLayout(new BorderLayout());
			//panel.setOpaque(true);
			panel.setBackground(Color.blue);
			
			JPanel items = new JPanel();
			items.setLayout(new GridLayout(4, 1, 5, 5));
			items.setOpaque(true);
			items.setBackground(Color.blue);
			JTextPane logoPicture = new JTextPane();
			logoPicture.insertIcon ( new ImageIcon ( getScaledImage(logo, 245, 126) ) );
			items.add(logoPicture);
			items.add(JButtonClasses);
			items.add(two);
			items.add(three);
			panel.add(items, BorderLayout.EAST);
			
			/**
			 * Add action listener to JButtonClasses to run classSchedule GUI
			 */
			
			JTextPane txtpnPicture = new JTextPane();
			

			//testing early implementations of the line controller
			//to draw directions
			
			LineController controller = new LineController(img);
			MapNode _21 = new MapNode(557, 177, "21");
			MapNode _22 = new MapNode(564, 175, "22");
			MapNode _23 = new MapNode(566, 190, "23");
			MapNode _24 = new MapNode(573, 176, "24");
			
			controller.drawDiagonal(_21, _22);
			controller.drawDiagonal(_22, _23);
			controller.drawDiagonal(_22, _24);
			
			img = controller.getCanvas();

			//end testing segments
			
			
			txtpnPicture.insertIcon ( new ImageIcon ( img ) );
			panel.add(txtpnPicture, BorderLayout.WEST);
			
			//three.addActionListener(this);
			//JButtonClasses.addActionListener(this);
			//JButtonClasses.addActionListener(this);
			frame.add(panel);
		
		
		JButtonClasses.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				classSchedule ClassSchedule = new classSchedule();
				ClassSchedule.setVisible(true);
			}
		});
		
		three.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Info info = new Info();
				info.setVisible(true);
			}
		});
		
		
			
			
		
		/*public void actionPerformed(ActionEvent e) {
			
			*//**
			 * Using booleans so multiple button presses won't open more than 1 window
			 *//*
			
			THREEFRAME = true;
			JButtonClassesFrame = true;
			
			Object source = e.getSource();
			Info info = new Info();
			info.setVisible(false);
			
			
			
			
			if (THREEFRAME && source == three){
				info.setVisible(true);
				THREEFRAME = false;
				
			}*/
			
					
				
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
		/**
		 * 
		 * Creates the classes Jframe 
		 *
		 */
		public class Info extends JPanel implements ActionListener{
			JLabel Class1 = new JLabel("Class One:");
			JTextArea Class01 = new JTextArea("");
			JLabel Class2 = new JLabel("Class Two:");
			JTextArea Class02 = new JTextArea("");
			JLabel Class3 = new JLabel("Class Three:");
			JTextArea Class03 = new JTextArea("");
			JButton submit = new JButton("Submit");
			JLabel blank1 = new JLabel();
			
			/**
			 * Creates update information Jframe 
			 */
			public Info(){
				JFrame frame = new JFrame("Your Information");
				frame.setVisible(true);
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
			
			/**
			 * @param ActionEvent sets the location of classes in the class menu
			 */
			public void actionPerformed(ActionEvent e) {
				Object source = e.getSource();
				
				if (source == submit){
					String c1 = Class01.getText();
					String c2 = Class02.getText();
					if (c1.length() > 0){
						//Location first = new Location(c1, 1, 1);
						//user.setClass1(first);
					}
					if (c2.length() > 0){
						//Location second = new Location(c2, 1, 1);
						//user.setClass1(second);
					}
				}
			}
		}
		/**
		 * 
		 * @param Img
		 * @param wt
		 * @param ht
		 * @return Image 
		 */
		Image getScaledImage(Image Img, int wt, int ht) {
		    BufferedImage resizedImg = new BufferedImage(wt, ht, BufferedImage.TYPE_INT_ARGB);
		    Graphics2D g2 = resizedImg.createGraphics();

		    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		    g2.drawImage(Img, 0, 0, wt, ht, null);
		    g2.dispose();

		    return resizedImg;
		}
		}
