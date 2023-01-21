import java.util.*;

/**
 * Problem: Longest Substring Without Duplicates 
 *
 * Strategy: 
 *
 * Sliding window with left / right pointers. 
 *
 * Traverse string with right pointer. 
 * When hit duplicate, move left pointer forward and remove character at left pointer from set until current character no longer in the set. This ensures that your window will always have unique characters. 
 *
 * Time Complexity: O(n): iterate through string once. 
 * Space Complexity: O(n): possible for character set to contain all the characters in the string because all are unique.
 */
public class LongestSubstringV2 {

	public int lengthOfLongestSubstring(String s) {
		int left = 0; 
		int len = 0; 
		Set<Character> charSet = new HashSet<>();
		for (int right = 0; right < s.length(); right++) {
			char c = s.charAt(right);
			while (charSet.contains(c)) {
				charSet.remove(s.charAt(left));
				left++;
			}
			charSet.add(c);
			len = Math.max(len, right - left + 1);
		}
		return len;
	}
}

