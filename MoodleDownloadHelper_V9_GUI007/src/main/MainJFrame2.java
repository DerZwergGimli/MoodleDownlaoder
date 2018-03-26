package main;

import javax.swing.JFrame;
import javax.swing.plaf.metal.MetalIconFactory;

import org.omg.CORBA.portable.BoxedValueHelper;

import objects.Login;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.*;

import javax.security.auth.Refreshable;
import javax.swing.*;

public class MainJFrame2 extends JFrame implements ActionListener

{
	static Login user;

	public static void main(String[] args) {
		
		// Create and set up a frame window
		String[] outputData = new String[3];
		JFrame.setDefaultLookAndFeelDecorated(false);
		JFrame frame = new JFrame("Layout");
		frame.setSize(500, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Define the panel to hold the components	
		JPanel panel = new JPanel();
		SpringLayout layout = new SpringLayout();
		
		JLabel label_username = new JLabel("Username: ");
		JLabel label_password = new JLabel("Password: ");
		JLabel label_moodleURL = new JLabel("Moodle-URL: ");
		JTextField textFiel_username = new JTextField("your Moodle Username", 15);
		JTextField textFiel_password = new JTextField("your Moodle Password", 15);
		JTextField textFiel_moodleURL = new JTextField("https://moodle.htwg-konstanz.de/moodle/index.php", 15);
		
		JButton button_login = new JButton("Login");
		button_login.setSize(60, 30);
		JButton button_exit = new JButton("Close");
		button_exit.setSize(60, 30);
		
		panel.setSize(300, 300);
		panel.setLayout(layout);
		panel.add(label_username);
		panel.add(label_password);
		panel.add(label_moodleURL);
		panel.add(textFiel_username);
		panel.add(textFiel_password);
		panel.add(textFiel_moodleURL);
		panel.add(button_login);
		panel.add(button_exit);

		// Put constraint on components
		//Label username
		layout.putConstraint(SpringLayout.WEST, label_username, 10, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, label_username, 20, SpringLayout.NORTH, panel);
		//Label password
		layout.putConstraint(SpringLayout.WEST, label_password, 10, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, label_password, 50, SpringLayout.NORTH, panel);
		// Label moodleUrl
		layout.putConstraint(SpringLayout.WEST, label_moodleURL, 10, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, label_moodleURL, 80, SpringLayout.NORTH, panel);
		//textField Usernma
		layout.putConstraint(SpringLayout.WEST, textFiel_username, 5, SpringLayout.EAST, label_username);
		layout.putConstraint(SpringLayout.NORTH, textFiel_username, 20, SpringLayout.NORTH, panel);
		//textField Password
		layout.putConstraint(SpringLayout.WEST, textFiel_password, 5, SpringLayout.EAST, label_username);
		layout.putConstraint(SpringLayout.NORTH, textFiel_password, 50, SpringLayout.NORTH, panel);
		//textField Moodle URL
		layout.putConstraint(SpringLayout.WEST, textFiel_moodleURL, 5, SpringLayout.EAST, label_moodleURL);
		layout.putConstraint(SpringLayout.NORTH, textFiel_moodleURL, 80, SpringLayout.NORTH, panel);
		
		layout.putConstraint(SpringLayout.WEST, button_login, 5, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, button_login, 120, SpringLayout.NORTH, panel);
		
		layout.putConstraint(SpringLayout.WEST, button_exit, 5, SpringLayout.EAST, button_login);
		layout.putConstraint(SpringLayout.NORTH, button_exit, 120, SpringLayout.NORTH, panel);
		
		
		button_exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Hallo545454352156316532165165321653216532165321");
				frame.setVisible(false); //you can't see me!
				frame.dispose(); //Destroy the JFrame object
				
				
			}
		});
		
		button_login.addActionListener(new ActionListener() {
			
			
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("...Getting LoginData");
				
				user = new Login(textFiel_username.getText(), textFiel_password.getText(), textFiel_moodleURL.getText());
				//user.setUsername(textFiel_username.getText());
				//user.setPassword(textFiel_password.getText());
				//user.setMoodleUrl(textFiel_moodleURL.getText());
				
				
				
		
				
				
			}
		});
		
		// Set the window to be visible as the default to be false
		frame.add(panel);
		//frame.pack();
		frame.setVisible(true);
		
		
		
	
		
	} 
	public void actionPerformed(ActionEvent e){
		System.out.println("Oui!");

	
	

		
	}
	class MyActionListener implements ActionListener{
		public MyActionListener(String msg){
			this.msg = msg;
		}
		public void actionPerformed(ActionEvent e){
			System.out.println(msg);
		}
		String msg;
	}
	
	public void sendData()
	{
		
		
	}
	

}

