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

	private static InputStream is;
	private static OutputStream os;

	// netstat -ano | find "8080"

	private static byte[] buffer = new byte[1024];

	public static void main(String[] args) throws UnknownHostException, IOException {
		out.println("------Client------");

		Inet4Address address = (Inet4Address) Inet4Address.getByName("localhost");
		connectEP = new InetSocketAddress(address, 3000);

		clientSocket = new Socket();
		clientSocket.connect(connectEP);

		out.println("Verbinde zu localhost");

		is = clientSocket.getInputStream();
		os = clientSocket.getOutputStream();

		int read = is.read(buffer);
		out.println("Empfange " + read + " Bytes vom Server");
		String temp = new String(buffer, 0, read);
		out.println("Nachricht von Server: " + temp);

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

		os.write(eingabe.getBytes());
		os.flush();

		read = is.read(buffer);
		temp = new String(buffer, 0, read);
		out.println("Nachricht von Server: " + temp);

		//clientSocket.close();
	}

}
