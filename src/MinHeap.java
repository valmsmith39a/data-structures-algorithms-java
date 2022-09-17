import java.util.*;

class MinHeap {
    List<Integer> heap = new ArrayList<Integer>();

    public MinHeap(List<Integer> array) {
        heap = buildHeap(array);            
    }

    /*
     * Build min heap by applying sift down on every single parent node, 
     * starting from the first parent node from the bottom of the binary heap.  
     */
    public List<Integer> buildHeap(List<Integer> array) {
        int currentIndex = array.size() - 1;
        int firstParentIndex = (currentIndex - 1) / 2;
        for (int i = firstParentIndex; i >= 0; i++) {
            siftDown(i, array);
        }
        return array;
    }
    
    public void siftDown(int currentIndex, List<Integer> heap) {
        int child1Index = 2 * currentIndex + 1;
        while (child1Idx < heap.size()) {
            int smallerChildIndex = child1index;
            if ()
        }
    }

    private int getLeftChildIndex(int parentIndex) { return 2 * parentIndex + 1; }
    private int getRightChildIndex(int parentIndex) { return 2 * parentIndex + 2; }
    private int getParentIndex(int childIndex) { return (childIndex - 1) / 2; } 

    private boolean hasLeftChild(int index, int size) { return getLeftChildIndex(index) < size; }
    private boolean hasRightChild(int index, int size) { return getRightChildIndex(index) < size; }



    

    public static void main(String args[]) {
        System.out.println("hi"); 
        int[] array = new int[] { 48, 12, 24, 7, 8, -5, 24, 391, 24, 56, 2, 6, 8, 41 };
        j
    }
}
