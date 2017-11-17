package guiPack;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.beans.Expression;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class Classes extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Classes frame = new Classes();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Classes() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(6, 6, 438, 266);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnDrawMap = new JButton("Draw Map");
		btnDrawMap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				dispose();
			}
		});
		btnDrawMap.setBounds(168, 178, 117, 29);
		panel.add(btnDrawMap);
		
		JTextPane txtpnYourClass = new JTextPane();
		txtpnYourClass.setText("Your location");
		txtpnYourClass.setBounds(6, 45, 93, 29);
		panel.add(txtpnYourClass);
		
		JTextPane txtpnYourBusStop = new JTextPane();
		txtpnYourBusStop.setText("Your classes");
		txtpnYourBusStop.setBounds(6, 98, 93, 29);
		panel.add(txtpnYourBusStop);
		String[] arrayClassschedule;
		
		
		classSchedule classschedule = new classSchedule();
		
		arrayClassschedule  = classschedule.getClassroomNames();
		
		 System.out.println("Success:  " +  classschedule.getClassroomNames()[0]);
		 System.out.println("Success:  " + classschedule.getClassroomLocation()[0]);
		
		String[] AllClassRooms = new  String[] {"Scroll to find a building","Alumni House", "Edward J. Frey LC", "Grace O. Kisler LC", "North C. LC", "Robert C. Pew LC",
				"William Kill Patrick LC", "Dale Stafford LC", "Max Swansin LC", "Seedman LC", 
				"Auther Hills LC", "William Packard LC", "Robert Kliener Commons", "Greg Olsin Kisler LC",
				 };
		
		
		
		JComboBox comboBox = new JComboBox(AllClassRooms);
		comboBox.setBounds(151, 45, 240, 29);
		panel.add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox(arrayClassschedule);
		comboBox_1.setBounds(151, 98, 240, 29);
		panel.add(comboBox_1);
	}
}
