/**
 * Problem: Sum of two integers without using + or - operator
 * 
 * Description: 
 * Calculate the sum of two integers a and b, but you are not allowed to use the operator + and -.
 * 
 * Time Complexity: O(1), because the number of bits in an integer is fixed. Each operation is performed bit by bit, from 
 * most significant bit to least significant bit. Since we perform XOR and AND operations on each bit, the number of operations 
 * is 2n, where n is the number of bits in an integer (ex. 32 bits for an integer in Java). 
 * 
 * Space Complexity: O(1), because we only use two variables to store the intermediate results.  
 * 
 */
public class SumOfTwoIntegersBitwiseOpV2 {

	public int getSum(int a, int b) {
		int ans = 0;
		int carry = 0;
		while (b != 0) {
			ans = a ^ b;   // bitwise XOR 
			carry = a & b; // bitwise AND 
			a = ans;
			b = carry;
		}
		return a;
	}

	public static void main(String[] args) {
		int a = 1;
		int b = 2;
		int c = 9;
		int d = 11;

		SumOfTwoIntegersBitwiseOp op = new SumOfTwoIntegersBitwiseOp();
		System.out.println("1 + 2 = " + op.getSumIterative(a, b));
		System.out.println("9 + 11 = " + op.getSumIterative(c, d));
    }
}
