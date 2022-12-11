import java.util.Arrays;

/**
 * Problem: Meeting Rooms: Leetcode #252
 * 
 * Key insight:
 * Sort intervals by start times.
 * Then can easily see if start time is < previous end time => meeting overlaps
 * and cannot attend all meetings.
 * 
 * Time Complexity: O(nlogn) time: bc of sort
 * Space Complexity: O(1) space
 */
public class MeetingRooms {

	public boolean canAttendMeetings(int[][] intervals) {
		Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
		for (int i = 1; i < intervals.length; i++) {
			int endPrev = intervals[i - 1][1];
			int start = intervals[i][0];
			if (start < endPrev) {
				return false;
			}
		}
		return true;
	}
}
