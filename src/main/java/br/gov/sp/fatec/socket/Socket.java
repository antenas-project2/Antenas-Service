package br.gov.sp.fatec.socket;

import br.gov.sp.fatec.Antena;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.util.Date;

public class Socket {

    private static final Logger logger = LogManager.getLogger(Antena.class);
    private static ObjectOutputStream output;
    private static ObjectInputStream input;
    private static java.net.Socket client;
    private static int port = 3000;

    public static void openSocket(String message) {
        try {
            logger.info("Opening socket connection");

            ServerSocket server = new ServerSocket(port);
            logger.info("Server listening on port " + port);
            while(true) {
                client = server.accept();
                logger.info("Client connected: " + client.getInetAddress().getHostAddress());

                sendMessage("teste");
            }
        } catch(Exception e) {
            e.printStackTrace();
            System.out.println("Socket error");
        }
    }

    public  static void sendMessage(String message) throws IOException {
        output = new ObjectOutputStream(client.getOutputStream());
        output.flush();
        output.writeObject(message);
        output.close();
    }
}
