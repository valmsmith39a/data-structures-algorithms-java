import java.util.HashMap;
import java.util.Map;

public class LRUCache {

	class DLinkedNode {
		int key;
		int value;
		DLinkedNode prev;
		DLinkedNode next;
	}

	private void addNode(DLinkedNode node) {
		// Ex add node 2
		// head -> 1 -> tail
		// head -> 2 -> 1 -> tail

		node.prev = head;
		node.next = head.next;

		head.next.prev = node;
		head.next = node;
	}

	private void removeNode(DLinkedNode node) {
		DLinkedNode prev = node.prev;
		DLinkedNode next = node.next;

		// Connect previous and next nodes because removed current node
		prev.next = next;
		next.prev = prev;
	}

	private void moveToHead(DLinkedNode node) {
		removeNode(node);
		addNode(node);
	}

	private DLinkedNode popTail() {
		// Ex pop tail
		// head -> 3 -> 2 -> 1 -> tail
		// node 1 is least recently used. tail.prev would get that value.
		// After the pop:
		// head -> 3 -> 2 -> tail
		DLinkedNode res = tail.prev;
		removeNode(res);
		return res;
	}

	private Map<Integer, DLinkedNode> cache = new HashMap<>();
	private int size;
	private int capacity;
	private DLinkedNode head, tail;

	public LRUCache(int capacity) {
		this.size = 0;
		this.capacity = capacity;

		head = new DLinkedNode();
		tail = new DLinkedNode();

		head.next = tail;
		tail.prev = head;
	}

	public int get(int key) {
		DLinkedNode node = cache.get(key);
		if (node == null)
			return -1;

		// node that you get is the most recently used
		moveToHead(node);

		return node.value;
	}

	public void put(int key, int value) {
		DLinkedNode node = cache.get(key);

		if (node == null) {
			DLinkedNode newNode = new DLinkedNode();
			newNode.key = key;
			newNode.value = value;

			cache.put(key, newNode);
			addNode(newNode);

			++size;

			if (size > capacity) {
				// Pop the tail
				DLinkedNode tail = popTail();
				cache.remove(tail.key);
				--size;
			}
		} else {
			node.value = value;
			moveToHead(node);
		}
	}

	public static void main(String args[]) {
		System.out.println("Hello world");
		/**
		 * Time Complexity: O(1) time to get, O(1) time to put
		 * Space Complexity: O(Capacity): space used is at most capacity + 1
		 */
		LRUCache lRUCache = new LRUCache(2);
		lRUCache.put(1, 1);
		lRUCache.put(2, 2);

		int item1 = lRUCache.get(1);
		// Expect 1
		System.out.println("item 1 is: " + item1);

		lRUCache.put(3, 3);
		int item2 = lRUCache.get(2);
		// Expect -1, because capacity is 2. 1 was most recently used. 2 was *least
		// recently used* so evicted.
		System.out.println("item 2 is: " + item2);

		int item3 = lRUCache.get(3);
		// Expect 3
		System.out.println("item 3 is: " + item3);
	}
}
