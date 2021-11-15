import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIInterface extends Remote, Serializable {
	public String sayHello() throws RemoteException;
	
	public double calculateE(double value) throws RemoteException;
}
