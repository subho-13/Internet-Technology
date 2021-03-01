package server.command;

import java.util.ArrayList;

public interface Command {
    String execute(String user, ArrayList<String> args);
}
