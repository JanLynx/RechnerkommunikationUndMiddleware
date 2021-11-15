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

}
