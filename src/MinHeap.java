import java.util.*;

/**
 * Min Heap
 *
 * Problem:
 * Build a min heap (parent node smaller than 2 child nodes) from an array of
 * integers.
 *
 * Summary:
 * 1. Start with last parent node.
 * 2. Sift the last parent node down.
 * 3. Move to the previous node and sift that node down.
 */
public class MinHeap {
    private int[] heap;
    private int capacity;
    private int size;

    public MinHeap(int[] numbers) {
        capacity = numbers.length;
        size = capacity;
        heap = buildHeap(numbers);
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

    private int getLeftChild(int parentIndex, int[] array) {
        return array[getLeftChildIndex(parentIndex)];
    }

    private int getRightChild(int parentIndex, int[] array) {
        return array[getRightChildIndex(parentIndex)];
    }

    private int getParent(int childIndex, int[] array) {
        return array[getParentIndex(childIndex)];
    }

    private boolean hasLeftChild(int parentIndex, int[] array) {
        return getLeftChildIndex(parentIndex) < size;
    }

    private boolean hasRightChild(int parentIndex, int[] array) {
        return getRightChildIndex(parentIndex) < size;
    }

    private boolean hasParent(int childIndex, int[] array) {
        return getParentIndex(childIndex) >= 0;
    }

    private void swap(int i, int j, int[] array) {
        int item = array[i];
        array[i] = array[j];
        array[j] = item;
    }

    /**
     * Runtime: O(n) time
     * Iterate through all the nodes: O(n)
     * For each node, call siftDown which is O(log n).
     * But the majority of siftDown calls are on nodes near the bottom
     * of the tree, which O(1) time (can only sift down once for the bottom parent
     * nodes).
     * Can represent runtime as a taylor series which converges to O(n) time
     */
    private int[] buildHeap(int[] numbers) {
        int firstParentIndex = getParentIndex(numbers.length - 1);
        for (int i = firstParentIndex; i >= 0; i--) {
            siftDown(i, numbers);
        }
        return numbers;
    }

    // O(log n) time | O(1) space (re-order nodes in-place)
    private void siftDown(int index, int[] heap) {
        if (hasLeftChild(index, heap)) {
            int smallestChildIndex = getLeftChildIndex(index);
            if (hasRightChild(index, heap) && getRightChild(index, heap) < getLeftChild(index, heap)) {
                smallestChildIndex = getRightChildIndex(index);
            }
            if (heap[index] < heap[smallestChildIndex]) {
                return;
            } else {
                swap(index, smallestChildIndex, heap);
                siftDown(smallestChildIndex, heap);
            }
        }
    }

    // method 2: iterative
    // private void siftDown(int idx, int[] heap) {
    // int index = idx;
    // while (hasLeftChild(index, heap)) {
    // int smallestChildIndex = getLeftChildIndex(index);
    // if (hasRightChild(index, heap) && getRightChild(index, heap) <
    // getLeftChild(index, heap)) {
    // smallestChildIndex = getRightChildIndex(index);
    // }
    // if (heap[index] < heap[smallestChildIndex]) {
    // break;
    // } else {
    // swap(index, smallestChildIndex, heap);
    // }
    // index = smallestChildIndex;
    // }
    // }

    // O(log n) time | O(1) space (re-order nodes in-place)
    private void siftUp(int index, int[] heap) {
        if (hasParent(index, heap) && heap[index] < getParent(index, heap)) {
            swap(index, getParentIndex(index), heap);
            index = getParentIndex(index);
            siftUp(index, heap);
        }
    }

    public void ensureCapacity() {
        if (size == capacity) {
            capacity *= 2;
            heap = Arrays.copyOf(heap, capacity);
        }
    }

    // O(log n) time | O(1) space
    public void add(int item) {
        ensureCapacity();
        heap[size] = item;
        size++;
        siftUp(size - 1, heap);
    }

    // O(1) time | O(1) space;
    public int peak() {
        if (this.heap.length == 0) {
            return -1;
        }
        return this.heap[0];
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
        int[] numbers = new int[] { 3, 5, 2, 4, 1 };
        int[] displayedNumbers = Arrays.copyOf(numbers, numbers.length);
        MinHeap heap = new MinHeap(numbers);

        // Heapify the array. Expected: 1, 3, 2, 4, 5
        System.out.println("Heapify the following array: " + Arrays.toString(displayedNumbers));
        heap.print();

        // Add 0 to the heap. Expected: 0, 3, 1, 4, 5, 2
        System.out.println("Add 0 to the heap");
        heap.add(0);
        heap.print();

        // Poll (remove), expected: 0
        System.out.println("Poll (remove number with highest priority) from heap");
        int polledValue = heap.poll();
        System.out.println("Polled value is: " + polledValue);
        // Expected: 1, 3, 2, 4, 5
        System.out.println("Heap is now: ");
        heap.print();

        // Peak (read the root node), expected: 1
        int item = heap.peak();
        System.out.println("Peak: " + item);
    }
}
