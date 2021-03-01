package server.client.handler;

import java.util.ArrayList;

public interface Handler {
    void setNext(Handler handler);
    String handle(ArrayList<String> args);
}
