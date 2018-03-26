
import java.*;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;
import java.nio.*;
import java.nio.channels.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.print.Doc;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.font.PDTrueTypeFont;
import org.apache.commons.codec.StringEncoderComparator;
import org.apache.commons.io.FileUtils;
import org.apache.pdfbox.pdmodel.*;
import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import org.jsoup.helper.HttpConnection;

import org.apache.commons.*;
public class main 
{

	public static void main(String[] args) throws IOException 
	{
		System.out.println("...Started");

		Scanner reader = new Scanner(System.in);  // Make Scanner for User Inputs
		
		String username = "ya721pau";
		String password = "htwg-gai-22";
		String urlBase = "https://moodle.htwg-konstanz.de/moodle/login/index.php";

		int input;
		
		Connection.Response res;

		res = connectToMoode(username, password, urlBase);

		System.out.println(res.url());
		
		System.out.println(res.statusCode());

		System.out.println("-------------------------------------------------------------------------------------------------------------------");
		
		
		
		//Go to Main Moodle Page
		//String url = "https://moodle.htwg-konstanz.de/moodle/";
		
		String url = res.url().toString();
		//Document doc = getDocumentFormServer(res, url);
		Document doc = Jsoup.connect(url)
						// .data("username", username)
						// .data("password", password)
						.cookies(res.cookies())
						.method(Connection.Method.GET)
						.get();

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
		
		//User Choose Kurse
		System.out.println("Choose Curse :");
		input = eingabeUser(reader);
		
		url = KursArray[start+input-1][1].toString(); //This is the Elektrotechnik 2 course 
		
		System.out.println("Ausgewaehlter Kurs: "+url);
		Document docX = getDocumentFormServer(res, url);
		Elements Vorlesung = docX.select("a[href]");
		String[][] VorlesungArray = makeList(Vorlesung);
		
		
		start = makeStartValue(VorlesungArray, "Kurse");
		ende = makeLenghtValue(VorlesungArray, "Einstellungen überspringen");
		
		
		cleanOutput(VorlesungArray, start, ende);
		
		
		System.out.println("==============================================================================");
		
		//File to Download
		
		
		System.out.println("Choose File to Download");
		input = 0;
		
		input = eingabeUser(reader);
		

		
		url = VorlesungArray[start + input-1][1].toString();
		
		System.out.println("Download Link (File): "+url);
		
		String filename = VorlesungArray[start+input-1][0].toString();
		System.out.println(filename);
		
		downloadFile(url, (filename+".pdf"), res);
		//downloadNummer3(url, 0, res);
		
		System.out.println();
		
		//Close all
		reader.close(); 
		
		System.out.println("Programm ende... programm geht schalfen programm ist muede...");

	}

	
	
	
	
	
	
	
	
	
	
	
	public static Connection.Response connectToMoode(String username, String password, String url) {
		Connection.Response res = null;
		try {

			res = Jsoup.connect("https://moodle.htwg-konstanz.de/moodle/login/index.php")
					.data("username", username)
					.data("password", password)
					.userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
					//.referrer("http://www.google.com")
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
			System.out.print((i-start+1)+") Name: " + Array[i][0]);
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
	
	public static String downloadNummer3(String link, int i, Response res) 
	{
		
		InputStream input = null;
	    OutputStream output = null;
	    HttpURLConnection connection = null;
	    
	   // CookieHandler.setDefault(new CookieManager());
	   
	    try {
	        URL url = new URL(link);
	        
	        
	        connection = (HttpURLConnection) url.openConnection();
	        //connection.setRequestProperty("username", "ya721pau");
	        //connection.setRequestProperty("password", "htwg-gai-22");
	        //connection.setRequestProperty("Cookie", res.cookies());
	        connection.connect();

	        // expect HTTP 200 OK, so we don't mistakenly save error report
	        // instead of the file
	        if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
	            return "Server returned HTTP " + connection.getResponseCode()
	                    + " " + connection.getResponseMessage();
	        }

	        // this will be useful to display download percentage
	        // might be -1: server did not report the length
	        int fileLength = connection.getContentLength();

	        // download the file
	        input = connection.getInputStream();
	        output = new FileOutputStream("file");

	        byte data[] = new byte[4096];
	        int count;
	        while ((count = input.read(data)) != -1) {
	            output.write(data, 0, count);
	        }
	    } catch (Exception e) {
	        return e.toString();
	    } finally {
	        try {
	            if (output != null)
	                output.close();
	            if (input != null)
	                input.close();
	        } catch (IOException ignored) {
	        }

	        if (connection != null)
	            connection.disconnect();
	    }
		return null;
		
	}
	

	
	public static String exeptionToLinkConverter(String message) 
	{
		int max=0;
		String[] parts = message.split(", ");
		String link;
		for (int i = 0; i < parts.length; i++) {
			
			if (parts[i].compareTo("URL=") > max) 
			{
				max = i;
			}
		
			
		}
		link = parts[max];
		link = link.substring(4);
		return link;
	}
	
	public static int eingabeUser(Scanner reader) throws IOException 
	{
		
		System.out.println("Enter a number: ");
		int n = reader.nextInt(); // Scans the next token of the input as an int.
		//once finished
		


	    System.out.println(System.in.available());
		return n;
		
	}


}
