import java.util.*;

public class MergeKSortedLists {

	private class ListNode {
		int val;
		ListNode next;

		ListNode(int val) {
			this.val = val;
		}
	}

	public ListNode mergeKLists(ListNode[] lists) {
		if (lists.length == 0) {
			return null;
		}
		while (lists.length > 1) {
			ListNode[] mergedLists = new ListNode[0];
			int k = 0;
			for (int i = 0; i < lists.length; i += 2) {
				ListNode l1 = lists[i];
				ListNode l2 = i + 1 < lists.length ? lists[i + 1] : null;
				mergedLists = Arrays.copyOf(mergedLists, mergedLists.length + 1);
				mergedLists[k] = mergeTwoLists(l1, l2);
				k++;
			}
			lists = mergedLists;
		}
		return lists[0];
	}

	private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		ListNode head = new ListNode(0);
		ListNode tail = head;
		while (l1 != null && l2 != null) {
			if (l1.val < l2.val) {
				tail.next = l1;
				l1 = l1.next;
			} else {
				tail.next = l2;
				l2 = l2.next;
			}
			tail = tail.next;
		}
		tail.next = l1 == null ? l2 : l1;
		return head.next;
	}
}
