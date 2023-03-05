import java.util.*;

/**
 * Problem: 
 * 
 * Check if there is a cycle in singly linked list. Return true if there is and false if not. 
 *
 * Solution 1: 
 *
 * HashSet: Store each node in HashSet and check each node to see if there is a duplicate. 
 * O(n) time: Traverse linked list
 * O(n) space: Store each node hash set. 
 *
 * Solution 2: 
 *
 * Floyd's Cycle Detection: Use 2 pointers (slow and fast) and check if they equal each other at some point (fast caught up to the slow)
 * Slow pointer moves 1 node in next iteration while fast node moves 2 nodes in next iteration. If there is a cycle, at some point the fast pointer will catch up to the slow pointer. 
 * O(n) time: need to iterate through linked list 
 * O(1) space: use 2 pointers. 
 */ 
public class LinkedListCycle {

	static class ListNode {
		public int val;
		public ListNode next;

		ListNode(int x) {
			val = x;
			next = null;
		}
	}

	// HashSet method: O(n) time, O(n) space
	public boolean hasCycle(ListNode head) {
		Set<ListNode> seen = new HashSet<>();
		while (head != null) {
			if (seen.contains(head)) {
				return true;
			}
			seen.add(head);
			head = head.next;
		}
		return false;
	}

	// Floyd's Cycle detection method: O(n) time, O(1) space 
	public boolean hasCycleFloyd(ListNode head) {
		if (head == null) {
			return false;
		}
		ListNode slow = head;
		ListNode fast = head.next;

		while (slow != fast) {
			if (fast == null || fast.next == null) {
				return false;
			}
			slow = slow.next;
			fast = fast.next.next;
		}
		return true;
	}

	public boolean hasCycleFloyd2(ListNode head) {
		if (head == null) {
			return false;
		}
		ListNode slow = head;
		ListNode fast = head;
		while (fast != null & fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
			if (slow == fast) {
				return true;
			}
		}
		return false;
	}

	public static void main(String[] args) {
		ListNode n1 = new ListNode(1);
		ListNode n2 = new ListNode(2);
		ListNode n3 = new ListNode(3);
		n1.next = n2;
		n2.next = n3;
		n3.next = n2; // Cycle 

		LinkedListCycle llc = new LinkedListCycle(); 
		Boolean hasCycle = llc.hasCycleFloyd2(n1);
		System.out.println("Linked list has cycle: " + hasCycle);
	}

}


