import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;
import java.util.Scanner;

public class BloomFilterClass implements BloomFilterInterface {
	private int[] bloomFilter;
	private int sizeOfBloomFilter;
	private int numberOfHashFunctions;
	private HashImplementation hash = new HashImplementation();
	public int[] hashFunctions;
	Random random = new Random();
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
		
		hashFunctions = new int[numberOfHashFunctions];
		
		for(int i = 0; i < numberOfHashFunctions; i++)
			hashFunctions[i] = random.nextInt();
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isPresent(String str) throws RemoteException {
		// TODO Auto-generated method stub
		System.out.println("Incoming request to isPresent string from client. Searching in bloom filter..");
		boolean ans = true;
		long hashValue = hash.sfold(str, sizeOfBloomFilter);
		for(int i = 0; i < numberOfHashFunctions; i++) {
			
			int val = (int) ((hashValue * hashFunctions[i]) % sizeOfBloomFilter);
			
			//System.out.println("Hash Value : "+i + " : " + val);
			
			ans &= getBit(Math.abs(val));
		
		}
		
		System.out.println("Sending response to client..");
		System.out.println("Number of bits set : " + numberOfBitSet());
		return ans;
	}

	@Override
	public void add(String s) throws RemoteException {
		// TODO Auto-generated method stub
		System.out.println("Incoming request to add string from client. Adding to bloom filter..");
		long hashValue = hash.sfold(s, sizeOfBloomFilter);
		for(int i = 0; i < numberOfHashFunctions; i++) {
			
			int val = (int) ((hashValue * hashFunctions[i]) % sizeOfBloomFilter);
			
			//System.out.println("Hash Value : "+i + " : " + val);
			
			setBit(Math.abs(val));
		
		}
		
		System.out.println("String added");
		
	}

	@Override
	public void reset() throws RemoteException {
		// TODO Auto-generated method stub
		for(int i = 0; i < sizeOfBloomFilter; i++)
			bloomFilter[i] = 0;
		
	}
	
	public void setBit(int val) {
		bloomFilter[val] = 1;
	}
	
	public boolean getBit(int val) {
		
		return bloomFilter[val] == 1? true : false;
	}
	
	public int numberOfBitSet() {
		int c = 0;
		for(int i = 0; i < sizeOfBloomFilter; i++) {
			if(bloomFilter[i] == 1)
				c++;
		}
		return c;
		
	}

}
