package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import objects.Login;

public class LoginWindow extends JFrame 
{
	//JPanel
	JPanel panel;
	
	//GUI_Layout
	SpringLayout layout = new SpringLayout();
	
	//JLabels
	JLabel label_username = new JLabel("Username:");
	JLabel label_password = new JLabel("Password:");
	JLabel label_moodleURL = new JLabel("Moodle Link:");
	
	//JTextFields
	JTextField textField_username = new JTextField("ya721pau");
	JTextField textFiled_password = new JTextField("htwg-gai-22");
	JTextField textFiled_moodleURL = new JTextField("https://moodle.htwg-konstanz.de/moodle/login/index.php");
	
	//JButtons
	JButton button_login = new JButton("Login");
	JButton button_close = new JButton("Close");
	

	//LoginInfoClass
	public Login login;
	
	//Constructor
	public LoginWindow() 
	{
		
		
		//Create NEW Panel
		panel = new JPanel();
		panel.setLayout(layout);
		
		//Add all elements
		panel.add(label_username);
		panel.add(label_password);
		panel.add(label_moodleURL);
		panel.add(textField_username);
		panel.add(textFiled_password);
		panel.add(textFiled_moodleURL);
		panel.add(button_login);
		panel.add(button_close);
		
	
		//Make Layout for all buttons, Panels etc.
		setLayout();
		setActionListener();
		
		
		
		
	
		panel.setVisible(true);
		//Setup parental Class -> JFrame
		this.setContentPane(panel);
		this.setSize(500, 300);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void setLayout() 
	{
		//this method is for making the layout only...
		//LayoutSettings
		
		//LayoutUsernameLabel: x and y
		layout.putConstraint(SpringLayout.WEST, label_username, 
							5,
							SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, label_username,
							5,
							SpringLayout.NORTH, panel);

		//LayoutUsernameTextBox: x and y
		layout.putConstraint(SpringLayout.WEST, textField_username,
							5, 
							SpringLayout.EAST, label_username);
		layout.putConstraint(SpringLayout.NORTH, textField_username,
							5, 
							SpringLayout.NORTH, panel);
		
		//LayoutPasswordLabel:
		layout.putConstraint(SpringLayout.WEST, label_password,
							5,
							SpringLayout.WEST, panel);		
		layout.putConstraint(SpringLayout.NORTH, label_password,
							5,
							SpringLayout.SOUTH, label_username);
		
		//LayoutPasswordTextBox:
		layout.putConstraint(SpringLayout.WEST, textFiled_password, 
							5, 
							SpringLayout.EAST, label_password);
		layout.putConstraint(SpringLayout.NORTH, textFiled_password,
							5,
							SpringLayout.SOUTH, textField_username);
		
		//LayoutMoodleURLLabel:
		layout.putConstraint(SpringLayout.WEST, label_moodleURL,
							5,
							SpringLayout.WEST, panel);		
		layout.putConstraint(SpringLayout.NORTH, label_moodleURL,
							5,
							SpringLayout.SOUTH, label_password);
		
		//LayoutMoodleURLTextBox:
		layout.putConstraint(SpringLayout.WEST, textFiled_moodleURL, 
							5, 
							SpringLayout.EAST, label_moodleURL);
		layout.putConstraint(SpringLayout.NORTH, textFiled_moodleURL,
							5,
							SpringLayout.SOUTH, textFiled_password);
		
		//LayoutButtonLogin
		layout.putConstraint(SpringLayout.EAST, button_login, 
							0, 
							SpringLayout.EAST, panel);
		layout.putConstraint(SpringLayout.SOUTH, button_login,
							0,
							SpringLayout.SOUTH, panel);

		//LayoutButtonClose
		layout.putConstraint(SpringLayout.WEST, button_close, 
							0, 
							SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.SOUTH, button_close,
							0,
							SpringLayout.SOUTH, panel);

				
		
	}
	
	public void setActionListener()
	{
		button_login.addActionListener(new ActionListener()
	    {
	      public void actionPerformed(ActionEvent e)
	      {
	       //what to do if LOGIN Button is pressed:
	    	System.out.println("Login...Pressed!");
	    	login = new Login(textField_username.getText(), textFiled_password.getText(), textFiled_moodleURL.getText());
	    	
	    	createMainGUI();
	    	
	    		    	  
	      }
	    });
		
		button_close.addActionListener(new ActionListener()
	    {
	      public void actionPerformed(ActionEvent e)
	      {
	       //what to do if CLOSE Button is pressed:
	    	  System.out.println("Closed...Pressed!");
	    	  //panel.setVisible(false);
	    	  System.exit(0);
	    	  
	      }
	    });
		
	}
	
	
	public void createMainGUI() 
	{
		
		//Make new Frame witch is mainWindow
		JFrame mainFrame = new MainWindow(this.login);
		mainFrame.setVisible(true);
		super.dispose();
			
	}
	
	
	

}


