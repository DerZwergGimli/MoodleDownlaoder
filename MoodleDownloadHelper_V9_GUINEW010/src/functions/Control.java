package functions;

import java.util.ArrayList;

import objects.Curses;

import objects.File;
import objects.Folder;
import objects.Login;

public class Control
{
	private ArrayList<Curses> list_Curses = new ArrayList<Curses>();
	private ArrayList<File> list_Files = new ArrayList<File>();
	private ArrayList<Folder> list_Folder; //= new ArrayList<Folder>();
	
	private Login login;
	private MConnect moodleConnection;
	
	public Control(Login login) 
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
	
	public String[] getCursesAsArray() 
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
		//AuswahlKriterium: -> im link steht _curse_
		
		moodleConnection.createElementsArray();
		String [][] temp = moodleConnection.getElementsArray();
		int index = 0;
		for (int i = 89; i < temp.length; i++) 
		{
			if (temp[i][2].contains("course") == true) 
			{
				list_Curses.add(new Curses(temp[i][0], temp[i][2], "Curse", index));
								
				if ((list_Curses.get(index).getName().contains("Kurse")) == true) 
				{
					//entfernt das letzte Element
					list_Curses.remove(index);
				}
				index++;
				
			}
			
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
	
	public void fetchFiles(String link, int folderID, String curseName) 
	{
		moodleConnection.setUrlSiteInMoodle(link);
		System.out.println("Checking for Files in: "+ link);
		moodleConnection.createDocument();
		moodleConnection.createElementsArray();
	
		String [][] temp = moodleConnection.getElementsArray();
		
		int numberOfFiles=0;
		
		for (int i = 0; i < temp.length; i++) 
		{
			//System.out.println(temp[i][2]);
			if ((temp[i][2].contains("resource")) == true) 
			{
				numberOfFiles++;
				list_Files.add(new File(folderID+i, temp[i][0], temp[i][2], curseName, ".pdf"));
				
			}
		} 
		System.out.println(numberOfFiles);
	
		
		
	}
	
	public void fetchFoldersOfCourse(int selectedCourse) 
	{
		//Make new Folder list
		list_Folder = new ArrayList<Folder>();
		
		//connectTo selcted Course
		moodleConnection.setUrlSiteInMoodle(list_Curses.get(selectedCourse).getLink());
		System.out.println("Checking for Files in: "+ list_Curses.get(selectedCourse).getLink());
		moodleConnection.createDocument();
		moodleConnection.createElementsArray();
		
		String [][] temp = moodleConnection.getElementsArray();
		
		int numberOfFolders=0;
		
		for (int i = 0; i < temp.length; i++) 
		{
			//System.out.println(temp[i][2]);
			if ((temp[i][2].contains("folder")) == true) 
			{
				numberOfFolders++;
				list_Folder.add(new Folder(numberOfFolders, temp[i][0], temp[i][2], list_Curses.get(selectedCourse).getName(), selectedCourse));
				
				
			}
		} 
		System.out.println(numberOfFolders);
		
		
		
		
		
	}
	

	public String [][] getStringForCurse_JList (int selectedCourse) 
	{
		//Zuerst alle Datein im Kurs
		fetchFiles(list_Curses.get(selectedCourse).getLink(), 0000, list_Curses.get(selectedCourse).getName());
		
		
		//dann alle Datein im Kurs in Ordern!!
		int idHelper = 100;
		for (int i = 0; i < list_Folder.size(); i++) 
		{
			list_Files.add(new File(idHelper, list_Folder.get(i).getName(), list_Folder.get(i).getLink(), "tempNix", "dr"));
			fetchFiles(list_Folder.get(i).getLink(), idHelper*(i+1), list_Curses.get(i).getName());
			
			
		}
		
		
		
		//Make String Array form ArrayList
		String [][] JListElements = new String[list_Files.size()][3];
		for (int i = 0; i < list_Files.size(); i++) 
		{
			JListElements[i][0] = list_Files.get(i).getName();
			JListElements[i][1] = list_Files.get(i).getFileExtension();
			JListElements[i][2] = list_Files.get(i).getLink();
			
		}
		
		return JListElements;
		
		
	}
	
	
	
	
	
	
	

}
