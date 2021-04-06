package controller.admin.add;

public class AddResponse {
	private boolean loggedIn;
	private String userid;
	private String password;

	public String getPassword() {
		return this.password;
	}

	public String getUserid() {
		return this.userid;
	}

	public boolean isLoggedIn() {
		return this.loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	@Override
	public String toString() {
		return "AddResponse [loggedIn=" + this.loggedIn + ", userid=" + this.userid + ", password=" + this.password
				+ "]";
	}

}
