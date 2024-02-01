package Server;

import java.io.IOException;
import java.net.ServerSocket;

public class Server {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        boolean listening = true;

        try {
            serverSocket = new ServerSocket(4444);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 4444.");
            System.exit(-1);
        }

        System.out.println("Server started. Listening on port 4444...");

        try {
            while (listening) {
                new ServerThread(serverSocket.accept()).start();
            }
        }
        catch (IOException e) {
            System.err.println("Error accepting client connection.");
        }
        finally {
            try {
                serverSocket.close();
            }
            catch (IOException e) {
                System.err.println("Error closing server socket.");
            }
        }
    }
}