import java.util.*;

/**
 * Basic Hash Table: Two Number Sum
 * 
 * Problem: Return indices of 2 numbers that equal the target sum or null.
 * 
 * Key idea: complement = targetSum - num
 * 
 * Strategy: iterate through the array, search for complement (number
 * when added to current number will produce target sum) by storing numbers in
 * a hash table and performing a constant time lookup.
 * 
 * Steps:
 * 1. Create a basic hash table.
 * 2. Iterate through array.
 * 3. complement = targetSum - num
 * 4. Lookup complement in hash table and return array of number and
 * complement
 * 5. If complement not found, store number in hash table
 * 
 * Time/Space Complexity:
 * O(n) time: iterate through the entire list
 * O(n) space: store n numbers in the hash table
 * 
 * @param numbers   array of numbers
 * @param targetSum desired sum
 * @return array of indices of two numbers or null
 * 
 */
public class TwoNumberSum {

    public int[] getTwoNumberSumIndices(int[] numbers, int targetSum) {
        Map<Integer, Integer> numbersMap = new HashMap<Integer, Integer>();
        for (int i = 0; i < numbers.length; i++) {
            int complement = targetSum - numbers[i];
            if (numbersMap.containsKey(complement)) {
                return new int[] { i, numbersMap.get(complement) };
            }
            numbersMap.put(numbers[i], i);
        }
        return null;
    }

   	public static void main(String[] args) {
		int[] numbers = new int[] { 1, 5, 8, 2, 4, 9, 12 };
		int targetSum = 21;
		TwoNumberSum sum = new TwoNumberSum();
		int[] twoNumberSumIndices = sum.getTwoNumberSumIndices(numbers, targetSum);
		// Solution: [6, 5]
		System.out.println("Two Number Sum. Target sum is: " + targetSum + " by adding " + numbers[twoNumberSumIndices[0]] + " and " 
            + numbers[twoNumberSumIndices[1]] + " from indices " 
            + twoNumberSumIndices[0] + ", " + twoNumberSumIndices[1]);
	}
}

