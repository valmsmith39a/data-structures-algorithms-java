/**
 *  Find Median of Two Sorted Arrays
 * 
 *  Problem Description:
 *  There are two sorted arrays nums1 and nums2 of size m and n respectively.
 *  Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
 * 
 *  Key insights:
 *  1. Use binary search to find the median
 *  2. Imagine the 2 arrays are merged into 1 array. Half of the elements in the merged array are smaller than the median.
 *  3. You know the index of the median in the merged array. You just need to figure out which numbers are to the left and 
 *     right of the median.
 *  
 *  nums1 = [1, 2, 3]
 *	nums2 = [4, 5, 6, 7]
 *  low: 0, 2, 3
 *	high: 3
 *	i: 1, 2, 3
 *	j: 3, 2, 1
 *	[x, x, x, x]
 *  [1, 2, 3, 4]
 *  left1: 1, 2, 3*
 *	right1: 2, 3, MAX_VALUE
 *	left2: 6, 5, 4**
 *	right2: 7, 6, 5
 *  return Math.max(3, 4) => 4
 */
public class FindMedianTwoSortedArrays {

	public double findMedianSortedArrays(int[] nums1, int[] nums2) {
		int n1 = nums1.length;
		int n2 = nums2.length;
		if (n1 > n2) {
			return findMedianSortedArrays(nums2, nums1);
		}
		int low = 0;
		int high = n1;
		while (low <= high) {
			int i = (low + high) / 2;
			int j = (n1 + n2 + 1) / 2 - i;
			
			int left1 = (i == 0) ? Integer.MIN_VALUE : nums1[i - 1];
			int right1 = (i == n1) ? Integer.MAX_VALUE : nums1[i];
			int left2 = (j == 0) ? Integer.MIN_VALUE : nums2[j - 1];
			int right2 = (j == n2) ? Integer.MAX_VALUE : nums2[j];
			
			if (left1 <= right2 && left2 <= right1) {
				if ((n1 + n2) % 2 == 0) {
					return ((Math.max(left1, left2) + Math.min(right1, right2))/2.0);
				} else {
					return Math.max(left1, left2);
				}
			} else if (left1 > right2) {
				high = i - 1;
			} else {
				low = i + 1;
			}
		}
		return -1.0;
	}

	public static void main(String[] args) {
		FindMedianTwoSortedArrays fm = new FindMedianTwoSortedArrays();
		int[] nums1 = {1, 3};
		int[] nums2 = {2};
		// Expected: 2.0
		System.out.println("Median: " + fm.findMedianSortedArrays(nums1, nums2));
		int[] nums3 = {1, 2};
		int[] nums4 = {3, 4};
		// Expected: 2.5
		System.out.println("Median: " + fm.findMedianSortedArrays(nums3, nums4));
	}
}
