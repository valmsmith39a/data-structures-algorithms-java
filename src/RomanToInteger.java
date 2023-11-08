import java.util.HashMap;
import java.util.Map;

/**
 * Problem: Roman to Integer 
 * 
 * Key Insights: 
 * 1. Iterate in reverse 
 * 2. If character at i is >= character at i + 1, then add, else subtract 
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(1) - HashMap has constant size 
 */
public class RomanToInteger {

    public int romanToInt(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        Map<Character, Integer> romanValues = new HashMap<>();
        romanValues.put('I', 1);
        romanValues.put('V', 5);
        romanValues.put('X', 10);
        romanValues.put('L', 50);
        romanValues.put('C', 100);
        romanValues.put('D', 500);
        romanValues.put('M', 1000);

        int res = romanValues.get(s.charAt(s.length() - 1)); 

        for (int i = s.length() - 2; i >= 0; i--) {
            if (romanValues.get(s.charAt(i)) >= romanValues.get(s.charAt(i + 1))) {
                res += romanValues.get(s.charAt(i));
            } else {
                res -= romanValues.get(s.charAt(i));
            } 
        }
        return res;
    }
}
