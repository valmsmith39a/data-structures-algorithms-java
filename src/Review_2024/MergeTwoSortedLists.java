/**
 * Merge Two Sorted Lists #21 
 * 
 * Key Points: 
 * 1. For smaller node, move to next node 
 * 2. Want last node to return to be the largest node 
 * 3. Try with simple example of list1: 1 and list2: 2 
 * 
 * Time Complexity: O(m + n)
 * Space Complexity: O(m + n)
 */
public class MergeTwoSortedLists {

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        if (list1.val <= list2.val) {
            list1.next = mergeTwoLists(list1.next, list2);
            return list1;
        } else {
            list2.next = mergeTwoLists(list1, list2.next);
            return list2;
        }
    }

    public class ListNode {
        int val;
        ListNode next;

        public ListNode() {}

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}

