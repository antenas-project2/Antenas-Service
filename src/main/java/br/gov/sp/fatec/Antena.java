package br.gov.sp.fatec;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.SpringVersion;

import static br.gov.sp.fatec.socket.Socket.openSocket;

@SpringBootApplication
public class Antena {

	private static final Logger logger = LogManager.getLogger(Antena.class);
	private static int port = 3000;

	public static void main(String[] args) {
		logger.info("Starting application.");
		logger.info("Spring version: " + SpringVersion.getVersion());
		SpringApplication.run(Antena.class, args);

		openSocket("teste");
	}
}
