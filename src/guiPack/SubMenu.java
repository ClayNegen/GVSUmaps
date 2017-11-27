package guiPack;

import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SubMenu implements ActionListener {

	private JFrame subFrame = new JFrame();

	private JPanel mainPanel = new JPanel();

	private JButton signUp = new JButton("Sign up");

	private JButton signOn = new JButton("Sign On");

	private GUI gui;

	private Vector<String> selections = new Vector<String>();

	public SubMenu(GUI instance, String type) {
		gui = instance;
		subFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		subFrame.setBounds(500, 100, 400, 200);;
		subFrame.setResizable(false);
		subFrame.setVisible(true);


		initLoginPanel();
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
					if (gui.userController.loadUser(name, null, 1)) {
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
						gui.userController.newUser(name, pass);
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

					if (!gui.userController.loadUser(name, pass, 0)) {
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
						gui.userController.loadUser(name, pass, 0);
						gui.enableButtons();
					} catch (Exception l) {
					}

					JOptionPane.showMessageDialog(subFrame,
							"Succesful login " + name);

				}
			}
		}
	}

	private void initLoginPanel() {
		mainPanel.setLayout(new BorderLayout());
		mainPanel.setBackground(new Color(51, 51, 255));

		signUp.addActionListener(this);
		signOn.addActionListener(this);
		signUp.setBounds(50, 60, 100, 30);
		signOn.setBounds(250, 60, 100, 30);

		subFrame.add(signOn);
		subFrame.add(signUp);
		subFrame.add(gui.getLogo());
	}
}
