package helloWorld;


import java.text.SimpleDateFormat;
import java.util.Date;

public class YMeasurementP 
{

	
	int pulse;
	int systolicBlood;
	int diastolicBlood;
	String time;
	
	
	//Standartkonstruktor
	public YMeasurementP(int pulse, int systolicBlood, int diastolicBlood) 
	{
		this.pulse = pulse;
		this.systolicBlood = systolicBlood;
		this.diastolicBlood = diastolicBlood;
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yy HH-mm-ss");
		Date date = new Date();
		this.time = dateFormat.format(date);
		
	}
	
	//getMethoden fuer alle Objektattribute
	
	public int getPulse() 
	{
		return this.pulse;
	}
	
	public int getSystolicBlood() 
	{
		return this.systolicBlood;
	}
	
	public int getDiastolicBlood() 
	{
		return this.diastolicBlood;
	}
	
	//printValues Methode
	public void printValues() 
	{
		System.out.print(this.pulse+" ");
		System.out.print(this.systolicBlood+" ");
		System.out.print(this.diastolicBlood+" ");
		System.out.println(time);
	}
	
}
