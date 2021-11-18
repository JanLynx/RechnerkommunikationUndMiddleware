//Name: Jan Schönitz m26336
//Datum: 18.11.2021

import java.rmi.*;

import org.w3c.dom.Document;

public interface RMIInterface extends Remote   {
	public String sayHello() throws RemoteException;

	public double calculateE(double value) throws RemoteException;
	
	public Document analyzeInput(String input) throws RemoteException;
}
