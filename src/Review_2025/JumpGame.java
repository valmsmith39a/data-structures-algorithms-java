package Review_2025;

/**
 * Time Complexity: O(n), bc iterate through every element
 * Space Complexity: O(1)
 */
public class JumpGame {

	public boolean canJump(int[] nums) {
		if (nums == null || nums.length == 0) {
			return false;
		}
		// Find max reach at each index
		int maxReach = 0;
		for (int i = 0; i < nums.length; i++) {
			// Or i > maxReach
			if (maxReach <= i) {
				return false;
			}
			maxReach = Math.max(maxReach, i + nums[i]);
			if (maxReach >= nums.length - 1) {
				return true;
			}
		}
		return true;
	}
}
