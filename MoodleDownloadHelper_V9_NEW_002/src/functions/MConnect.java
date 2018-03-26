package functions;

import java.io.IOException;
import java.util.Arrays;

import org.jsoup.Connection;
import org.jsoup.Connection.Response;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import objects.Login;

import org.jsoup.Jsoup;

public class MConnect 
{ 
	private Connection.Response response;
	private Document document;
	private Elements elements;
	private String[][] elementsArray;
	
	private Login login;
	
	private String urlMoodleBase;			//"https://moodle.htwg-konstanz.de/moodle/login/index.php"
	private String urlSiteInMoodle; 
	
	
	//Constructor
	
	public MConnect(Login login) 
	{
		super();
		this.login = login;
		this.urlMoodleBase = login.getMoodleUrl();
		createCookie();
		
	}
	
	public void createCookie() 
	{
		
		try {
			this.response = Jsoup
					.connect(urlMoodleBase)
					.data("username", this.login.getUsername())
					.data("password", this.login.getPassword())
					.userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
					.method(Connection.Method.POST)
					
					.execute();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Something bad happend");
		}
		
	}

	public void createDocument(String link)
	{
		
		urlSiteInMoodle = link;
		
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
		
		for (int i = 0; i < elementsArray.length; i++) 
		{
			elementsArray[i][0] = elements.get(i).text();
			elementsArray[i][1] = null;
			elementsArray[i][2] = elements.get(i).absUrl("href");
			
		}
		
		System.out.println("done");;
	}

	public String[][] fetchData(String link) 
	{
		createDocument(link);
		createElements("a[href]");
		createElementsArray();
		
		return elementsArray;
	}


	
	
	
	
	

}
