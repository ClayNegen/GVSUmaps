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
import java.util.Map;

public class GUI extends JPanel implements ActionListener {
	
	/** Controller to talk to the model */
	public DirectionsController controller;
	
	/** JPanel to represent a background to add all other components to */
	private JPanel background = new JPanel();
	
	/** JPanel to hold the logo and buttons to navigate to various sub-menus */
	private JPanel eastPanel = new JPanel();
	
	/** JLabel to hold the logo image */
	private JLabel gvLogo;
	
	/** JButton to take the user to a submenu to choose a class to navigate to */
	private JButton classes = new JButton("Go to Class");;

	/** JButton to take the user to a submenu to choose a food spot to navigate
	 * to */
	private JButton foods = new JButton("Find Food");
	
	/** JButton to take the user to a a page of favorites for quick access */
	private JButton favorites = new JButton("Favorites");
	
	/** JButton to take the user to a submenu to find bus stops */
	private JButton busStops = new JButton("Bus Stops");
	
	/** JFrame to display the application */
	private JFrame frame;
	
	private List<MapNode> userClassList;
	private Map<MapNode, String> userFavoriteList;
	private Map<MapNode, String> busStopList;
	
	
	
	
	
	
	
	Boolean THREEFRAME, JButtonClassesFrame;
	JButton three = new JButton("Update Information");
	JLabel blank1 = new JLabel("");
	JLabel blank2 = new JLabel("");
	JLabel blank3 = new JLabel("");
	User user = new User();
	
	/***************************************************************************
	 * Constructor for our GUI. Loads the user into the mainMenu
	 * 
	 * @throws IOException
	 **************************************************************************/
	public GUI() throws IOException  {
		this.initBorderLayoutEast();
		controller = new DirectionsController(ImageIO.read
				(new File("src/gvsuMaps.jpg")));
		
		background.setLayout(new BorderLayout());
		background.setBackground(Color.DARK_GRAY);
		
		frame = initFrame();
		frame.setVisible(true);
		
		//testing submenus by preloading some choices
		
		userClassList = new LinkedList<MapNode>();
		userClassList.add(controller.map.getNode("William Kill Patrick LC"));
		userClassList.add(controller.map.getNode("Edward J. Frey LC"));
		userClassList.add(controller.map.getNode("Robert Kliener Commons"));
	}
	
	/***************************************************************************
	 * Main method for testing of GUI
	 * 
	 * @param args
	 * @throws IOException
	 **************************************************************************/
	public static void main(String args[]) throws IOException {
		GUI gooy = new GUI();
	}
	
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		SubMenu tempMenu;
		
		if (source == classes){
			tempMenu = new SubMenu(this, "classes" );
		}
		
		if (source == foods){
			tempMenu = new SubMenu(this, "foods");
		}
		
		if (source == busStops){
			tempMenu = new SubMenu(this, "busStops");
		}
		
		if (source == favorites){
			tempMenu = new SubMenu(this, "favorites");
		}
	}
	
	public void setVisibility(boolean flag) {
		if(flag == true) {
			frame.setVisible(flag);
		}
		else {
			frame.setVisible(flag);
		}
	}
	
	public List<MapNode> getUserClassList() {
		return userClassList;
	}
	
	public Map<MapNode, String> getUserFavoriteList() {
		return userFavoriteList;
	}
	
	/***************************************************************************
	 * Method to do most of the legwork to open up the main menu; creates the
	 * frame, sets up bounds and other options, and adds neccesary panels.
	 * 
	 * @return
	 * @throws IOException
	 **************************************************************************/
	private JFrame initFrame() throws IOException {
		JFrame frame = new JFrame("GVSU Maps");
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 1050, 770);
		frame.setResizable(false);
		frame.setVisible(true);
		
		JLabel westLabel = new JLabel(new ImageIcon(controller.getImage()));
		background.add(westLabel, BorderLayout.WEST);		
		background.add(eastPanel, BorderLayout.EAST);
		frame.add(background);		
		
		return frame;
	}
	
	/***************************************************************************
	 * This creates and sets up the JPanel which holds all the buttons and 
	 * the GV logo
	 * 
	 * @throws IOException
	 **************************************************************************/
	private void initBorderLayoutEast() throws IOException {
		eastPanel.setLayout(new GridLayout(5, 1, 0, 3));
		eastPanel.setBackground(Color.DARK_GRAY);
		
		BufferedImage logo = ImageIO.read(new File("src/GVMaps.png"));
		gvLogo = new JLabel(new ImageIcon(getScaledImage
				(logo, 245, 126)));
		
		classes.addActionListener(this);
		
		eastPanel.add(gvLogo);
		eastPanel.add(classes);
		eastPanel.add(foods);
		eastPanel.add(busStops);
		eastPanel.add(favorites);
	}
	
	public void reDrawMap() {
		JPanel temp = new JPanel();
		
		background.remove(0);
		
		JLabel imgHolder = new JLabel(new ImageIcon(controller.getImage()));

		temp.add(imgHolder);
		background.add(temp, 0);
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
			}
		}
	}

	
	/***************************************************************************
	 * Private helper method to res-cale images in the GUI. Will be called in
	 * the ActionPerformed Method
	 * 
	 * @param img Image: The input image to be resized
	 * @param width int: The width in pixels desired for the output
	 * @param height int: The height in pixels desired for the output
	 * 
	 * @return Image: The resized image
	 **************************************************************************/
	private Image getScaledImage(Image img, int width, int height) {
		BufferedImage resizedImg = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = resizedImg.createGraphics();

		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.drawImage(img, 0, 0, width, height, null);
		g2.dispose();

		return resizedImg;
	}
}
