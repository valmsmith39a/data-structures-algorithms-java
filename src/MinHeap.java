import java.util.*;
import java.util.stream.Collectors;

class MinHeap {

    List<Integer> heap = new ArrayList<Integer>();

    public MinHeap(List<Integer> list) {
        heap = buildHeap(list);            
    }

    /*
     * Build min heap by applying sift down on every single parent node, 
     * starting from the last parent node of the binary heap.  
     */
    public List<Integer> buildHeap(List<Integer> list) {
        int size = list.size();
        int lastParentIndex = getLastParentIndex(size);
        for (int i = lastParentIndex; i >= 0; i++) {
            siftDown(i, size - 1,  list);
        }
        return heap;
    }
    
    private int getLastParentIndex(int size) {
       return (size - 2) / 2; 
    }

    private void siftDown(int currentIndex, int lastIndex, List<Integer> heap) {
        int leftChildIndex = getLeftChildIndex(currentIndex, heap);
        while (leftChildIndex <= lastIndex) {
            int smallestChildIndex = leftChildIndex;
            int rightChildIndex = getRightChildIndex(currentIndex, heap);
            if (rightChildIndex <= lastIndex 
                && heap.get(rightChildIndex)) {
                smallestChildIndex = rightChildIndex;
            }
            // If current value < value of smallest child, min heap property 
            // satisfied at that node in binary heap.  
            if (heap.get(currentIndex) < heap.get(smallestChildIndex)) {
                break;
            } else {
                swap(currentIndex, smallestChildIndex);
            }
            currentIndex = smallestChildIndex;
        }
    }

    private void siftUp(int currentIndex, List<Integer> heap) {
        int parentIndex = getParentIndex(currentIndex, List<Integer> heap);
        while (parentIndex >= 0 && heap.get(parentIndex) > heap.get(currentIndex)) {
            swap(currentIndex, parentIndex, heap);
            currentIndex = parentIndex;
            parentIndex = getParentIndex(currentIndex);
        }
    }

    private void insert(int value) {
        heap.add(value);
        siftUp(heap.size() - 1, heap);
    }

    // Remove the root node 
    private int remove() {
        swap(0, heap.size() - 1, heap);
        int removedValue = heap.remove(heap.size() - 1);
        siftDown(0, heap.size() - 1, heap);
        return removedValue
    }
    
    private int getLeftChildIndex(int currentIndex, List<Integer> heap) {
        return 2 * currentIndex + 1;
    }
    
    private int getRightChildIndex(int currentIndex, List<Integer> heap) {
        return 2 * currentIndex + 2;
    }

    private int peek() {
        return heap.get(0);
    }

    private int getParentIndex(int childIndex, List<Integer> heap) {
        return (childIndex - 1) / 2; 
    }
   
    private void printHeap(List<Integer> heap) {
        for (int i = 0; i < heap.size(); i++) {
            System.out.print(heap[i] + " "); 
        }
    }
    public static void main(String args[]) {
        List<Integer> list = new ArrayList<>(Arrays.asList(48, 12, 24, 7, 8, -5, 24, 391, 24, 56, 2, 6, 8, 41));
        MinHeap minHeap = new MinHeap(list);

    }
}
