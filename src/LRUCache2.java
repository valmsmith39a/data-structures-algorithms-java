public class LRUCache2 {
      
    private class DLinkedNode {
        int key;
        int value;

        DLinkedNode prev;
        DLinkedNode next;
        
        private DLinkedNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private void addNode(DLinkedNode node) {
        /*
         * Link new node to prev / next nodes 
         * Recall we have pseudo head / pseudo tail
         * in this implementation 
         */ 
        node.prev = head;
        node.next = head.next;

        // Link prev / next nodes to new node 
        head.next.prev = node;
        head.next = node;
    }

    private void removeNode(DLinkedNode node) {
        DLinkedNode prev = node.prev;
        DLinkedNode next = node.next;

        prev.next = next;
        next.prev = prev; 
    }
  
    private void moveToHead(DLinkedNode node) {
        removeNode(node);
        addNode(node);
    }

    private DLinkedNode popTail() {
        DLinkedNode removedNode = tail.prev;
        removeNode(removedNode);
        return removedNode;
    }
    
    private Map<Integer, DLinkedNode> cache = new HashMap<>();
    private int size;
    private int capacity;
    private DLinkedNode head, tail;

    public LRUCache2(int capacity) {
        this.size = 0;
        this.capacity = capacity;a
       
        /*
         * We create pseudo head / pseudo tail 
         * nodes so we don't need to check for null
         * when updating (add / remove) list
         * null <- (prev) head (next) <-> (prev) tail (next) -> null 
         */
        head = new DLinkedNode();
        tail = new DLinkedNode();
        
        head.next = tail;
        head.prev = null; 

        tail.prev = head;
        tail.next = null;
    }
    
    public int get(int key) {
        DLinkedNode node = cache.get(key);
        if (node == null) return -1;

        // This node becomes the most recently used, 
        // because it was just accessed. 
        moveToHead(node);

        return node.value;
    }
    
    public void put(int key, int value) {
        DLinkedNode node = cache.get(key);
        if (node == null) {
            DLinkedNode newNode = new DLinkedNode(key, value);
            // Add node to cache 
            cache.put(key, newNode);
            // Add node to doubly linked list
            addNode(newNode);

            /*
             * Increase size of the cache 
             * because need to check size 
             * to determine if need to evict
             * least recently used node from cache
             */ 
            ++size; 
            // if not enough space in cache
            // evict least recently used node 
            // by removing from DLinkedList and cache
            if (size > capacity) {
                DLinkedNode tail = popTail();
                cache.remove(tail.key);
                --size;
            }
        } else {
            node.value = value;
            // Updated node becomes the 
            // most recently used node 
            moveToHead(node);
        }
    }
}
