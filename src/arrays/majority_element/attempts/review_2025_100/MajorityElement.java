package Review_2025_100;

/**
 * Time Complexity: O(n), iterate through array once
 * Space Complexity: O(1), have 2 variables
 */
public class MajorityElement {

	public int majorityElement(int[] nums) {
		int candidate = nums[0];
		int count = 1;

		for (int i = 1; i < nums.length; i++) {
			if (count == 0) {
				candidate = nums[i];
				count = 1;
			} else if (nums[i] == candidate) {
				count++;
			} else {
				count--;
			}
		}
		return candidate;
	}
}
