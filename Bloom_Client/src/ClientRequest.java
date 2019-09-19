
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ClientRequest {

    
    public static void main(String[] args) {

        String host = (args.length < 1) ? null : args[0];
        try {
            Registry registry = LocateRegistry.getRegistry(8080);
            BloomFilterInterface stub = (BloomFilterInterface) registry.lookup("bloomfilter");
            stub.add("akshay");
            boolean response = stub.isPresent("akshay");
            
            System.out.println("response: " + response);
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}