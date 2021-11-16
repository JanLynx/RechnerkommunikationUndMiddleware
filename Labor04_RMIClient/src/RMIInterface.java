import java.rmi.*;

public interface RMIInterface extends Remote   {
	public String sayHello() throws RemoteException;

	public double calculateE(double value) throws RemoteException;
	
	public String analyzeInput(String input) throws RemoteException;
}
