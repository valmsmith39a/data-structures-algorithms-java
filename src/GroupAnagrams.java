import java.util.*;

/**
 * Problem: Group Anagrams (#49)
 * 
 * Key Insights: 
 * 1. Use Hash Map to track same anagrams
 * 2. Sort the strings to determine which strings are anagrams 
 * 
 * Time Complexity: O(N * K log K), where N: number of anagrams, K: average (or max if want worst case) length of string 
 * Space Complexity: O(N * K)
 */
public class GroupAnagrams {
    
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> anagramGroups = new HashMap<>();

        for (String str : strs) {
            char[] chArr = str.toCharArray();
            Arrays.sort(chArr);
            String sortedStr = new String(chArr);
            
            if (anagramGroups.containsKey(sortedStr)) {
                anagramGroups.get(sortedStr).add(str);
            } else {
                List<String> group = new ArrayList<>();
                group.add(str);
                anagramGroups.put(sortedStr, group);
            }
        }
        List<List<String>> res = new ArrayList<>(anagramGroups.values());
        return res;
    }
}
