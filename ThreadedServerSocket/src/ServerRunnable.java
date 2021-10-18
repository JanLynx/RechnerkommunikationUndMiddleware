//Name: Jan Schönitz m26336
//Datum: 16.10.2021

import static java.lang.System.out;

import java.io.*;
import java.net.Socket;

public class ServerRunnable implements Runnable {

	private static Socket clientSocket;

	private static InputStream is;
	private static OutputStream os;

	private static byte[] buffer = new byte[1024];

	public ServerRunnable(Socket clientSocket) {
		ServerRunnable.clientSocket = clientSocket;
	}

	@Override
	public void run() {
		out.println("Ein Client hat sich verbunden");

		int read = 0;

		try {
			is = clientSocket.getInputStream();
			os = clientSocket.getOutputStream();

			os.write(" Willkommen\nWas ist 5+5?".getBytes());
			os.flush();
			read = is.read(buffer);
		} catch (IOException e) {
			e.printStackTrace();
		}

		int eingabe = Integer.valueOf(new String(buffer, 0, read));
		out.println("Eingabe: " + eingabe);

		try {
			if (eingabe == 10) {
				out.println("Eingabe war Richtig");
				os.write("Richtig!".getBytes());
			} else {
				out.println("Eingabe war Falsch");
				os.write("Falsch!".getBytes());
			}

			os.flush();
			clientSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}