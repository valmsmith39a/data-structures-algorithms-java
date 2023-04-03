import java.util.*;

/**
 * Binary Search
 * 
 * Problem: Return index of target element or -1
 * 
 * Key idea: Search for target by recursively dividing the array in
 * half and look for the target in either the left or right half. Each
 * iteration reduces the search space by 1/2.
 * 
 * Steps:
 * 1. Left/Right pointers at beginning/end of array
 * 2. Check terminal (base) case: left index > right index
 * 3. Compute floor of middle index
 * 4. Check 3 cases:
 * a. target == potentialMatch
 * b. target < potentialMatch => look in left half of array
 * c. target > potentialMatch => look in right half of array
 * 
 * Time/Space Complexity:
 * O(log n) time: eliminate 1/2 of elements in each iteration
 * For iterative approach, O(1) space (constant space): no elements stored
 * For recursive approach, O(log n) space:
 * 
 * @param numbers array of numbers
 * @param target  target number
 * @param left    left pointer index
 * @param right   right pointer index
 * @return index of the target number or -1
 */
public class BinarySearch {

    public int binarySearch(int[] numbers, int target, int left, int right) {
        /**
         * Base case:
         * When get to final number (either first or last),
         * left == right, pivot index is 0 or lastIndex
         * In the next iteration, left and right will cross
         * because either left = pivot + 1 or right = pivot - 1,
         * depending on whether target is greater or less than
         * the potential match at the pivot index, indicating
         * search is complete.
         */
        if (left > right) {
            return -1;
        }
        /**
         * 1. Java rounds down by default
         * 2. Use this method for finding middle index to prevent Integer overflow
         * if left, right or both are a large value (Integer.MAX_VALUE)
         */
        int pivotIdx = left + (right - left) / 2;
        if (target == numbers[pivotIdx]) {
            return pivotIdx;
        }
        if (target < numbers[pivotIdx]) {
            return binarySearch(numbers, target, left, pivotIdx - 1);
        }
        return binarySearch(numbers, target, pivotIdx + 1, right);
    }

    public static void main(String[] args) {
        int[] numbers = new int[] { 2, 5, 6, 9, 15, 19, 23 };
        BinarySearch search = new BinarySearch();
        int target = 19;
        int resultBinarySearch = search.binarySearch(numbers, target, 0, numbers.length);
        // Target is: 19 Index of target is: 5
        System.out.println("Binary Search. " + "Target is: " + target + " Index of target is: " + resultBinarySearch);
    }
}
