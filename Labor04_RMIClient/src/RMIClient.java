
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
			rmiMessage = rmiObject.sayHello();
			out.println("RMI-Nachricht: " + rmiMessage);
			
			scanner = new Scanner(System.in);
			rmiResult = rmiObject.calculateE(scanner.nextDouble());
			out.println("RMI-Nachricht: " + rmiResult);
		}catch(Exception e){
			 out.println(e.getMessage());			 
		}	
	}
}
