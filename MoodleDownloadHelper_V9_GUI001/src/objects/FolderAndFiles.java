package objects;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class FolderAndFiles 
{
	
	private Elements elements;
	private String[][] elementsArray;
	
	private Folder[] folder;
	private File[] file;
			
	private int anzahlFolder=0;
	private int anzahlFiles=0;
	
	
	
	



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
				folder[anzahlFolder] = new Folder(kurs.text(), "dr", kurs.absUrl("href"), anzahlFolder);
				anzahlFolder++;
			}
			
			
			if (elementsArray[y][x].contains("Datei")||elementsArray[y][x].contains("File")) 
			{
				elementsArray[y][x+1] = "f"; //f=file
				//Create File
				file[anzahlFiles] = new File(kurs.text(), "f", kurs.absUrl("href"), anzahlFiles);
				anzahlFiles++;		
			} 
			
			x = x+2;
			
			elementsArray[y][x] = kurs.absUrl("href"); //Speichere in [][2] den Link der Datei/Ordner
			y++;
			

		
	}
	

}
}
