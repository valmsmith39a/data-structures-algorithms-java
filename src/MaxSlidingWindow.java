import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Time Complexity: O(n)
 * Space Complexity: O(k)
 * Strategy: Monotonic double-ended queue
 */
public class MaxSlidingWindow {

	public int[] maxSlidingWindow(int[] nums, int k) {
		int[] res = new int[nums.length - k + 1];
		Deque<Integer> dq = new ArrayDeque<>();

		for (int right = 0; right < nums.length; right++) {
			int left = right - k + 1;

			if (!dq.isEmpty() && dq.peekFirst() < left) {
				dq.pollFirst();
			}

			while (!dq.isEmpty() && nums[dq.peekLast()] <= nums[right]) {
				dq.pollLast();
			}

			dq.offerLast(right);

			if (left >= 0) {
				res[left] = nums[dq.peekFirst()];
			}
		}

		return res;
	}

}
