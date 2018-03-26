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
	private String[][] elementsArray;
	
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
	
	
	
	
	public String getForCursToString() 
	{
		//createDocument();
		//documentToElements("a");
		createElementsArray();
		
		
		return Arrays.toString(elementsArray);
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
	
	public void createCurses() 
	{
		//Convert  elements to String just to be shure
		createElementsArray();
		
		
		//Scheide von oben die Liste ab
		String[][] newArray = new String[100][3];
		int j=0;
		
			for (int i = 90; i < elementsArray.length; i++) 
			{
				newArray[j][0]=elementsArray[i][0];
				newArray[j][1]=elementsArray[i][1];
				newArray[j][2]=elementsArray[i][2];
				j++;
				
			}
		
	
		
		//Ermittle letzten Kurs
		int endeListe=0;
		for (int i = 0; i < newArray.length; i++) 
		{
			if (newArray[i][2].equals("https://moodle.htwg-konstanz.de/moodle/course/index.php")) 
			{
				endeListe = i;
				break;
			}
			
		}
		
		//Baue Ausgabe Array neu
		String[][] finalArray = new String[endeListe][3];
		
		for (int i = 0; i < endeListe; i++) 
		{
			finalArray[i][0] = newArray[i][0];
			finalArray[i][1] = newArray[i][1];
			finalArray[i][2] = newArray[i][2];
			
		}
		
		
		this.elementsArray = finalArray;
		
		
		
		
	}




	public void createElementsArray() 
	{
		createElements("a[href]");
	
		int length = elements.size();
		this.elementsArray = new String[length][3];
		
		int y = 0;
		for (Element kurs : this.elements) {

			int x = 0;
			
			
			this.elementsArray[y][x] = kurs.text(); //Speichere in [][0] den Namen des Links
			
			
			//gebe an ob Datei der Order
			
			if (elementsArray[y][x].contains("Ordner")||elementsArray[y][x].contains("Folder")) 
			{
				elementsArray[y][x+1] = "dr"; 
				//Create Folder
				//folder[anzahlFolder] = new Folder(kurs.text(), "dr", kurs.absUrl("href"), anzahlFolder);
				//anzahlFolder++;
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
	
	}





	
	
	
	
	

}
