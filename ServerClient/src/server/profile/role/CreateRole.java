package server.profile.role;

public class CreateRole {
    public static Role forRole(String name) {
        if (name.equals("manager")) {
            return new Manager();
        } else if (name.equals("guest")) {
            return new Guest();
        }

        return null;
    }
}
