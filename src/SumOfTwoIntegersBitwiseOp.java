import java.util.*;
/**
 * Sum of Two Integers (bitwise)
 *
 * Problem description: 
 * Add 2 integers without using + or - operator.
 *
 * Insight: 
 * 1. Use XOR to add 
 * 2. Use AND for carry 
 * 
 * Time/Space Complexity: 
 * O(1) time because number of bits for an integer is set (8-bit, 16-bit, 32-bit, 64-bit).
 * O(1) space because always storing a set number of bits. 
 * 
 *   3 + 3 = 6 
 *
 *   a = 0011
 *   b = 0011
 *
 *   carry: 0110, 0000        
 *   a:     0000, 0110*
 *   b:     0110, 0000
 *
 *   a = 0110 = 6
 *
 */
public class SumOfTwoIntegersBitwiseOp {
    /**
     * XOR the 2 integers, save the result 
     * AND the 2 integers (original, before change), shift bits to the left, save the result
     * Continue to find the XOR and the AND, << 1 until the AND << 1 is 0 (no carry). 
     */ 
    public int getSumIterative(int a, int b) {
        while (b != 0) {
            int carry = (a & b) << 1;
            a = a ^ b;
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
