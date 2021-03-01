package server;

import server.client.Client;
import server.client.handler.CommandHandler;
import server.client.handler.LoginHandler;
import server.command.*;
import server.file.StringMapString;
import server.login.Login;
import server.profile.Profile;
import server.storage.Storage;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicBoolean;

public class Server {
    final String loginFile = "login.txt";
    final String profileFile = "profile.txt";
    final int maxthreads = 8;
    final int randStrSize = 8;

    Login login;
    Profile profile;
    Storage storage;
    AtomicBoolean closeServer;

    Access access;
    Create create;
    Get get;
    Insert insert;
    Put put;
    Terminate terminate;

    ThreadPoolExecutor executors;
    ServerSocket serverSocket;

    Server(int port) {
        init();

        login = new Login(loginFile);
        profile = new Profile(profileFile);
        storage = new Storage();
        closeServer = new AtomicBoolean(false);

        access = new Access(storage, profile);
        create = new Create(login, profile);
        get = new Get(storage);
        insert = new Insert(storage, profile);
        put = new Put(storage);
        terminate = new Terminate(closeServer, profile);

        executors = (ThreadPoolExecutor) Executors.newFixedThreadPool(maxthreads);

        try {
            serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout(2000);
        } catch (IOException e) {
            System.out.println("Cannot open server socket");
            e.printStackTrace();
        }
    }

    public void serve() {
        Socket clientSocket;
        int count = 0;
        while (!closeServer.get()) {
            try {
                clientSocket = serverSocket.accept();
                count++;
                System.out.println("Accepting client:: " + count);
                System.out.println(clientSocket.getInetAddress() + " " + clientSocket.getPort());
                handleClient(clientSocket);
            } catch (SocketTimeoutException e) {
            } catch (IOException e) {
                System.out.println("Cannot accept connection");
                e.printStackTrace();
            }
        }

        executors.shutdown();
        exit();
    }

    private void handleClient(Socket clientSocket) {
        Client client = new Client(clientSocket);

        LoginHandler loginHandler = new LoginHandler(client);
        CommandHandler commandHandler = new CommandHandler(client);

        loginHandler.setLogin(login);
        loginHandler.setNext(commandHandler);

        commandHandler.addCmd("access", access);
        commandHandler.addCmd("create", create);
        commandHandler.addCmd("get", get);
        commandHandler.addCmd("insert", insert);
        commandHandler.addCmd("put", put);
        commandHandler.addCmd("terminate", terminate);
        commandHandler.setNext(null);

        client.setHandler(loginHandler);
        executors.execute(client);
    }

    private void init() {
        File file = new File(loginFile);

        if (!file.exists()) {
            String username = randString(randStrSize);
            String password = randString(randStrSize);
            String userrole = "manager";

            Map<String, String> userPass = new TreeMap<>();
            Map<String, String> userRole = new TreeMap<>();

            userPass.put(username, password);
            StringMapString.write(loginFile, userPass);

            userRole.put(username, userrole);
            StringMapString.write(profileFile, userRole);

            System.out.println(username + " " + password);
            System.out.println("Create new accounts using this");
        }
    }

    private String randString(int size) {
        StringBuilder builder = new StringBuilder(size);

        String allchars = "abcdefghijklmnopqrstuvwxyz";

        int length = allchars.length();
        int tmpIndex;

        for (int i = 0; i < size; i++) {
            tmpIndex = (int) (length * Math.random());
            builder.append(allchars.charAt(tmpIndex));
        }

        return builder.toString();
    }

    private void exit() {
        login.save();
        profile.save();
    }
}
