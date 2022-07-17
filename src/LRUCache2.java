import java.util.*;

class LRUCache2 {
    /**
     * 
     * LRUCache: Least Recently Used Cache
     * 
     * Question:
     *  Implement LRUCache class which supports:
     *      1. Insert key-value pairs with insertKeyValuePair method 
     *      2. Retrieve a key's value with getValueFromKey method
     *      3. Retrieve the most recently used (most recently inserted/retrieved) key with getMostRecentlKey method.
     *
     *  ***These methods should run in constant time.***
     *
     *  maxSize: maximum number of key-value-pairs a cache can store. 
     *
     *  *** When adding a new key-value pair, if the maximum capacity 
     *  has been reached, the least recently used key-value pair should 
     *  be evicted from the cache, to make room for the new key-value pair. *** 
     *  
     * Key Insight: 
     *  Use a hash table to map keys to doubly linked list nodes with head/tail
     *  nodes which track most/least recently used nodes (key/value pairs).
     *
     *  Ex 
     *      c -> (c -> 3)
     *              ∧
     *              |
     *              ∨ 
     *      b -> (b -> 2)
     *              ∧
     *              |
     *              ∨
     *      a -> (a -> 1)
     *    
     */ 

    Map<String, DoublyLinkedListNode> cache = new HashMap<String, DoublyLinkedListNode>();
    int maxSize;
    int currentSize = 0;
    DoublyLinkedList listOfMostRecent = new DoublyLinkedList();
   
    LRUCache2(int maxSize) {
        this.maxSize = maxSize > 1 ? maxSize : 1;
    }

    // O(1) time | O(1) space
    void insertKeyValuePair(String key, int value) {
        if (!cache.containsKey(key)) {
            if (currentSize == maxSize) {
                evictLeastRecent();
            } else {
                currentSize++;
            }
            cache.put(key, new DoublyLinkedListNode(key, value)); 
        } else {
            replaceKey(key, value);
        }
        // The node added to cache is the most recently used.
        // So will be the head of the doubly linked list.
        updateMostRecent(cache.get(key)); 
    }

    // O(1) time | O(1) space 
    LRUResult getValueFromKey(String key) {
        if (!cache.containsKey(key)) {
            return new LRUResult(false, -1);
        }
        // Node retrieved is the most recently used,
        // so will be the head of the doubly linked list.
        updateMostRecent(cache.get(key));
        return new LRUResult(true, cache.get(key).value);
    }
   
    // O(1) time | O(1) space 
    String getMostRecentKey() {
        if (listOfMostRecent.head == null) {
            return "";
        }
        return listOfMostRecent.head.key; 
    }

    void evictLeastRecent() {
        String keyToRemove = listOfMostRecent.tail.key;
        listOfMostRecent.removeTail();
        cache.remove(keyToRemove);
    }

    void replaceKey(String key, int value) {
        if (!this.cache.containsKey(key)) {
            return;
        }
        cache.get(key).value = value;
    }

    void updateMostRecent(DoublyLinkedListNode node) {
        listOfMostRecent.setHeadTo(node);
    }

    class DoublyLinkedList {
        DoublyLinkedListNode head = null;
        DoublyLinkedListNode tail = null;

        void setHeadTo(DoublyLinkedListNode node) {
            if (head == node) {
                return;
            } else if (head == null) {
                head = node;
                tail = node;
            } else if (head == tail) {
                /** 
                    Single node that is head and tail 
                    In a doubly linked list, 
                    head.next = next node in the list (tail in this case)
                    tail.prev = head 
                    so tail.prev = node, since node is new head */ 
                tail.prev = node;
                head = node;
                head.next = tail;
            } else {
                if (tail == node) {
                    removeTail();
                }
                node.removeBindings();
                head.prev = node;
                node.next = head; 
                head = node;
            }
        }

        void removeTail() {
            if (tail == null) {
                return;
            }
            if (tail == head) {
                head = null;
                tail = null;
                return;
            }
            tail = tail.prev;
            tail.next = null;
        }
    }

    class DoublyLinkedListNode {
        String key;
        int value;
        DoublyLinkedListNode prev = null;
        DoublyLinkedListNode next = null;

        DoublyLinkedListNode(String key, int value) {
            this.key = key;
            this.value = value;
        }

        void removeBindings() {
            if (prev != null) {
                prev.next = next;
            }
            if (next != null) {
                next.prev = prev;
            }
            prev = null;
            next = null;
        }
    }
    
    class LRUResult {
        boolean found;
        int value;

        public LRUResult(boolean found, int value) {
            this.found = found;
            this.value = value;
        }
    }
    
    public static void main(String args[]) {
        LRUCache2 lruCache = new LRUCache2(3);
        System.out.println("LRUCache. Key-value pairs inserted: {\"a\": 1}, {\"b\", 2}, {\"c\", 3}max size is 3");
        lruCache.insertKeyValuePair("a", 1);       
        lruCache.insertKeyValuePair("b", 2);
        lruCache.insertKeyValuePair("c", 3); 
        
        // Get most recent key
        String mostRecentKey = lruCache.getMostRecentKey();
        // Expected: c
        System.out.println("Most recent key is: " + mostRecentKey);
        
        // Get value from key 
        LRUResult result = lruCache.getValueFromKey("c");
        // Expected: 3
        System.out.println("Get value from key \"c\": " + result.value);
        
        // Insert key-value pair beyond capacity of 3 
        System.out.println("insert key-value pair beyond capacity, {\"d\", 4}"); 
        lruCache.insertKeyValuePair("d", 4);
        mostRecentKey = lruCache.getMostRecentKey();
        // Expected: d
        System.out.println("Most recent key is: " + mostRecentKey);

        // Demonstrate successful eviction of least recently used key-value pair in cache 
        LRUResult result2 = lruCache.getValueFromKey("a");
        // Expected: null 
        System.out.println("{a:1} should have been evicted. Try to get value from key a " + result2.value);
    }
}

