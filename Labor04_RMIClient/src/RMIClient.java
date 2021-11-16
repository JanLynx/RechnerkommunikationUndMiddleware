
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
			out.println("Wählen Sie die Methode, welche Sie entfernt aufrufen möchten:\n1 Textanalyse\n2 Exponetielle Berechnung von x");
			int methodNumber = scanner.nextInt();
			out.println("Ihre Menüwahl: " + methodNumber);
			
			if( methodNumber == 1) {
				int charCounter = 0;
				int wordCounter = 0;
				int eCounter = 0;
				
				out.print("Geben Sie den Dateinamen der Textdatei an: ");
				String filename = scanner.next();
				String text = Files.readString(Path.of(filename));				
				String[] parts = rmiObject.analyzeInput(text).split(",");
				charCounter = Integer.parseInt(parts[0]);
				wordCounter = Integer.parseInt(parts[1]);
				eCounter = Integer.parseInt(parts[2]);
				out.println("Zeichenzahl (ohne Carriage Return und Line Feed): " + charCounter + "\nWörterzahl: " + wordCounter +"\nAnzahl von \"e\": " + eCounter);
			} else if (methodNumber == 2) {
				out.print("Welche Zahl möchten Sie als Exponent einsetzen?:");
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
