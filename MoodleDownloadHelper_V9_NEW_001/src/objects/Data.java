package objects;

public class Data {

	private int id;
	private String name;
	private String link;
	
	private String curseName;

	public Data(int id, String name, String link, String curseName) {
		super();
		this.id = id;
		this.name = name;
		this.link = link;
		this.curseName = curseName;
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
