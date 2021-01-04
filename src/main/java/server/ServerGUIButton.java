package server;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ServerGUIButton extends JButton implements ActionListener {

    public static final String START = "START";
    public static final String STOP = "STOP";
    private JTextField inputPort = null;
    private JTextArea log = null;
    private Server server = null;
    private Thread serverThread;

    public ServerGUIButton(JTextField inputPort, JTextArea log, Server server) {
        setText(START);
        this.inputPort = inputPort;
        this.log = log;
        this.server = server;
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (isInputPortEmpty()) {
            log.setText("Port jest pusty. Prosze podac port.");
            return;
        }
        String text = getText();
        if (START.equals(text)) {
            int portNumber = Integer.parseInt(inputPort.getText());
            serverThread = new Thread() {
                public void run() {
                    try {
                        setEditable(false, STOP);
                        server.start(portNumber);
                    } catch (IOException | IllegalArgumentException ex) {
                        log.setText("Could not listen on port " + portNumber);
                        setEditable(true, START);
                        interrupt();
                    }
                }
            };
            serverThread.start();
        } else {
            try {
                server.stop();
                serverThread.interrupt();
                setEditable(true, START);
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }
    }

    private void setEditable(boolean editable, String text) {
        setText(text);
        inputPort.setEditable(editable);
    }

    private boolean isInputPortEmpty() {
        return inputPort.getText().equals("");
    }
}