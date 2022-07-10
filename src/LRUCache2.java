import java.util.*;

class LRUCache2 {
    Map<String, DoublyLinkedListNode> cache = new HashMap<String, DoublyLinkedListNode>();
    int maxSize;
    int currentSize = 0;
    DoublyLinkedList listOfMostRecent = new DoublyLinkedList();
    
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

        public DoublyLinkedListNode(String key, int value) {
            this.key = key;
            this.value = value;
        }

        public void removeBindings() {
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
}


