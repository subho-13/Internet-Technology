package server.client;

import server.client.handler.Handler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;

public class Client implements Runnable {
    Socket socket;
    PrintWriter out;
    BufferedReader in;
    Handler handler;
    String username;

    public Client(Socket socket) {
        this.socket = socket;
        try {
            out = new PrintWriter(this.socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        } catch (IOException e) {
            System.out.println("Couldn't get I/O from connection");
            e.printStackTrace();
            System.exit(-1);
        }
    }

    private boolean checkSocket() {
        return !socket.isClosed() && socket.isConnected() && socket.isBound();
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void run() {
        String inputString;
        String outputString;
        ArrayList<String> args = new ArrayList<>();

        while (checkSocket() && handler != null) {
            try {
                inputString = in.readLine();

                args.clear();
                Collections.addAll(args, inputString.split(" "));

                outputString = handler.handle(args);

                out.println(outputString);
            } catch (IOException e) {
                System.out.println("Couldn't recieve input or send output");
            }
        }

        out.println("Closing connection");

        try {
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            System.out.println("Error in closing socket");
            e.printStackTrace();
        }
    }
}