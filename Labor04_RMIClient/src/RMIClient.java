
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.util.Scanner;

import static java.lang.System.out;

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
			out.println("Wählen Sie die Methode, welche Sie entfernt aufrufen möchten:\n1 Textanalyse\n2 Exponetielle Berechnung von x");
			int methodNumber = scanner.nextInt();
			
			if( methodNumber == 1) {
				rmiResult = rmiObject.calculateE(scanner.nextDouble());
				out.println("RMI-Nachricht: " + rmiResult);
			} else if (methodNumber == 2) {
				rmiMessage = rmiObject.analyseInput("Testtext zum testen");
				out.println("RMI-Nachricht: " + rmiMessage);
			}else {
				out.println("Bitte eine valide Zahl eingeben!");
			}	
			
		}catch(Exception e){
			 out.println(e.getMessage());			 
		}	
	}
}
