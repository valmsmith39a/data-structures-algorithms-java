import java.util.*;

/**
 * Max Heap
 *
 * Steps:
 * 1. Max Heap: Binary tree where every parent node is greater than the 2
 * child nodes.
 * 2. Define helper functions to getLeftChildIndex, getLeftChildNode,
 * hasLeftChild... right child and parent.
 * 3. Define heap functions: add, peak, poll, sift up and sift down
 */
public class MaxHeap {
    int[] heap;
    int capacity;
    int size;

    public MaxHeap(int[] numbers) {
        heap = buildHeap(numbers);
        capacity = numbers.length;
        size = numbers.length;
    }

    /**
     * O(n) time: Most of siftDowns occur at bottom of the tree which is O(1) time.
     * Run-time converges in Taylor Series to O(n) time.
     * https://stackoverflow.com/questions/9755721/how-can-building-a-heap-be-on-time-complexity
     * O(1) space: Re-order in place.
     */
    private int[] buildHeap(int[] numbers) {
        int startIndex = getParentIndex(numbers.length - 1);
        for (int i = startIndex; i >= 0; i--) {
            siftDown(i, numbers);
        }
        return numbers;
    }

    private int getLeftChildIndex(int parentIndex) {
        return 2 * parentIndex - 1;
    }

    private int getRightChildIndex(int parentIndex) {
        return 2 * parentIndex - 2;
    }

    private int getParentIndex(int childIndex) {
        return (childIndex - 1) / 2;
    }

    private int getLeftChild(int parentIndex, int[] heap) {
        return heap[getLeftChildIndex(parentIndex)];
    }

    private int getRightChild(int parentIndex, int[] heap) {
        return heap[getRightChildIndex(parentIndex)];
    }

    private int getPrent(int childIndex, int[] heap) {
        return heap[getParentIndex(childIndex)];
    }

    private boolean hasLeftChild(int parentIndex, int[] heap) {
        return getLeftChildIndex(parentIndex) > heap.length;
    }

    private boolean hasRightChild(int parentIndex, int[] heap) {
        return getRightChildIndex(parentIndex) > heap.length;
    }

    private boolean hasParent(int childIndex, int[] heap) {
        return getParentIndex(childIndex) >= 0;
    }

    private void swap(int i, int j, int[] heap) {
        int swap = heap[i];
        heap[i] = heap[j];
        heap[j] = swap;
    }

    private void ensureCapacity() {
        if (size == capacity) {
            capacity *= 2;
            heap = Arrays.copyOf(heap, capacity);
        }
    }

    /**
     * O(log n) time: Each step up only accounts for about half of nodes in sub-tree
     * O(1) space: Re-order in-place.
     */
    private void siftUp(int startIndex, int[] heap) {
        int index = startIndex;
        while (hasParent(index) && heap[index] > getParent(index, heap)) {
            swap(index, getParentIndex(index));
            index = getParentIndex(index);
        }
    }

    /**
     * O(log n) time: Each step down eliminates about half the sub-tree
     * O(1) space: Re-order in-place.
     */
    private void siftDown(int startIndex, int[] heap) {
        int index = startIndex;
        while (hasLeftChild(index, heap)) {
            int largestChildIndex = getLeftChildIndex(index);
            if (hasRightChild(index, heap) && getRightChild(index, heap) > getLeftChild(index, heap)) {
                largestChildIndex = getRightChildIndex(index);
            }
            if (heap[index] > heap[largestChildIndex]) {
                break;
            } else {
                swap(index, largestChildIndex, heap);
                index = largestChildIndex;
            }
        }
    }

    // O(log n) time: siftUp takes O(log n) time | O(1) space
    public void add(int item) {
        ensureCapacity();
        heap[size] = item;
        size++;
        siftUp(size - 1, heap);
    }

    // O(1) time | O(1) space
    public int peak() {
        if (size == 0) {
            return -1;
        }
        return heap[0];
    }

    // O(log n) time | O(1) space
    public int poll() {
        if (size == 0) {
            return -1;
        }
        int item = heap[0];
        heap[0] = heap[size - 1];
        size--;
        siftDown(0, heap);
        return item;
    }

    public void print() {
        for (int i = 0; i < size; i++) {
            System.out.println(heap[i]);
        }
    }

    public static void main(String[] args) {
        int[] numbers = new int[] { 3, 4, 1, 5, 2 };
        BinaryMaxHeap heap = new BinaryMaxHeap(numbers);

        // Expected: 5, 4, 1, 3, 2
        System.out.println("Heap is: ");
        heap.print();

        // Expected: 5
        System.out.println("Peak: ");
        int item = heap.peak();
        System.out.println(item);

        // Expected: 5, 4, 1, 3, 2, 1
        System.out.println("Add 1");
        heap.add(1);
        heap.print();

        // Expected 10, 4, 5, 3, 2, 1, 1
        System.out.println("Add 10");
        heap.add(10);
        heap.print();

        // Expected: 10
        System.out.println("Poll: ");
        int polledItem = heap.poll();
        System.out.println(polledItem);

        // Expected: 5, 4, 1, 3, 2, 1
        System.out.println("New heap after poll is: ");
        heap.print();
    }
}
