package GUIpack;

import java.awt.*;
import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.border.TitledBorder;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

import javax.swing.border.LineBorder;
import java.awt.Color;

import javax.imageio.ImageIO;

import java.awt.Font;
import java.awt.Canvas;

public class classSchedule extends JFrame {
	
	private static String [] n = new String[6] ;
	private static String [] p = new String[6] ;
	boolean isClicked = false;
	//public String getTxtpnEnterClass_1 ,getTxtpnEnterClass_2, getTxtpnEnterClass_3, getTxtpnEnterClass_4, getTxtpnEnterClass_5, getTxtpnEnterClass_6;

	//public String getClassRoom_1, getClassRoom_2, getClassRoom_3, getClassRoom_4, getClassRoom_5, getClassRoom_6;
	
	private JPanel contentPane;

	/**
	 * @PostConstruct
	 * @param args
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
		
		
		
		try {
			
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		}
		finally{
		
		}
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
		
		JLabel txtpnClassSchedule = new JLabel();
		
		txtpnClassSchedule.setBounds(231, 33, 133, 16);
		txtpnClassSchedule.setText("Class Schedule");
		panel.add(txtpnClassSchedule);
		
		
		
		JButton btnGetDirections = new JButton("Get Directions");
		btnGetDirections.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				setClassroomLoc(	
						 txtpnEnterClass_1.getText().toString(),
						
						txtpnEnterClass_2.getText(),
		
						txtpnEnterClass_3.getText(),
		
						 txtpnEnterClass_4.getText(),
		
						txtpnEnterClass_5.getText(),
		
						txtpnEnterClass_6.getText());
						
					
					
					setClassroomNames(classRoom_1.getSelectedItem().toString(),
					
						classRoom_2.getSelectedItem().toString(),
						
						classRoom_3.getSelectedItem().toString(),
						
						classRoom_4.getSelectedItem().toString(),
						
						classRoom_5.getSelectedItem().toString(),
						
						classRoom_6.getSelectedItem().toString());
				
				Classes classes = new Classes();
				classes.setVisible(true);
				
				dispose();
			}
		});
		btnGetDirections.setBounds(307, 469, 166, 29);
		panel.add(btnGetDirections);
		
		
		Panel panel_1 = new Panel();
		panel_1.setBounds(611, 5, 661, 550);
		
		contentPane.add(panel_1);
		
		ImageIcon IMG_PATH1 = new ImageIcon("src/gvsuMaps.jpg");
		
		JLabel label = new JLabel("", IMG_PATH1, JLabel.CENTER);
		
		panel_1.add(label, BorderLayout.CENTER);
			
	}
	/**
	 * 
	 * @return
	 */
	
	public void setClassroomNames(String  setTxtpnEnterClass_1 ,String  setTxtpnEnterClass_2 ,String  setTxtpnEnterClass_3 ,String  setTxtpnEnterClass_4 ,String  setTxtpnEnterClass_5 ,String  setTxtpnEnterClass_6) 
	{
		n[0] = setTxtpnEnterClass_1;
		n[1] = setTxtpnEnterClass_2;
		n[2] = setTxtpnEnterClass_3;
		n[3] = setTxtpnEnterClass_4;
		n[4] = setTxtpnEnterClass_5;
		n[5] = setTxtpnEnterClass_6;
		
	}
	public void setClassroomLoc(String setClassRoom_1, String setClassRoom_2, String setClassRoom_3, String setClassRoom_4, String setClassRoom_5, String setClassRoom_6) 
	{
		p[0] = setClassRoom_1;
		p[1] = setClassRoom_2;
		p[2] = setClassRoom_3;
		p[3] = setClassRoom_4;
		p[4] = setClassRoom_5;
		p[5] = setClassRoom_6;
	}
	
	public String[] getClassroomNames() 
	{
		return p;	
	}
	/**
	 * 
	 * @return getClassroomLocation
	 */
	
	public String[] getClassroomLocation() 
	{
		return  n;
				
	}
}
