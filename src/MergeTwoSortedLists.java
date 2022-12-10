import java.util.*;

/**
 * Problem: Merge Two Sorted Lists
 * 
 * Recursive Solution:
 * O(m + n) time
 * O(m + n) space: number of stack frames from recursive calls
 *
 * Iterative Solution:
 * O(m + n) time
 * O(1) space: only create 1 new node, regardless of number of nodes in each
 * list.
 */
public class MergeTwoSortedLists {

    public Node mergeSortedListsRecursive(Node l1, Node l2) {
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        } else if (l1.val < l2.val) {
            l1.next = mergeSortedListsRecursive(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeSortedListsRecursive(l1, l2.next);
            return l2;
        }
    }

    public Node mergeSortedListsIterative(Node l1, Node l2) {
        // Track head node: use pseudo-head node (dummy head)
        Node pseudoHead = new Node();
        Node tail = pseudoHead;

        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                tail.next = l1;
                l1 = l1.next;
            } else {
                tail.next = l2;
                l2 = l2.next;
            }
            tail = tail.next;
        }

        /**
         * Append remaining nodes to our list
         * because it's possible that either l1 or l2 is not null.
         */
        tail.next = l1 == null ? l2 : l1;

        return pseudoHead.next;
    }

    public void printList(Node node) {
        while (node != null) {
            System.out.println(node.val);
            node = node.next;
        }
    }

    public static void main(String[] args) {
        Node l1A = new Node(1);
        Node l1B = new Node(2);
        Node l1C = new Node(4);
        l1A.next = l1B;
        l1B.next = l1C;

        Node l2A = new Node(1);
        Node l2B = new Node(3);
        Node l2C = new Node(4);
        l2A.next = l2B;
        l2B.next = l2C;

        MergeTwoSortedLists op = new MergeTwoSortedLists();

        // System.out.println("--- Iterative Solution --- ");
        // Node mergedListHead = op.mergeSortedListsIterative(l1A, l2A);
        // // Expected: 1
        // System.out.println("Merged list has head: " + mergedListHead.val);
        // // Expected: 1, 1, 2, 3, 4, 4
        // System.out.println("Merged list is: ");
        // op.printList(mergedListHead);

        System.out.println("--- Recursive Solution ---");
        Node mergedListHead = op.mergeSortedListsRecursive(l1A, l2A);
        // Expected: 1
        System.out.println("Merged list has head: " + mergedListHead.val);
        // Expected: 1, 1, 2, 3, 4, 4
        System.out.println("Merged list is: ");
        op.printList(mergedListHead);
    }
}

class Node {
    public int val;
    public Node next;

    public Node() {
        // no-op
    }

    public Node(int val) {
        this.val = val;
    }
}
