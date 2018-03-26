package functions;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.Connection.Response;

import objects.*;

public class Control 
{
	
	Login login;
	MConnect connection;
	
	ArrayList<List> summerizedList;
	ArrayList<List> downloadList = new ArrayList<List>();
	
	public Control()
	{
		login = new Login("ya721pau", "htwg-gai-22", "https://moodle.htwg-konstanz.de/moodle/login/index.php");
		
		connection = new MConnect(login);
		
	}
	
	public Control(Login login) 
	{
		this.login = login;
		this.connection = new MConnect(this.login);
	}
	
	
	
	
	
	
	public ArrayList<Courses> getCurses() 
	{
		String[][] coursesArray = connection.fetchData(null);
		ArrayList<Courses> list_Courses = new ArrayList<Courses>();
		
		for (int i = 89; i < coursesArray.length; i++) 
		{
			if (coursesArray[i][2].contains("course") == true) 
			{
				list_Courses.add(new Courses(coursesArray[i][0], coursesArray[i][2], "Course", i));
			}
			
		}
		return list_Courses;
		
		
	}
	
	public ArrayList<File> getFiles(String link)
	{
		String [][] filesArray = connection.fetchData(link);
		ArrayList<File> list_Files  = new ArrayList<File>();
		
		for (int i = 0; i < filesArray.length; i++) 
		{
			if (filesArray[i][2].contains("resource") == true) 
			{
				list_Files.add(new File(i, filesArray[i][0], filesArray[i][2], "nothing", "pdf"));
			}
			
		}
		
		
		
		return list_Files;
	}
	
	public ArrayList<Folder> getFolder(String link)
	{
		String [][] folderArray = connection.fetchData(link);
		ArrayList<Folder> list_Folder = new ArrayList<Folder>();
		
		for (int i = 0; i < folderArray.length; i++) 
		{
			if (folderArray[i][2].contains("folder") == true) 
			{
				list_Folder.add(new Folder(i, folderArray[i][0], folderArray[i][2], "nothing"));
			}
			
		}
		
		return list_Folder;
	}
	
	public ArrayList<File> getFolderContent(String link)
	{
		String [][] contentArray = connection.fetchData(link);
		ArrayList<File> list_Files  = new ArrayList<File>();
		
		for (int i = 0; i < contentArray.length; i++) 
		{
			if (contentArray[i][2].contains("content") == true) 
			{
				list_Files.add(new File(i, contentArray[i][0], contentArray[i][2], "nothing", "pdf"));
			}
			
		}
		
		
		
		return list_Files;
	}
	
	public ArrayList<List> summarizeCourseData(int selectedCourse) 
	{
		summerizedList = new ArrayList<List>();
		
		//this funktion is for collecting all Folders and Files in a Curse
		//1. We serach and add all Files in the Curse
		//2. We scan for Folders
		//3. We collect all Files in a/all Folders 
		//4. We summarize all Files and Folder together
		int id = 0;
		
		//temp
		ArrayList<Courses> list_Courses = getCurses();
		
		//int selectedCourse = 1;
		//First Add Current curse Folder so we can save them later easier
		summerizedList.add(new List(id, list_Courses.get(selectedCourse).getName(), true, list_Courses.get(selectedCourse).getName(), list_Courses.get(selectedCourse).getLink()));
		id++;
		
		//search for files and add thme
		ArrayList<File> list_Files = getFiles(list_Courses.get(selectedCourse).getLink());
		for (int i = 0; i < list_Files.size(); i++) {
			summerizedList.add(new List(id, list_Courses.get(selectedCourse).getName(), false, list_Files.get(i).getName(), list_Files.get(i).getLink()));
			id++;
		}
		
		//search for Folders
		ArrayList<Folder> list_Folder = getFolder(list_Courses.get(selectedCourse).getLink());
		System.out.println();
		for (int i = 0; i < list_Folder.size(); i++) {
			//add new Folder
			summerizedList.add(new List(id, list_Courses.get(selectedCourse).getName(), true, list_Folder.get(i).getName(), list_Folder.get(i).getLink()));
			id++;
			//search for all Files and add them
			ArrayList<File> filesInFolder = getFolderContent(list_Folder.get(i).getLink());
			for (int j = 0; j < filesInFolder.size(); j++) {
				summerizedList.add(new List(id, list_Courses.get(selectedCourse).getName(), false, filesInFolder.get(j).getName(), filesInFolder.get(j).getLink()));
				id++;
			}
		}
	
		printToScreenSummerizedData();
		
		return summerizedList;
		
		
		
	}
	
	public void printToScreenSummerizedData() 
	{
		for (int i = 0; i < summerizedList.size(); i++) {
			System.out.print(i);
			System.out.print("- "+ summerizedList.get(i).getId()+" -> ");
			System.out.print("- "+ summerizedList.get(i).getCourseName()+" -> ");
			System.out.print("- "+ summerizedList.get(i).isFolder()+" -> ");
			System.out.print("- "+ summerizedList.get(i).getName()+" -> ");
			System.out.println("- "+ summerizedList.get(i).getLink()+" -> ");
			
		}
	}
	
	public static void downloadFile(String link, String name, Response res) throws IOException 
	{
		
		Response docRespone = Jsoup.connect(link).maxBodySize(0).timeout(600000).cookies(res.cookies())
                .ignoreContentType(true).execute();
		System.out.println("Downloadung ..."+docRespone.url());
		
		FileOutputStream out = (new FileOutputStream(new java.io.File(name)));
		out.write(docRespone.bodyAsBytes());  // resultImageResponse.body() is where the image's contents are.
		byte[] buffer =  new byte[504096];
		int bytesRead = 0;
		
		
		/*
		
		while ((bytesRead = 0) != -1) {
            out.write(buffer, 0, bytesRead);
            //System.out.println(".");
        }
		*/
		out.close();
		
		
	}
	
	public void addToDownload(int id) 
	{
		downloadList.add(summerizedList.get(id));
	}
	
	public void removeFromDownload() 
	{
		
	}
	
	public ArrayList<List> getDownloadList() 
	{
		return downloadList;
	}
	
}
