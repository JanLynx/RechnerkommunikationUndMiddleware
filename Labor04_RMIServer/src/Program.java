import static java.lang.System.out;

import java.rmi.*;

public class Program {
		
	public static void main(String[] args) {					
		try {			
			 			
			if (System.getSecurityManager() == null) {
		        System.setSecurityManager(new SecurityManager());
		    }
			
			RMIServer server = new RMIServer();
			
			out.println("Binding ...");			
			Naming.rebind("rmi://localhost:5000/RMIServer", server);
			
			out.println("Server is ready...");
			
		} catch (Exception e) {
			out.println(e.getMessage());
		}
		out.println("Programm Ende.");
	}
}
