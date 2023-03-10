import java.util.*;
/**
 * Longest Repeating Character Replacement
 * 
 * Problem: 
 * Given a string s that consists of only uppercase English letters, you can perform at most k operations on that string.
 * In one operation, you can choose any character of the string and change it to any other uppercase English character.
 * Find the length of the longest sub-string containing all repeating letters you can get after performing the above operations.
 * 
 * Key insights: 
 * 1. Sliding window
 * 2. Character count
 * 
 * Time complexity: O(n)
 * Space complexity: O(1)
 */
public class LongestRepeatingCharReplace {

	public int characterReplacement(String s, int k) {
		int[] count = new int[26];
		int left = 0, maxLen = 0, maxCount = 0;
		for (int right = 0; right < s.length(); right++) {
			count[s.charAt(right) - 'A']++;
			maxCount = Math.max(maxCount, count[s.charAt(right) - 'A']);
			while (right - left + 1 - maxCount > k) {
				count[s.charAt(left) - 'A']--;
				left++;
			}
			maxLen = Math.max(maxLen, right - left + 1);
		}
		return maxLen;
	}

	public static void main(String[] args) {
		LongestRepeatingCharReplace lrcr = new LongestRepeatingCharReplace();
		// Expected: 4
		System.out.println(lrcr.characterReplacement("ABAB", 2));
		// Expected: 4
		System.out.println(lrcr.characterReplacement("AABABBA", 1));
	}
}
