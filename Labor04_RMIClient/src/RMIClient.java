
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.util.Scanner;

import static java.lang.System.out;

import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class RMIClient {

	public static void main(String[] args) {
		String rmiMessage;
		double rmiResult;
		RMIInterface rmiObject;
		Scanner scanner;
		
		try{
			if (System.getSecurityManager() == null) {
		        System.setSecurityManager(new SecurityManager());
		    }
			out.println("Client started...");
			rmiObject = (RMIInterface)Naming.lookup("rmi://localhost:5000/RMIServer");
			
			scanner = new Scanner(System.in);
			out.println("W�hlen Sie die Methode, welche Sie entfernt aufrufen m�chten:\n1 Textanalyse\n2 Exponetielle Berechnung von x");
			int methodNumber = scanner.nextInt();
			
			if( methodNumber == 1) {
				out.println("Geben Sie den Dateinamen der Textdatei an: ");
				String filename = scanner.next();
				String text = Files.readString(Path.of(filename));
				rmiMessage = rmiObject.analyzeInput(text);
				out.println("RMI-Nachricht: " + rmiMessage);
			} else if (methodNumber == 2) {
				rmiResult = rmiObject.calculateE(scanner.nextDouble());
				out.println("RMI-Nachricht: " + rmiResult);
			}else {
				out.println("Bitte eine valide Zahl eingeben!");
			}	
			
		}catch(Exception e){
			 out.println(e.getMessage());			 
		}	
	}
}
