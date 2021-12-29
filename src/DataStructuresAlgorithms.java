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
	 * Strategy: iterate through the array, search for potential match (number
	 * when added to current number will produce target sum) by storing numbers in 
	 * a simple hash table and performing a constant time lookup.
	 * 
	 * Key idea: potentialMatch = targetSum - num
	 * 
	 * Steps:
	 * 	1. Create a basic hash table.
	 * 	2. Iterate through array.
	 * 	3. potentialMatch = targetSum - num
	 * 	4. Lookup potentialMatch in hash table and return array of number and potentialMatch
	 * 	5. If potentialMatch not found, store number in hash table 
	 * @param  numbers	array of numbers 
	 * @param  targetSum desired sum
	 * @return array of number and potential match or null 
	 */
	private int[] twoNumberSum(int[] numbers, int targetSum) {
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

	public static void main(String[] args) {
		System.out.println("Hello Metaverse");
		DataStructuresAlgorithms problemSet = new DataStructuresAlgorithms();
		
		/**
		 * Two Number Sum
		 */
		int[] numbers = new int[] {1, 5, 8, 2, 4, 9, 12};
		int targetSum = 21;
		int[] result = problemSet.twoNumberSum(numbers, targetSum);
		System.out.println("target sum is: " + targetSum + " solution is: " + result[0] + ", " + result[1]);
	}
}
