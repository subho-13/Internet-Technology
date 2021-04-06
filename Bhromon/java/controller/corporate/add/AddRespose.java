package controller.corporate.add;

import java.util.List;

public class AddRespose {
    boolean loggedIn;
    List<String> ids;

    public List<String> getIds() {
        return this.ids;
    }

    public boolean isLoggedIn() {
        return this.loggedIn;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }
}
