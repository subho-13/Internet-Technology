package server.profile.role;

public class Guest implements Role {
    public String getIdentity() {
        return "guest";
    }

    public boolean canAccessStorage() {
        return false;
    }

    public boolean canAccessSystem() {
        return false;
    }
}
