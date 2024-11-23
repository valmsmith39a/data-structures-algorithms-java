package Review_2024;

import java.util.Map;
import java.util.ArrayList;
import java.util.List;

/**
 * Letter Combination of Phone Number #17
 * 
 * Key Insight:
 * 1. Backtracking - when move back up recursive call, remove the previous
 * appended character
 * 
 * Time Complexity:
 * n: number of digits (ex "23")
 * m: number of characters associated with each digit (ex "abc")
 * 
 * m^n number of combinations
 * each combinations takes O(n) time. Ex for 2 digits like "23" need to append
 * and remove character 2 times
 * O (n * m^n), since m^n dominates, runtime is O(m^n)
 * 
 * Space complexity:
 * O(n) space, depth of recursive call stack is equal to number of digits
 * ("23"), recursive call stack depth is 2.
 */
public class LetterCombinationsOfPhoneNumber {
	private List<String> combinations = new ArrayList<>();
	Map<Character, String> letters = Map.of(
			'2', "abc",
			'3', "def",
			'4', "ghi",
			'5', "jkl",
			'6', "mno",
			'7', "pqrs",
			'8', "tuv",
			'9', "wxyz");
	private String phoneDigits;

	public List<String> letterCombinations(String digits) {
		if (digits.length() == 0) {
			return combinations;
		}

		phoneDigits = digits;
		backtrack(0, new StringBuilder());
		return combinations;
	}

	private void backtrack(int index, StringBuilder path) {
		if (path.length() == phoneDigits.length()) {
			combinations.add(path.toString());
			return;
		}

		String possibleLetters = letters.get(phoneDigits.charAt(index));

		for (char letter : possibleLetters.toCharArray()) {
			path.append(letter);
			backtrack(index + 1, path);
			path.deleteCharAt(path.length() - 1);
		}
	}
}
