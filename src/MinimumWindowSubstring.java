import java.util.*;

/**
 * Problem: Minimum Window Substring
 * 
 * Description:
 * Given a string s and string t, find the minimum window substring of s that
 * contains all the characters of t, including repeating characters.
 * 
 * Key Insights:
 * 1. 2 maps for window and t (needed characters)
 * 2. Keep track of counts need and have
 * 3. When need == have, move left pointer and decrement count in window map
 * 
 * Time Complextity: O(n), because interate through string once
 * Space Complexity: O(n), because of hash maps to store character and counts;
 */
public class MinimumWindowSubstring {

	public String minWindow(String s, String t) {
		String res = "";
		Map<Character, Integer> tMap = new HashMap<>();
		Map<Character, Integer> wMap = new HashMap<>();
		int have = 0;
		int need = t.length();
		int minLen = Integer.MAX_VALUE;
		for (int i = 0; i < t.length(); i++) {
			char ch = t.charAt(i);
			tMap.put(ch, tMap.getOrDefault(ch, 0) + 1);
		}
		int left = 0;
		for (int right = 0; right < s.length(); right++) {
			char ch = s.charAt(right);
			wMap.put(ch, wMap.getOrDefault(ch, 0) + 1);
			if (tMap.containsKey(ch) && wMap.get(ch) <= tMap.get(ch)) {
				have++;
			}
			while (have == need) {
				if (right - left + 1 < minLen) {
					minLen = right - left + 1;
					res = s.substring(left, right + 1);
				}
				char removedCh = s.charAt(left);
				wMap.put(removedCh, wMap.get(removedCh) - 1);
				left++;
				if (tMap.containsKey(removedCh) && wMap.get(removedCh) < tMap.get(removedCh)) {
					have--;
				}
			}
		}
		return res;
	}

	public static void main(String[] args) {
		String s = "ADOBECODEBANC";
		String t = "ABC";
		MinimumWindowSubstring mws = new MinimumWindowSubstring();
		String substring = mws.minWindow(s, t);
		// Expected: BANC
		System.out.println(substring);
	}
}