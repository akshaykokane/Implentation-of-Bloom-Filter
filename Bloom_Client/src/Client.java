import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
	 public static void main(String[] args) throws IOException, NotBoundException {
		 //read the input from the file
		 Registry registry = LocateRegistry.getRegistry(8080);
         BloomFilterInterface stub = (BloomFilterInterface) registry.lookup("bloomfilter");
		 File inputFile = new File("/Users/akshaykokane/Documents/eclipse-workplace/Bloom_Client/src/inputFile2.txt");
	  	 BufferedReader br = new BufferedReader(new FileReader(inputFile));
	  	 String st;
	  	 float falsePositiveCount = 0;
	     float numberOfInputs = 0;
	     float numberOfTestInputs = 0;
	  	 while ((st = br.readLine()) != null) {
	  		
	  		 if(st.contains("add")) {
	  			stub.add(st.split(" ")[1]);
	  			numberOfInputs++;
	  		 }
	  		 
	  		 if(st.contains("test")) {
	  			numberOfTestInputs++;
	  			if(stub.isPresent(st.split(" ")[1]))
	  				falsePositiveCount++;
	  			 
	  		 }
	  		 if(st.contains("reset")) {
	  			 stub.reset();
	  		 }
	  	 }
	  	
	    String fileContent =  "\nFalse Positive Count : " + falsePositiveCount;
	
	    fileContent += "\nFalse Positive Rate Report: \n When number of input " + numberOfInputs + " Test Input size is : "+numberOfTestInputs
	    		;
	 
	    float falsPositiveRate = (falsePositiveCount/numberOfTestInputs);
	      fileContent += "\nFalse Positive Rate is : " +falsPositiveRate + " ";
	  	 
		BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));
		writer.write(fileContent);
	    writer.close();
	    System.out.println("Output stored in file output.txt");
		 
	 }
}
