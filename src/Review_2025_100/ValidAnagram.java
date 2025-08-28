package Review_2025_100;

import java.util.Arrays;

public class ValidAnagram {

	/**
	 * Time Complexity: O(n), iterate through string once,
	 * Space Complexity: O(1), charCount array is fixed array of size 26
	 * 
	 * @param s
	 * @param t
	 * @return
	 */
	public boolean isAnagramCountsSolution(String s, String t) {
		if (s.length() != t.length()) {
			return false;
		}
		int[] charCount = new int[26];
		for (int i = 0; i < s.length(); i++) {
			charCount[s.charAt(i) - 'a']++;
			charCount[t.charAt(i) - 'a']--;
		}
		for (int count : charCount) {
			if (count != 0) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Time Complexity: O(n log n), runtime of a sorting algorithm.
	 * Space Complexity: O(n), size of array is size of a string
	 * 
	 * @param s
	 * @param t
	 * @return
	 */
	public boolean isAnagramSortSolution(String s, String t) {
		char[] sArray = s.toCharArray();
		char[] tArray = t.toCharArray();
		Arrays.sort(sArray);
		Arrays.sort(tArray);
		return Arrays.equals(sArray, tArray);
	}

}
