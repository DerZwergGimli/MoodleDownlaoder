package objects;


public class Curse
{
	private String name;
	private String link;
	private String type;
	private int id;
	//private int parend_id
	
	
	public Curse() 
	{
		
	}
	public Curse(String name, String link, String type, int id)
	{
		
		super();
		this.name = name;
		this.link = link;
		this.type = type;
		this.id = id;
	}
	
	public String getLink() 
	{
		return this.link;
	}
	
	
	public void printToConsole() 
	{
		System.out.println(this.id+") Type:"+this.type+" - Name:"+this.name+" - Link:"+this.link);
		
	}

	public int getID() 
	{
		return this.id;
	}
	
	public String getName() 
	{
		return this.name;
	}
	
	
	

}
