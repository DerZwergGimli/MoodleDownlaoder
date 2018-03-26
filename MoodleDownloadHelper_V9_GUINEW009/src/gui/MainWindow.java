package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import functions.Controll;
import functions.MConnect;

import objects.Login;


public class MainWindow extends JFrame
{
	
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
	
	//Constructor
	public MainWindow(Login login)
	{
		//Save login data from LoginPanel
		this.login = login;
		
		//make moodelConnection
		getMConnectData();
		
		//Basic INIT
		JPanel panel = new JPanel();
		panel.setSize(500, 300);
		panel.setLayout(layout);
		panel.add(label_Kurse, layout.NORTH);
		
		
		//ForJComboBox
		//comboBoxList_Curses = new JComboBox(cursesList_Output);
		comboBoxList_Curses.setSelectedIndex(0);
		panel.add(comboBoxList_Curses, layout.NORTH);
		
		
		createRowData();
		table = new JTable(rowData, columnNames);
		scrollPane = new JScrollPane(table);
		panel.add(scrollPane);
		
		setActionListener();
		
		this.setContentPane(panel);
		this.pack();
		this.setTitle("MoodleDownloadHelper");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
	}
	
	public void createRowData()
	{
		rowData = new String[5][3];
		int index = 0;
		for (int i = 0; i < 5; i++) 
		{
			//if (selectedID == list_files.get(i).getParned_ID()) 
			//{
				rowData[index][0] = "First";
				rowData[index][1] = "Secound";
				rowData[index][2] = "Third";
				index++;
			//}
		}
		
	}
	
	
	public void getMConnectData() 
	{
		//Variables
		
		//Show me UserData
		System.out.println("Getting MoodleData...");
		System.out.println(login.getUsername());
		System.out.println(login.getPassword());
		System.out.println(login.getMoodleUrl());
		
		System.out.println("+++++++++++++++++++++++++++++++++ LOADING Controll ++++++++++++++++++++++++++++++++++++++");
		
		//ConnectToMoodle
		//MConnect moodleConnection = new MConnect(login.getUsername(), login.getPassword(), login.getMoodleUrl());
		//System.out.println(moodleConnection.getUrlSiteInMoodle());
		//moodleConnection.createElementsArray();
		
		
		//SplitData
		
		Controll controll = new Controll(login);
		comboBoxList_Curses = new JComboBox(controll.getOnlyCurses());
		
		
		
		System.out.println("+++++++++++++++++++++++++++++++++ LOADING FOLDER ++++++++++++++++++++++++++++++++++++++");
		
		
		
	}
	
	public void setActionListener()
	{
		comboBoxList_Curses.addActionListener(new ActionListener()
	    {
	      public void actionPerformed(ActionEvent e)
	      {
	       //what to do if ComboBoxItem is Selected:
	    	System.out.println("ComboBox Selected" + comboBoxList_Curses.getSelectedItem());
	    	
	    	
	    	
	    	
	    	
	    	
	    		    	  
	      }
	    });
		
		
		
	}
	
}
