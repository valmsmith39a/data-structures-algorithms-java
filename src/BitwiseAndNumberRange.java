import java.util.*;
/**
 * Problem: Bitwise AND of Numbers Range 
 *
 * Description: 
 * Given 2 integers[left, right], return the bitwise AND of all numbers in this range, inclusive,
 *
 * Insight: 
 * Shift bits of each number to the right until find the bit (1 or 0) that is common to all the numbers in the range.
 */ 
public class BitwiseAndNumberRange {

    public int rangeBitwiseAnd(int m, int n) {
        int shift = 0;
        while (m < n) {
            /**
             * Same as m = m >> 1
             * Shift m to the right by 1 bit.
             */
            m >>= 1;
            n >>= 1;
            shift++;
        }
        return m << shift;
    }

    public static void main(String[] args) {
        BitwiseAndNumberRange bitwiseAnd = new BitwiseAndNumberRange();
        // Expected: 4
        System.out.println(bitwiseAnd.rangeBitwiseAnd(5, 7));
        // Expected: 8
        System.out.println(bitwiseAnd.rangeBitwiseAnd(9, 12));
        // Expected: 0
        System.out.println(bitwiseAnd.rangeBitwiseAnd(1, 2147483647));
    }
}
