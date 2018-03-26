
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
	
	
	
	

}
