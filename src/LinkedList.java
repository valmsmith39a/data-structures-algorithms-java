public class LinkedList {
	Node head;
	
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
	
	public void deleteWithValue(int data) {
		if (head == null) return;
		if (head.data == data) {
			head = head.next;
			return;
		}
		
		Node current = head;
		while (current.next != null) {
			if (current.next.data == data) {
				current.next = current.next.next;
				return;
			}
			current = current.next;
		}
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
	 * 	1. currentNode.next = previousNode
	 * 
	 * Steps:
	 * 	1. keep track of next node 
	 * 	2. currentNode.next = previousNode
	 * 	3. move previousNode pointer to current node
	 *  4. move currentNode pointer to next node
	 *  
	 * O(n) time: Traverse each node in the list.
	 * O(1) space: Always store currentNode, previousNode, nextNode,
	 * regardless of size of the list 
	 * 
	 */
	
	public void reverseLinkedList() {		
		Node currentNode = head;
		Node previousNode = null;
		
		while (currentNode != null) {
			Node nextNode = currentNode.next;
			currentNode.next = previousNode;
			previousNode = currentNode;
			currentNode = nextNode;
		}
		head = previousNode;
	}
	
	public static void main(String args[]) {
		int[] values = new int[]{ 1, 2, 3, 4, 5 };
		LinkedList linkedList = new LinkedList();
		
		// Create the linked list
		for (int value : values) {
			linkedList.append(value);
		}
		
		// Reverse a linked list 
		System.out.println("Linked list original order: ");
		linkedList.printLinkedList();
		linkedList.reverseLinkedList();
		System.out.println("Reversed linked list: ");
		linkedList.printLinkedList();
	}
}
