package objects;

public class List 
{
	
	/**
	 * 
	 * 	ID 	Curse		Folder 		SUB_folder	File 		 	Link 		(extension)
	 *	0	Numerik 	'null'		'null'		'null'			'wwwxx'		'null'
	 *	1	Numerik 	aufgaben	'null'		'null'			'wwwxx'		'null'
	 *	2	Numerik		'null'		'null'		aba.pdf			'wwwpdf'	'pdf
	 *	3
	 *	4	 	
	 * 	
	 *	ID	CurseName	folder	name 			link
	 * 	0	Numerik		true	Numerik			www.wwww
	 *	1	Numerik		false	aufagbe1.pdf	www.wwww
	 *	2	Numerik		false	aufagbe2.pdf	ww.www
	 *	3	Numerik		true	Hallo			www.sasasa
	 *	4	Numerik		false	uebung.pdf		wwwwss
	 *
	 *
	 */	
	
	
	
	private int id;
	private String courseName;
	private boolean folder;
	private String name;
	private String link;
	
	public List(int id, String courseName, boolean folder, String name, String link) {
		super();
		this.id = id;
		this.courseName = courseName;
		this.folder = folder;
		this.name = name;
		this.link = link;
	}

	public int getId() {
		return id;
	}

	public String getCourseName() {
		return courseName;
	}

	public boolean isFolder() {
		return folder;
	}

	public String getName() {
		return name;
	}

	public String getLink() {
		return link;
	}
	
	
	
	
	
	
	
	

}
