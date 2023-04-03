public class SearchInRotatedSortedArray {
	public int search(int[] nums, int target) {
		int left = 0;
		int right = nums.length - 1;
		while (left <= right) {
			int mid = left + (right - left) / 2;
			if (target == nums[mid]) {
				return mid;
			}
			// In the left sorted portion (left of rotation pivot, if there is one)
			if (nums[mid] >= nums[left]) {
				// Search right of mid pivot
				if (target > nums[mid] || target < nums[left]) {
					left = mid + 1;
				} else {
					// Search left of mid pivot
					right = mid - 1;
				}
			} else {
				// In the right sorted portion (right of rotation pivot, if there is one)
				// Search left of pivot
				if (target < nums[mid] || target > nums[right]) {
					right = mid - 1;
				} else {
					// Search right of pivot
					left = mid + 1;
				}
			}
		}
		return -1;
	}
}
