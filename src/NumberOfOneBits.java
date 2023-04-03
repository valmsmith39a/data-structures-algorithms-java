/**
 * Problem: NumberOfOneBits
 * 
 * Description: Write a function that takes an unsigned integer and returns the number of '1' bits it has (also known as the Hamming weight).
 * 
 * For example, the 32-bit integer '11' has binary representation 00000000000000000000000000001011, so the function should return 3.
 * 
 * Key Insights: 
 * 1. Get least significant bit (LSB) by using n & 1 or n % 2
 * 2. Right shift n by 1 bit to get the next LSB. 
 * 	a. Use n = n >> 1 for signed integers
 *  b. Use n = n >>> 1 for unsigned integers. >>> will fill the leftmost bits with 0s, effectively treating the number as unsigned.
 * 
 * Time Complexity: O(1) - 32 iterations, because the input is a 32-bit integer
 * Space Complexity: O(1) - no extra space used 
 */
public class NumberOfOneBits {

	public int hammingWeight(int n) {
		int count = 0;
		while (n != 0) {
			count += n & 1;
			n = n >>> 1;
		}
		return count;
	}

	public static void main(String[] args) {
		NumberOfOneBits obj = new NumberOfOneBits();
		// Expected output: 2
		System.out.println(obj.hammingWeight(3));
	}

}
