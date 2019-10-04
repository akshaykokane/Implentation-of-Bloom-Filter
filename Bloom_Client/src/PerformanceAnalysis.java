import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class PerformanceAnalysis {

	public static void main(String[] args) throws NotBoundException, IOException {
		// TODO Auto-generated method stub
	
	  Scanner s = new Scanner(System.in);
  	  File inputFile = new File("/Users/akshaykokane/Documents/eclipse-workplace/Bloom_Client/src/input.txt");
  	  BufferedReader br = new BufferedReader(new FileReader(inputFile)); 
  	  
  	  System.out.println("Enter how many inputs to test from input file : (10, 50, 100, 1000, 5000)");
  	  int numberOfInputs = s.nextInt();
  	  ArrayList<String> inputSet = new ArrayList<>();
  	  ArrayList<String> testSet = new ArrayList<>();
  
  	    
  	  choose(inputFile, inputSet, testSet, numberOfInputs);
  	
  		 System.out.println("Input size : "+inputSet.size()); 
  	  
  	  String st;
  	  
  	  int count = 0;
  	  Registry registry = LocateRegistry.getRegistry(8080);
      BloomFilterInterface stub = (BloomFilterInterface) registry.lookup("bloomfilter");
       
      for(String str : inputSet)
      {
    	  System.out.println(str);
    	  System.out.println("added string " + count);
    	  count++;;
    	  stub.add(str);
       
  	  }
  	  System.out.println("Analysing by using testSet input : ");
  	  count = 0;
  	  float falsePositiveCount = 0;
      for(String str : testSet)
      {
    	  System.out.println(str);
    	  System.out.println("getting string(Count)" + count);
    	  
    	  if(stub.isPresent(str)) {
    		  falsePositiveCount++;
    
    	  }
    		  
    	  
    	  count++;
       
  	  }
  	  
      System.out.println("False Positive Count : " + falsePositiveCount);
      System.out.println("False Positive Rate Report: \n When number of input " + numberOfInputs);
      float falsPositiveRate = (falsePositiveCount/numberOfInputs) * 100;
      System.out.println("False Positive Rate is : " +falsPositiveRate + "% ");
      
  	  


	}
	
	 public static void choose(File file, ArrayList<String> inputSet, ArrayList<String> testSet, int numberOfInputs) throws IOException
	  {
		 //generate random line number for file
	     Random rand = new Random();
	     ArrayList<Integer> randomLineNumbers = new ArrayList<Integer>();
	     
	     for(int i = 0; i < numberOfInputs * 2; i++) {
	    	 int randomLine = Math.abs(rand.nextInt()) % 60000;
	    	 randomLineNumbers.add(randomLine);
	    //	 System.out.println(randomLine);
	     }
	     
	     BufferedReader br = new BufferedReader(new FileReader(file)); 
	     
	     String st; 
	     boolean toggle = false;
	     int currentLineNumber = 0;
	     while ((st = br.readLine()) != null) { 
	    	 currentLineNumber++;
	    	 if(randomLineNumbers.contains(currentLineNumber)) {
	    		if(toggle)
	    		 inputSet.add(st);	 
	    		else
	    		  testSet.add(st);	
	    		
	    		toggle = !toggle;
	    	}
	    	 	 
	     } 
	
	     
	     
	     
	     
	        
	  }

}