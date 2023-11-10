import java.util.Arrays;

/**
 * Problem: H Index 
 * 
 * Key Insights: 
 * 1. Sort in ascending order and work backwards 
 * 2. h index ex 3 papers with at least 3 citations 
 * 3. If current paper citations >= count, then previous paper must have been >= to the current count as well 
 * 
 * Time Complexity: O(n log n) time: Iterate array once - O(n) and sorting is O(n log n) time => O(n log n)
 * Space Complexity: O(1) time  
 */
public class HIndex {

    public int hIndex(int[] citations) {
        Arrays.sort(citations);
        int h = 0;
        for (int i = citations.length - 1; i >= 0; i--) {
            int count = citations.length - i;
            if (citations[i] >= count) {
                h = count;
            } else {
                break;
            }
        }
        return h;
    }
}
