import java.util.*; 

/**
 * Problem: Minimum Window Substring 
 * 
 * Key Insights: 
 * 1. Sliding window 
 * 2. Track the number of characters that we need in a hash map 
 * 3. Move right pointer forward until find all the characters we need 
 * 4. Move left pointer forward to minimize the window until we lose a character that we need 
 */
public class MinimumWindowSubstring2 {

    public String minWindow (String s, String t) {
        if (s == null || t == null || s.length() < t.length()) {
            return "";
        }

        Map<Character, Integer> map = new HashMap<>();
        for (char ch : t.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        int left = 0; 
        int start = 0; 
        int count = 0; 
        int minLen = Integer.MAX_VALUE; 

        for (int right = 0; right < s.length(); right++) {
            char rightChar = s.charAt(right);
            if (map.containsKey(rightChar)) {
                map.put(rightChar, map.get(rightChar) - 1);
                if (map.get(rightChar) >= 0) {
                    count++;
                }
            }
            
            while (count == t.length()) {
                if (right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    start = left;
                }
                char leftChar = s.charAt(left);
                if (map.containsKey(leftChar)) {
                    map.put(leftChar, map.get(leftChar) + 1);
                    if (map.get(leftChar) > 0) {
                        count--; 
                    }
                }
                left++;
            }
        }
        return minLen == Integer.MAX_VALUE ? "" : s.substring(start, start + minLen);
    }

    
}
