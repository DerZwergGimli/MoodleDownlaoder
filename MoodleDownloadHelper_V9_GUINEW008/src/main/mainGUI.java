package main;



import javax.swing.JFrame;

import gui.LoginWindow;
import gui.MainWindow_new;

public class mainGUI 
{

	public static void main(String[] args) 
	{
	
		System.out.println("Programm started....");
		
		//MakeLoginFrame
		JFrame loginFrame = new LoginWindow();
		loginFrame.setVisible(true);
		
		
		//MakeFrame
		//JFrame mainFrame = new MainWindow_new();
		//mainFrame.setVisible(true);
	
		
	}

}
