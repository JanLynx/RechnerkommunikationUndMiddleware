import java.rmi.*;
import java.rmi.server.*;

public class RMIServer extends UnicastRemoteObject implements RMIInterface {
			
	private static final long serialVersionUID = 1L;

	protected RMIServer() throws RemoteException {
		super();
	}

	@Override
	public String sayHello() throws RemoteException {		
		return "Hello World, Iam the Server.";
	}
	
	@Override
	public double calculateE(double value) throws RemoteException {	
		return Math.pow(2.718281828, value);
	}

	@Override
	public String analyzeInput(String input) throws RemoteException {
		int charCounter = 0;
		int wordCounter = 0;
		int eCounter = 0;
		
		//Counting Chars
        for(int i = 0; i < input.length(); i++) {    
            if(input.charAt(i) > 30)    
                charCounter++;    
        }
        
        //Counting Words
        String[] words = input.split("\\s+");
        wordCounter = words.length;
        
        //Counting "e"
        for(int i = 0; i < input.length(); i++) {    
            if(input.charAt(i) == 'e')    
                eCounter++;   
        }
		
        String message = "Zeichenzahl (ohne Carriage Return und Line Feed): " + charCounter + "\nWörterzahl: " + wordCounter +"\nAnzahl von \"e\": " + eCounter;
		return message;
	}

}
