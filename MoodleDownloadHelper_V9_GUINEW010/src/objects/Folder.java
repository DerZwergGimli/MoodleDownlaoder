package objects;

public class Folder 
{
	private int id;
	private String name;
	private String link;
	
	private String curseName;
	private int curseID;

	public Folder(int id, String name, String link, String curseName, int curseID) {
		super();
		this.id = id;
		this.name = name;
		this.link = link;
		this.curseName = curseName;
		this.curseID = curseID;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	} 
	
	
	
	
	
}
