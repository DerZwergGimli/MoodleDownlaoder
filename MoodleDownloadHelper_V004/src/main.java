
import java.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.*;
import java.nio.*;
import java.nio.channels.*;


import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

public class main {

	public static void main(String[] args) throws IOException 
	{
		System.out.println("...Started");

		String username = "ya721pau";
		String password = "htwg-gai-22";
		String urlBase = "https://moodle.htwg-konstanz.de/moodle/login/index.php";

		Connection.Response res;

		res = connectToMoode(username, password, urlBase);

		System.out.println(res.url());
		System.out.println(res.statusCode());

		System.out.println("-------------------------------------------------------------------------------------------------------------------");
		
		
		//Go to Main Moodle Page
		String url = "https://moodle.htwg-konstanz.de/moodle/";
		
		Document doc = getDocumentFormServer(res, url);

		//String title = doc.title();
		//System.out.println("Title: " + title);

		
		//Elements links = doc.select("a");
		//System.out.println(links.text());

		System.out.println("-----------------------------------------------------------");

		Elements kurse = doc.select("a[href]");
		String[][] KursArray = makeList(kurse);
		
		//counter for start valueMain
		int start = makeStartValue(KursArray, "EIB Info");
		int ende = makeLenghtValue(KursArray, "Alle Kurse");
		
		
		
		

		cleanOutput(KursArray, start, ende);

		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		
		//Go to a Kurse and show me the Data
		url = KursArray[92][1]; //This is the Elektrotechnik 2 course 
		Document docX = getDocumentFormServer(res, url);
		Elements Vorlesung = docX.select("a[href]");
		String[][] VorlesungArray = makeList(Vorlesung);
		
		
		start = makeStartValue(VorlesungArray, "Skript Datei");
		ende = makeLenghtValue(VorlesungArray, "Einstellungen überspringen");
		
		
		cleanOutput(VorlesungArray, start, ende);
		
		
		
		
		
		//Download File
		System.out.println(VorlesungArray[VorlesungArray.length-ende-1][1]);
		
		String saveFileTo = "/home/yannick/Desktop/downoadTempWhatever/";
		
		url = "https://moodle.htwg-konstanz.de/moodle/mod/resource/view.php?id=79214";
		
		//Document docF = getDocumentFormServer(res, url);
		
		//Elements pdf  = docF.select("embed");
		//String linkToFile = pdf.attr("src");
		
	
		
		
		//downloadFile(url, saveFileTo);
		
		System.out.println("-----------------------------------");
		Document docPDF = getDocumentFormServer(res, url);
		String title = docPDF.title();
		System.out.println(title);
		
		
		
		System.out.println("...Programm ENDE");

	}

	
	
	
	
	
	
	
	
	
	
	
	public static Connection.Response connectToMoode(String username, String password, String url) {
		Connection.Response res = null;
		try {

			res = Jsoup.connect("https://moodle.htwg-konstanz.de/moodle/login/index.php").data("username", username)
					.data("password", password)
					// .userAgent( "\"Mozilla/5.0 (Windows NT\"")
					.method(Connection.Method.POST).execute();

		} catch (Exception e) {
			// TODO: handle exception
		}

		return res;

	}

	public static Document getDocumentFormServer(Response res, String url) {
		Document doc = null;
		try {

			doc = Jsoup.connect(url)
					// .data("username", username)
					// .data("password", password)
					.cookies(res.cookies()).get();

		} catch (Exception e) {
			// TODO: handle exception
		}

		return doc;

	}

	public static String[][] makeList(Elements kurse) {

		String[][] Array = new String[kurse.size()][3];

		int y = 0;
		for (Element kurs : kurse) {

			int x = 0;
			Array[y][x] = kurs.text();
			x++;
			Array[y][x] = kurs.absUrl("href");

			y++;
			// System.out.println("Text: "+kurs.text());
			// System.out.println("URL: "+kurs.absUrl("href"));

		}

		return Array;
	}

	public static void cleanOutput(String[][] Array, int start, int ende) {
		for (int i = start; i < Array.length - ende; i++) {
			System.out.print("Name: " + Array[i][0]);
			System.out.println("\t\t--> Link: " + Array[i][1]);

		}

	}
	
	public static int makeStartValue(String[][] Array, String testFor) 
	{
		int start = 0;
		
		for (int i = 0; i < Array.length; i++) 
		{
			
			if (Array[i][0].equals(testFor)) 
			{
				start = i;
				System.out.println("True = "+start);
				break;
			}
			else System.out.println("False");
		}
		return start;
		
	}
	
	public static int makeLenghtValue(String[][] Array, String testFor) 
	{
		int length = 0;
		
		for (int i = Array.length-1; i >= 0; i--) 
		{
			
			if (Array[i][0].equals(testFor)) 
			{
				length = i;
				System.out.println("True = "+length);
				break;
			}
			else System.out.println("False");
		}
		
		if (length == 0) {
			return length;

		} else {
			return (Array.length - length);
		}
	}
	
	public static void downloadFile(String url, String output) throws IOException 
	{
		
		URL website;
		try {
			website = new URL(url);
			ReadableByteChannel rbc = Channels.newChannel(website.openStream());
			FileOutputStream fos = new FileOutputStream(output);
			fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
			
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
