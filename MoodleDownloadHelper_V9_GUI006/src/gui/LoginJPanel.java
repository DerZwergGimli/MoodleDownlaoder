package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import objects.Login;

public class LoginJPanel extends JFrame
{
	
	public Login user;
	public boolean input = true;
	
	JTextField textFiel_username = new JTextField("ya721pau", 15);
	JTextField textFiel_password = new JTextField("htwg-gai-22", 15);
	JTextField textFiel_moodleURL = new JTextField("https://moodle.htwg-konstanz.de/moodle/login/index.php", 15);
	
	
	public void LoginJPanel() 
	{
		
		JFrame.setDefaultLookAndFeelDecorated(false);
		JFrame frame = new JFrame("Layout");
		frame.setSize(500, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		SpringLayout layout = new SpringLayout();
		
		JLabel label_username = new JLabel("Username: ");
		JLabel label_password = new JLabel("Password: ");
		JLabel label_moodleURL = new JLabel("Moodle-URL: ");
		//JTextField textFiel_username = new JTextField("your Moodle Username", 15);
		//JTextField textFiel_password = new JTextField("your Moodle Password", 15);
		//JTextField textFiel_moodleURL = new JTextField("https://moodle.htwg-konstanz.de/moodle/index.php", 15);
		
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
						
						System.out.println("Username: "+textFiel_username.getText());
						System.out.println("Password: "+textFiel_password.getText());
						System.out.println("URL: "+textFiel_moodleURL.getText());
						
						setData();
						//this.user = new Login(textFiel_username.getText(), textFiel_password.getText(), textFiel_moodleURL.getText());
						//user.setUsername(textFiel_username.getText());
						//user.setPassword(textFiel_password.getText());
						//user.setMoodleUrl(textFiel_moodleURL.getText());
						
						input = false;
						frame.setVisible(false); //you can't see me!
						frame.dispose(); //Destroy the JFrame object
				
						
						
					}
				});
				frame.add(panel);
				frame.setVisible(true);
				
		
		
	}
	
	public String getLoginUsername() 
	{
		return user.getUsername();
	}
	
	public String getLoginPassword() 
	{
		return user.getPassword();
	}
	public String getLoginLink() 
	{
		return user.getMoodleUrl();
	}
	
	public boolean getInput() 
	{
		return input;
	}
	public void setData() 
	{
	
		this.user = new Login(textFiel_username.getText(), textFiel_password.getText(), textFiel_moodleURL.getText());
		this.input = true;
	}

}
