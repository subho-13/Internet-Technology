package server.command;

import server.profile.Profile;
import server.profile.role.Role;
import server.storage.Storage;

import java.util.ArrayList;

public class Insert implements Command {
    Storage storage;
    Profile profile;

    String errorArgs = "Three arguments required";
    String errorRole = "Role not supported";
    String success = "Successfully stored";

    public Insert(Storage storage, Profile profile) {
        this.storage = storage;
        this.profile = profile;
    }

    public String execute(String user, ArrayList<String> args) {
        if (args.size() != 3) {
            return errorArgs;
        }

        Role role = profile.get(user);

        if (role == null || !role.canAccessStorage()) {
            return errorRole;
        }

        storage.store(args.get(0), args.get(1), args.get(2));
        return success;
    }
}
