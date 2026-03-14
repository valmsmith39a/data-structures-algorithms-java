import java.util.*;

/**
 * Problem: Merge K Sorted Lists 
 *
 * Solution strategy: 
 * 	1. Merge pairs of lists so that we perform merge operation log(k) times
 *  2. Use intervals to traverse the list of linked-lists. 
 * 
 * Time complexity: O(n log k): 
 * 	1. O(n) time to merge 2 lists, n is number of nodes in the 2 lists 
 * 	2. O(log k) number of merges to complete, k is the number of lists
 *
 * Space complexity: O(1) space: No additional memory needed to merge 2 lists
 *
 * value: 1, 2, 3, 4, 5, 6
 * index: 0, 1, 2, 3, 4, 5
 * iteration 1: 
 * merge values at index 0, 1 -> store in index 0
 * merge values at index 2, 3 -> store in index 2
 * merge values at index 4, 5 -> store in index 4
 * iteration 2: 
 * merge values at index 0 and 2  -> store in index 0
 * iteration 3: 
 * merge values at index 0 and index 4 -> store in index 0
 * 
 */
public class MergeKSortedLists2 {
	
	public class ListNode {
		int val;
		ListNode next;
		ListNode() {}
		ListNode(int val) { this.val = val; }
		ListNode(int val, ListNode next) { this.val = val; this.next = next; }}
	}
	
	public ListNode mergeKLists(ListNode[] lists) {
		if (lists.length == 0) {
			return null;
		}
		int interval = 1;
		while (interval < lists.length) {
			for (int i = 0; i + interval < lists.length; i + 2 * interval) {
				lists[i] = mergeTwoLists(lists[i], lists[i + interval]);
			}
			interval *= 2;
		}
	}

	private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		ListNode h = new ListNode(0);
		ListNode ans = h;
		while (l1 != null && l2 != null) {
			if (l1.val < l2.val) {
				h.next = l1;
				h = h.next;
				l1 = l1.next;
			} else {
				h.next = l2;
				h = h.next;
				l2 = l2.next;
			}
		}
		h.next = l1 == null ? l2 : l1;
		return ans.next;
	}
}
