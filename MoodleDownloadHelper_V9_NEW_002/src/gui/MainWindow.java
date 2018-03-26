package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.Spring;
import javax.swing.SpringLayout;

import functions.Control;
import functions.MConnect;
import objects.Courses;
import objects.List;
import objects.Login;


public class MainWindow extends JFrame
{
	JPanel panel;
	
	//GUI_Labels
	JLabel label_Kurse = new JLabel("Kurse");
	
	//GUI_Tables
	JTable tableINPUT;
	JTable tableOUTPUT;
	JScrollPane scrollPaneINPUT;
	JScrollPane scrollPaneOUTPUT;
	
	//GUI JComboBox
	private JComboBox comboBoxList_Curses;
	
	//Buttons
	JButton buttonADD;
	JButton buttonREMOVE;
	
	//ImageIcon
	ImageIcon loading;
	JLabel label_loading;
	
	//GUI_Layout
	SpringLayout layout = new SpringLayout();
	
	//Objects
	Object columnNames[] = { "Folder", "File", "Link" };
	Object [][] rowDataINPUT;
	Object [][] rowDataOUTPUT;
	
	
	public String[] cursesList_Output;
	
	//LoginInfoClass
	Login login;
	Control control;
	
	//other
	
	//Constructor
	public MainWindow(Login login)
	{
		//Save login data from LoginPanel
		this.login = login;
		control = new Control(this.login);
		
		
		
		
		//make data for JComboBox
		createElements_JComboBox();
		
		//Basic INIT
		panel = new JPanel();
		//panel.setSize(500, 300);
		panel.setLayout(layout);
		
		
		
		buttonADD = new JButton(" --> ");
		buttonREMOVE = new JButton(" <-- ");
		
		comboBoxList_Curses.setSelectedIndex(0);
		createRowDataINPUT(0);
		
		rowDataOUTPUT = new String[1][3];
		tableINPUT = new JTable(rowDataINPUT, columnNames);
		tableOUTPUT = new JTable(rowDataOUTPUT, columnNames);
		scrollPaneINPUT = new JScrollPane(tableINPUT);
		scrollPaneOUTPUT = new JScrollPane(tableOUTPUT);
		

		loading = new ImageIcon("ajax-loader.gif");
		label_loading = new JLabel(loading);
		
		
		setLayout();
		setActionListener();
		
		panel.add(buttonADD);
		panel.add(buttonREMOVE);
		panel.add(comboBoxList_Curses);
		panel.add(label_Kurse);
		panel.add(scrollPaneINPUT);
		panel.add(scrollPaneOUTPUT);
		panel.add(label_loading);
		
		
		panel.setVisible(true);
		
		//this.pack();
		this.setSize(1000, 600);
		this.setContentPane(panel);
		this.setTitle("MoodleDownloadHelper");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
	}
	
	public void createRowDataINPUT(int selectCurse)
	{
	
		
		ArrayList<List> allCourseData = control.summarizeCourseData(selectCurse);
		rowDataINPUT = new String[allCourseData.size()][3];
		for (int i = 0; i < allCourseData.size(); i++) {
			rowDataINPUT[i][1] = Boolean.toString(allCourseData.get(i).isFolder());
			rowDataINPUT[i][0] = allCourseData.get(i).getName();
			rowDataINPUT[i][2] = allCourseData.get(i).getLink();
			
		}
		
		
		
	}
	public void createRowDataOUTPUT()
	{
	
		
		ArrayList<List> allCourseData = control.getDownloadList();
		rowDataOUTPUT = new String[allCourseData.size()][3];
		for (int i = 0; i < allCourseData.size(); i++) {
			rowDataOUTPUT[i][1] = Boolean.toString(allCourseData.get(i).isFolder());
			rowDataOUTPUT[i][0] = allCourseData.get(i).getName();
			rowDataOUTPUT[i][2] = allCourseData.get(i).getLink();
			
		}
		
		
		
	}
	
	
	public void createElements_JComboBox() 
	{
		
		
		
		ArrayList<Courses> courses  = control.getCurses();
		String [] coursesNames = new String[courses.size()];
		
		for (int i = 0; i < coursesNames.length; i++) {
			coursesNames[i] = courses.get(i).getName();
		}
		
	
		comboBoxList_Curses = new JComboBox(coursesNames);
		
		System.out.println("hallo");
		
		
	}
	
	public void setActionListener()
	{
		comboBoxList_Curses.addActionListener(new ActionListener()
	    {
	      public void actionPerformed(ActionEvent e)
	      {
	    	  
	    	  //what to do if ComboBoxItem is Selected:
	    	  System.out.println("Selected IDNEX:"+comboBoxList_Curses.getSelectedIndex());
	    	  System.out.println("ComboBox Selected" + comboBoxList_Curses.getSelectedItem());
	    	
	    	  refresh();
	    
	    	  
	    	
	    		    	  
	      }
	    });
		
		buttonADD.addActionListener(new ActionListener()
	    {
	      public void actionPerformed(ActionEvent e)
	      {
	    	  System.out.println("ADD");
	    	  control.addToDownload(tableINPUT.getSelectedRow());
	    	  createRowDataOUTPUT(); 
	    	  refresh();
	    	  
	      }
	    });
	
		buttonREMOVE.addActionListener(new ActionListener()
	    {
	      public void actionPerformed(ActionEvent e)
	      {
	    	  System.out.println("REMOVED");	    	  
	      }
	    });
		
		
	}
	
	public void refresh() 
	{
		createRowDataINPUT(comboBoxList_Curses.getSelectedIndex());
    	
		tableINPUT = new JTable(rowDataINPUT, columnNames);
		panel.remove(scrollPaneINPUT);
		scrollPaneINPUT = new JScrollPane(tableINPUT);
		panel.add(scrollPaneINPUT);
		
		tableOUTPUT = new JTable(rowDataOUTPUT, columnNames);
		panel.remove(scrollPaneOUTPUT);
		scrollPaneOUTPUT = new JScrollPane(tableOUTPUT);
		panel.add(scrollPaneOUTPUT);
		
		
		setLayout();
		
		panel.revalidate();
    	panel.repaint();
    	
    	
		
    	
	}
	
	public void setLayout() 
	{
		//Set labelKurse
		layout.putConstraint(SpringLayout.WEST, label_Kurse, 
				5,
				SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, label_Kurse,
				5,
				SpringLayout.NORTH, panel);

				
		//comboBoxList_Curses
		layout.putConstraint(SpringLayout.WEST, comboBoxList_Curses, 
				50,
				SpringLayout.WEST, label_Kurse);
		layout.putConstraint(SpringLayout.NORTH, comboBoxList_Curses,
				5,
				SpringLayout.NORTH, panel);

		
		//Set scrollPaneINPUT
		layout.putConstraint(SpringLayout.WEST, scrollPaneINPUT, 
				5,
				SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, scrollPaneINPUT,
				50,
				SpringLayout.NORTH, panel);
		
		//Set scrollPaneOUTPUT
		layout.putConstraint(SpringLayout.EAST, scrollPaneOUTPUT, 
				-5,
				SpringLayout.EAST, panel);
		layout.putConstraint(SpringLayout.NORTH, scrollPaneOUTPUT,
				50,
				SpringLayout.NORTH, panel);
		
		
		//Set buttonADD
		layout.putConstraint(SpringLayout.WEST, buttonADD, 
						5,
						SpringLayout.EAST, scrollPaneINPUT);
		layout.putConstraint(SpringLayout.NORTH, buttonADD,
						100,
						SpringLayout.NORTH, panel);
		
		//Set buttonADD
		layout.putConstraint(SpringLayout.WEST, buttonREMOVE, 
								5,
								SpringLayout.EAST, scrollPaneINPUT);
		layout.putConstraint(SpringLayout.NORTH, buttonREMOVE,
								200,
								SpringLayout.NORTH, panel);
		
				
		
		//Set loading
		layout.putConstraint(SpringLayout.EAST, label_loading, 
						20,
						SpringLayout.EAST, comboBoxList_Curses);
		layout.putConstraint(SpringLayout.NORTH, label_loading,
						5,
						SpringLayout.NORTH, panel);
				
		
		
		
	}
	

	
}
