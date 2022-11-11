import java.util.*;

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
	 * 1. Create a basic hash table.
	 * 2. Iterate through array.
	 * 3. potentialMatch = targetSum - num
	 * 4. Lookup potentialMatch in hash table and return array of number and
	 * potentialMatch
	 * 5. If potentialMatch not found, store number in hash table
	 * 
	 * Time/Space Complexity:
	 * O(n) time: iterate through the entire list
	 * O(n) space: store n numbers in the hash table
	 * 
	 * @param numbers   array of numbers
	 * @param targetSum desired sum
	 * @return array of number and potential match or null
	 * 
	 */
	public int[] twoNumberSum(int[] numbers, int targetSum) {
		HashMap<Integer, Boolean> numbersHashMap = new HashMap<>();
		for (int number : numbers) {
			int potentialMatch = targetSum - number;
			if (numbersHashMap.containsKey(potentialMatch)) {
				return new int[] { potentialMatch, number };
			}
			numbersHashMap.put(number, true);
		}
		return new int[] {};
	}

	/**
	 * Balanced Brackets
	 * 
	 * Key idea:
	 * 1. If there is a closed bracket, the previous bracket
	 * (the last bracket on the stack) must be a matching open bracket,
	 * otherwise it's unbalanced.
	 * 
	 * 2. A string has balanced brackets only if the stack is empty after
	 * iterating through the entire string.
	 * 
	 * Time/Space Complexity:
	 * O(n) time: iterate through characters in expression
	 * O(n) space: store brackets in the expression
	 */

	public boolean isBalancedBrackets(String expression) {
		Stack<Character> stack = new Stack<>();
		String openBrackets = "([{";
		String closedBrackets = ")]}";
		HashMap<Character, Character> brackets = new HashMap<>();
		brackets.put(')', '(');
		brackets.put(']', '[');
		brackets.put('}', '{');

		for (char c : expression.toCharArray()) {
			if (openBrackets.contains(Character.toString(c))) {
				stack.push(c);
			} else if (closedBrackets.contains(Character.toString(c))) {
				// Check if c is a closing bracket for
				// the previous open bracket (top of the stack).
				// If it's not or if stack is empty before completely
				// iterating through the array, then expression does not
				// contain balanced brackets.
				if (stack.isEmpty() || brackets.get(c) != stack.pop()) {
					return false;
				}
			}
		}
		return stack.isEmpty();
	}

	/**
	 * Binary Search
	 * 
	 * Problem: Return index of target element or -1
	 * 
	 * Key idea: Search for target by recursively dividing the array in
	 * half and look for the target in either the left or right half. Each
	 * iteration reduces the search space by 1/2.
	 * 
	 * Steps:
	 * 1. Left/Right pointers at beginning/end of array
	 * 2. Check terminal (base) case: left index > right index
	 * 3. Compute floor of middle index
	 * 4. Check 3 cases:
	 * a. target == potentialMatch
	 * b. target < potentialMatch => look in left half of array
	 * c. target > potentialMatch => look in right half of array
	 * 
	 * Time/Space Complexity:
	 * O(log n) time: eliminate 1/2 of elements in each iteration
	 * For iterative approach, O(1) space (constant space): no elements stored
	 * For recursive approach, O(log n) space:
	 * 
	 * @param numbers array of numbers
	 * @param target  target number
	 * @param left    left pointer index
	 * @param right   right pointer index
	 * @return index of the target number or -1
	 */
	public int binarySearch(int[] numbers, int target, int left, int right) {
		/**
		 * Base case:
		 * When get to final number (either first or last),
		 * left == right, pivot index is 0 or lastIndex
		 * In the next iteration, left and right will cross
		 * because either left = pivot + 1 or right = pivot - 1,
		 * depending on whether target is greater or less than
		 * the potential match at the pivot index, indicating
		 * search is complete.
		 */
		if (left > right) {
			return -1;
		}
		// 1. Java rounds down by default
		// 2. Use this method for finding middle index to prevent Integer overflow
		// if left, right or both are a large value (Integer.MAX_VALUE)
		int pivot = left + (right - left) / 2;
		int potentialMatch = numbers[pivot];
		if (potentialMatch == target) {
			return pivot;
		}
		if (target > potentialMatch) {
			return binarySearch(numbers, target, pivot + 1, right);
		}
		return binarySearch(numbers, target, left, pivot - 1);
	}

	public void printList(List<List<Integer>> elements) {
		for (List<Integer> el : elements) {
			System.out.println(el.toString());
		}
	}

	public static void main(String[] args) {
		System.out.println("Hello Metaverse");

		DataStructuresAlgorithms problemSet = new DataStructuresAlgorithms();
		int[] numbers = new int[0];

		/**
		 * Two Number Sum/
		 */
		numbers = new int[] { 1, 5, 8, 2, 4, 9, 12 };
		int targetSum = 21;
		int[] resultTwoNumberSum = problemSet.twoNumberSum(numbers, targetSum);
		System.out.println(resultTwoNumberSum);
		System.out.println("Two Number Sum. Target sum is: " + targetSum + " solution is: " + resultTwoNumberSum[0]
				+ ", " + resultTwoNumberSum[1]);

		/**
		 * Balanced Brackets
		 */
		String expression = "{{[[((a, b))],[]]}}"; // true
		// String expression = "{{[}}]]"; // false
		System.out.println("Balanced Brackets. Expression is: "
				+ expression
				+ " Is balanced brackets? " + problemSet.isBalancedBrackets(expression));

		/**
		 * Binary Search
		 */
		numbers = new int[] { 2, 5, 6, 9, 15, 19, 23 };
		int target = 19;
		int resultBinarySearch = problemSet.binarySearch(numbers, target, 0, numbers.length);
		System.out.println("Binary Search. " + "Target is: " + target + " Index of target is: " + resultBinarySearch);

	}
}
