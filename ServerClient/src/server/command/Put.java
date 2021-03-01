package server.command;

import server.storage.Storage;

import java.util.ArrayList;

public class Put implements Command {
    Storage storage;
    String errorArgs = "One argument required";
    String success = "Successfully stored";

    public Put(Storage storage) {
        this.storage = storage;
    }

    public String execute(String user, ArrayList<String> args) {
        if (args.size() != 2) {
            return errorArgs;
        }

        storage.store(user, args.get(0), args.get(1));
        return success;
    }
}
