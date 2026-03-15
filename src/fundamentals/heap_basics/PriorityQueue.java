import java.util.Arrays;

/**
 * Priority Queue using a Min Heap
 * 
 * Resources:
 * 1. Visualization: https://www.cs.usfca.edu/~galles/visualization/Heap.html
 * 
 * Summary:
 * 1. heap (array), capacity, size
 * 2. Methods to build the heap: Parent, Child, Heapify Up, Heapify Down, Swap
 * 3. Priority Queue methods: (1) Add, (2) Peak (3) Poll (remove)
 */
public class PriorityQueue {
    private int[] heap;
    private int capacity;
    private int size;

    public PriorityQueue(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        heap = new int[capacity];
    }

    private int getLeftChildIndex(int parentIndex) {
        return 2 * parentIndex + 1;
    }

    private int getRightChildIndex(int parentIndex) {
        return 2 * parentIndex + 2;
    }

    private int getParentIndex(int childIndex) {
        return (childIndex - 1) / 2;
    }

    private int getLeftChild(int parentIndex) {
        return heap[getLeftChildIndex(parentIndex)];
    }

    private int getRightChild(int parentIndex) {
        return heap[getRightChildIndex(parentIndex)];
    }

    private int getParent(int childIndex) {
        return heap[getParentIndex(childIndex)];
    }

    private boolean hasLeftChild(int parentIndex) {
        return getLeftChildIndex(parentIndex) < size;
    }

    private boolean hasRightChild(int parentIndex) {
        return getRightChildIndex(parentIndex) < size;
    }

    private boolean hasParent(int childIndex) {
        return getParentIndex(childIndex) >= 0;
    }

    public int getSize() {
        return this.size;
    }

    public void print() {
        int queueSize = this.size;
        for (int i = 0; i < queueSize; i++) {
            System.out.println(heap[i]);
        }
    }

    private void swap(int i, int j) {
        int item = heap[i];
        heap[i] = heap[j];
        heap[j] = item;
    }

    private void ensureCapacity() {
        if (size == capacity) {
            capacity *= 2;
            heap = Arrays.copyOf(heap, capacity);
        }
    }

    // Runtime: O (log n)
    public void add(int item) {
        ensureCapacity();
        heap[size] = item;
        size++;
        heapifyUp();
    }

    // Runtime: O(1)
    public int peak() {
        if (size == 0) {
            return -1;
        }
        return heap[0];
    }

    public int poll() {
        if (size == 0) {
            return -1;
        }
        int item = heap[0];
        heap[0] = heap[size - 1];
        size--;
        heapifyDown();
        return item;
    }

    // Runtime: O(log n) worst case
    public void heapifyUp() {
        int index = size - 1;
        while (hasParent(index) && getParent(index) > heap[index]) {
            swap(getParentIndex(index), index);
            index = getParentIndex(index);
        }
    }

    // Runtime: O(log n) worst case
    public void heapifyDown() {
        int index = 0;
        while (hasLeftChild(index)) {
            int smallestChildIndex = getLeftChildIndex(index);
            if (hasRightChild(index) && getRightChild(index) < getLeftChild(index)) {
                smallestChildIndex = getRightChildIndex(index);
            }
            if (heap[index] < heap[smallestChildIndex]) {
                break;
            } else {
                swap(index, smallestChildIndex);
            }
            index = smallestChildIndex;
        }
    }

    public static void main(String[] args) {
        PriorityQueue queue = new PriorityQueue(5);
        queue.add(3);
        queue.add(5);
        queue.add(4);
        queue.add(1);
        queue.add(2);
        int size = queue.getSize();
        // Print the queue. Expected: 1, 2, 4, 5, 3
        System.out.println("Print the queue: ");
        queue.print();

        // Peak. Expected: 1
        System.out.println("Peak (get first element (root node in min heap) in the queue): ");
        System.out.println(queue.peak());

        // Poll: Remove an item. Expected: 1
        System.out.println("Poll: Remove item from queue with highest priority (smallest in this case)");
        System.out.println(queue.poll());

        // Print the queue. Expected: 2, 3, 4, 5
        System.out.println("Print the queue: ");
        queue.print();

        // Poll each element in the queue. Expected: 2, 3, 4, 5
        System.out.println("Poll each element in the queue: ");
        for (int i = 0; i < size; i++) {
            System.out.println(queue.poll());
        }
    }
}
