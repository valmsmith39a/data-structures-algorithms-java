package Review_2025_2;

/**
 * Time Complexity: O (log n)
 * Space Complexity: O (log n)
 */
public class BinarySearch {

	public int search(int[] nums, int target) {
		return binarySearch(nums, target, 0, nums.length - 1);
	}

	public int binarySearch(int[] nums, int target, int left, int right) {
		if (left > right) {
			return -1;
		}
		int mid = left + (right - left) / 2;
		if (target == nums[mid]) {
			return mid;
		} else if (target < nums[mid]) {
			return binarySearch(nums, target, left, mid - 1);
		} else {
			return binarySearch(nums, target, mid + 1, right);
		}
	}
}
