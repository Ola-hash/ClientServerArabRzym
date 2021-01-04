package client;

import javax.swing.*;
import java.io.*;
import java.net.*;

public class Client {
    private Socket socket;
    private JTextArea log;
    private PrintWriter out;

    public Client(JTextArea log) {
        this.log = log;
    }

    public void start(int portNumber, String name) throws IOException {
        InetAddress hostName = InetAddress.getLocalHost();
        socket = new Socket(hostName, portNumber);
        out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
        BufferedReader stdIn =
                new BufferedReader(new InputStreamReader(System.in));
        String fromServer;
        String fromUser;

        while ((fromServer = in.readLine()) != null) {
            addMessage(fromServer);
            if (fromServer.equals("Bye."))
                break;
        }
    }

    public void stop() throws IOException {
        socket.close();
    }

    private void addMessage(String message) {
        String newMessage = "Server: " + message;
        String text = log.getText() + "\n";
        log.setText(text + newMessage);
    }

    public void sendMessage(String message) {
        out.println(message);
    }
}