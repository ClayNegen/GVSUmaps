package GUIpack;
//Louis Sullivan Gui 2

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JList;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.awt.Component;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.SpringLayout;
import javax.swing.JEditorPane;
import javax.swing.JTextPane;

public class MapDirections {

	private JFrame frame;
	public static String IMG_PATH = "src/pictures/GVSU.jpg";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MapDirections window = new MapDirections();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MapDirections() {
		
		initialize();
		//Build google map 
		
		//https://maps.googleapis.com/maps/api/directions/json?origin=Toronto&destination=Montreal&key=
		
		//key
		//AIzaSyCUSXmNXJlNckYnzAiWVKPYiIeWud6bvYo
		
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JList list = new JList();
		
		DefaultListModel listModel = new DefaultListModel();
		
		// BufferedImage img = ImageIO.read(new File(IMG_PATH));
        // ImageIcon icon = new ImageIcon(img);
		
		//while (resultSet.next()) {
		String Defualt = ("Default");
		String student_Class1 = ("Math");
        String student_Class2 = ("Physics");
        String student_Class3 = ("CIS");

        
        //listModel.addElement("" + student_Class1 + " " + student_Class2 + " " + student_Class3);
        listModel.addElement("" + Defualt);
        listModel.addElement("" + student_Class1);
        listModel.addElement("" + student_Class2);
        listModel.addElement("" + student_Class3);
		//}
        list.setModel(listModel);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		
		JButton btnSubmit = new JButton("Submit");
		
		frame.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JEditorPane dtrpnEnterDestination = new JEditorPane();
		dtrpnEnterDestination.setText("Destination");
		frame.getContentPane().add(dtrpnEnterDestination);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		frame.getContentPane().add(horizontalStrut_1);
		frame.getContentPane().add(list);
		frame.getContentPane().add(horizontalStrut);
		frame.getContentPane().add(btnSubmit);
		frame.getContentPane().add(btnSubmit);
		
		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		frame.getContentPane().add(horizontalStrut_2);
		
		JTextPane txtpnPicture = new JTextPane();
		//txtpnPicture.setText("Picture");
		txtpnPicture.insertIcon ( new ImageIcon ( IMG_PATH ) );
		//txtpnPicture.set
		frame.getContentPane().add(txtpnPicture);
	}
}
