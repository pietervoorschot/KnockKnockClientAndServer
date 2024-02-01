package Server;

import Protocol.Protocol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Logger;

public class ServerThread extends Thread {
    private final Socket socket;

    public ServerThread (Socket socket) {
        super("KnockKnockServerThread");
        this.socket = socket;
    }

    public void run() {
        try (
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))
        ) {
            String inputLine, outputLine;

            // Knock Knock Protocol.Protocol
            Protocol kkp = new Protocol();
            outputLine = kkp.processInput(null);
            out.println(outputLine);

            while ((inputLine = in.readLine()) != null) {
                outputLine = kkp.processInput(inputLine);
                out.println(outputLine);
                if (outputLine.equals("Bye."))
                    break;
            }

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
