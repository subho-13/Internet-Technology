package controller.corporate.login;

public class LoginResponse {
    private boolean loggedIn;
    private String token;
    private String corporateName;

    public String getCorporateName() {
        return this.corporateName;
    }

    public String getToken() {
        return this.token;
    }

    public boolean isLoggedIn() {
        return this.loggedIn;
    }

    public void setCorporateName(String corporateName) {
        this.corporateName = corporateName;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
