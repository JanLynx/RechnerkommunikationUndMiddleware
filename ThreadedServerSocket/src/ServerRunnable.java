//Name: Jan Schönitz m26336
//Datum: 22.10.2021

import static java.lang.System.out;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.ThreadLocalRandom;

public class ServerRunnable implements Runnable {

	private static Socket clientSocket;

	private static ObjectOutputStream os;
	private static ObjectInputStream is;
	
	private static ServerChallengeInfo info;

	public ServerRunnable(Socket clientSocket) {
		ServerRunnable.clientSocket = clientSocket;
	}
	
	private void sendMessage (String output) throws IOException {
		 info.setChallenge(output);
		 os = new ObjectOutputStream(clientSocket.getOutputStream());
		 os.writeObject(info);
		 os.flush();
	}
	
	private static void receiveMessage () throws Exception, IOException, ClassNotFoundException {
		is = new ObjectInputStream(clientSocket.getInputStream());
        info = (ServerChallengeInfo) is.readObject();
	}


	@Override
	public void run() {
		out.println("Ein Client hat sich verbunden");
		try {
			info = new ServerChallengeInfo();
			
			int randomNum1 = ThreadLocalRandom.current().nextInt(0, 501);
			int randomNum2 = ThreadLocalRandom.current().nextInt(0, 501);
			int randomSym = ThreadLocalRandom.current().nextInt(1, 5);
			int symbol = 43;
			double result = 0;
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
			receiveMessage();
			
			if (info.getSolution() == result) {
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