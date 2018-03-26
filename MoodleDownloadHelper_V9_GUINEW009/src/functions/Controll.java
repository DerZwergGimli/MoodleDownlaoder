package functions;

import java.util.ArrayList;

import objects.Curses;

import objects.File;

import objects.Login;

public class Controll 
{
	private ArrayList<Curses> list_Curses = new ArrayList<Curses>();
	private ArrayList<File> list_Files = new ArrayList<File>();
	
	
	private Login login;
	private MConnect moodleConnection;
	
	public Controll(Login login) 
	{
		this.login = login;
		moodleConnection = new MConnect(login.getUsername(), login.getPassword(), login.getMoodleUrl());
		
		System.out.println(login.getUsername());
		System.out.println(login.getPassword());
		System.out.println(login.getMoodleUrl());
		
		moodleConnection.createCookie();
		moodleConnection.createDocument();
		moodleConnection.getNextUrl();
		
		//Create Curses
		fetchCurses();
		
		
		
		
	}
	
	
	
	public ArrayList<Curses> getArrayList()
	{
		return list_Curses;
	}
	
	public String[] getOnlyCurses() 
	{
		//Bestimme Anzahl Kurse
		//Gebe nur Kurse zurueck
		String[] curses = new String[list_Curses.size()];
		for (int i = 0; i < list_Curses.size(); i++) 
		{
			curses[i] = list_Curses.get(i).getName();
			
		}
		return curses;
	}
	
	public void fetchCurses() 
	{
		//Create Curses
		moodleConnection.createElementsArray();
		String [][] kurs = moodleConnection.getElementsArray();
		
		int anzahlKurse=0;
		for (int i = 89; i < kurs.length; i++) 
		{
			anzahlKurse++;
			if (kurs[i][2].equals("https://moodle.htwg-konstanz.de/moodle/course/index.php")) 
			{
			break;	
			}	
			
			
		}
		
		int id =0;
		for (int i = 89; i < 89+anzahlKurse-1; i++) 
		{
			list_Curses.add(new Curses(kurs[i][0], kurs[i][2], "Curse", id));
			id++;
			
		}
		
		
		System.out.println("done");
		
	}
	
	public void fetchFiles() 
	{
		int numberOfFolders = 0;
		
		for (int x = 0; x < list_Curses.size(); x++) 
		{
			
			moodleConnection.setUrlSiteInMoodle(list_Curses.get(x).getLink());
			System.out.println(list_Curses.get(x).getLink());
			moodleConnection.createDocument();
			moodleConnection.createElementsArray();
			String [][] temp = moodleConnection.getElementsArray();

			numberOfFolders = 0;
			for (int i = 0; i < temp.length; i++) 
			{
				if (temp[i][1] != null) 
				{
					if ((temp[i][1]).equals("dr")) 
					{
						numberOfFolders++;
						//list_Files.add(new File);
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
	}
	
	
	
	
	
	
	
	

}
