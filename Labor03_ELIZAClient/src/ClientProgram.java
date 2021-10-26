//Name: Jan Schönitz m26336
//Datum: 16.10.2021

import java.io.*;
import java.net.*;
import java.rmi.UnknownHostException;
import java.util.Scanner;


import static java.lang.System.out;

public class ClientProgram {
	
	private static Socket clientSocket;
	private static SocketAddress connectEP;
	
	private static ObjectInputStream ois;
	private static ObjectOutputStream os;
	
	private static ServerCommunication comm;
	
	private static Scanner scanner;
	
	private static void sendMessage (String output) throws IOException {
		 comm.setMessage(output);
		 os = new ObjectOutputStream(clientSocket.getOutputStream());
		 os.writeObject(comm);
		 os.flush();
	}
	
	private static void receiveMessage() throws IOException, ClassNotFoundException {
		ois = new ObjectInputStream(clientSocket.getInputStream());
       comm = (ServerCommunication) ois.readObject();
       out.print("Server: " + comm.getMessage());
	}
	
	public static void main(String[] args) throws Exception {
		out.println("------Client------");

		Inet4Address address = (Inet4Address) Inet4Address.getByName("localhost");
		connectEP = new InetSocketAddress(address, 3000);

		clientSocket = new Socket();
		clientSocket.connect(connectEP);

		out.println("Verbinde zu localhost");
		
		comm = new ServerCommunication();
		
		Thread.sleep(2000);
		
		receiveMessage();

		scanner = new Scanner(System.in);
		String eingabe = scanner.nextLine();

		sendMessage(eingabe);
		
		receiveMessage();
		out.println("ELIZA: " + comm.getMessage());
		
		receiveMessage();
		out.println("ELIZA: " + comm.getMessage());

		scanner.close();
		// clientSocket.close();
	}
}
