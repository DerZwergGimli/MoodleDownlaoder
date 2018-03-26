package main;

import java.awt.List;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import functions.Funktions;
import functions.MConnect;
import objects.Curse;
import objects.Folder;
import objects.File;
import objects.FolderAndFiles;

public class main {

	public static void main(String[] args) throws IOException {
		
		
		ArrayList<Curse> list_Curse = new ArrayList<Curse>();
		ArrayList<Folder> list_Folder = new ArrayList<Folder>();
		ArrayList<File> list_File = new ArrayList<File>();
		ArrayList<File> list_OrderZip = new ArrayList<File>();
				
		
		Folder[] folders;
		File[] files;
		// startcontroll Module

		System.out.println("Programm started....");

		// Login Data to Moodle
		String username = "ya721pau";
		String password = "htwg-gai-22";
		String urlBase = "https://moodle.htwg-konstanz.de/moodle/login/index.php";

		// Connect to Moodle
		MConnect moodleConnection = new MConnect(username, password, urlBase);
		System.out.println(moodleConnection.getUrlSiteInMoodle());

		// Create new Curses
		moodleConnection.createElementsArray();
		String[][] temp = moodleConnection.getElementsArray();
		temp = Funktions.getOnlyCurses(temp);
		Curse[] curses = new Curse[temp.length];
		for (int i = 0; i < temp.length; i++) {
			curses[i] = new Curse(temp[i][0], temp[i][2], "curse", i);
			list_Curse.add(new Curse(temp[i][0], temp[i][2], "curse", i));
			curses[i].printToConsole();
		}
		
		

		System.out.println("+++++++++++++++++++++++++++++++++ LOADING FOLDER ++++++++++++++++++++++++++++++++++++++");
		// Create Folder
		int numberOfFolders = 0;
		
		for (int x = 0; x < curses.length; x++) {
			
			moodleConnection.setUrlSiteInMoodle(curses[x].getLink());
			System.out.println(curses[x].getLink());
			moodleConnection.createDocument();
			moodleConnection.createElementsArray();
			temp = moodleConnection.getElementsArray();

			numberOfFolders = 0;
			for (int i = 0; i < temp.length; i++) {
				if (temp[i][1] != null) {
					if ((temp[i][1]).equals("dr")) {
						numberOfFolders++;
						list_Folder.add(new Folder(numberOfFolders, temp[i][0], temp[i][2], null, curses[x].getID()));
					}
				}
			}

			/*if (numberOfFolders != 0) {
				folders = new Folder[numberOfFolders];
				for (int i = 0; i < numberOfFolders; i++) {
					list_Folder.add(new Folder(i, temp[i][0], temp[i][2], null, curses[x].getID()));
				}

			}*/	
			System.out.println(numberOfFolders);
		}
		
		System.out.println("+++++++++++++++++++++++++++++++++ LOADING FILES ++++++++++++++++++++++++++++++++++++++");
		//Create Files
		int numberOfFiles = 0;
		for (int x = 0; x < curses.length; x++) {
			moodleConnection.setUrlSiteInMoodle(curses[x].getLink());
			System.out.println(curses[x].getLink());
			moodleConnection.createDocument();
			moodleConnection.createElementsArray();
			temp = moodleConnection.getElementsArray();

			numberOfFiles = 0;
			for (int i = 0; i < temp.length; i++) {
				if (temp[i][1] != null) {
					if ((temp[i][1]).equals("f")) {
						numberOfFiles++;
						list_File.add(new File(i, temp[i][0], temp[i][2], ".pdf", curses[x].getID()));
					}
				}
			}

			/*if (numberOfFiles != 0) {
				files = new File[numberOfFiles];
				for (int i = 0; i < numberOfFiles; i++) {
					list_File.add(new File(i, temp[i][0], temp[i][2], ".pdf", curses[x].getID()));
				}

			}*/	
			System.out.println(numberOfFiles);
		}
		
		
		System.out.println("++++++++++++++++++++++++++++++ Loading Files form Directorys ++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		
		//
		//Create Files
		numberOfFiles = 0;
		for (int x = 0; x < list_Folder.size(); x++) {
			moodleConnection.setUrlSiteInMoodle(list_Folder.get(x).getLink());
			System.out.println(list_Folder.get(x).getLink());
			moodleConnection.createDocument();
			moodleConnection.createElementsArray();
			temp = moodleConnection.getElementsArray();

			numberOfFiles = 0;
			for (int i = 0; i < temp.length; i++) {
				if (temp[i][1] != null) {
					if ((temp[i][1]).equals("f")) {
						numberOfFiles++;
						list_OrderZip.add(new File(i, temp[i][0], temp[i][2], ".pdf", list_Folder.get(x).getParned_ID()));
					}
				}
			}

			/*if (numberOfFiles != 0) {
				files = new File[numberOfFiles];
				for (int i = 0; i < numberOfFiles; i++) {
					list_File.add(new File(i, temp[i][0], temp[i][2], ".pdf", curses[x].getID()));
				}

			}*/	
			System.out.println(numberOfFiles);
		}
		//
		
		
		
		Scanner reader = new Scanner(System.in);  // Make Scanner for User Inputs
		int run=1;
		int eingabe = -1;
		while (run == 1) 
		{
			for (int i = 0; i < list_Curse.size(); i++) 
			{
				list_Curse.get(i).printToConsole();
			}
			
			eingabe = Funktions.eingabeUser(reader);
			
			for (int i = 0; i < list_Folder.size(); i++) {
				if (list_Folder.get(i).getParned_ID() == eingabe) {
					list_Folder.get(i).printToConsole();
				}
			}
			
			//eingabe = Funktions.eingabeUser(reader);
			
			for (int i = 0; i < list_File.size(); i++) {
				if (list_File.get(i).getParned_ID() == eingabe) {
					list_File.get(i).printToConsole();
				}
			}
			
			for (int i = 0; i < list_OrderZip.size(); i++) {
				if (list_OrderZip.get(i).getParned_ID() == eingabe) {
					list_OrderZip.get(i).printToConsole();
				}
			}
			
			
			run = 0;
			
		}
		
		reader.close(); 
		
		System.out.println("...Programm ende... Programm muede");

	}

}
