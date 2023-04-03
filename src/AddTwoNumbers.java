
public class AddTwoNumbers {
    /**
     * Problem: Add Two Numbers
     * 
     * Description:
     * Store 2 numbers in reverse order in 2 linked lists and add them.
     * Store solution in a linked list in reverse order
     *
     * Key insight:
     * 1. carry (when add 2 numbers) is computed by dividing by 10, because you're
     * asking
     * "how many tens, hundreds... am I carrying over to the next place?"
     * 2. The number in the current place (ones, tens, hundreds place etc) is the
     * remainder
     * from dividing by 10, so take the some and modulo 10 to get the remainder.
     * 
     * Time complexity: O(max(m, n))
     * m: length of l1
     * n: length of l2
     * Executes as most O(max(m, n)) times.
     *
     * Space complexity: O(max(m, n))
     * Length of solution list is at most max(m, n) + 1 (including dummy head) * ->
     * O(max(m, n))
     *
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry = 0;
        ListNode dummyHead = new ListNode(0);
        ListNode currentNode = dummyHead;

        while (l1 != null || l2 != null || carry != 0) {
            int x = l1 == null ? 0 : l1.data;
            int y = l2 == null ? 0 : l2.data;
            int sum = x + y + carry;
            int newNodeValue = sum % 10;
            carry = sum / 10;
            currentNode.next = new ListNode(newNodeValue);
            currentNode = currentNode.next;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        return dummyHead.next;
    }

    public ListNode createLinkedList(int[] values) {
        LinkedList list = new LinkedList();
        for (int value : values) {
            list.append(value);
        }
        return list.head;
    }

    public void printLinkedList(ListNode head) {
        ListNode current = head;
        while (current != null) {
            System.out.print(current.data);
            current = current.next;
        }
        System.out.println();

    }

    public class LinkedList {
        ListNode head;

        public LinkedList() {
            // Empty constructor
        }

        public LinkedList(ListNode node) {
            head = node;
        }

        public void append(int data) {
            if (head == null) {
                head = new ListNode(data);
                return;
            }

            ListNode current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = new ListNode(data);
        }
    }

    private class ListNode {
        int data;
        ListNode next;

        private ListNode(int data) {
            this.data = data;
        }
    }

    public static void main(String[] args) {
        AddTwoNumbers operations = new AddTwoNumbers();
        int[] list1 = new int[] { 2, 4, 3 };
        int[] list2 = new int[] { 5, 6, 4 };
        ListNode list1Head = operations.createLinkedList(list1);
        ListNode list2Head = operations.createLinkedList(list2);
        ListNode solutionNode = operations.addTwoNumbers(list1Head, list2Head);
        // 243
        operations.printLinkedList(list1Head);
        // 564
        operations.printLinkedList(list2Head);
        // 708
        operations.printLinkedList(solutionNode);
    }
}
