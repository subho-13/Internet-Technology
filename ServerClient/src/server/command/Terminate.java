package server.command;

import server.profile.Profile;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class Terminate implements Command {
    Profile profile;
    AtomicBoolean closeServer;

    String errArgs = "No argument required";
    String errorRole = "Role not supported";
    String success = "Terminating server";

    public Terminate(AtomicBoolean closeServer, Profile profile) {
        this.closeServer = closeServer;
        this.profile = profile;
    }

    public String execute(String user, ArrayList<String> args) {
        if (!args.isEmpty()) {
            return errArgs;
        }

        if (!profile.get(user).canAccessSystem()) {
            return errorRole;
        }

        closeServer.set(true);

        return success;
    }
}
