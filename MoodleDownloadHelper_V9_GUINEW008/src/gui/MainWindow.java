package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SpringLayout;

import objects.Curse;
import objects.File;

public class MainWindow extends JFrame implements ActionListener
{
	CountDownLatch loginSignal = new CountDownLatch(1);
	
	public boolean running = true;
	public ArrayList<Curse> list_curse;
	public ArrayList<File> list_files;
	
	public String[] cursesList_Output;
	public Object [][] rowData;
	
	private JComboBox comboBoxList_Curses;
	private int selectedID;
	
	private JTable table;
	JScrollPane scrollPane;
	
	Object columnNames[] = { "Name", "Typ", "Link" };
	private JFrame frame = new JFrame("Layout");
	
	JPanel panel = new JPanel();
	BorderLayout layout = new BorderLayout();
	JLabel label_Kurse = new JLabel("Kurse:");
	
	
   // public void windowOpened(WindowEvent e) {}
	
	
    
	public void MainWindow(String[] args, ArrayList<Curse> list_curse, ArrayList<File> list_files) 
	{
		this.list_curse = list_curse;
		this.list_files = list_files;
		
		JFrame.setDefaultLookAndFeelDecorated(false);
		JFrame frame = new JFrame("Layout");
		//frame.setSize(1000, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		
		
		
		//some code
		//String[] petStrings = { "Bird", "Cat", "Dog", "Rabbit", "Pig" };

		//Create the combo box, select item at index 4.
		//Indices start at 0, so 4 specifies the pig.
		createCuresList();
		comboBoxList_Curses = new JComboBox(cursesList_Output);
		comboBoxList_Curses.setSelectedIndex(0);
		
		//petList.addActionListener(this);
		//comboBoxList_Curses.addActionListener(this);
		
		createRowData();
		
	    
	    table = new JTable(rowData, columnNames);
		table.isCellEditable(0, 0);
	    scrollPane = new JScrollPane(table);
	        
		//selectedID = 2;
		//createRowData();
		//table = new JTable(rowData, columnNames);
		
		
		panel.add(label_Kurse, layout.NORTH);
		panel.add(comboBoxList_Curses, layout.WEST);
		panel.add(scrollPane, layout.CENTER);
		
		
		
		
		//layout.putConstraint(SpringLayout.WEST, panel, 10, SpringLayout.WEST, panel);
		//layout.putConstraint(SpringLayout.NORTH, label_Kurse, 20, SpringLayout.NORTH, panel);
		
		
		//layout.putConstraint(SpringLayout.WEST, label_Kurse, 10, SpringLayout.WEST, panel);
		//layout.putConstraint(SpringLayout.NORTH, comboBoxList_Curses, 50, SpringLayout.NORTH, panel);
		
		//layout.putConstraint(SpringLayout.WEST, comboBoxList_Curses, 10, SpringLayout.WEST, panel);
		//layout.putConstraint(SpringLayout.NORTH, scrollPane, 50, SpringLayout.NORTH, panel);
		
		
		frame.add(panel);
		frame.pack();
		
		//frame.add(label_username);
		frame.setVisible(true);
		
	}
	
	public void createCuresList() 
	{
		this.cursesList_Output = new String[list_curse.size()];
		
		for (int i = 0; i < list_curse.size(); i++) 
		{
			this.cursesList_Output[i] = list_curse.get(i).getName();
			
		}
		
	}
	
	public void createRowData() 
	{
		//selectedID = 0;
		
		
		
		int anzahl = 0;
		for (int i = 0; i < list_files.size(); i++) 
		{
			if (selectedID == list_files.get(i).getParned_ID()) 
			{
				anzahl++;
			}
		}
		
		rowData = new String[anzahl][3];
		int index = 0;
		for (int i = 0; i < list_files.size(); i++) 
		{
			if (selectedID == list_files.get(i).getParned_ID()) 
			{
				rowData[index][0] = list_files.get(i).getName();
				rowData[index][1] = list_files.get(i).getFileEnding();
				rowData[index][2] = list_files.get(i).getLink();
				index++;
			}
		}
	}
	
	public boolean isCellEditable(int row, int cols) 
	{
		return false;
		
	}
	
	public void actionPerformed (ActionEvent e){
        
		System.out.println("...auswahl..");
		//createRowData();
		
		selectedID = comboBoxList_Curses.getSelectedIndex();
		System.out.println(selectedID);
		createRowData();
		table = new JTable(rowData, columnNames);
		scrollPane = new JScrollPane(table);
	    
		panel.add(scrollPane);
		//scrollPane.repaint();
		frame.add(panel);
		frame.pack();
		//frame.pack();
		System.out.println("finished");
		
	}

}
