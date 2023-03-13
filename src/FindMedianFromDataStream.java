import java.util.PriorityQueue;
/**
 * Find Median From Data Stream 
 * 
 * Problem Description:
 * Find the median of a stream of numbers.
 * 
 * Key insights: 
 * Use 2 heaps to track the left and right half of the stream.
 * 
 * Time Complexity:
 * 	1. addNum: O(log n): O(log n) for heap insert, O(1) for heap poll
 *  2. findMedian: O(1)
 * Space complexity: O(n): 2 heaps 
 */
public class FindMedianFromDataStream {
	PriorityQueue<Integer> maxHeap; 
	PriorityQueue<Integer> minHeap;

	public FindMedianFromDataStream() {
		maxHeap = new PriorityQueue<>((a, b) -> b - a);
		minHeap = new PriorityQueue<>();
	}

	public void addNum(int num) {
		maxHeap.offer(num);
		minHeap.offer(maxHeap.poll());
		if (minHeap.size() > maxHeap.size()) {
			maxHeap.offer(minHeap.poll());
		}
	}

	public double findMedian() {
		if (maxHeap.size() == minHeap.size()) {
			return (maxHeap.peek() + minHeap.peek()) / 2.0;
		}
		return maxHeap.peek();
	}

	public static void main(String[] args) {
		FindMedianFromDataStream fm = new FindMedianFromDataStream();
		fm.addNum(1);
		fm.addNum(2);
		// Expected: 1.5
		System.out.println("Median: " + fm.findMedian());
		fm.addNum(3);
		// Expected: 2
		System.out.println("Median: " + fm.findMedian());
	}
}
