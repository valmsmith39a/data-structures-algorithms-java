import java.util.*;

/**
 * Problem: Longest Consecutive Sequence(#128)
 * 
 * Key Insights: 
 * 1. Use a hash set to for constant time lookups 
 * 2. If set doesn't contain current num - 1, then it's a beginning of a sequence 
 * 
 * Time Complexity: O(n)
 * 
 * Space Complexity: O(n)
 */
public class LongestConsecutiveSequence2 {

    public int longestConsecutive(int[] nums) {
        Set<Integer> numSet = new HashSet<>();

        for (int num : nums) {
            numSet.add(num);
        }

        int maxSeq = 0;

        for (int num : numSet) {
            if (!numSet.contains(num - 1)) {
                int currentNum = num;
                int currentMaxSeq = 1;

                while (numSet.contains(currentNum + 1)) {
                    currentNum += 1;
                    currentMaxSeq += 1;
                }

                maxSeq = Math.max(maxSeq, currentMaxSeq);
            }
        }

        return maxSeq;
    }
}
