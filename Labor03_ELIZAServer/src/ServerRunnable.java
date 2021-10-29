//Name: Jan Sch�nitz m26336
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

	private void sendMessage() throws IOException {
		os = new ObjectOutputStream(clientSocket.getOutputStream());
		os.writeObject(comm);
		os.flush();
	}

	private static void receiveMessage() throws Exception, IOException, ClassNotFoundException {
		is = new ObjectInputStream(clientSocket.getInputStream());
		comm = (ServerCommunication) is.readObject();
	}

	public void buildHash() {
		map = new HashMap<String, String>();

		map.put("krank", "Geh lieber zu einem Arzt!");
		map.put("gut", "Das ist aber sch�n!");
		map.put("ok", "Was? Nur \"ok\"?");
		map.put("schlecht", "Das ist aber schade!");
		map.put("perfekt", "Dann nutze den Tag!");
		map.put("nicht", "Schade!");
	}

	public String generateAnswer(String[] input) throws Exception {
		for (Entry<String, String> entry : map.entrySet()) {
			for (int i = 0; i < input.length; i++) {
				input[i] = input[i].replace(".", ""); // replace unnecessary full stops
				if (input[i].equals(entry.getKey())) {
					return entry.getValue();
				}
			}
		}

		return "Leider kann ich dir nicht weiterhelfen, probiers doch einfach sp�ter nocheinmal.";
	}

	@Override
	public void run() {
		out.println("Ein Client hat sich verbunden");

		try {
			comm = new ServerCommunication();

			comm.setMessage("Hallo, hier ist Eliza. Mit wem habe ich das Vergn�gen?");
			sendMessage();

			receiveMessage();
			String username = comm.getName();
			String output = "Hallo " + username + ", wie geht es dir?";

			// greet user
			comm.setMessage(output);
			sendMessage();

			// receive answerarry
			receiveMessage();
			buildHash();
			output = generateAnswer(comm.getFeelings());

			// send answer
			comm.setAnswer(output);
			sendMessage();

			output = "Tsch�ss " + username;
			comm.setMessage(output);
			sendMessage();

			clientSocket.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}