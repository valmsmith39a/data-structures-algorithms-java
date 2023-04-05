/**
	Problem: Remove Nth Node from End of List

	Key insight: 
 	1. 2 pointers, n spaces apart
    2. dummy node so that left node at the node before removed node 
	
	Time Complexity: O(n) 
  	Space Complexity: O(1) 
*/
public class RemoveNthNodeFromEndOfList {

	public ListNode removeNthNodeFromEnd(ListNode head, int n) {
		ListNode dummy = new ListNode(0, head);
		ListNode left = dummy;
		ListNode right = head;
		while (n > 0 && right != null) {
			right = right.next;
			n--;
		}
		while (right != null) {
			left = left.next;
			right = right.next;
		}
		left.next = left.next.next;
		return dummy.next;
	}
}