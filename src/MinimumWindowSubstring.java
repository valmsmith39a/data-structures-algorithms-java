import java.util.*;

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
}