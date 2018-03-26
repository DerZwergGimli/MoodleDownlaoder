package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import functions.Funktions;
import functions.MConnect;
import objects.Curse;
import objects.Login;


public class MainWindow_new extends JFrame
{
	
	//GUI_Labels
	JLabel label_Kurse = new JLabel("Kurse");
	//GUI_Tables
	JTable table;
	JScrollPane scrollPane;
	//Buttons
	//GUI_Layout
	BorderLayout layout = new BorderLayout();
	
	//Objects
	Object columnNames[] = { "Name", "Typ", "Link" };
	Object [][] rowData;
	
	//LoginInfoClass
	Login login;
	
	//Constructor
	public MainWindow_new(Login login)
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
		
		
		createRowData();
		table = new JTable(rowData, columnNames);
		scrollPane = new JScrollPane(table);
		panel.add(scrollPane);
		
		
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
		ArrayList<Curse> list_Curse = new ArrayList<Curse>();
		
		//Show me UserData
		System.out.println("Getting MoodleData...");
		System.out.println(login.getUsername());
		System.out.println(login.getPassword());
		System.out.println(login.getMoodleUrl());
		
		//ConnectToMoodle
		MConnect moodleConnection = new MConnect(login.getUsername(), login.getPassword(), login.getMoodleUrl());
		System.out.println(moodleConnection.getUrlSiteInMoodle());
		moodleConnection.createElementsArray();
		
		//SplitData
		String[][] temp = moodleConnection.getElementsArray();
		temp = Funktions.getOnlyCurses(temp);
		Curse[] curses = new Curse[temp.length];
		for (int i = 0; i < temp.length; i++) 
		{
			curses[i] = new Curse(temp[i][0], temp[i][2], "curse", i);
			list_Curse.add(new Curse(temp[i][0], temp[i][2], "curse", i));
			curses[i].printToConsole();
		}
		
		
		System.out.println("+++++++++++++++++++++++++++++++++ LOADING FOLDER ++++++++++++++++++++++++++++++++++++++");
		
		
		
	}
}
