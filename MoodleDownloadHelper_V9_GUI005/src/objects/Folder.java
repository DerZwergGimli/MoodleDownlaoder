package objects;

public class Folder

{


	private int id;
	private String name;
	private String link;
	private String subfilesAmount;
	private int parend_id;
	
	
	public Folder(int id, String name, String link, String subfilesAmount, int parend_id) {
		super();
		this.id = id;
		this.name = name;
		this.link = link;
		this.subfilesAmount = subfilesAmount;
		this.parend_id = parend_id;
	}
	
	

	public int getParned_ID() 
	{
		return this.parend_id;
	}
	public void printToConsole() 
	{
		System.out.println(this.id+") - Name:"+this.name+" - Link:"+this.link);
		
	}
	
	public String getLink() 
	{
		return this.link;
	}
	public int getID() 
	
	{
		return this.id;
	}
	
	

}
