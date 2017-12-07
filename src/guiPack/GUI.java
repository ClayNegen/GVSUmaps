package guiPack;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;


/*******************************************************************************
 * A graphical interface to interact with the application. Contains instances
 * of the DirectionsController model and the UserController model.
 * 
 * @author Douglas Wallin
 ******************************************************************************/
@SuppressWarnings("serial")
public class GUI extends JPanel implements ActionListener {

	/** Controller to talk to the model. */
	public EngineController directionsController;

	/** JPanel to represent a background to add all other components to. */
	private JPanel background = new JPanel();

	/** JPanel to hold the logo and buttons to navigate to various sub-menus. */
	private JPanel eastPanel = new JPanel();

	/** JLabel to hold the logo image. */
	private JLabel gvLogo;

	/** JComboBox for the user to add classes to their favorites. */
	private JComboBox<String> addClasses;
	
	/** JButton to take the user to a submenu to choose a class to navigate 
	 * to. */
	private JButton classes = new JButton("All Classes");;

	/** JButton to take the user to a a page of favorites for quick access. */
	private JButton favorites = new JButton("Favorites");

	/** JButton to take the user to a settings submenu. */
	private JButton settings = new JButton("Settings");

	/** JButton to allow to user to log in or create an account. */
	private JButton loadUser = new JButton("Sign In/Create Account");
	
	/** JButton to reset the users saved classes. */
	private JButton resetClasses = new JButton("Reset Saved Classes");
	
	/** JButton to reset the list of users for the application. */
	private JButton resetUsers = new JButton("Reset Saved Users");
	
	/** JButton to add a class to the users saved classes. */
	private JButton addClass = new JButton("Add Class");

	/** JFrame to display the application. */
	private JFrame mainFrame;
	
	/** JFrame for the login menu. */
	private JFrame loginFrame = new JFrame();
	
	/** JFrame for the login menu. */
	private JFrame settingsFrame = new JFrame();
	
	/** List of Strings that represents the users saved classes. */
	private String[] loadedClassList;

	/** JButton to create an account. */
	private JButton signUp = new JButton("Sign up");

	/** JButton to login to an existing account. */
	private JButton signOn = new JButton("Sign On");
	
	/** Controller to be used in the login menu. */
	private UserController userController = new UserController();
	
	/** List of all buildings that can be navigated to. */
	private String[] allLocations;
	
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

		getAllLocations();
		
		initFrame();
		initLoginFrame();
		initSettingsFrame();
	
		mainFrame.setVisible(true);
		
		disableButtons();
	}

	/***************************************************************************
	 * Main method for testing of GUI.
	 * 
	 * @param args String[]: An array of input commands for main. Not yet used
	 * @throws IOException Exception
	 **************************************************************************/
	@SuppressWarnings("unused")
	public static void main(final String[] args) throws IOException {
		GUI gooy = new GUI();
		gooy.directionsController.drawAllLines();
		gooy.reDrawMap();
	}

	/***************************************************************************
	 * The action performed method is an override of a method to capture all
	 * button presses and other actions in the GUI.
	 * 
	 * @param event ActionEvent: The event captured
	 **************************************************************************/
	@Override
	public void actionPerformed(final ActionEvent event) {
		try {
			Object source = event.getSource();

			Tuple<String, String> output = null;

			if (source == classes) {
				output = selectClasses(allLocations);
		
				directionsController.reset();

				directionsController.getDirections(output.getElement1(),
						output.getElemnt2());

				reDrawMap();
			}
			
			if (source == favorites) {
				output = selectClasses(userController.getUserClassList());
				
				directionsController.reset();

				directionsController.getDirections(output.getElement1(),
						output.getElemnt2());

				reDrawMap();
			}

			if (source == loadUser) {
				loginFrame.setVisible(true);
				mainFrame.setVisible(false);
			}

			if (source == signUp) {
				String name = null;
				String pass = null;

				boolean validUserName = false;
				while (true) {
					name = JOptionPane.showInputDialog(loginFrame,
							"Enter the desired username");
					if (userController.loadUser(name, null, 1)) {
						JOptionPane.showMessageDialog(loginFrame,
								"Username is already taken");
					}  else if (name != null) {
						validUserName = true;
					}

					if (validUserName) {
						while (pass == null || pass.equals("")) {
							pass = JOptionPane.showInputDialog(loginFrame,
									"Enter the desired password");
						}

						userController.newUser(name, pass);

						JOptionPane.showMessageDialog(loginFrame,
								"Account for " + name + " has been created.");
						break;
					}
				}
			}

			if (source == signOn) {
				String name = null;
				String pass = null;

				boolean flag = true;
				while (flag) {
					name = JOptionPane.showInputDialog(loginFrame,
							"Enter your username");
					pass = JOptionPane.showInputDialog(loginFrame,
							"Enter your password");

					if (!userController.loadUser(name, pass, 0)) {
						JOptionPane.showMessageDialog(loginFrame,
								"Either the username or password is inccorect");

					} else if (name != null) {
						flag = false;
					}

					if (!flag) {
						userController.loadUser(name, pass, 0);

						JOptionPane.showMessageDialog(loginFrame,
								"Succesful login " + name);
						
						loginFrame.setVisible(false);
						mainFrame.setVisible(true);
						enableButtons();
						
						loadedClassList = userController.getUserClassList();
					}
				}
			}
			
			if (source == settings) {
				settingsFrame.setVisible(true);
				mainFrame.setVisible(false);
			}
			
			if (source == addClass) {
				String tempClass = addClasses.getSelectedItem().toString();
				boolean alreadyHas = false;
				
				for (String clss: loadedClassList) {
					if (clss.equals(tempClass)) {
						alreadyHas = true;
					}
				}
				
				if (!alreadyHas) {
					userController.addClass(tempClass);
					loadedClassList = userController.getUserClassList();
					JOptionPane.showMessageDialog(loginFrame,
							"Class Added: " + tempClass);
					mainFrame.setVisible(true);
					settingsFrame.setVisible(false);
					
				} else {
					JOptionPane.showMessageDialog(loginFrame,
							"You already have that class");
				}
				
			}
			
			if (source == resetClasses) {
				int confirm = JOptionPane.showOptionDialog(
			             null,
			             "Are you sure you want to reset your saved classes", 
			             "Reset Confirmation", JOptionPane.YES_NO_OPTION, 
			             JOptionPane.QUESTION_MESSAGE, null, null, null);
			        if (confirm == 0) {
			        	userController.resetClasses();
						mainFrame.setVisible(true);
						settingsFrame.setVisible(false);
						loadedClassList = userController.getUserClassList();
			        }
			}
			
			if (source == resetUsers) {
				int confirm = JOptionPane.showOptionDialog(
			             null,
			             "Are you sure you want to reset the user File (WARNING"
			             + " THIS WILL ERASE ALL USERS)", 
			             "Reset Confirmation", JOptionPane.YES_NO_OPTION, 
			             JOptionPane.QUESTION_MESSAGE, null, null, null);
			        if (confirm == 0) {
			        	userController.resetUsers();
						mainFrame.setVisible(true);
						settingsFrame.setVisible(false);
						disableButtons();
			        }
			}
		} catch (Exception iAcceptMyFateHere) {
		}
	}

	/***************************************************************************
	 * Creates a JOptionPane for the user to enter classes.
	 * 
	 * @return Tuple<String, String>
	 * @param selections String[]: The choices to be provided
	 **************************************************************************/
	public Tuple<String, String> selectClasses(final String[] selections) {
        String source = (String) JOptionPane.showInputDialog(
        		mainFrame,
        		"Please Select an origin class\n"
        				+ "Press Ok when finished",
        				"Directions",
        				JOptionPane.PLAIN_MESSAGE,
        				null,
        				selections,
        				null);

        
        if (source != null) {
        	String dest = (String) JOptionPane.showInputDialog(
        			mainFrame,
        			"Please Select a destination class\n"
        					+ "Press Ok when finished",
        					"Directions",
        					JOptionPane.PLAIN_MESSAGE,
        					null,
        					selections,
        					null);
        	Tuple<String, String> result =
        			new Tuple<String, String>(source, dest);
        	return result;
        } else {
        	return null;
        }
	}

	/***************************************************************************
	 * Method to do most of the legwork to open up the main menu; creates the
	 * frame, sets up bounds and other options, and adds necessary panels.
	 **************************************************************************/
	private void initFrame() {
		mainFrame = new JFrame("GVSU Maps");

		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setBounds(100, 100, 1050, 770);
		mainFrame.setResizable(false);
		mainFrame.setVisible(true);

		JLabel westLabel = new JLabel(new ImageIcon(
				directionsController.getImage()));
		background.add(westLabel, BorderLayout.WEST);		
		background.add(eastPanel, BorderLayout.EAST);
		mainFrame.add(background);		
	}

	/***************************************************************************
	 * This creates and sets up the JPanel which holds all the buttons and 
	 * the GV logo.
	 * 
	 * @throws IOException Exception
	 **************************************************************************/
	private void initBorderLayoutEast() throws IOException {
		eastPanel.setLayout(new GridLayout(4, 1, 0, 3));
		eastPanel.setBackground(Color.DARK_GRAY);

		BufferedImage logo = ImageIO.read(new File("src/GVMaps.png"));
		gvLogo = new JLabel(new ImageIcon(getScaledImage(
				logo, 245, 126)));


		Box userSettingsBox = new Box(BoxLayout.Y_AXIS);

		loadUser.setAlignmentX(CENTER_ALIGNMENT);
		settings.setAlignmentX(CENTER_ALIGNMENT);
		loadUser.setMaximumSize(new Dimension(180, 30));
		settings.setMaximumSize(new Dimension(180, 30));

		userSettingsBox.add(Box.createRigidArea(new Dimension(0, 50)));
		userSettingsBox.add(settings);
		userSettingsBox.add(Box.createRigidArea(new Dimension(0, 5)));
		userSettingsBox.add(loadUser);

		classes.addActionListener(this);
		favorites.addActionListener(this);
		
		settings.addActionListener(this);
		loadUser.addActionListener(this);

		eastPanel.add(gvLogo);
		eastPanel.add(classes);
		eastPanel.add(favorites);
		eastPanel.add(userSettingsBox);
	}
	
	/***************************************************************************
	 * This method initializes the login subFrame(loginFrame).
	 * 
	 * @throws IOException Exception
	 **************************************************************************/
	private void initLoginFrame() throws IOException {
		loginFrame.setDefaultCloseOperation(
				WindowConstants.DO_NOTHING_ON_CLOSE);
		loginFrame.setBounds(500, 100, 400, 200);
		loginFrame.setResizable(false);
		loginFrame.setVisible(false);

		signUp.addActionListener(this);
		signOn.addActionListener(this);
		signUp.setBounds(50, 60, 100, 30);
		signOn.setBounds(250, 60, 100, 30);

		loginFrame.add(signOn);
		loginFrame.add(signUp);
		
		BufferedImage logo = ImageIO.read(new File("src/GVMaps.png"));
		
		loginFrame.add(new JLabel(new ImageIcon(getScaledImage(
				logo, 245, 126))));
	}
	/***************************************************************************
	 * This method initializes the settings subFrame(settingsFrame).
	 * 
	 * @throws IOException Exception
	 **************************************************************************/
	private void initSettingsFrame() throws IOException {
		settingsFrame.setDefaultCloseOperation(
				WindowConstants.DO_NOTHING_ON_CLOSE);
		settingsFrame.setBounds(500, 100, 350, 240);
		settingsFrame.setResizable(false);
		settingsFrame.setVisible(false);
		
		addClasses = new JComboBox<>(allLocations);

		addClass.addActionListener(this);
		resetClasses.addActionListener(this);
		resetUsers.addActionListener(this);
		
		addClass.setBounds(20, 30, 160, 30);
		resetClasses.setBounds(20, 115, 160, 30);
		resetUsers.setBounds(20, 150, 160, 30);
		addClasses.setBounds(200, 30, 130, 20);

		settingsFrame.add(addClass);
		settingsFrame.add(addClasses);
		settingsFrame.add(resetUsers);
		settingsFrame.add(resetClasses);
		
		BufferedImage logo = ImageIO.read(new File("src/GVMaps.png"));
		
		
		settingsFrame.add(new JLabel(new ImageIcon(getScaledImage(
				logo, 245, 126))));
	}


	/***************************************************************************
	 * This method reDraws the map to reflect or not reflect directions
	 * depending on the state of the directionsController.
	 * @throws IOException 
	 **************************************************************************/
	public void reDrawMap() {
		mainFrame.getContentPane().removeAll();
		
		background.removeAll();

		JLabel tempImage = new JLabel(new ImageIcon(
				directionsController.getImage()));

		background.add(tempImage, BorderLayout.WEST);	
		background.add(eastPanel, BorderLayout.EAST);

		mainFrame.getContentPane().add(background);
		mainFrame.revalidate();
		mainFrame.repaint();
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
	private void enableButtons() {
		classes.setEnabled(true);
		favorites.setEnabled(true);
		addClass.setEnabled(true);
		settings.setEnabled(true);
	}

	/***************************************************************************
	 * Sets all buttons to be disabled.
	 **************************************************************************/
	private void disableButtons() {
		classes.setEnabled(false);
		favorites.setEnabled(false);
		addClass.setEnabled(false);
		settings.setEnabled(false);
	}
	
	/***************************************************************************
	 *TODO
	 **************************************************************************/
	private void getAllLocations() {
		List<MapNode> allNodes = directionsController.getNodeList();
		
		int count = 0;
		
		for (int i = 0; i < allNodes.size(); i++) {
			MapNode temp = allNodes.get(i);
			if (temp.getNodeInfo().length() > 3) {
				count++;
			}
		}
		
		allLocations = new String[count];
		
		count = 0;
		
		for (int i = 0; i < allNodes.size(); i++) {
			MapNode temp = allNodes.get(i);
			if (temp.getNodeInfo().length() > 3) {
				allLocations[count] = temp.getNodeInfo();
				count++;
			}
		}
	}
}

