import java.util.*;
import java.util.PriorityQueue;
/**
 * Top K Frequent Elements
 * 
 * Problem Description:
 * Given a non-empty array of integers, return the k most frequent elements. 
 * 
 * Key insights:
 * 1. Use a map to store the frequency of each element. Key is the number and value is the frequency.
 * 2. Use a priority queue to store the map entries. 
 *     a. The priority queue is sorted by the frequency in descending order (Max Heap).
 * 3. Poll the first k elements from the priority queue and return them.
 * 
 * Time Complexity: 
 * O(nlogn): n is the number of elements in the array. logn is the time complexity of the priority queue to poll the elements.
 * 
 * Space Complexity: 
 * O(n): n is the number of elements in the array. The map and priority queue will store all the elements.
 */
public class TopKFrequentElements {

	public int[] topKFrequent(int[] nums, int k) {
		Map<Integer, Integer> numsMap = new HashMap<>();
		for (int i = 0; i < nums.length; i++) {
			numsMap.put(nums[i], numsMap.getOrDefault(nums[i], 0) + 1);
		}
		PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());
		pq.addAll(numsMap.entrySet());
		int[] res = new int[k];
		for (int i = 0; i < k; i++) {
			res[i] = pq.poll().getKey();
		}
		return res;
	}
}
