
package functions;

import java.io.IOException;
import java.util.Scanner;

public class Funktions 
{

	
	
	
	public static int eingabeUser(Scanner reader) throws IOException 
	{
		
		System.out.println("Enter a number: ");
		int n = reader.nextInt(); // Scans the next token of the input as an int.
		//once finished
		


	    System.out.println(System.in.available());
		return n;
		
	}
	
	public static void ausgabe2dArray(String [][] array) 
	{
		
		for (int i = 0; i < array.length; i++) 
		{
			for (int j = 0; j < 3; j++) 
			{
				System.out.println(array[i][j]);
				
			}
			
		}
		
	}
	
	public static String[][] getOnlyCurses(String[][] curses) 
	{
		//Convert  elements to String just to be shure
		//createElementsArray();
		
		
		//Scheide von oben die Liste ab
		String[][] newArray = new String[100][3];
		int j=0;
		
			for (int i = 90; i < curses.length; i++) 
			{
				newArray[j][0]=curses[i][0];
				newArray[j][1]=curses[i][1];
				newArray[j][2]=curses[i][2];
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
		
		
		curses = finalArray;
		return curses;
		
		
		
		
	}
	
	
	

}
