package br.gov.sp.fatec;

import br.gov.sp.fatec.utils.commons.SendEmail;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.SpringVersion;

import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.util.Date;

@SpringBootApplication
public class Antena {

	private static final Logger logger = LogManager.getLogger(Antena.class);

	public static void main(String[] args) {
		logger.info("Starting application.");
		logger.info("Spring version: " + SpringVersion.getVersion());
		SpringApplication.run(Antena.class, args);

		logger.info("Opening socket connection");
		openSocket();
	}

	public static void openSocket() {
		try {
			ServerSocket server = new ServerSocket(8090);
			logger.info("Server listening on port 8090");
			while(true) {
				java.net.Socket client = server.accept();
				logger.info("Client connected: " + client.getInetAddress().getHostAddress());
				ObjectOutputStream output = new ObjectOutputStream(client.getOutputStream());
				output.flush();
				output.writeObject(new Date());
				output.close();
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("Socket error");
		}
	}
}
