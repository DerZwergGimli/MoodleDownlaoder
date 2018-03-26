package objects;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.omg.CORBA.PUBLIC_MEMBER;

public class FolderAndFiles 
{
	
	private Elements elements;
	private String[][] elementsArray;
	
	private Folder[] folder;
	private File[] file;
	private Folder[] kurs;
			
	private int anzahlFolder=0;
	private int anzahlFiles=0;
	
	
	
	


	public String[][] getElementsArray()
	{
		return this.elementsArray;
	}

	public Folder[] getFolder() {
		return folder;
	}



	public void setFolder(Folder[] folder) {
		this.folder = folder;
	}



	public File[] getFile() {
		return file;
	}



	public void setFile(File[] file) {
		this.file = file;
	}



	public FolderAndFiles(Elements elements) {
		super();
		this.elements = elements;
		
		int x=0;
		for(Element element : elements) 
		{x++;}
		
		this.elementsArray = new String[x][3];
		//this.elementsArray = elementsArray;
		elementsToString();
	}



	public void elementsToString() 
	{
		
	
		
		int y = 0;
		for (Element kurs : elements) {

			int x = 0;
			
			
			elementsArray[y][x] = kurs.text(); //Speichere in [][0] den Namen des Links
			
			
			//gebe an ob Datei der Order
			
			if (elementsArray[y][x].contains("Ordner")||elementsArray[y][x].contains("Folder")) 
			{
				elementsArray[y][x+1] = "dr"; 
				//Create Folder
				//folder[anzahlFolder] = new Folder(kurs.text(), "dr", kurs.absUrl("href"), anzahlFolder);
				anzahlFolder++;
			}
			
			
			if (elementsArray[y][x].contains("Datei")||elementsArray[y][x].contains("File")) 
			{
				elementsArray[y][x+1] = "f"; //f=file
				//Create File
				//file[anzahlFiles] = new File(kurs.text(), "f", kurs.absUrl("href"), anzahlFiles);
				anzahlFiles++;		
			} 
			
			x = x+2;
			
			elementsArray[y][x] = kurs.absUrl("href"); //Speichere in [][2] den Link der Datei/Ordner
			y++;
			

		
		}
	
	}
	
	public  void makeKurse(String url, String[][] oldArray) 
	{
		String[][] newArray = new String[100][3];
		int j=0;
		if (url.equals("https://moodle.htwg-konstanz.de/moodle/")) 
		{
			for (int i = 90; i < elementsArray.length; i++) 
			{
				newArray[j][0]=oldArray[i][0];
				newArray[j][1]=oldArray[i][1];
				newArray[j][2]=oldArray[i][2];
				j++;
				
			}
		
	
		}
		
		int endeListe=0;
		for (int i = 0; i < newArray.length; i++) 
		{
			if (newArray[i][2].equals("https://moodle.htwg-konstanz.de/moodle/course/index.php")) 
			{
				endeListe = i;
				break;
			}
			
		}
		
		String[][] finalArray = new String[endeListe][3];
		
		for (int i = 0; i < finalArray.length; i++) 
		{
			finalArray[i][0] = newArray[i][0];
			finalArray[i][1] = newArray[i][1];
			finalArray[i][2] = newArray[i][2];
			
		}
		
		
		this.elementsArray = finalArray;
		
		
		
		
	}
	
	public String getURL(int index) 
	{
		System.out.println(elementsArray[index][2]);
		return this.elementsArray[index][2];
	}
	
	
	
public void printElementsArray() 
{
	
	for (int i = 0; i < elementsArray.length; i++) 
	{
		System.out.println((i+1)+") Name: "+elementsArray[i][0]+ "-> TYPE: "+elementsArray[i][1]+" -> Link: "+elementsArray[i][2]);
		
	}
	
	}

public void printFiles() 
	{
	for (int i = 0; i < elementsArray.length; i++) 
	{
		if (elementsArray[i][1]== null) {
			
		} else if (elementsArray[i][1].equals("f")) 
		{
			System.out.println(elementsArray[i][0]);
		}
		
	}
	
	}
	
	
}
