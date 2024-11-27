package Review_2024;

/**
 * Add Two Numbers #2
 * 
 * Time Complexity: O(max(m, n)), m: length of l1, n: length of l2
 * Space Complexity: O(max(m, n))
 */
public class AddTwoNumbers {

	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		ListNode dummy = new ListNode(0);
		ListNode current = dummy;
		int carry = 0;

		while (l1 != null || l2 != null) {
			int x = (l1 != null) ? l1.val : 0;
			int y = (l2 != null) ? l2.val : 0;
			int sum = x + y + carry;

			carry = sum / 10;
			current.next = new ListNode(sum % 10);
			current = current.next;

			if (l1 != null)
				l1 = l1.next;
			if (l2 != null)
				l2 = l2.next;
		}

		if (carry > 0) {
			current.next = new ListNode(carry);
		}
		return dummy.next;

	}

}
