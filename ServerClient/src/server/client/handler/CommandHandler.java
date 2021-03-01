package server.client.handler;

import server.client.Client;
import server.command.Command;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class CommandHandler implements Handler {
    Client client;
    Handler nextHandler;
    Map<String, Command> nameCmd = new TreeMap<>();

    String errorArgs = "No arguments were provided";
    String errorCmd = "Command not found";
    String successExit = "Exit successful";

    public CommandHandler(Client client) {
        this.client = client;
    }

    public void setNext(Handler handler) {
        this.nextHandler = handler;
    }

    public void addCmd(String name, Command cmd) {
        nameCmd.put(name, cmd);
    }

    public String handle(ArrayList<String> args) {
        if (args.isEmpty()) {
            return errorArgs;
        }

        String cmdName = args.remove(0);

        if (cmdName.equals("exit")) {
            client.setHandler(nextHandler);
            return successExit;
        }

        if (nameCmd.containsKey(cmdName)) {
            return nameCmd.get(cmdName).execute(client.getUsername(), args);
        }

        return errorCmd;
    }
}
