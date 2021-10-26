//Name: Jan Schönitz m26336
//Datum: 22.10.2021

import static java.lang.System.out;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.concurrent.ThreadLocalRandom;

public class ServerRunnable implements Runnable {

	private static Socket clientSocket;

	private static ObjectOutputStream os;
	private static ObjectInputStream is;
	
	private static ServerCommunication comm;
	
	private static HashMap<String, String> map;

	public ServerRunnable(Socket clientSocket) {
		ServerRunnable.clientSocket = clientSocket;
	}
	
	private void sendMessage (String output) throws IOException {
		 comm.setMessage(output);
		 os = new ObjectOutputStream(clientSocket.getOutputStream());
		 os.writeObject(comm);
		 os.flush();
	}
	
	private static void receiveMessage () throws Exception, IOException, ClassNotFoundException {
		is = new ObjectInputStream(clientSocket.getInputStream());
        comm = (ServerCommunication) is.readObject();
	}
	
	public void buildingHash() {
		map.put("krank", "Geh lieber zu einem Arzt!");
		map.put("gut", "Das ist aber schön!");
		map.put("ok", "Was? Nur \"ok\"?");
		map.put("schlecht", "Das ist aber schade!");
		map.put("perfekt", "Dann nutze den Tag!");
		map.put("nicht", "Schade!");
	}

//	public String getUsername() throws Exception {
//
//		String username = "";
//		String[] usernameArr = splitInputString();
//
//		if (usernameArr.length == 1) {
//			username = usernameArr[0];
//		} else {
//			username = usernameArr[usernameArr.length - 1];
//		}
//
//		return username;
//
//	}

//	public String[] splitInputString() throws Exception {
//
//		receiveMessage();
//		String input = comm.getMessage();
//		out.println("Eingabe: " + input);
//		String[] inputArr = input.split(" ");
//
//		return inputArr;
//	}

	public String getAnswer() throws Exception {

		String[] eingabeArr = splitInputString();

		for (Entry<String, String> entry : map.entrySet()) {
			for (int i = 0; i < eingabeArr.length; i++) {
				if (eingabeArr[i].equals(entry.getKey())) {
					return entry.getValue();
				}
			}
		}

		return "Leider kann ich dir nicht weiterhelfen, probiers doch einfach später nocheinmal.";
	}


	@Override
	public void run() {
		out.println("Ein Client hat sich verbunden");

		try {
			comm = new ServerCommunication();

			sendMessage("Hallo, hier ist Eliza. Mit wem habe ich das Vergnügen?");

			// user begrüßen
			receiveMessage();
			String username = comm.getUsername();
			String ausgabe = "Hallo " + username + ", wie geht es dir?";
			sendMessage(ausgabe);

			map = new HashMap<String, String>();
			buildingHash();

			ausgabe = getAnswer();

			sendMessage(ausgabe);

			ausgabe = "Tschüss " + username;
			sendMessage(ausgabe);

			clientSocket.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}