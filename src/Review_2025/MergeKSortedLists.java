package Review_2025;

import java.util.PriorityQueue;

/**
 * 
 * Time Complexity: O (N log k)
 * N: total number nodes in all the lists
 * k: number of lists
 * For each node, we need to run poll and offer operations which is log (k) bc
 * at any given time there is at most k nodes in the heap.
 * 
 * When offer (insert): we insert at bottom of the heap and sift up. O(log k)
 * When poll (remove): we remove from the top of the heap, put the bottom node
 * in the root position and then sift down. O(log k)
 * 
 * Side note:
 * Iterate through list and insert each node into heap: O(N log N) bc iterate
 * through each node,
 * insert at bottom of the heap, then sift up operation
 * 
 * Heapify (sort in place): run sift down operation (log N) starting from first
 * parent node at bottom of heap.
 * Most of the sift down operation are at the bottom of the heap though. Taylor
 * series converges to O(N) time.
 * 
 * Space Complexity: O(k)
 * k: number of lists
 * Remember at any given time the max number of nodes in the heap is k not N
 * (total number of nodes)
 * 
 */
class MergeKSortedLists {
	public ListNode mergeKLists(ListNode[] lists) {
		if (lists == null || lists.length == 0) {
			return null;
		}

		PriorityQueue<ListNode> minHeap = new PriorityQueue<>((a, b) -> a.val - b.val);

		for (ListNode node : lists) {
			if (node != null) {
				minHeap.offer(node);
			}
		}

		ListNode temp = new ListNode();
		ListNode tail = temp;

		while (!minHeap.isEmpty()) {
			ListNode smallest = minHeap.poll();
			tail.next = smallest;
			tail = tail.next;
			if (smallest.next != null) {
				minHeap.offer(smallest.next);
			}
		}
		return temp.next;
	}
}

class ListNode {
	ListNode next;
	int val;

	ListNode() {
	}

	ListNode(int val) {
		this.val = val;
	}

	ListNode(int val, ListNode next) {
		this.val = val;
		this.next = next;
	}
}
