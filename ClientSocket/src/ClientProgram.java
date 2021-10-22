//Name: Jan Schönitz m26336
//Datum: 16.10.2021

import java.io.*;
import java.net.*;
import java.rmi.UnknownHostException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import static java.lang.System.out;

public class ClientProgram {
	private static Socket clientSocket;
	private static SocketAddress connectEP;

	private static ObjectInputStream is;
	private static ObjectOutputStream os;
	private static FileInputStream in;
	private static FileOutputStream outp;
	
	private static ServerChallengeInfo info;

	// netstat -ano | find "8080"

	private static byte[] buffer = new byte[1024];
	
	private static void sendMessage (String output) throws IOException {
		info.setString(output);
		os.writeObject(info);
	}
	
	private static void receiveMessage () throws Exception {
		TimeUnit.MILLISECONDS.sleep(500);
		info = (ServerChallengeInfo) is.readObject();
		out.println("Nachricht von Server: " + info.getString());
	}

	public static void main(String[] args) throws Exception {

		out.println("------Client------");

		Inet4Address address = (Inet4Address) Inet4Address.getByName("localhost");
		connectEP = new InetSocketAddress(address, 3000);

		clientSocket = new Socket();
		clientSocket.connect(connectEP);

		out.println("Verbinde zu localhost");

		outp = new FileOutputStream("../challengeInfoIn.ser");
		os = new ObjectOutputStream(outp);
		
		TimeUnit.MILLISECONDS.sleep(100);
		
		in = new FileInputStream("../challengeInfoOut.ser");
		is = new ObjectInputStream(in);
		
		//receive task
		receiveMessage();
		Scanner scanner = new Scanner(System.in);
		String eingabe = "";

		if (scanner.hasNextInt()) {
			eingabe = scanner.next();
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
