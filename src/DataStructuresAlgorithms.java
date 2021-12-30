import java.util.Map;
import java.util.HashMap;

/**
 * Data structures and algorithms problem set 
 * 
 * @author George Wee
 * @version 1.0
 * 
 */
public class DataStructuresAlgorithms {
	
	/**
	 * Basic Hash Table: Two Number Sum
	 * 
	 * Problem: Return 2 numbers that equal the target sum or [].
	 * 
	 * Key idea: potentialMatch = targetSum - num
	 * 
	 * Strategy: iterate through the array, search for potential match (number
	 * when added to current number will produce target sum) by storing numbers in 
	 * a simple hash table and performing a constant time lookup.
	 * 
	 * Steps:
	 * 	1. Create a basic hash table.
	 * 	2. Iterate through array.
	 * 	3. potentialMatch = targetSum - num
	 * 	4. Lookup potentialMatch in hash table and return array of number and potentialMatch
	 * 	5. If potentialMatch not found, store number in hash table 
	 * 
	 * Time/Space Complexity:
	 * 	O(n) time: iterate through the entire list
	 * 	O(n) space: store n numbers in the hash table
	 * 
	 * @param  numbers	array of numbers 
	 * @param  targetSum desired sum
	 * @return array of number and potential match or null
	 *  
	 */
	public int[] twoNumberSum(int[] numbers, int targetSum) {
		Map<Integer, Integer> numbersHashMap = new HashMap<>();
		for (int i = 0; i < numbers.length; i++) {
			int potentialMatch = targetSum - numbers[i];
			if (numbersHashMap.containsKey(potentialMatch)) {
				int potentialMatchIndex = numbersHashMap.get(potentialMatch);
				return new int[] { numbers[potentialMatchIndex], numbers[i] };	
			}
			numbersHashMap.put(numbers[i], i);
		}
		return null;
	}
	
	/**
	 * Binary Search
	 * 
	 * Problem: Return index of target element or -1
	 * 
	 * Key idea: Search for element by repeatedly dividing array in half and see if 
	 * element is in right or left half.
	 * 
	 * Steps:
	 * 	1. Left/Right pointers at beginning/end of array
	 * 	2. Check terminal (base) case: left index > right index 
	 * 	3. Compute floor of middle index
	 *  4. Check 3 cases: 
	 *  	a. target == potentialMatch
	 *  	b. target < potentialMatch => look in left half of array
	 *  	c. target > potentialMatch => look in right half of array
	 * 
	 * Time/Space Complexity:
	 * 	O(log n) time: eliminate 1/2 of elements in each iteration 
	 * 	O(1) space (constant space): no elements stored
	 * 
	 * @param numbers	array of numbers
	 * @param target	target number
	 * @param left		left pointer index
	 * @param right	 	right pointer index
	 * 
	 */
	public int binarySearch(int[] numbers, int target, int left, int right) {
		if (left > right) {
			return -1;
		}
		int pivot = (left + right) / 2; // Java rounds down by default
		int potentialMatch = numbers[pivot];
		if (target == potentialMatch) {
			return pivot;
		}
		if (target < potentialMatch) {
			return binarySearch(numbers, target, left, pivot - 1);
		}
		return binarySearch(numbers, target, pivot + 1, right);	
	}

	public static void main(String[] args) {
		System.out.println("Hello Metaverse");

		DataStructuresAlgorithms problemSet = new DataStructuresAlgorithms();
		int[] numbers = new int[0];
		
		/**
		 * Two Number Sum
		 */
		numbers = new int[] { 1, 5, 8, 2, 4, 9, 12 };
		int targetSum = 21;
		int[] resultTwoNumberSum = problemSet.twoNumberSum(numbers, targetSum);
		System.out.println("Two Number Sum. Target sum is: " + targetSum + " solution is: " + resultTwoNumberSum[0] + ", " + resultTwoNumberSum[1]);
		
		/**
		 * Binary Search
		 */
		numbers = new int[] { 2, 5, 6, 9, 15, 19, 23 };
		int target = 23;
		int resultBinarySearch = problemSet.binarySearch(numbers, target, 0, numbers.length);
		System.out.println("Binary Search. " + "Target is: " + target + " Index of target is: " + resultBinarySearch);	
	}
}
