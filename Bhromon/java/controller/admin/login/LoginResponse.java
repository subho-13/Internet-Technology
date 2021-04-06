package controller.admin.login;

public class LoginResponse {
    boolean loggedIn;
    String token;

    public String getToken() {
        return this.token;
    }

    public boolean isLoggedIn() {
        return this.loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "Response [loggedIn=" + this.loggedIn + ", token=" + this.token + "]";
    }

}
