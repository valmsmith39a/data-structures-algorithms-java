import java.util.*;
/**
 *
 * Insight: 
 * 1. Use XOR to add 
 * 2. Use AND for carry 
 */
public class SumOfTwoIntegerBit {
    // O(n) time | O(1) space
    public int getSum(int a, int b) {
        while (b != 0) {
            int tmp = (a & b) << 1;
            a = a ^ b;
            b = tmp;
        }
        return a;
    }

    public static void main(String[] args) {
        int a = 1; 
        int b = 2;
        int c = 9; 
        int d = 11;

        SumOfTwoIntegerBit op = new SumOfTwoIntegerBit();
        System.out.println("1 + 2 = " + op.getSum(a, b));
        System.out.println("9 + 11 = " + op.getSum(c, d));
    }
}
