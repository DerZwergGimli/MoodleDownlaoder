package functions;

import java.io.IOException;
import java.util.Arrays;

import org.jsoup.Connection;
import org.jsoup.Connection.Response;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import objects.Folder;

import org.jsoup.Jsoup;

public class MConnect 
{ 
	private Connection.Response response;
	private Document document;
	private Elements elements;
	public String[][] elementsArray;
	
	private String username;
	private String password;
	
	private String urlMoodleBase;			//"https://moodle.htwg-konstanz.de/moodle/login/index.php"
	private String urlSiteInMoodle; 
	
	
	//Constructor
	
	public MConnect(String username, String password, String urlMoodleBase) {
		super();
		this.username = username;
		this.password = password;
		this.urlMoodleBase = urlMoodleBase;
		
		createCookie();
		createDocument();
		
		getNextUrl();
		
	}

	
	
	
	//Setter-Methodes
	public void setUrlMoodleBase (String urlMoodleBase) 
	{
		this.urlMoodleBase = urlMoodleBase;
	}
	public void setUsername(String username) 
	{
		this.username = username;
	}
	public void setPassword(String password) 
	{
		this.password = password;
	}
	public void setUrlSiteInMoodle(String urlSiteInMoodle) 
	{
		this.urlSiteInMoodle = urlSiteInMoodle;
	}
	
	
	
	//Getter-Methodes
	public Document getDocument() 
	{
		return this.document;
	}
	public Elements getElements() 
	{
		return this.elements;
	}
	public String getUrlSiteInMoodle() 
	{
		return this.urlSiteInMoodle; 
	}
	public String[][] getElementsArray()
	{
		return this.elementsArray;
		
	}
	
	
	
	




	public void getNextUrl() 
	{
		this.urlSiteInMoodle = response.url().toString();
	}




	//other
	public void createCookie() 
	{
		
		try {
			this.response = Jsoup
					.connect(urlMoodleBase)
					.data("username", this.username)
					.data("password", this.password)
					.userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
					.method(Connection.Method.POST)
					
					.execute();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Something bad happend");
		}
		
	}
	
	public void createDocument()
	{
		
		if (urlSiteInMoodle == null) 
		{

			urlSiteInMoodle = response.url().toString();
		}	
		
		try {
			document = Jsoup
					.connect(urlSiteInMoodle)
					.cookies(response.cookies())
					.get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Something bad happend");
		}
		
		createElements("a");
	}
	
	public void createElements(String selectBy) 
	{
		if (selectBy == null) {
			selectBy = "a[href]"; //Standart soll nach links gesucht werden diese beginnen mit href
		}
		elements = document.select(selectBy);
	}
	
	



	public void createElementsArray() 
	{
		createElements("a[href]");
	
		elementsArray = new String[elements.size()][3];
		int y = 0;
		for (Element kurs : elements) {

			int x = 0;
			
			
			elementsArray[y][x] = kurs.text(); //Speichere in [][0] den Namen des Links
			
			
			//gebe an ob Datei der Order
			
			if (elementsArray[y][x].contains("section")||elementsArray[y][x].contains("Verzeichnis"))
			
			{
				
				elementsArray[y][x+1] = "dr"; 
				//Create Folder
				//folder[anzahlFolder] = new Folder(kurs.text(), "dr", kurs.absUrl("href"), anzahlFolder);
				//anzahlFolder++;
				
			}
			
			if (elementsArray[y][x].contains(".pdf")) 
			{
				elementsArray[y][x+1] = "f";
				
			}
			
		
			
			
			if (elementsArray[y][x].contains("Datei")||elementsArray[y][x].contains("File")) 
			{
				elementsArray[y][x+1] = "f"; //f=file
				//Create File
				//file[anzahlFiles] = new File(kurs.text(), "f", kurs.absUrl("href"), anzahlFiles);
				//anzahlFiles++;		
			} 
			
			x = x+2;
			
			elementsArray[y][x] = kurs.absUrl("href"); //Speichere in [][2] den Link der Datei/Ordner
			y++;
			

		
		}
		System.out.println("done");;
	
	}





	
	
	
	
	

}
