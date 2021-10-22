//Name: Jan Schönitz m26336
//Datum: 22.10.2021

import java.io.*;
import java.net.*;
import java.util.Scanner;

import static java.lang.System.out;

public class ClientProgram {
	private static Socket clientSocket;
	private static SocketAddress connectEP;

	private static ObjectInputStream ois;
	private static ObjectOutputStream os;
	
	private static ServerChallengeInfo info;

	// netstat -ano | find "8080"

	
	private static void sendMessage (double output) throws IOException {
		 info.setSolution(output);
		 os = new ObjectOutputStream(clientSocket.getOutputStream());
		 os.writeObject(info);
		 os.flush();
	}
	
	private static void receiveMessage() throws IOException, ClassNotFoundException {
		ois = new ObjectInputStream(clientSocket.getInputStream());
        info = (ServerChallengeInfo) ois.readObject();
        out.print("Server: " + info.getChallenge());
	}

	public static void main(String[] args) throws Exception {

		out.println("------Client------");

		Inet4Address address = (Inet4Address) Inet4Address.getByName("localhost");
		connectEP = new InetSocketAddress(address, 3000);

		clientSocket = new Socket();
		clientSocket.connect(connectEP);

		out.println("Verbinde zu localhost");

		//receive challenge
		receiveMessage();
		Scanner scanner = new Scanner(System.in);
		double eingabe = 0;

		if (scanner.hasNextInt()) {
			eingabe = scanner.nextInt();
		} else {
			System.out.println("Bitte eine Zahl eingeben.");
			scanner.close();
			return;
		}
		scanner.close();

		sendMessage(eingabe);
		receiveMessage();

	}



}
