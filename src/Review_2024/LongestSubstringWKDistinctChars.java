package Review_2024;

import java.util.Map;
import java.util.HashMap;

public class LongestSubstringWKDistinctChars {

	public int longestSubstringWKDistinctChars(String s, int k) {
		if (s == null || s.length() == 0 || k == 0) {
			return 0;
		}

		Map<Character, Integer> charMap = new HashMap<>();
		int left = 0;
		int maxLen = 0;

		for (int right = 0; right < s.length(); right++) {
			char c = s.charAt(right);
			charMap.put(c, right);

			while (charMap.size() > k) {
				char lc = s.charAt(left);
				if (charMap.get(lc) == left) {
					charMap.remove(lc);
				}
				left++;
			}
			maxLen = Math.max(maxLen, right - left + 1);
		}
		return maxLen;
	}

	public static void main(String[] args) {
		LongestSubstringWKDistinctChars ls = new LongestSubstringWKDistinctChars();
		String s = "eceba";
		int k = 2;
		int len = ls.longestSubstringWKDistinctChars(s, k);
		System.out.println(len);
	}
}
