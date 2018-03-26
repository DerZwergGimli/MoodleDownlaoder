package helloWorld;

public class main {

	public static void main(String[] args) 
	{
		
		YGenericTestP max = new YGenericTestP("Max");
		YGenericTestP tom = new YGenericTestP("Tom");
		YGenericTestP peter = new YGenericTestP("Peter");

		max.print();
		tom.print();
		peter.print();
		
		YMeasurementP maxM = new YMeasurementP(80,60,100);
		YMeasurementP tomM = new YMeasurementP(90,70,90);
		YMeasurementP peterM = new YMeasurementP(90,80,80);
		
		maxM.printValues();
		tomM.printValues();
		peterM.printValues();
		
		
		
	}

}
