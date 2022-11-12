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
	}
}
