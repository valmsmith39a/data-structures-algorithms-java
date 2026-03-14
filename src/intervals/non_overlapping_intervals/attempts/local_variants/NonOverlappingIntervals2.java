import java.util.Arrays;

/**
 * Problem: #435 Non-overlapping Intervals
 * Time Complexity: O(nlogn), because of sorting. O(nlogn) + O(n) => O(n(logn +
 * 1)) => O(nlogn) // can drop the constant
 * Space Complexity: O(logn), because of sorting (recursive stack space).
 */
public class NonOverlappingIntervals2 {
	public int eraseOverlapIntervals(int[][] intervals) {
		if (intervals == null || intervals.length == 0)
			return 0;
		Arrays.sort(intervals, (a, b) -> Integer.compare(a[1], b[1]));
		int pe = intervals[0][1];
		int removals = 0;

		for (int i = 1; i < intervals.length; i++) {
			int s = intervals[i][0];
			int e = intervals[i][1];
			if (s < pe) {
				removals++;
			} else {
				pe = e;
			}
		}

		return removals;
	}
}
