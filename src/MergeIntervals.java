import java.util.LinkedList;
import java.util.Arrays;

public class MergeIntervals {
    /**
     * Merge Intervals
     *
     *  Problem Statement: 
     * 
     *  Given an array of intervals where intervals[i] = [starti, endi],
     *  merge all overlapping intervals and return an array of non-overlapping 
     *  intervals that cover all the intervals in the input. 
     *  
     *  Key insight: 
     *      1. Sort the intervals by the start number.
     *      2. Because it's sorted by the start number, 
     *         if you see an interval with a start number 
     *         greater than or equal to the end number of the 
     *         most recent interval in the list of merged intervals,
     *         then that interval should be merged with that interval. 
     *
     *         Ex [1, 9], [2, 5]
     *         2 < 9, so [2, 5] should be merged with [1, 9] 
     *
     *         If not sorted, cannot do this. 
     *         Ex [19, 20], [10, 11]
     *         10 < 20, but 19 > 10, so [10, 11] should *not* be merged with [19, 20]
     *      3. Use a linked list to store merged intervals
     *  Time Complexity: 
     *      O(n log n) time: The Arrays.sort is a dual pivot Quicksort on primitives and and has a runtime of O(n log n).
     *  Space Complexity: 
     *      O(log n) space for Java's sort algorithm.
     */
    
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        LinkedList<int[]> merged = new LinkedList<>();
        for (int[] interval : intervals) {
            if (merged.isEmpty() || merged.getLast()[1] < interval[0]) {
                merged.add(interval);
            } else {
                merged.getLast()[1] = Math.max(merged.getLast()[1], interval[1]);
            }
        }
        return merged.toArray(new int[merged.size()][]); 
    }

    public static void main(String[] args) {
        int[][] intervals = { 
            { 1, 9 },
            { 2, 5 },
            { 19, 20 }, 
            { 10, 11 }, 
            { 12, 20 }, 
            { 0, 3 }, 
            { 0, 1 },
            { 0, 2 }
        };

        MergeIntervals mi = new  MergeIntervals(); 
        int[][] mergedIntervals = mi.merge(intervals);

        for (int[] interval : intervals) {
            System.out.println("intervals: " + interval[0] + ", " + interval[1]);
        }
        for (int[] interval : mergedIntervals) {
            // Solution: [0, 9], [10, 11], [12, 20]
            System.out.println("merged intervals: " + interval[0] + ", " + interval[1]);
        }
    }
}
