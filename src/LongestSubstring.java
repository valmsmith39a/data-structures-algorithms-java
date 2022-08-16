import java.util.HashMap;
import java.util.Map;

class LongestSubstring {
    
    public int findLengthLongestSubstring(String s) {
        int n = s.length();
        int length = 0;
        Map<Character, Integer> map = new HashMap<>();
        // 2 pointers i, j. Traverse array with j 
        for (int i = 0, j = 0; j < n; j++) {
           if (map.containsKey(s.charAt(j))) {
               i = Math.max(i, map.get(s.charAt(j)));
           }
           // In beginning, i = 0, j = 0, so use j - i + 1
           length = Math.max(length, j - i + 1);
           map.put(s.charAt(j), j + 1);
        }
        return length;
    }

    public static void main(String[] args) {
        String s = "abcabcbb";
        String s2 = "bbbbb";
        String s3 = "pwwkew";
        LongestSubstring substring = new LongestSubstring();
        int length = substring.findLengthLongestSubstring(s);
        int length2 = substring.findLengthLongestSubstring(s2);
        int length3 = substring.findLengthLongestSubstring(s3);
        System.out.println("For string " + s + " Longest substring without repeating characters is: " + length);
        System.out.println("For string " + s2 + " Longest substring without repeating characters is: " + length2);
        System.out.println("For string " + s3 + " Longest substring without repeating characters is: " + length3);
    }
}
