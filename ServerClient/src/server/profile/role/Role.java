package server.profile.role;

public interface Role {
    String getIdentity();

    boolean canAccessStorage();

    boolean canAccessSystem();
}
