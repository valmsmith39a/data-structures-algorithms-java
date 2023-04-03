import java.util.Arrays;
/**
 * Problem: Non-overlapping Intervals
 * 
 * Problem Description: 
 * Given a collection of intervals, find the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.
 * 
 * Example 1:
 * Input: [[1,2],[2,3],[3,4],[1,3]]
 * Output: 1
 * Explanation: [1,3] can be removed and the rest of intervals are non-overlapping.
 * 
 * Key Insights: 
 * 1. Find max number of non-overlapping intervals.
 * 2. Total number of intervals - max number of non-overlapping intervals = min number of intervals to remove.
 * 3. Sort intervals by end time.
 *   a. When choosing which interval to keep, always choose the one with the smallest end time.
 *   b. If sort by end time, then the first interval will always be kept.
 * 
 * Time Complexity: O(nlogn), because of sorting.
 * Space Complexity: O(1), because we are not using any extra space. 
 */
public class NonOverlappingIntervals {

    public int eraseOverlappingIntervals(int[][] intervals) {
		Arrays.sort(intervals, (a, b) -> a[1] - b[1]);
        int maxNumOfValidIntervals = 1;
        int prevEnd = intervals[0][1]; 
        
        for (int i = 0; i < intervals.length; i++) {
            if (intervals[i][0] >= prevEnd) {
                maxNumOfValidIntervals++;
                prevEnd = intervals[i][1];
            }
        }
        return intervals.length - maxNumOfValidIntervals;
    }

    public static void main(String[] args) {
        NonOverlappingIntervals noi = new NonOverlappingIntervals();
        int[][] intervals = {{1,2},{2,3},{3,4},{1,3}};
        // Expected output: 1
        System.out.println(noi.eraseOverlappingIntervals(intervals));
    }
}