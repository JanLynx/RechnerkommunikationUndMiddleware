//Name: Jan Sch?nitz m26336
//Datum: 29.10.2021

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

	private static void sendMessage() throws IOException {
		os = new ObjectOutputStream(clientSocket.getOutputStream());
		os.writeObject(comm);
		os.flush();
	}

	private static void receiveMessage() throws IOException, ClassNotFoundException {
		ois = new ObjectInputStream(clientSocket.getInputStream());
		comm = (ServerCommunication) ois.readObject();
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

		// Welcome-Message
		receiveMessage();
		out.print("ELIZA: " + comm.getMessage() + "\n");

		scanner = new Scanner(System.in);
		String eingabe = scanner.nextLine();

		// send username
		comm.setNameFromString(eingabe);
		sendMessage();

		//receive question: how are you?
		receiveMessage();
		out.print("ELIZA: " + comm.getMessage() + "\n");

		eingabe = scanner.nextLine();

		// send feelings
		comm.setFeelings(eingabe);
		sendMessage();
		
		//receive answer
		receiveMessage();
		out.println("ELIZA: " + comm.getAnswer() + "\n");
		
		//receive bye bye
		receiveMessage();
		out.print("ELIZA: " + comm.getMessage() + "\n");

		scanner.close();
		// clientSocket.close();
	}
}
