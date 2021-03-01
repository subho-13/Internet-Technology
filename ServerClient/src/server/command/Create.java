package server.command;

import server.login.Login;
import server.profile.Profile;
import server.profile.role.Role;

import java.util.ArrayList;

public class Create implements Command {
    Login login;
    Profile profile;

    String errorArgs = "Three Arguments Required";
    String errorRole = "Role not supported";
    String errorUser = "User already present";
    String success = "New profile created";

    public Create(Login login, Profile profile) {
        this.login = login;
        this.profile = profile;
    }

    public String execute(String user, ArrayList<String> args) {
        if (args.size() != 3) {
            return errorArgs;
        }

        Role role = profile.get(user);

        if (role == null || !role.canAccessSystem()) {
            return errorRole;
        }

        String newUser = args.get(0);
        String newPass = args.get(1);
        String newRole = args.get(2);

        if (!login.add(newUser, newPass)) {
            return errorUser;
        }

        if (!profile.add(newUser, newRole)) {
            login.remove(newUser);
            return errorUser;
        }

        return success;
    }
}
