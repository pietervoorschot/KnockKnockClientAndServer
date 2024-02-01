package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.logging.Logger;

public class Server {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        boolean listening = true;
        Logger logger = Logger.getLogger(Server.class.getName());


        try {
            serverSocket = new ServerSocket(4444);
        } catch (IOException e) {
            logger.info("Could not listen on port: 4444");
            System.exit(-1);
        }

        logger.info("Server started. Listening on port 4444...");

        try {
            while (listening) {
                new ServerThread(serverSocket.accept()).start();
            }
        }
        catch (IOException e) {
            logger.info("Error accepting client connection.");
        }
        finally {
            try {
                serverSocket.close();
            }
            catch (IOException e) {
                logger.info("Error closing server socket.");
            }
        }
    }
}