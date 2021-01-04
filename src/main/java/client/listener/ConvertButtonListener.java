package client.listener;

import client.Client;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConvertButtonListener implements ActionListener {
    private JTextField input;
    private Client client;

    public ConvertButtonListener(JTextField input, Client client) {
        this.input = input;
        this.client = client;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String message = input.getText();
        if (!message.isEmpty()) {
            client.sendMessage(message);
        }

    }
}
