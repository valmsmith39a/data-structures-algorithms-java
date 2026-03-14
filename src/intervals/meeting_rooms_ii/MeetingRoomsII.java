import java.util.Arrays;
import java.util.Queue;
import java.util.PriorityQueue;

/**
 * Time Complexity: O(n log n)
 * Space Complexity: O(n)
 */
public class MeetingRoomsII {

	public int minMeetingRooms(int[][] intervals) {

		if (intervals == null || intervals.length == 0)
			return 0;

		Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

		Queue<Integer> pq = new PriorityQueue<>();
		pq.offer(intervals[0][1]);

		for (int i = 1; i < intervals.length; i++) {
			int start = intervals[i][0];
			int end = intervals[i][1];

			if (start >= pq.peek()) {
				pq.poll();
			}

			pq.offer(end);
		}

		return pq.size();
	}

}
