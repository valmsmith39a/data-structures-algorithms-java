import java.util.HashMap;
import java.util.Map;
/**
 * LRU Cache 
 * 
 * Key Points: 
 * 1. HashMap is cache. 
 * 2. Doubly linked list with to get most recently used (at head of linked list (head.next, bc dummy head))
 * and least recently used (at tail of the linked list (tail.prev, bc dummy tail))
 * 
 * Diagram: 
 * head (dummy head) (next) <-> (prev) newNode (next) <-> (prev) tail (dummy tail)
 *
 * Time Complexity: 
 * 	1. GET (get from cache): O(1)
 * 		a. Get node from HashMap is O(1) time 
 * 		b. Move node to HEAD of Doubly linked list is O(1) time 
 * 	2. PUT (insert into cache): O(1)
 * 		a. If key exists, replace value is O(1) time 
 * 		b. If key does not exist, add new node to hash map and doubly linked list are O(1) time 
 * Space Complexity: 
 * 	1. Cache is O(capacity) 
 * 	2. Doubly linked list is O(capacity) or can think of as O(1) constant space 
 *
 * Final Notes: 
 * 1. Cannot use Singly Linked List to achieve O(1) deletion even with a tail, because need to access tail's previous node
 * 2. Can use Singly Linked List however to achieve O(1) insertion if keep track of HEAD. newNode.next = HEAD. HEAD = newNode 
 * 
 */

public class LRUCache {
    private Map<String, DoublyLinkedListNode> cache;
    private int capacity;
    private int size;
    private DoublyLinkedListNode head;
    private DoublyLinkedListNode tail;

    public LRUCache(int capacity) {
        cache = new HashMap<>();
        this.capacity = capacity;
        size = 0;
        head = new DoublyLinkedListNode();
        tail = new DoublyLinkedListNode();
        head.next = tail;
        tail.prev = head;
        head.prev = null;
        tail.next = null;
    }

    private class DoublyLinkedListNode {
        String key;
        int value;
        DoublyLinkedListNode prev;
        DoublyLinkedListNode next;

        private DoublyLinkedListNode() {
            // no-op
        }

        private DoublyLinkedListNode(String key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private void addNode(DoublyLinkedListNode node) {
        // Connect node to next / prev
        node.next = head.next;
        node.prev = head;
        // Connect next / prev to node
        head.next.prev = node;
        head.next = node;
    }

    private void removeNode(DoublyLinkedListNode node) {
        node.next.prev = node.prev;
        node.prev.next = node.next;
    }

    private DoublyLinkedListNode popTail() {
        DoublyLinkedListNode poppedNode = tail.prev;
        removeNode(poppedNode);
        return poppedNode;
    }

    private void moveToHead(DoublyLinkedListNode node) {
        removeNode(node);
        addNode(node);
    }

    public int get(String key) {
        DoublyLinkedListNode node = cache.get(key);
        if (node == null)
            return -1;
        // When get an item, it becomes the most recently used.
        moveToHead(node);
        return node.value;
    }

    public void put(String key, int value) {
        if (!cache.containsKey(key)) {
            if (size == capacity) {
                DoublyLinkedListNode evictedNode = popTail();
                cache.remove(evictedNode.key);
                size--;
            }
            DoublyLinkedListNode newNode = new DoublyLinkedListNode(key, value);
            cache.put(key, newNode);
            addNode(newNode);
            size++;
        } else {
            DoublyLinkedListNode node = cache.get(key);
            node.value = value;
            moveToHead(node);
        }
    }

    public String getMostRecentKey() {
        return head.next.key;
    }

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(3);
        lruCache.put("a", 1);
        lruCache.put("b", 2);
        lruCache.put("c", 3);

        // Get value from key
        int value = lruCache.get("c");
        // Expect: 3
        System.out.println("Get value from key \"c\": " + value);

        // Get most recent key
        String mostRecentKey1 = lruCache.getMostRecentKey();
        // Expect: c
        System.out.println("Get most recent key: " + mostRecentKey1);

        // Put more values than capacity of cache
        lruCache.put("d", 4);
        lruCache.put("e", 5);

        // Get most recent key
        String mostRecentKey2 = lruCache.getMostRecentKey();
        // Expect: e
        System.out.println("Insert more values than capacity. Most recent key is: " + mostRecentKey2);

        /**
         * Demonstrate successful eviction of least recently used key-value pair.
         * Expect for both cases: -1
         */
        int evicted1 = lruCache.get("a");
        int evicted2 = lruCache.get("b");
        int nodeC = lruCache.get("c");
        System.out.println("{ a: 1 } should have been evicted. Try to get value from key \"a\":" + evicted1);
        System.out.println("{ b: 2 } should have been evicted. Try to get value from key \"b\":" + evicted2);
        System.out.println("{ c: 3 } should still be in cache. Try to get value from key \"c\":" + nodeC);
    }
}
