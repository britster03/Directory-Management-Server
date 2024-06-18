package server;

import java.net.ServerSocket;
import java.net.Socket;

public class DirectoryServer {
    private static final int PORT = 8080;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server started on port " + PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected");
                new ClientHandler(clientSocket).start();
            }
        } catch (Exception e) {
            System.err.println("Server exception: " + e.getMessage());
        }
    }
}
