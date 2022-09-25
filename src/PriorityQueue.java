import java.util.Arrays;
import java.util.NoSuchElementException;

public class PriorityQueue {
    
    private int capacity;
    private int size = 0;
    private int[] heap;

    public PriorityQueue(int capacity) {
        this.capacity = capacity;
        this.heap = new int[capacity];
    }

    private int getLeftChildIndex(int parentIndex) {
        return 2 * parentIndex + 1;
    }

    private int getRightChildIndex(int parentIndex) {
        return 2 * parentIndex + 2;
    }

    private int getParentIndex(int index) {
        return (index - 1) / 2;
    }

    private boolean hasLeftChild(int index) {
        return getLeftChildIndex(index) < size;
    }

    private boolean hasRightChild(int index) {
        return getRightChildIndex(index) < size;
    }

    private boolean hasParent(int index) {
        return getParentIndex(index) >= 0;
    }

    private int getLeftChild(int parentIndex) {
        return heap[getLeftChildIndex(parentIndex)];
    }

    private int getRightChild(int parentIndex) {
        return heap[getRightChildIndex(parentIndex)];
    }

    private int getParent(int childIndex) {
        return heap[getParentIndex(index)];
    }

    private void swap(int index1, int index2) {
        int element = heap[index1];
        heap[index1] = heap[index2];
        heap[index2] = element;
    }

    private void ensureCapacity() {
        if (size == capacity) {
            // Double the size of the capacity
            heap = Arrays.copyOf(heap, capcity * 2);
        }
    }

    // Runtime: O (log n)
    private void add(int item) {
        ensureCapacity();
        heap[size] = item;
        size++;
        heapifyUp();
    }

    // Runtime: O(1)
    private int peak() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        return heap[0];
    }

    /*
     * Remove and return element with highest priority (ex min)
     */
    private int poll() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        int element = heap[0];
        swap(0, size - 1);
        size--;
        heapifyDown();
        return element;
    }

    // Runtime: O(log n) in worst case
    private void heapifyUp() {
        int index = size - 1;
        while (hasParent(index) && getParent(index) > heap[index]) {
            swap(getParentIndex(index), index);
            index = getParentIndex(index)
        }
    }

    // Runtime: O(log n) in worst case
    private void heapifyDown() {
        int index = 0;
        while (hasLeftChild(index)) {
            int smallestChildIndex = getLeftChild(index);
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
}
