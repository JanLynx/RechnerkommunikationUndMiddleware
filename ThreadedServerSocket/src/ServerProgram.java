//Name: Jan Schönitz m26336
//Datum: 22.10.2021

import java.io.*;
import java.net.*;
import java.util.concurrent.*;

import static java.lang.System.out;

public class ServerProgram {
	private static ServerSocket serverSocket;
	private static Socket clientSocket;
	private static SocketAddress bindPoint;

	// netstat -ano | find "8080"

	public static void main(String[] args) throws IOException {
		
		ExecutorService pool = Executors.newCachedThreadPool();
		
		Inet4Address address = (Inet4Address) Inet4Address.getByName("localhost");
		bindPoint = new InetSocketAddress(address, 3000);

		out.println("------Server------");
		serverSocket = new ServerSocket();
		serverSocket.bind(bindPoint);

		out.println("Warte auf eingehende Verbindung");

		while (true) {
			clientSocket = serverSocket.accept();
			pool.execute(new ServerRunnable(clientSocket));
		}
	}

}