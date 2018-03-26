package helloWorld;


import java.text.*;

import java.util.*;

public class YGenericTestP 
{
	

	String name;
	
	String dateAndTime;
	
	
	
	
	
	public YGenericTestP(String name) 
	{
		this.name=name;
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yy HH-mm-ss");
		Date dateobj = new Date();
		this.dateAndTime = dateFormat.format(dateobj);
	}
	
	public String getName() 
	{
		return this.name;
	}
	
	public void setName(String name)
	{
		this.name=name;
	}
	
	public void print() 
	{
		System.out.println(name);
	}
	
	

}
