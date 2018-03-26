package objects;

public class Login 
{
	private String username;
	private String password;
	private String moodleUrl;
	
	
	public Login(String username, String password, String moodleUrl) {
		super();
		this.username = username;
		this.password = password;
		this.moodleUrl = moodleUrl;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getMoodleUrl() {
		return moodleUrl;
	}


	public void setMoodleUrl(String moodleUrl) {
		this.moodleUrl = moodleUrl;
	}
	
	
	

}
