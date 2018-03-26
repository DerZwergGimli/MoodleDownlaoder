package main;

import java.lang.reflect.Array;
import java.util.ArrayList;

import functions.Control;
import functions.MConnect;
import objects.Courses;
import objects.Data;
import objects.File;
import objects.Folder;
import objects.Login;

public class tempMakeControll {

	
	
	
	public static void main(String[] args) 
	{
	
		Control control = new Control();
		
		
	/*	//Show my Curses
		ArrayList<Courses> list_Courses = control.getCurses();
		
		for (int i = 0; i < list_Courses.size(); i++) 
		{
			System.out.println(list_Courses.get(i).getName());
		}
		
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		
		//Show me Files in Curse
		int selectedCourse = 1;
		ArrayList<File> list_Data = control.getFiles(list_Courses.get(selectedCourse).getLink());
		
		for (int i = 0; i < list_Data.size(); i++) 
		{
			System.out.println("Datei: "+list_Data.get(i).getName());
			
		}
		
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		
		ArrayList<Folder> list_Folder = control.getFolder(list_Courses.get(selectedCourse).getLink());
		for (int i = 0; i < list_Folder.size(); i++) 
		{
			System.out.println("Ordner: "+list_Folder.get(i).getName());
		}
		
		System.out.println("++++++++++FolderFiles+++++++++++++++++++++++++++++++++++++++");
		
		for (int i = 0; i < list_Folder.size(); i++) 
		{
			System.out.println("--> "+list_Folder.get(i).getName());
			
			ArrayList<File> list_DataInFolder = control.getFolderContent(list_Folder.get(i).getLink());
			
			for (int j = 0; j < list_DataInFolder.size(); j++) 
			{
				System.out.println("Datei: "+list_DataInFolder.get(j).getName());
				
			}
			
			
		}
		
		System.out.println("**********************************************************************");
		*/
		control.summarizeCourseData(null);
		
		
		
		System.out.println("...ende");
		
		
	}
	


}
