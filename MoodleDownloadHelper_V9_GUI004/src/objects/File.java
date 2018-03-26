package objects;

public class File 
{

	private int id;
	private String name;
	private String link;
	private String fileEnding;
	private int parend_id;
	
	
	public File(int id, String name, String link, String fileEnding, int parend_id) {
		super();
		this.id = id;
		this.name = name;
		this.link = link;
		this.fileEnding = fileEnding;
		this.parend_id = parend_id;
	}
	
	
	public int getParned_ID() 
	{
		return this.parend_id;
	}
	public void printToConsole() 
	{
		System.out.println(this.id+") - Name:"+this.name+" - Link:"+this.link + " - FileEndung: "+fileEnding);
		
	}
	

	

}
