
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class ClientRequest {

    public static void main(String[] args) {
    	Scanner s = new Scanner(System.in);
        try {
        	Registry registry = LocateRegistry.getRegistry(8080);
            BloomFilterInterface stub = (BloomFilterInterface) registry.lookup("bloomfilter");
        	while(true) {
        		System.out.println("Input option : 1 : add, 2 : isPresent, 3 : exit");
        		 String query = s.nextLine();
        		switch(query) {
        		case "1" :
    				System.out.println("Enter the string to add : ");
    				String input = s.nextLine();
    				stub.add(input);
    				break;
        		case "2":
        			System.out.println("Enter the string to search : ");
    				input = s.nextLine();
    				boolean response = stub.isPresent(input);
    				System.out.println(response ? "Present" : "May be not present");
    				break;
    			
        		case "3":
        			return;
        		}
        			
        		
        				
        	}  
           
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}