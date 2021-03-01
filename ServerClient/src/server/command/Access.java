package server.command;

import server.profile.Profile;
import server.profile.role.Role;
import server.storage.Storage;

import java.util.ArrayList;

public class Access implements Command {
    Storage storage;
    Profile profile;

    String errorArgs = "Two arguments required";
    String errorRole = "Role not supported";
    String errorValue = "No value returned";

    public Access(Storage storage, Profile profile) {
        this.storage = storage;
        this.profile = profile;
    }

    public String execute(String user, ArrayList<String> args) {
        if (args.size() != 2) {
            return errorArgs;
        }

        Role role = profile.get(user);

        if (role == null || !role.canAccessStorage()) {
            return errorRole;
        }

        String tmp = storage.access(args.get(0), args.get(1));

        if (tmp == null) {
            return errorValue;
        }

        return tmp;
    }
}
