import java.util.PriorityQueue;
import java.util.Arrays;

public class KthLargest {

    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<Integer>((n1, n2) -> n1 - n2);
        for (int n : nums) {
            heap.add(n);
            if (heap.size() > k) {
                heap.poll();
            }
        }
        return heap.poll();
    }

    public static void main(String[] args) {
        KthLargest arrayOperations = new KthLargest();
        int[] test1 = new int[] { 3, 2, 1, 5, 6, 4 };
        int k1 = 2;
        // Solution: 5
        System.out.println("For array: " + Arrays.toString(test1) + " find " + k1 + "nd largest: " +  arrayOperations.findKthLargest(test1, k1));
        int[] test2 = new int[] { 3, 2, 3, 1, 2, 4, 5, 5, 6 };
        int k2 = 4;
        // Solution: 4
        System.out.println("For array: " + Arrays.toString(test2) + " find " + k2 + "th largest: " + arrayOperations.findKthLargest(test2, k2));
    }
}
