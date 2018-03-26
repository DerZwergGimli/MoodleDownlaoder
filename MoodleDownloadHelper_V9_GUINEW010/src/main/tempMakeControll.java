package main;

import functions.Control;
import objects.Login;

public class tempMakeControll {

	
	
	
	public static void main(String[] args) 
	{
	
		Control control;
		Login login;
		//Login
		login = new Login("ya721pau", "htwg-gai-22", "https://moodle.htwg-konstanz.de/moodle/login/index.php");
		control = new Control(login);
		
		
		
		//getCurses As Array
		String curses[] = control.getCursesAsArray();
		for (int i = 0; i < curses.length; i++) 
		{
			System.out.println(i+") " + curses[i]);
			
		}
		
		//SelectCurse and get Files
		int selectedCourse = 1;
		
		control.fetchFoldersOfCourse(selectedCourse);
		control.getStringForCurse_JList(selectedCourse);
		
		String [][] jListArrayTemp = control.getStringForCurse_JList(selectedCourse);
		
		for (int i = 0; i < jListArrayTemp.length; i++) 
		{
			System.out.println(i+") "+jListArrayTemp[i][0]+ " | "+jListArrayTemp[i][1] + " | "+jListArrayTemp[i][2]);
			
		}
		
	}

}
