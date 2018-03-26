package mainPackage;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.*;

public class MainContent_jPanel extends JPanel
{
	
	JPanel panel;
	JButton testButton;
	
	public MainContent_jPanel()
	{
		super();
		this.panel = new JPanel(new BorderLayout());
		panel.setSize(200,200);
		makeButtons();
		panel.add(testButton);
		panel.repaint();
		
		
	}
	
	public void makeButtons() 
	{
		this.testButton = new JButton();
		testButton.setText("Hit me!!!");
	}
	

}
