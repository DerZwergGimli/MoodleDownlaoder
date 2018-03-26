import java.io.IOException;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;





public class main {

	public static void main(String[] args) 
	{
		System.out.println("...Started");
		
		
		try {
		
			//Response res;
			
			Response res = Jsoup
				    .connect("https://moodle.htwg-konstanz.de/moodle/index.php")
				    .data("username", "ya721pau") 
				    .data("password", "htwg-gai-22")
				    //.userAgent( "\"Mozilla/5.0 (Windows NT\"")
				    .method(Method.POST)
				    .execute();
			

			//This will get you cookies
			Map<String, String> loginCookies = res.cookies();

			//Here you parse the page that you want. Put the url that you see when you have logged in
			Document doc = Jsoup.connect("https://moodle.htwg-konstanz.de/moodle/course/view.php?id=2931")
			      .cookies(loginCookies)
			      .get();
			
			
			//doc = Jsoup.connect("https://moodle.htwg-konstanz.de/moodle/").get();
			
			
			
			
			
			String title = doc.title();
			System.out.println("Title: "+title); 
			
			Elements links = doc.select("a[class]");
			for (int i = 0; i < links.size(); i++) 
			{
				System.out.println("\nLink:" + links.attr("class"));
				System.out.println("Text :" + links.text());
			}
			
			
			
			
			
			
			System.out.println("-----------------------------------------------------------");
			
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		//Document doc = null;
		
		
	}

}
