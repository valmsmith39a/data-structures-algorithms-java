package Review_2025;

public class MergeTwoSortedLists {

	public static class ListNode {
		int val;
		ListNode next;

		public ListNode() {
			/* no-op */
		}

		public ListNode(int val) {
			this.val = val;
			this.next = null;
		}
	}

	public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
		if (list1 == null) {
			return list2;
		}
		if (list2 == null) {
			return list1;
		}
		if (list1.val < list2.val) {
			list1.next = mergeTwoLists(list1.next, list2);
			return list1;
		} else {
			list2.next = mergeTwoLists(list1, list2.next);
			return list2;
		}
	}
}
