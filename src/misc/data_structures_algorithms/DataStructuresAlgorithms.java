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

		DataStructuresAlgorithms problemSet = new DataStructuresAlgorithms();

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
