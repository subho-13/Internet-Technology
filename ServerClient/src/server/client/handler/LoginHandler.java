package server.client.handler;

import server.client.Client;
import server.login.Login;

import java.util.ArrayList;

public class LoginHandler implements Handler {
    final int numArgs = 2;
    final int maxAttempts = 5;
    Client client;
    Login login;
    Handler nextHandler;
    String errorArgs = "Two arguments required";
    String errorLogin = "Invalid username/password";
    String errorAttempts = "Exceeded number of attempts";
    String success = "Login Successful";
    int numAttempts = 0;

    public LoginHandler(Client client) {
        this.client = client;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public void setNext(Handler handler) {
        this.nextHandler = handler;
    }

    public String handle(ArrayList<String> args) {
        numAttempts++;

        if (numAttempts > maxAttempts) {
            client.setHandler(null);
            return errorAttempts;
        }

        if (args.size() > numArgs) {
            return errorArgs;
        }

        if (!login.check(args.get(0), args.get(1))) {
            return errorLogin;
        }

        client.setUsername(args.get(0));
        client.setHandler(nextHandler);
        return success;
    }
}
