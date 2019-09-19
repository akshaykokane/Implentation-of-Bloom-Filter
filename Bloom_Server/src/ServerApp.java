import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ServerApp {

	public static void main(String[] args) throws RemoteException, MalformedURLException {
		// TODO Auto-generated method stub
		try {
			System.out.println("Server Started");
			
			BloomFilterClass obj = new BloomFilterClass();
			BloomFilterInterface stub = (BloomFilterInterface) UnicastRemoteObject.exportObject(obj, 0);

			Registry registry = LocateRegistry.createRegistry(8080);
	        registry.bind("bloomfilter", stub);
	        System.err.println("Server ready");

			
		}
		catch(Exception e){
			
			System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
			
			
		}

	}

}
