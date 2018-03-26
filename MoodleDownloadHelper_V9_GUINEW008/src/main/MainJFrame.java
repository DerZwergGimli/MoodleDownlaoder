package main;

import javax.swing.JFrame;
import javax.swing.plaf.metal.MetalIconFactory;

import org.omg.CORBA.portable.BoxedValueHelper;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.*;

import javax.security.auth.Refreshable;
import javax.swing.*;

public class MainJFrame extends JFrame

{
	 public static void main(String[] args) {
		 MainJFrame ex = new MainJFrame();
		 	//ex.pack();
	       ex.setVisible(true);
	    }
		   


		public MainJFrame() {
		       setTitle("Simple example");
		       setSize(300, 200);
		       setLocationRelativeTo(null);
		       setDefaultCloseOperation(EXIT_ON_CLOSE);
		       
		       initUI();
		    }
		
		
		

	    public final void initUI() {

	       JPanel panel = new JPanel();
	       getContentPane().add(panel);

	       panel.setLayout(new BorderLayout());

	       JButton quitButton = new JButton("Quit");
	       quitButton.setBounds(50, 60, 80, 30);
	       quitButton.addActionListener(new ActionListener() {
	           public void actionPerformed(ActionEvent event) {
	               System.exit(0);
	          }
	       });

	       panel.add(quitButton, BorderLayout.SOUTH);

	       Icon icon = MetalIconFactory.getFileChooserHomeFolderIcon();
	       
	       
	       
	       JLabel label_username = new JLabel("usernmame", icon, JLabel.CENTER);
	       label_username.setText("Username");
	       label_username.setBounds(50,60,80,30);
	       panel.add(label_username, BorderLayout.WEST);
	       
	       JLabel label_password = new JLabel("password", icon, JLabel.CENTER);
	       //label_password.setVerticalAlignment(JLabel.TOP);
	       //label_password.setHorizontalAlignment(JLabel.LEFT);
	       label_password.setText("password");
	       label_password.setBounds (50,60,80,30);
	       panel.add(label_password, BorderLayout.WEST);
	       
	       
	       
	       setTitle("Quit button");
	       setSize(300, 200);
	       setLocationRelativeTo(null);
	       setDefaultCloseOperation(EXIT_ON_CLOSE);
	       
	       panel.setVisible(true);
	       
	       
	       
	       
	    }
	    
	    
	    
	    

	    
		


}

