package server;

import utils.FileUtils;

import java.net.Socket;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ClientHandler extends Thread {
    private Socket socket;
    private ObjectInputStream input;
    private ObjectOutputStream output;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            output = new ObjectOutputStream(socket.getOutputStream());
            input = new ObjectInputStream(socket.getInputStream());

            String command;
            while ((command = (String) input.readObject()) != null) {
                String response = FileUtils.processCommand(command);
                output.writeObject(response);
            }
        } catch (Exception e) {
            System.err.println("Error handling client connection: " + e.getMessage());
        } finally {
            close();
        }
    }

    private void close() {
        try {
            if (output != null) output.close();
            if (input != null) input.close();
            if (socket != null) socket.close();
        } catch (Exception e) {
            System.err.println("Error closing streams: " + e.getMessage());
        }
    }
}
