package main;

import functions.MConnect;
import objects.FolderAndFiles;

public class main {

	public static void main(String[] args) 
	{
	
		
		
		System.out.println("Programm started....");

		
		String username = "ya721pau";
		String password = "htwg-gai-22";
		String urlBase = "https://moodle.htwg-konstanz.de/moodle/login/index.php";

		MConnect moodleConnection = new MConnect(username, password, urlBase);
		System.out.println(moodleConnection.getUrlSiteInMoodle());
		
		// Zu erst alle Kurse auflisten...
		
		FolderAndFiles kurse = new FolderAndFiles(moodleConnection.getElements());
		
		kurse.makeKurse(moodleConnection.getUrlSiteInMoodle(), kurse.getElementsArray());
		kurse.printElementsArray();
		
		
		
		//Kurs Auswahahlen
		
		int kursNr = 5;
		
		moodleConnection.setUrlSiteInMoodle(kurse.getURL(kursNr-1));
		moodleConnection.createDocument();
		
		FolderAndFiles kurs = new FolderAndFiles(moodleConnection.getElements());
		
		kurs.printElementsArray();
		
		
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		
		kurs.printFiles();
		
		
		
		
		
		
		
		
		
		
		
		
		
		System.out.println("...Programm ende... Programm muede");
		
		
		
		
		
	}

}
