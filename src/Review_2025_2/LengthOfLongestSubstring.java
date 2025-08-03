package Review_2025_2;

import java.util.HashMap;
import java.util.Map;

/**
 * Time complexity: O(n), iterate through every character of the string
 * Space complexity: O(min(m, n)), m: size of character set (e.g. 128 for
 * ASCII), n: length of string
 * Reason is bc possible for length of string n to be larger than character set
 * bc of duplicates, but we only
 * store at most size of the character set m.
 */
public class LengthOfLongestSubstring {

	public int lengthOfLongestSubstring(String s) {
		Map<Character, Integer> charIndex = new HashMap<>();
		int maxLength = 0;
		int left = 0;
		for (int right = 0; right < s.length(); right++) {
			char currentChar = s.charAt(right);
			if (charIndex.containsKey(currentChar) && charIndex.get(currentChar) >= left) {
				left = charIndex.get(currentChar) + 1;
			} else {
				maxLength = Math.max(maxLength, right - left + 1);
			}
			charIndex.put(currentChar, right);
		}
		return maxLength;
	}
}
