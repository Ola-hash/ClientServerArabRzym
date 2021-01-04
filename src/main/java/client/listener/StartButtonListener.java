package client.listener;

import client.Client;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class StartButtonListener implements ActionListener {
    private static final String REGEX = "^[1-9][0-9]*$";
    public static final String START = "START";
    public static final String STOP = "STOP";
    private JTextField inputPort;
    private JTextField userName;
    private JTextArea log;
    private JPanel convertPanel;
    private Client client;
    private Thread clientThread;

    public StartButtonListener(JTextField inputPort, JTextField userName, JPanel convertPanel, JTextArea log, Client client) {
        this.inputPort = inputPort;
        this.userName = userName;
        this.convertPanel = convertPanel;
        this.log = log;
        this.client = client;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        String text = button.getText();
        if (START.equals(text)) {
            if (isFieldsEmpty() || !isNumber()) {
                log.setText("Nieprawidlowa wartosc w polu port lub nazwa.\nPodaj prawidlowa wartosc.");
            } else {
                log.setText("");
                setEditable(false, STOP, button);
                int port = Integer.parseInt(inputPort.getText());
                String name = userName.getText();
                clientThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            client.start(port, name);
                        } catch (IOException ex) {
                            String error = ex.getMessage();
                            log.setText(log.getText() + "\n" + error);
                        }
                    }
                });
                clientThread.start();
            }
        } else {
            setEditable(true, START, button);
            try {
                client.stop();
            } catch (IOException ex) {
                String error = ex.getMessage();
                log.setText(log.getText() + "\n" + error);
            }
        }
    }

    private void setEditable(boolean editable, String text, JButton button) {
        inputPort.setEditable(editable);
        userName.setEditable(editable);
        convertPanel.setVisible(!editable);
        button.setText(text);
    }

    private boolean isFieldsEmpty() {
        String intputPortText = inputPort.getText();
        String userNameText = userName.getText();

        return intputPortText.isEmpty() || userNameText.isEmpty();
    }

    private boolean isNumber() {
        String intputPortText = inputPort.getText();
        return intputPortText.matches(REGEX);
    }
}
