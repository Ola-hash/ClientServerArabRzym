package client;

import client.listener.ConvertButtonListener;
import client.listener.StartButtonListener;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class ClientGUI extends JFrame {
    private JButton button;
    private JButton convert;
    private JTextField inputPort;
    private JTextField userName;
    private JTextField convertInput;
    private JTextArea log;
    private JPanel convertPanel;
    private Client client;

    public ClientGUI() {
        setSize(400, 300);
        setLayout(new GridBagLayout());
        initPanel(getContentPane());
        initLogPanel(getContentPane());
        client=new Client(log);
        setButtonActionListener();
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void initPanel(Container container) {
        BorderLayout borderLayout = new BorderLayout();
        JPanel panel = new JPanel(borderLayout);
        FlowLayout flowLayout = new FlowLayout();
        JPanel serverConPanel = new JPanel(new FlowLayout());
        initServerConPanelElements(serverConPanel);
        convertPanel = new JPanel(flowLayout);
        initConvertPanel(convertPanel);
        convertPanel.setVisible(false);

        panel.add(serverConPanel, BorderLayout.PAGE_START);
        panel.add(convertPanel, BorderLayout.CENTER);

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

    private void initServerConPanelElements(JPanel panel) {
        initServerConInputPanel(panel);
        initServerConButtonPanel(panel);
    }

    private void initServerConInputPanel(JPanel panel) {
        GridLayout gridLayout = new GridLayout(2, 2, 2, 2);
        JPanel inputPanel = new JPanel(gridLayout);
        JLabel port = new JLabel("Port");
        JLabel name = new JLabel("Nazwa");
        inputPort = new JTextField(10);
        userName = new JTextField(10);
        inputPanel.add(port);
        inputPanel.add(inputPort);
        inputPanel.add(name);
        inputPanel.add(userName);
        panel.add(inputPanel);

    }

    private void initServerConButtonPanel(JPanel panel) {
        JPanel conButtonPanel = new JPanel();
        button = new JButton("START");
        conButtonPanel.add(button);
        panel.add(conButtonPanel);
    }

    private void initConvertPanel(JPanel panel) {
        FlowLayout flowLayout = new FlowLayout();
        JPanel convertPanel = new JPanel(flowLayout);
        convertInput = new JTextField(10);
        convert = new JButton("Przelicz");
        convertPanel.add(convertInput);
        convertPanel.add(convert);
        panel.add(convertPanel);
    }

    private void setButtonActionListener() {
        button.addActionListener(new StartButtonListener(inputPort, userName, convertPanel, log,client));
        convert.addActionListener(new ConvertButtonListener(convertInput,client));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ClientGUI();
            }
        });
    }
}

