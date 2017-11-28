package guiPack;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class LoginMenu implements ActionListener {

	private JFrame subFrame = new JFrame();

	private JPanel mainPanel = new JPanel();

	private JButton signUp = new JButton("Sign up");

	private JButton signOn = new JButton("Sign On");
	
	private UserController controller;
	
	private boolean hasUser = false;

	public LoginMenu() {
		subFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		subFrame.setBounds(500, 100, 400, 200);;
		subFrame.setResizable(false);
		subFrame.setVisible(true);

		try {
			controller = new UserController();
		} catch (Exception dontCare) {
		}

		try {
			initLoginPanel();
		} catch (IOException e) {
		}
	}	

	public void setVisibility(final boolean flag) {
		subFrame.setVisible(flag);
	}
	
	public boolean hasUser() {
		return hasUser;
	}
	
	public String[] getUserClassList() {
		return controller.getUserClassList();
	}
	
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();

		if (source == signUp) {
			String name = null;
			String pass = null;

			boolean flag = true;
			while (flag) {
				try {
					name = JOptionPane.showInputDialog(subFrame,
							"Enter the desired username");
					if (controller.loadUser(name, null, 1)) {
						throw new Exception();
					} else if (name != null) {
						flag = false;
					}
				} catch (Exception k) {
					JOptionPane.showMessageDialog(subFrame,
							"Username is already taken");
				}

				if (!flag) {
					while (pass == null || pass.equals("")) {
						pass = JOptionPane.showInputDialog(subFrame,
								"Enter the desired password");
					}
					try {
						controller.newUser(name, pass);
					} catch (Exception l) {
					}
					JOptionPane.showMessageDialog(subFrame,
							"Account for " + name + " has been created.");

				}
			}
		}

		if (source == signOn) {
			String name = null;
			String pass = null;

			boolean flag = true;
			while (flag) {
				try {
					name = JOptionPane.showInputDialog(subFrame,
							"Enter your username");
					pass = JOptionPane.showInputDialog(subFrame,
							"Enter your password");

					if (!controller.loadUser(name, pass, 0)) {
						throw new Exception();
					} else if (name != null) {
						flag = false;
					}
				} catch (Exception k) {
					JOptionPane.showMessageDialog(subFrame,
							"Either the username or password is inccorect");
				}
				if (!flag) {
					try {
						controller.loadUser(name, pass, 0);
						hasUser = true;
					} catch (Exception l) {
					}

					JOptionPane.showMessageDialog(subFrame,
							"Succesful login " + name);

				}
			}
		}
	}

	private void initLoginPanel() throws IOException {
		mainPanel.setLayout(new BorderLayout());
		mainPanel.setBackground(new Color(51, 51, 255));

		signUp.addActionListener(this);
		signOn.addActionListener(this);
		signUp.setBounds(50, 60, 100, 30);
		signOn.setBounds(250, 60, 100, 30);

		subFrame.add(signOn);
		subFrame.add(signUp);
		
		// This is probably going to break everything
		// TODO Make it not do that xD
		BufferedImage logo = ImageIO.read(new File("src/GVMaps.png"));
		
		subFrame.add(new JLabel(new ImageIcon(getScaledImage(logo, 245, 126))));
	}
	
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
}
