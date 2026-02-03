import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * LeetCode 347 - Top K Frequent Elements
 * 
 * Time complexity: O(N log k)
 * Space complexity: O(N)
 * Key: Frequency map + Priority Queue. Remove the least frequent element when
 * size exceeds k.
 */

public class TopKFrequentElements2 {
	public int[] topKFrequent(int[] nums, int k) {
		Map<Integer, Integer> f = new HashMap<>();

		for (int i = 0; i < nums.length; i++) {
			f.put(nums[i], f.getOrDefault(nums[i], 0) + 1);
		}

		PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> f.get(a) - f.get(b));

		for (int key : f.keySet()) {
			pq.offer(key);
			if (pq.size() > k) {
				pq.poll();
			}
		}

		int[] res = new int[k];
		for (int i = 0; i < k; i++) {
			res[i] = pq.poll();
		}

		return res;
	}
}
