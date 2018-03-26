import java.io.IOException;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;





public class main {

	public static void main(String[] args) 
	{
		System.out.println("...Started");
		
		
		String username = "ya721pau";
		String password = "htwg-gai-22";
		
		
		int keineKurse  = 17; //Liste von oben beginnt mit Name, Dashboard, Profiel etc.. mit den Sprachen sind es ins 17 Elemente die irrelevant sind.
		
		
		
		Connection.Response res;
		try {
		
					
			res = Jsoup
				    .connect("https://moodle.htwg-konstanz.de/moodle/login/index.php")
				    .data("username", username) 
				    .data("password", password)
				    //.userAgent( "\"Mozilla/5.0 (Windows NT\"")
				    .method(Connection.Method.POST)
				    .execute();
			
			System.out.println(res.url());
			System.out.println(res.statusCode());
			

			//This will get you cookies
			//Map<String, String> loginCookies = res.cookies();

			
			
			//Here you parse the page that you want. Put the url that you see when you have logged in
			String url = "https://moodle.htwg-konstanz.de/moodle/";
			
			Document doc = Jsoup.connect(url)
					//.data("username", username)
					//.data("password", password)
					.cookies(res.cookies())
					.get();
			
			
			//doc = Jsoup.connect("https://moodle.htwg-konstanz.de/moodle/").get();
			
			
			
			
			
			String title = doc.title();
			System.out.println("Title: "+title); 
			
			Elements links = doc.select("a");
			System.out.println(links.text());
			
			
			
			
			
			
			
			System.out.println("-----------------------------------------------------------");
			
			
			Elements kurse = doc.select("a");
			//System.out.println(kurse.text());
			//System.out.println(kurse.get(5));
			
			
			//Remove not relevant elements
			
			
			//System.out.println(kurse.text());
			//System.out.println(kurse.size());
			
			
			//System.out.println(kurse.attr("abs:href"));
			
			
			String [][] KursArray = new String [kurse.size()][3];
			int y = 0;
			for (Element kurs : kurse) 
			{
				
					int x = 0;
					KursArray[y][x] = kurs.text();
					x++;
					KursArray[y][x] = kurs.absUrl("href");
					
					y++;
				//System.out.println("Text: "+kurs.text());
				//System.out.println("URL: "+kurs.absUrl("href"));
				
			}
			
			
			for (int i = 90; i < KursArray.length-13; i++) 
			{
				System.out.print("Name: "+KursArray[i][0]);
				System.out.println("\t\t--> Link: "+KursArray[i][1]);
				
			}
			
			
			
			
			
			System.out.println("...Programm ENDE");
			
			
			//Kurs Auswahlen (erst Spater)
			
			
			
			Document doc0 = Jsoup.connect(KursArray[92][1])
					//.data("username", username)
					//.data("password", password)
					.cookies(res.cookies())
					.get();
			
			
			Elements ausgewaehlterKurs = doc0.select("a[href]");
			System.out.println(ausgewaehlterKurs.text());
			
			String [][] AktuellerKursArray = new String [ausgewaehlterKurs.size()][3];
			int y1 = 0;
			for (Element kurs : ausgewaehlterKurs) 
			{
				
					int x = 0;
					AktuellerKursArray[y1][x] = ausgewaehlterKurs.text();
					x++;
					AktuellerKursArray[y1][x] = "hsd";
					
					y1++;
				//System.out.println("Text: "+kurs.text());
				//System.out.println("URL: "+kurs.absUrl("href"));
				
			}
			
			
			for (int i = 0; i < AktuellerKursArray.length-13; i++) 
			{
				System.out.print("Name: "+AktuellerKursArray[i][0]);
				System.out.println("\t\t--> Link: "+AktuellerKursArray[i][1]);
				
			}
			
			
			
			
			
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		//Document doc = null;
		
		
	}

}
