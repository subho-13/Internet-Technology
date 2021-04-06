package controller.admin.add;

public class AddCredentials {
    private String token;
    private String corporateName;

    public String getcorporateName() {
        return this.corporateName;
    }

    public String getToken() {
        return this.token;
    }

    public void setCorporateName(String corporateName) {
        this.corporateName = corporateName;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "Credentials [token=" + this.token + ", corporateName="
                + this.corporateName + "]";
    }
}
