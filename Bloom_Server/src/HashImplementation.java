
public class HashImplementation {
	// Use folding on a string, summed 4 bytes at a time
	//ref: http://research.cs.vt.edu/AVresearch/hashing/strings.php
	long sfold(String s, int M) {
	     int intLength = s.length() / 4;
	     long sum = 0;
	     for (int j = 0; j < intLength; j++) {
	       char c[] = s.substring(j * 4, (j * 4) + 4).toCharArray();
	       long mult = 1;
	       for (int k = 0; k < c.length; k++) {
		 sum += c[k] * mult;
		 mult *= 256;
	       }
	     }

	     char c[] = s.substring(intLength * 4).toCharArray();
	     long mult = 1;
	     for (int k = 0; k < c.length; k++) {
	       sum += c[k] * mult;
	       mult *= 256;
	     }

	     return(Math.abs(sum) % M);
	   }

}
