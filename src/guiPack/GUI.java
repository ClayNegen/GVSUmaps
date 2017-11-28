package guiPack;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


/*******************************************************************************
 * A graphical interface to interact with the application. Contains instances
 * of the DirectionsController model and the UserController model.
 * 
 * @author Douglas Wallin
 ******************************************************************************/
public class GUI extends JPanel implements ActionListener {

	/** Controller to talk to the model. */
	private EngineController directionsController;

	/** JPanel to represent a background to add all other components to. */
	private JPanel background = new JPanel();

	/** JPanel to hold the logo and buttons to navigate to various sub-menus. */
	private JPanel eastPanel = new JPanel();

	/** JLabel to hold the logo image. */
	private JLabel gvLogo;

	/** JButton to take the user to a submenu to choose a class to navigate 
	 * to. */
	private JButton classes = new JButton("Go to Class");;

	/** JButton to take the user to a submenu to choose a food spot to navigate
	 * to. */
	private JButton foods = new JButton("Find Food");

	/** JButton to take the user to a a page of favorites for quick access. */
	private JButton favorites = new JButton("Favorites");

	/** JButton to take the user to a submenu to find bus stops. */
	private JButton busStops = new JButton("Bus Stops");

	/** JButton to take the user to a settings submenu. */
	private JButton settings = new JButton("Settings");

	/** JButton to allow to user to log in or create an account. */
	private JButton loadUser = new JButton("Sign In/Create Account");

	/** JFrame to display the application. */
	private JFrame frame;
	
	/** List of Strings that represents the users saved classes */
	private String[] loadedClassList;
	
	/** Login screen Object */
	private LoginMenu tempMenu;

	/***************************************************************************
	 * Constructor for our GUI. Loads the user into the mainMenu
	 * 
	 * @throws IOException Exception
	 **************************************************************************/
	public GUI() throws IOException  {
		this.initBorderLayoutEast();
		directionsController = new EngineController(ImageIO.read(
				new File("src/gvsuMaps.jpg")));


		background.setLayout(new BorderLayout());
		background.setBackground(Color.DARK_GRAY);
		
		tempMenu = new LoginMenu();
		tempMenu.setVisibility(false);

		initFrame();
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
		System.out.println("Test1");
		
		Tuple<String, String> output;
		
		if (source == classes){
			output = selectClasses();

			try {
				directionsController.reset();
			} catch (IOException e1) {
			}

			directionsController.getDirections(output.getElement1(),
					output.getElemnt2());

			reDrawMap();
			System.out.println("Supposedly working, probs not though");
		}

		
		
		//the solution here is probably to reimplement a more basic submenu
		// concept with two 
		//actually no....i dont know
		if (source == loadUser) {
		}

		System.out.println("Test2");
	}

	/***************************************************************************
	 * Creates a JOptionPane for the user to enter classes.
	 * 
	 * @return Tuple<String, String>
	 **************************************************************************/
	public Tuple<String, String> selectClasses() {
		String[] sourceList = loadedClassList;
        String[] destinationList = loadedClassList;

        String source = (String) JOptionPane.showInputDialog(
        		frame,
        		"Please Select an origin class\n"
        				+ "Press Ok when finished",
        				"Directions",
        				JOptionPane.PLAIN_MESSAGE,
        				null,
        				sourceList,
        				null);

        String dest = (String) JOptionPane.showInputDialog(
        		frame,
        		"Please Select a destination class\n"
        				+ "Press Ok when finished",
        				"Directions",
        				JOptionPane.PLAIN_MESSAGE,
        				null,
        				destinationList,
        				null);
        Tuple<String, String> result = new Tuple<String, String>(source, dest);
        return result;
	}

	/***************************************************************************
	 * Method to do most of the legwork to open up the main menu; creates the
	 * frame, sets up bounds and other options, and adds necessary panels.
	 **************************************************************************/
	private void initFrame() {
		frame = new JFrame("GVSU Maps");

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 1050, 770);
		frame.setResizable(false);
		frame.setVisible(true);

		JLabel westLabel = new JLabel(new ImageIcon(
				directionsController.getImage()));
		background.add(westLabel, BorderLayout.WEST);		
		background.add(eastPanel, BorderLayout.EAST);
		frame.add(background);		
	}

	/***************************************************************************
	 * This creates and sets up the JPanel which holds all the buttons and 
	 * the GV logo.
	 * 
	 * @throws IOException Exception
	 **************************************************************************/
	private void initBorderLayoutEast() throws IOException {
		eastPanel.setLayout(new GridLayout(6, 1, 0, 3));
		eastPanel.setBackground(Color.DARK_GRAY);

		BufferedImage logo = ImageIO.read(new File("src/GVMaps.png"));
		gvLogo = new JLabel(new ImageIcon(getScaledImage(
				logo, 245, 126)));


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
	 * This method reDraws the map to reflect or not reflect directions
	 * depending on the state of the directionsController.
	 * @throws IOException 
	 **************************************************************************/
	public void reDrawMap() {
		JPanel temp = new JPanel();

		background.removeAll();

		JLabel imgHolder = new JLabel(new ImageIcon(
				directionsController.getImage()));
		
		temp.add(imgHolder);
		

		background.add(temp, BorderLayout.WEST);	
		background.add(eastPanel, BorderLayout.EAST);

		frame.getContentPane().removeAll();
		frame.getContentPane().add(background);
		frame.revalidate();
		frame.repaint();
	}

	/***************************************************************************
	 * Private helper method to re-scale images in the GUI. Will be called in
	 * the ActionPerformed Method
	 * 
	 * @param img Image: The input image to be resized
	 * @param width int: The width in pixels desired for the output
	 * @param height int: The height in pixels desired for the output
	 * 
	 * @return Image: The resized image
	 **************************************************************************/
	private Image getScaledImage(final Image img, final int width,
			final int height) {
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
	 * Simply put, sets all buttons to be able to be clicked.
	 **************************************************************************/
	public void enableButtons() {
		classes.setEnabled(true);
		busStops.setEnabled(true);
		foods.setEnabled(true);
		favorites.setEnabled(true);
	}

	/***************************************************************************
	 * Sets all buttons to be disabled (unclickable).
	 **************************************************************************/
	public void disableButtons() {
		classes.setEnabled(false);
		busStops.setEnabled(false);
		foods.setEnabled(false);
		favorites.setEnabled(false);
	}
}

