package GUIpack;

import java.awt.*;
import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.border.TitledBorder;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.border.LineBorder;
import java.awt.Color;

import javax.imageio.ImageIO;
import javax.swing.AbstractListModel;
import javax.swing.ImageIcon;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JTextPane;
import javax.swing.JLayeredPane;
import javax.swing.JInternalFrame;
import javax.swing.JToolBar;
import java.awt.Canvas;

public class classSchedule extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					classSchedule frame = new classSchedule();
					frame.setTitle("Class Schedule");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame for the GUI
	 * 
	 */
	public classSchedule()  {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1300, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JPanel panel = new JPanel();
		panel.setBounds(5, 5, 588, 550);
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		contentPane.add(panel);
		panel.setLayout(null);
		
		/**
		 * Add the classRooms to a string
		 * Classrooms string will be used to build the jcombobox
		 */
		
		String[] ClassRooms = new  String[] {"Scroll to find a building","Alumni House", "Edward J. Frey LC", "Grace O. Kisler LC", "North C. LC", "Robert C. Pew LC",
				"William Kill Patrick LC", "Dale Stafford LC", "Max Swansin LC", "Seedman LC", 
				"Auther Hills LC", "William Packard LC", "Robert Kliener Commons", "Greg Olsin Kisler LC",
				 };
		/** Add Class Text boxes to user information GUI
		 * 
		 */
		JTextPane txtpnEnterClass_1 = new JTextPane();
		txtpnEnterClass_1.setBounds(72, 89, 133, 27);
		txtpnEnterClass_1.setText("Enter Class Name");
		panel.add(txtpnEnterClass_1);
		
		JTextPane txtpnEnterClass_2 = new JTextPane();
		txtpnEnterClass_2.setBounds(72, 147, 133, 27);
		txtpnEnterClass_2.setText("Enter Class Name");
		panel.add(txtpnEnterClass_2);
		
		JTextPane txtpnEnterClass_3 = new JTextPane();
		txtpnEnterClass_3.setBounds(72, 209, 133, 27);
		txtpnEnterClass_3.setText("Enter Class Name");
		panel.add(txtpnEnterClass_3);
		
		JTextPane txtpnEnterClass_4 = new JTextPane();
		txtpnEnterClass_4.setBounds(72, 267, 133, 27);
		txtpnEnterClass_4.setText("Enter Class Name");
		panel.add(txtpnEnterClass_4);
		
		JTextPane txtpnEnterClass_5 = new JTextPane();
		txtpnEnterClass_5.setBounds(72, 327, 133, 27);
		txtpnEnterClass_5.setText("Enter Class Name");
		panel.add(txtpnEnterClass_5);
		
		JTextPane txtpnEnterClass_6 = new JTextPane();
		txtpnEnterClass_6.setBounds(72, 396, 133, 27);
		txtpnEnterClass_6.setText("Enter Class Name");
		panel.add(txtpnEnterClass_6);
		
		
		/**
		 *  Add JCombo Boxes to the user interface gui
		 */
		JComboBox classRoom_1 = new JComboBox(ClassRooms);
		classRoom_1.setBounds(344, 89, 213, 27);
		panel.add(classRoom_1);
		
		JComboBox classRoom_2 = new JComboBox(ClassRooms);
		classRoom_2.setBounds(344, 147, 213, 27);
		panel.add(classRoom_2);
		
		JComboBox classRoom_3 = new JComboBox(ClassRooms);
		classRoom_3.setBounds(344, 209, 213, 27);
		panel.add(classRoom_3);
		
		JComboBox classRoom_4 = new JComboBox(ClassRooms);
		classRoom_4.setBounds(344, 267, 213, 27);
		panel.add(classRoom_4);
		
		JComboBox classRoom_5 = new JComboBox(ClassRooms);
		classRoom_5.setBounds(344, 327, 213, 27);
		panel.add(classRoom_5);
		
		JComboBox classRoom_6 = new JComboBox(ClassRooms);
		classRoom_6.setBounds(344, 396, 213, 27);
		panel.add(classRoom_6);
		
		JTextPane txtpnClassSchedule = new JTextPane();
		txtpnClassSchedule.setBounds(231, 33, 103, 16);
		txtpnClassSchedule.setText("Class Schedule");
		panel.add(txtpnClassSchedule);
		
		JButton btnClass = new JButton("Save Classes");
		btnClass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String getTxtpnEnterClass_1 ,getTxtpnEnterClass_2, getTxtpnEnterClass_3, getTxtpnEnterClass_4, getTxtpnEnterClass_5, getTxtpnEnterClass_6;

				String getClassRoom_1, getClassRoom_2, getClassRoom_3, getClassRoom_4, getClassRoom_5, getClassRoom_6;
				
				getTxtpnEnterClass_1 = txtpnEnterClass_1.getText();
				
				getTxtpnEnterClass_2 = txtpnEnterClass_2.getText();

				getTxtpnEnterClass_3 = txtpnEnterClass_3.getText();

				getTxtpnEnterClass_4 = txtpnEnterClass_4.getText();

				getTxtpnEnterClass_5 = txtpnEnterClass_5.getText();

				getTxtpnEnterClass_6 = txtpnEnterClass_6.getText();

				getClassRoom_1 = classRoom_1.getSelectedItem().toString();
				
				getClassRoom_2 = classRoom_2.getSelectedItem().toString();
				
				getClassRoom_3 = classRoom_3.getSelectedItem().toString();
				
				getClassRoom_4 = classRoom_4.getSelectedItem().toString();
				
				getClassRoom_5 = classRoom_5.getSelectedItem().toString();
				
				getClassRoom_6 = classRoom_6.getSelectedItem().toString();
			}
		});
		
		btnClass.setBounds(231, 468, 117, 29);
		btnClass.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		btnClass.setForeground(Color.BLACK);
		panel.add(btnClass);
		
		
		Panel panel_1 = new Panel();
		panel_1.setBounds(611, 5, 661, 550);
		
		contentPane.add(panel_1);
		

		ImageIcon IMG_PATH1 = new ImageIcon("src/gvsuMaps.png");
		
		JLabel label = new JLabel("", IMG_PATH1, JLabel.CENTER);
		
		panel_1.add(label, BorderLayout.CENTER);
		
		
	
		
        
		
	}
}
