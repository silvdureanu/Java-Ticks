package uk.ac.cam.sdu21.tick3;

public class FibonacciCache {
 //TODO: Test your program with values other than 20 as given here
 public static long[] fib = new long[20];
 
 public static void main(String[] args) {
 	store();
 	System.out.println(get(5));

 }

 public static void store() {
  //TODO: using a for loop, fill "fib" with the Fibonacci numbers 
  //      e.g. if length of "fib" is zero, store nothing; and
  //           if length is six, store 1,1,2,3,5,8 in "fib"
 	if (fib.length>1) {
 		fib[0]=1;
 		fib[1]=1;
 		for(int i=2; i<fib.length; i++) {
 			fib[i]=fib[i-1]+fib[i-2];
 		}
 	}
 	else if(fib.length==1)
 			fib[0]=1;
 	

 }

 public static void reset() {
  //TODO: using a for loop, set all the elements of fib to zero
 	for(int i=0; i<fib.length; i++)
 		fib[i]=0;
 }
 
 public static long get(int i) {
  //TODO: return the value of the element in fib found at index i
  //      e.g. "get(3)" should return the fourth element of fib
  //
  //Note: your code should check that i is within the bounds of fib
  //      and if it is outside this range return the literal "-1L"
 	if(i>=fib.length||i<0)
 		return -1L;
 	else return fib[i];
 }
}