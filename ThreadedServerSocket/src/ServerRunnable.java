//Name: Jan Schönitz m26336
//Datum: 16.10.2021

import static java.lang.System.out;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class ServerRunnable implements Runnable {

	private static Socket clientSocket;

	private static FileInputStream in;
	private static FileOutputStream outp;
	private static ObjectOutputStream os;
	private static ObjectInputStream is;
	
	private static ServerChallengeInfo info;

	private static byte[] buffer = new byte[1024];

	public ServerRunnable(Socket clientSocket) {
		ServerRunnable.clientSocket = clientSocket;
	}
	
	private void sendMessage (String output) throws IOException {
		info.setString(output);
		os.writeObject(info);
	}
	
	private static String receiveMessage () throws Exception, IOException, ClassNotFoundException {
		TimeUnit.MILLISECONDS.sleep(500);
		info = (ServerChallengeInfo) is.readObject();
		return info.getString();
	}


	@Override
	public void run() {
		out.println("Ein Client hat sich verbunden");
		int read = 0;
		try {
			info = new ServerChallengeInfo();
			
			outp = new FileOutputStream("../challengeInfoOut.ser");
			os = new ObjectOutputStream(outp);
			
			TimeUnit.MILLISECONDS.sleep(100);
			
			in = new FileInputStream("../challengeInfoIn.ser");
			is = new ObjectInputStream(in);
			
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

			sendMessage(" Willkommen\nWas ist " + randomNum1 + " " + (char) symbol + " " + randomNum2 + "?");
			
			while(in.available() == 0);
			int eingabe = Integer.valueOf(receiveMessage());
			
			out.println("Eingabe: " + eingabe);

			if (eingabe == result) {
				out.println("Eingabe war Richtig");
				sendMessage("Richtig");
			} else {
				out.println("Eingabe war Falsch");
				sendMessage("Falsch");
			}
			clientSocket.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}