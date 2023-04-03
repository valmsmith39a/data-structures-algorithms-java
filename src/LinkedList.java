/**
 * Linked List
 * 
 * In Singly Linked List, in java, the structure is:
 * (Head) 1 -> (next) 2 -> (next) 3 (Tail)
 * 
 * First in, first out.
 * Add to new node to tail.
 * Remove from head.
 * 
 * Side note:
 * In Doubly Linked List in LRU Cache, structure is:
 * Pseudo Head (prev) <-> (next) 1 (prev) <-> (next) 2 <-> 3 <-> Pseudo Tail
 */
public class LinkedList {
	private Node head;

	public class Node {
		Node next;
		int data;

		public Node(int data) {
			this.data = data;
		}
	}

	public void append(int data) {
		if (head == null) {
			head = new Node(data);
			return;
		}
		Node current = head;
		// if current.next is == null, then at the final node
		// if use current == null, then will traverse pasted the final node
		while (current.next != null) {
			current = current.next;
		}
		current.next = new Node(data);
	}

	public void prepend(int data) {
		Node newHead = new Node(data);
		newHead.next = head;
		head = newHead;
	}

	public Node remove(int data) {
		if (head == null) {
			return null;
		}
		if (head.data == data) {
			Node removed = head;
			head = head.next;
			return removed;
		}
		Node current = head;
		while (current.next != null) {
			if (current.next.data == data) {
				Node removed = current.next;
				current.next = current.next.next;
				return removed;
			}
			current = current.next;
		}
		return null;
	}

	public void printLinkedList() {
		Node current = head;
		while (current != null) {
			System.out.println(current.data);
			current = current.next;
		}
	}

	/**
	 * Reverse a Linked List
	 * 
	 * Key idea
	 * 1. currentNode.next = previousNode
	 * 
	 * Steps:
	 * 1. keep track of next node
	 * 2. currentNode.next = previousNode
	 * 3. move previousNode pointer to current node
	 * 4. move currentNode pointer to next node
	 * 
	 * O(n) time: Traverse each node in the list.
	 * O(1) space: Always store currentNode, previousNode, nextNode,
	 * regardless of size of the list
	 * 
	 */

	public void reverseLinkedList() {
		Node currentNode = head;
		Node previousNode, nextNode;
		previousNode = nextNode = null;
		// Must operation on final node
		// Cannot break out of loop before then
		while (currentNode != null) {
			nextNode = currentNode.next;
			currentNode.next = previousNode;
			previousNode = currentNode;
			currentNode = nextNode;
		}
		head = previousNode;
	}

	public static void main(String args[]) {
		int[] values = new int[] { 1, 2, 3, 4, 5 };
		LinkedList linkedList = new LinkedList();

		// Create the linked list
		for (int value : values) {
			linkedList.append(value);
		}

		// Reverse a linked list
		// Output: 1, 2, 3, 4, 5
		System.out.println("Linked list original order: ");
		linkedList.printLinkedList();
		linkedList.reverseLinkedList();
		// Output: 5, 4, 3, 2, 1
		System.out.println("Reversed linked list: ");
		linkedList.printLinkedList();

		// Remove node 3
		// Output: 5, 4, 2, 1
		linkedList.remove(3);
		System.out.println("Remove node 3, new linked list is: ");
		linkedList.printLinkedList();

		// Prepend
		System.out.println("Test Prepend. Old head value: " + linkedList.head.data);
		System.out.println("Prepend 0");
		linkedList.prepend(0);
		System.out.println("New head value: " + linkedList.head.data);
	}
}
