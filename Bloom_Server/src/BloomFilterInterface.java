import java.rmi.Remote;
import java.rmi.RemoteException;


public interface BloomFilterInterface extends Remote {
	
	public boolean isPresent(String str) throws RemoteException; 
	public void add(String s) throws RemoteException;
	public void reset() throws RemoteException;

}
