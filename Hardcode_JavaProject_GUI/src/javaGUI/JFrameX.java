package javaGUI;

import javax.swing.*;

public class JFrameX 
{
public static void main(String[] args) 
	{
	
	JFrame frame = new JFrame();
    frame.setSize(600,800);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
    frame.setLocationRelativeTo(null);

    JMenuBar mb = new JMenuBar();
    JMenu menu1 = new JMenu("Colour");
    mb.add(menu1);
    JMenu menu2 = new JMenu("Size");
    mb.add(menu2);

    //frame.setJMenuBar(mb);
    
    frame.validate();
    frame.repaint();

	}
}
