import java.util.*;

/**
 * Merge Sort
 * 
 * Divide and conquer algorithm
 * 
 * Key Idea:
 * Divide the array in half recursively until we get to 1 element arrays
 * and merge them into sorted sub-arrays until the entire array is sorted.
 * 
 * Time Complexity:
 * O(n log n) time: Each level takes O(n) time and we have O (log n) levels
 * because we continuously divide the arrays in half.
 * 
 * Space Complexity:
 * O(n): array used to store sorted numbers
 * O(log n): number of frames on call stack when recursively call merge sort)
 * O(n log n)
 * 
 * @param numbers array of numbers
 * @return sorted array
 * 
 */
public class MergeSort {

    public int[] mergeSort(int[] numbers) {
        // Base case: 1 element array
        if (numbers.length == 1) {
            return numbers;
        }
        int pivotIdx = numbers.length / 2;
        // Arrays.copyOfRange, start - inclusive, end - exclusive
        int[] leftHalf = Arrays.copyOfRange(numbers, 0, pivotIdx);
        int[] rightHalf = Arrays.copyOfRange(numbers, pivotIdx, numbers.length);
        return mergeSortedArrays(mergeSort(leftHalf), mergeSort(rightHalf));
    }

    /**
     * Merge Sorted Array
     * 
     * @param leftHalf  left half of array
     * @param rightHalf right half of array
     * @return sorted array
     */
    private int[] mergeSortedArrays(int[] leftHalf, int[] rightHalf) {
        int[] sortedArray = new int[leftHalf.length + rightHalf.length];
        int i, j, k;
        i = j = k = 0;
        while (i < leftHalf.length && j < rightHalf.length) {
            if (leftHalf[i] < rightHalf[j]) {
                sortedArray[k] = leftHalf[i];
                i++;
            } else {
                sortedArray[k] = rightHalf[j];
                j++;
            }
            k++;
        }
        while (i < leftHalf.length) {
            sortedArray[k] = leftHalf[i];
            i++;
            k++;
        }
        while (j < rightHalf.length) {
            sortedArray[k] = rightHalf[j];
            j++;
            k++;
        }
        return sortedArray;
    }

    public static void main(String[] args) {
        int[] numbers = new int[] { 8, 5, 2, 9, 5, 6, 3 };
        MergeSort sort = new MergeSort();
        int[] sortedArray = sort.mergeSort(numbers);
        for (int num : sortedArray) {
            System.out.println(num);
        }
    }

}
