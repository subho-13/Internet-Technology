package server.profile.role;

public class Manager implements Role {
    public String getIdentity() {
        return "manager";
    }

    public boolean canAccessStorage() {
        return true;
    }

    public boolean canAccessSystem() {
        return true;
    }


}
