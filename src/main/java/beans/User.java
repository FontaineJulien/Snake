package beans;

public class User {
	
	private long id;
	private String username;
	private String password;
	private String email;
	private long credit;
	private short isAdmin;
	
	public User() {
		
	}
	
	public User(String username,String email, String password) {
		this.username = username;
		this.email = email;
		this.password = password;
		this.credit = 0;
		this.isAdmin = 0;
	}
	
	/*********************************************************/
	/*                GETTERS & SETTERS                      */
	/*********************************************************/
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getCredit() {
		return credit;
	}

	public void setCredit(long credit) {
		this.credit = credit;
	}

	public short getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(short isAdmin) {
		this.isAdmin = isAdmin;
	}

}
