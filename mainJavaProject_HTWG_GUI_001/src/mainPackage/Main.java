package mainPackage;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.*;

public class Main {

	public static void main(String[] args) 
	{
		//
		JFrame frame = new JFrame("JFrame Example");

		MainContent_jPanel panel = new MainContent_jPanel();
		//panel.setLayout(new FlowLayout());

		//JLabel label = new JLabel("This is a label!");

		//JButton button = new JButton();
		//button.setText("Press me");

		//panel.add(label);
		//panel.add(button);

		frame.add(panel, BorderLayout.SOUTH);
		frame.setSize(300, 300);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.repaint();
		//
	}

}
