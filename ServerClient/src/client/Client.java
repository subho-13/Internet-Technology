package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
    Socket socket;
    PrintWriter out;
    BufferedReader in;

    public Client(String address, int port) {
        try {
            socket = new Socket(address, port);
            this.socket = socket;
            out = new PrintWriter(this.socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            System.out.println("Connected");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void communicate() {
        int count = 0;

        String inputString;
        String outputString;

        Scanner scanner = new Scanner(System.in);
        boolean stop = false;
        while (!stop) {
            count++;
            System.out.print("[" + java.time.LocalTime.now() + "]");

            inputString = scanner.nextLine();
            out.println(inputString);

            try {
                outputString = in.readLine();
                System.out.println(outputString);
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (inputString.equals("exit")) {
                stop = true;
            }
        }

        System.out.println("Stopping communication");
        try {
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
