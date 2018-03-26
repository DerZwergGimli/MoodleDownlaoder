package main;

import functions.MConnect;
import objects.Curse;
import objects.FolderAndFiles;

public class main {

	public static void main(String[] args) 
	{
	
		
		
		//startcontroll Module
		
		System.out.println("Programm started....");

		
		String username = "ya721pau";
		String password = "htwg-gai-22";
		String urlBase = "https://moodle.htwg-konstanz.de/moodle/login/index.php";

		//Curse[] meineKurse = new Curse(name, id, nr, link);
		
		MConnect moodleConnection = new MConnect(username, password, urlBase);
		System.out.println(moodleConnection.getUrlSiteInMoodle());
		
		
		
		
		System.out.println(moodleConnection.getForCursToString());
		// Zu erst alle Kurse auflisten...
		
		FolderAndFiles kurse = new FolderAndFiles(moodleConnection.getElements());
		
		
		
		kurse.makeKurse(moodleConnection.getUrlSiteInMoodle(), kurse.getElementsArray());
		kurse.printElementsArray();
		
		
		
		//Kurs Auswahahlen
		
		int kursNr = 5;
		
		moodleConnection.setUrlSiteInMoodle(kurse.getURL(kursNr-1));
		moodleConnection.createDocument();
		
		FolderAndFiles kurs = new FolderAndFiles(moodleConnection.getElements());
		
		
		
		
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		
		
		
		kurs.printFiles();
		
		
		
		
		
		
		
		
		
		
		
		
		System.out.println("...Programm ende... Programm muede");
		
		
		
		
		
	}

}
