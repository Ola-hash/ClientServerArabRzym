package server;

import client.ClientGUI;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class ServerGUI extends JFrame {
    private JButton button;
    private JTextField inputPort;
    private JTextArea log;
    private Server server;

    public ServerGUI(Server server) {
        setSize(400, 300);
        setLayout(new GridBagLayout());
        this.server = server;
        initLogPanel(getContentPane());
        this.server.setLog(log);
        initPortPanel(getContentPane());
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void initPortPanel(Container container) {
        FlowLayout flowLayout = new FlowLayout();
        JPanel panel = new JPanel(flowLayout);
        inputPort = new JTextField();
        inputPort.setPreferredSize(new Dimension(114, 26));
        JLabel label = new JLabel("Port: ");
        button = new ServerGUIButton(inputPort, log, server);
        panel.add(label);
        panel.add(inputPort);
        panel.add(button);

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(0, 5, 0, 5);
        c.weightx = 1;
        c.weighty = 1;

        container.add(panel, c);
    }

    private void initLogPanel(Container container) {
        log = new JTextArea();
        log.setEditable(false);
        Border border1 = BorderFactory.createLineBorder(Color.GRAY);
        log.setBorder(BorderFactory.createCompoundBorder(border1,
                BorderFactory.createEmptyBorder(2, 2, 2, 2)));
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 4;
        c.insets = new Insets(5, 5, 5, 5);
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weighty = 10;
        log.setVisible(true);
        container.add(log, c);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Server server = new Server();
                new ServerGUI(server);
            }
        });

    }
}

