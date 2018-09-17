package Week_1;

/**
 * Programming Assignment #1
 * 
 * In this programming assignment you will implement one or more of the integer multiplication algorithms described in lecture.
 * 
 * To get the most out of this assignment, your program should restrict itself to multiplying only pairs of single-digit numbers. 
 * 
 * You can implement the grade-school algorithm if you want, but to get the most out of the assignment 
 * you'll want to implement recursive integer multiplication and/or Karatsuba's algorithm.
 * 
 * So: what's the product of the following two 64-digit numbers?
 * 
 * 3141592653589793238462643383279502884197169399375105820974944592
 * 
 * 2718281828459045235360287471352662497757247093699959574966967627

[TIP: before submitting, first test the correctness of your program on some small test cases of your own devising. 
Then post your best test cases to the discussion forums to help your fellow students!]

[Food for thought: the number of digits in each input number is a power of 2. 
Does this make your life easier? Does it depend on which algorithm you're implementing?]

The numeric answer should be typed in the space below. So if your answer is 1198233847, 
then just type 1198233847 in the space provided without any space / commas / any other punctuation marks.

(We do not require you to submit your code, 
so feel free to use any programming language you want --- just type the final numeric answer in the following space.)


 * */
/*
 * Thoughts: I used the approach of multiplying strings instead of multiplying integers due to the limit exceeding in long.
 * 
 * https://leetcode.com/problems/multiply-strings/discuss/17605/Easiest-JAVA-Solution-with-Graph-Explanation?page=7
 * 
 * for the given inputs, the result will be:
 * result: 8539734222673567065463550869546574495034888535765114961879601127067743044893204848617875072216249073013374895871952806582723184
 * */
public class IntegerMultiplication {
	
	public static String multiply (String num1, String num2){
		int m = num1.length(), n = num2.length();
		int[] pos = new int[m + n];
		
		for(int i = m -1; i >= 0; i--) {
			for(int j = n - 1; j >= 0; j--){
				int mul = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
				
				int p1 = i + j, p2 = i + j + 1;
				int sum = mul + pos[p2];
				
				pos[p1] += sum/10;
				pos[p2] = (sum) % 10;
				
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int p : pos) if(!(sb.length() == 0 && p == 0)) sb.append(p);
		
		return sb.length() == 0 ? "0" : sb.toString();
	}
	 /** Main function **/
    public static void main (String[] args) 
    {
    	String num1 = "3141592653589793238462643383279502884197169399375105820974944592";
    	String num2 = "2718281828459045235360287471352662497757247093699959574966967627";
    	
    	System.out.println("result: " + multiply(num1, num2));
    	
    }
    
    

}
