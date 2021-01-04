package server;

import rzymarab.RzymArab;
import rzymarab.RzymArabException;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerThread implements Runnable {
    private Socket socket = null;
    private static final String END_MESSAGES = "Bye";
    private JTextArea log;

    public ServerThread(Socket socket, JTextArea log) {
        this.socket = socket;
        this.log = log;
    }

    @Override
    public void run() {
        try (PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(
                     new InputStreamReader(
                             socket.getInputStream()));
        ) {
            String messageFromUser = null;

            out.println("Witaj na serwerze");

            while ((messageFromUser = in.readLine()) != null) {
                String message = "Client: " + messageFromUser;
                log.setText(log.getText() + "\n" + message);
                try {
                    if (RzymArab.isNumber(messageFromUser)) {
                        int number = Integer.parseInt(messageFromUser);
                        out.println(RzymArab.arab2rzym(number));
                    } else {
                        out.println(RzymArab.rzym2arab((messageFromUser)));
                    }
                } catch (RzymArabException e) {
                    out.println(e.getMessage());
                }
            }
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
