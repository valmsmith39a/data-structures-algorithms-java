package Review_2025_2;

/**
 * Iterative solution
 * Time Complexity: O(m + n), m: number of nodes in list 1, n: number of nodes
 * in list 2
 * Space Complexity: O(1)
 * 
 * Recursive solution
 */

public class MergeTwoSortedLists {

	public static class ListNode {
		int val;
		ListNode next;

		ListNode(int value) {
			this.val = value;
			this.next = null;
		}
	}

	public ListNode mergeTwoSortedListsIterative(ListNode list1, ListNode list2) {
		ListNode temp = new ListNode(0);
		ListNode current = temp;

		while (list1 != null && list2 != null) {
			if (list1.val <= list2.val) {
				current.next = list1;
				list1 = list1.next;
			} else {
				current.next = list2;
				list2 = list2.next;
			}
			current = current.next;
		}
		if (list1 != null) {
			current.next = list1;
		} else if (list2 != null) {
			current.next = list2;
		}
		return temp.next;
	}

	public ListNode mergeTwoListsRecursive(ListNode list1, ListNode list2) {
		if (list1 == null) {
			return list2;
		}
		if (list2 == null) {
			return list1;
		}
		if (list1.val <= list2.val) {
			list1.next = mergeTwoListsRecursive(list1.next, list2);
			return list1;
		} else {
			list2.next = mergeTwoListsRecursive(list1, list2.next);
			return list2;
		}
	}
}
