import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class BloomFilterClass implements BloomFilterInterface {
	private int[] bloomFilter;
	private int sizeOfBloomFilter;
	private int numberOfHashFunctions;
	private HashImplementation hash = new HashImplementation();
	protected BloomFilterClass() throws RemoteException {
		
		super();
		Scanner s = new Scanner(System.in);
		System.out.println("Enter size of bloom filter");
		sizeOfBloomFilter = s.nextInt();
		
		System.out.println("Enter number of hash functions");
		numberOfHashFunctions = s.nextInt();
		
		bloomFilter = new int[sizeOfBloomFilter];
		
		for(int i = 0; i < sizeOfBloomFilter; i++) {
			bloomFilter[i] = 0;
		}
		
	
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isPresent(String str) throws RemoteException {
		// TODO Auto-generated method stub
		System.out.println(str);
		if(bloomFilter[0] == 123)
			return true;
		
		return false;
	}

	@Override
	public void add(String s) throws RemoteException {
		// TODO Auto-generated method stub
		System.out.println(hash.sfold(s, sizeOfBloomFilter));
		
		
		
	}

	@Override
	public void reset() throws RemoteException {
		// TODO Auto-generated method stub
		
	}

}
