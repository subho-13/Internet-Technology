package controller.admin.login;

public class LoginCredentials {
	private String userid;
	private String password;

	public String getPassword() {
		return this.password;
	}

	public String getUserid() {
		return this.userid;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	@Override
	public String toString() {
		return "Credentials [userid=" + this.userid + ", password=" + this.password + "]";
	}
}
