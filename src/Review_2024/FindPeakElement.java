/**
 * Find Peak Element #162
 * 
 * Key insights: 
 *  1. Need to solve in O(log n) time, so use Binary Search
 * 
 * Time Complexity: O(log n)
 * Space Complexity: O(1), for iterative solution, O(log n) for recursive solution bc of recursive call stack 
 */
public class FindPeakElement {

    public int findPeakElement(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int mid = left + ((right - left) / 2);
            if (nums[mid] > nums[mid + 1]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return right;
    }
} 
