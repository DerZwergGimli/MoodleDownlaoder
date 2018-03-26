package functions;

import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Connection.Response;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.Jsoup;

public class MConnect 
{ 
	private Connection.Response response;
	private Document document;
	private Elements elements;
	
	private String username;
	private String password;
	
	private String urlMoodleBase;			//"https://moodle.htwg-konstanz.de/moodle/login/index.php"
	private String urlSiteInMoodle; 
	
	
	//Constructor
	
	public MConnect(String username, String password, String urlMoodleBase) {
		//super();
		this.username = username;
		this.password = password;
		this.urlMoodleBase = urlMoodleBase;
		
		createCookie();
		createDocument();
		
		returnNextUrl();
		
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
		
		documentToElements("a");
	}
	
	public void documentToElements(String selectBy) 
	{
		if (selectBy == null) {
			selectBy = "a[href]"; //Standart soll nach links gesucht werden diese beginnen mit href
		}
		elements = document.select(selectBy);
	}
	
	public void returnNextUrl() 
	{
		this.urlSiteInMoodle = response.url().toString();
	}
	
	
	
	
	
	
	

}
