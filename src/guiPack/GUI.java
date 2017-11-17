package guiPack;
import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


/*******************************************************************************
 *
 * 
 * @author Douglas Wallin
 ******************************************************************************/
public class GUI extends JPanel implements ActionListener {

	/** Controller to talk to the model */
	public EngineController directionsController;

	/** Controller to handle User information */
	public UserController userController;

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

	/** JButton to take the user to a settings submenu */
	private JButton settings = new JButton("Settings");

	/** JButton to allow to user to log in or create an account */
	private JButton loadUser = new JButton("Sign In/Create Account");

	/** JFrame to display the application */
	private JFrame frame;

	Boolean THREEFRAME, JButtonClassesFrame;
	JButton three = new JButton("Update Information");
	JLabel blank1 = new JLabel("");
	JLabel blank2 = new JLabel("");
	JLabel blank3 = new JLabel("");

	/***************************************************************************
	 * Constructor for our GUI. Loads the user into the mainMenu
	 * 
	 * @throws IOException
	 **************************************************************************/
	public GUI() throws IOException  {
		this.initBorderLayoutEast();
		directionsController = new EngineController(ImageIO.read
				(new File("src/gvsuMaps.jpg")));
		userController = new UserController();


		background.setLayout(new BorderLayout());
		background.setBackground(Color.DARK_GRAY);

		frame = initFrame();
		frame.setVisible(true);
		disableButtons();
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
			//classSchedule ClassSchedule = new classSchedule();

			//ClassSchedule.setVisible(true);
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
		
		if (source == loadUser) {
			tempMenu = new SubMenu(this, "login");
			
		}
	}

	public void setVisibility(boolean flag) {
		frame.setVisible(flag);
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

		JLabel westLabel = new JLabel(new ImageIcon(
				directionsController.getImage()));
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
		eastPanel.setLayout(new GridLayout(6, 1, 0, 3));
		eastPanel.setBackground(Color.DARK_GRAY);

		BufferedImage logo = ImageIO.read(new File("src/GVMaps.png"));
		gvLogo = new JLabel(new ImageIcon(getScaledImage
				(logo, 245, 126)));


		Box userSettingsBox = new Box(BoxLayout.Y_AXIS);

		loadUser.setAlignmentX(CENTER_ALIGNMENT);
		settings.setAlignmentX(CENTER_ALIGNMENT);
		loadUser.setMaximumSize(new Dimension(180, 30));
		settings.setMaximumSize(new Dimension(180, 30));

		userSettingsBox.add(Box.createRigidArea(new Dimension(0, 30)));
		userSettingsBox.add(settings);
		userSettingsBox.add(Box.createRigidArea(new Dimension(0, 5)));
		userSettingsBox.add(loadUser);

		classes.addActionListener(this);
		settings.addActionListener(this);
		loadUser.addActionListener(this);

		eastPanel.add(gvLogo);
		eastPanel.add(classes);
		eastPanel.add(foods);
		eastPanel.add(busStops);
		eastPanel.add(favorites);
		eastPanel.add(userSettingsBox);
	}

	/***************************************************************************
	 * Method to be called in any frame (Submenu) that gets directions between
	 * two places
	 **************************************************************************/
	public void reDrawMap() {
		JPanel temp = new JPanel();

		background.remove(0);

		JLabel imgHolder = new JLabel(new ImageIcon(
				directionsController.getImage()));

		temp.add(imgHolder);
		background.add(temp, 0);
	}

//	public class Info extends JPanel implements ActionListener{
//		JLabel Class1 = new JLabel("Class One:");
//		JTextArea Class01 = new JTextArea("");
//		JLabel Class2 = new JLabel("Class Two:");
//		JTextArea Class02 = new JTextArea("");
//		JLabel Class3 = new JLabel("Class Three:");
//		JTextArea Class03 = new JTextArea("");
//		JButton submit = new JButton("Submit");
//		JLabel blank1 = new JLabel();
//
//		/**
//		 * Creates update information Jframe 
//		 */
//		public Info(){
//			JFrame frame = new JFrame("Your Information");
//			frame.setVisible(true);
//			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//			frame.setSize(300, 400);
//			JPanel panel = new JPanel();
//			panel.setLayout(new GridLayout(4, 2, 5, 10));
//			panel.add(Class1);
//			panel.add(Class01);
//			panel.add(Class2);
//			panel.add(Class02);
//			panel.add(Class3);
//			panel.add(Class03);
//			panel.add(submit);
//			panel.add(blank1);
//			frame.add(panel);
//
//			submit.addActionListener(this);
//		}
//
	//		@Override
	//		public void actionPerformed(ActionEvent arg0) {
	//			// TODO Auto-generated method stub
	//			
	//		}
	//	}


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

	/***************************************************************************
	 * @return gvLogo
	 **************************************************************************/
	public JLabel getLogo() {
		return gvLogo;
	}

	/***************************************************************************
	 * Simply put, sets all buttons to be able to be clicked.
	 **************************************************************************/
	public void enableButtons() {
		classes.setEnabled(true);
		busStops.setEnabled(true);
		foods.setEnabled(true);
		favorites.setEnabled(true);
	}

	/***************************************************************************
	 * Sets all buttons to be disabled (unclickable)
	 **************************************************************************/
	public void disableButtons() {
		classes.setEnabled(false);
		busStops.setEnabled(false);
		foods.setEnabled(false);
		favorites.setEnabled(false);
	}
}
