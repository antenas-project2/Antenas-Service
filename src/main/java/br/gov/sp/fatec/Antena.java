package br.gov.sp.fatec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.SpringVersion;

import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.util.Date;

@SpringBootApplication
public class Antena {

	public static void main(String[] args) {
		System.out.println("Spring version: " + SpringVersion.getVersion());
		SpringApplication.run(Antena.class, args);

		openSocket();
	}

	public static void openSocket() {
		try {
			ServerSocket server = new ServerSocket(8090);
			System.out.println("Server listening on port 8090");
			while(true) {
				java.net.Socket client = server.accept();
				System.out.println("Client connected: " + client.getInetAddress().getHostAddress());
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
