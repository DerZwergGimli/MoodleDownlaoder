package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

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
	JTable table;
	JScrollPane scrollPane;
	
	//GUI JComboBox
	private JComboBox comboBoxList_Curses;
	
	//Buttons
	
	//GUI_Layout
	BorderLayout layout = new BorderLayout();
	
	//Objects
	Object columnNames[] = { "Name", "Typ", "Link" };
	Object [][] rowData;
	public String[] cursesList_Output;
	
	//LoginInfoClass
	Login login;
	Control control;
	
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
		panel.setSize(500, 300);
		panel.setLayout(layout);
		panel.add(label_Kurse, layout.NORTH);
		
		
		//ForJComboBox
		//comboBoxList_Curses = new JComboBox(cursesList_Output);
		comboBoxList_Curses.setSelectedIndex(0);
		panel.add(comboBoxList_Curses, layout.NORTH);
		
		
		createRowData(0);
		table = new JTable(rowData, columnNames);
		scrollPane = new JScrollPane(table);
		panel.add(scrollPane);
		
		setActionListener();
		
		this.setContentPane(panel);
		this.pack();
		this.setTitle("MoodleDownloadHelper");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
	}
	
	public void createRowData(int selectCurse)
	{
	
		
		ArrayList<List> allCourseData = control.summarizeCourseData(selectCurse);
		rowData = new String[allCourseData.size()][3];
		for (int i = 0; i < allCourseData.size(); i++) {
			rowData[i][1] = Boolean.toString(allCourseData.get(i).isFolder());
			rowData[i][0] = allCourseData.get(i).getName();
			rowData[i][2] = allCourseData.get(i).getLink();
			
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
		
		
		
	}
	
	public void refresh() 
	{
		createRowData(comboBoxList_Curses.getSelectedIndex());
    	
		table = new JTable(rowData, columnNames);
		panel.remove(scrollPane);
		scrollPane = new JScrollPane(table);
		panel.add(scrollPane);
		
		panel.revalidate();
		//this.pack();
    	panel.repaint();
		
    	
	}
	
}
