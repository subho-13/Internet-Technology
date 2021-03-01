package server.command;

import server.storage.Storage;

import java.util.ArrayList;

public class Get implements Command {
    Storage storage;
    String errorArgs = "One argument required";
    String errorValue = "No value returned";

    public Get(Storage storage) {
        this.storage = storage;
    }

    public String execute(String user, ArrayList<String> args) {
        if (args.size() != 1) {
            return errorArgs;
        }

        String tmp = storage.access(user, args.get(0));

        if (tmp == null) {
            return errorValue;
        }

        return tmp;
    }
}
