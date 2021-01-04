package server;

import javax.swing.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.Date;

public class Server {
    private ServerSocket serverSocket;
    private boolean isStarted = false;
    private JTextArea log = null;

    public void start(int portNumber) throws IOException {
        serverSocket = new ServerSocket(portNumber);
        isStarted = true;
        log.setText("Server started at " + new Date());
        while (isStarted) {
            new Thread(new ServerThread(serverSocket.accept(), log)).start();
        }
    }

    public void stop() throws IOException {
        serverSocket.close();
        isStarted = false;
        log.setText("Server end at " + new Date());
    }

    public void setLog(JTextArea log) {
        this.log = log;
    }

    public boolean isStarted() {
        return isStarted;
    }
}