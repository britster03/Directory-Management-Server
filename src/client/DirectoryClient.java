package client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class DirectoryClient extends JFrame implements ActionListener {
    private JTextField inputField;
    private JTextArea textArea;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private Socket socket;

    public DirectoryClient() {
        super("Directory Management Client");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLayout(new BorderLayout());

        inputField = new JTextField();
        inputField.addActionListener(this);
        add(inputField, BorderLayout.NORTH);

        textArea = new JTextArea();
        textArea.setEditable(false);
        add(new JScrollPane(textArea), BorderLayout.CENTER);

        setVisible(true);
        connectToServer();
    }

    private void connectToServer() {
        try {
            socket = new Socket("localhost", 8080);
            output = new ObjectOutputStream(socket.getOutputStream());
            input = new ObjectInputStream(socket.getInputStream());
            new Thread(this::listenForServerMessages).start();
        } catch (Exception e) {
            textArea.append("Connection failed: " + e.getMessage() + "\n");
        }
    }

    private void listenForServerMessages() {
        try {
            String message;
            while ((message = (String) input.readObject()) != null) {
                textArea.append("Server: " + message + "\n");
            }
        } catch (Exception e) {
            textArea.append("Lost connection to server.\n");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            output.writeObject(inputField.getText());
            inputField.setText("");
        } catch (Exception ex) {
            textArea.append("Failed to send message: " + ex.getMessage() + "\n");
        }
    }

    public static void main(String[] args) {
        new DirectoryClient();
    }
}
