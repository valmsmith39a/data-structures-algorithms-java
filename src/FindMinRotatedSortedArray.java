/**
 * Problem: Find Minimum in Rotated Sorted Array 
 * 
 * Key Insight: 
 * 1. Binary search to get O (log n) time 
 * 2. Compare mid value with rightmost value to figure out 
 *    which half min value should be in.
 * 
 * Time Complexity: O(log n)
 * Space Complexity: O(1) 
 */
public class FindMinRotatedSortedArray {

    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1; 

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return nums[left];
    }
    
}
