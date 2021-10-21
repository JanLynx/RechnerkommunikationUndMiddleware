//Name: Jan Schönitz m26336
//Datum: 16.10.2021

import static java.lang.System.out;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.ThreadLocalRandom;

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

			int randomNum1 = ThreadLocalRandom.current().nextInt(0, 501);
			int randomNum2 = ThreadLocalRandom.current().nextInt(0, 501);
			int randomSym = ThreadLocalRandom.current().nextInt(1, 5);
			int symbol = 43;
			int result = 0;
			switch (randomSym) {
			case 1:
				symbol = 43;
				result = randomNum1 + randomNum2;
				break;
			case 2:
				symbol = 45;
				result = randomNum1 - randomNum2;
				break;
			case 3:
				symbol = 42;
				result = randomNum1 * randomNum2;
				break;
			case 4:
				symbol = 47;
				result = randomNum1 / randomNum2;
				break;
			}

			String ausgabe = " Willkommen\nWas ist " + randomNum1 + " " + (char)symbol + " " + randomNum2 + "?";
			os.write(ausgabe.getBytes());
			os.flush();
			read = is.read(buffer);

			int eingabe = Integer.valueOf(new String(buffer, 0, read));
			out.println("Eingabe: " + eingabe);

			if (eingabe == result) {
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