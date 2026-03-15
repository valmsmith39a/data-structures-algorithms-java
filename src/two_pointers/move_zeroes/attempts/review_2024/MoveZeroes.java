package Review_2024;

/**
 * Move Zeroes #283
 * 
 * Method: Two Pointers
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
public class MoveZeroes {

	public void moveZeroes(int[] nums) {
		int slow = 0;

		for (int fast = 0; fast < nums.length; fast++) {
			if (nums[fast] != 0) {
				int temp = nums[slow];
				nums[slow] = nums[fast];
				nums[fast] = temp;
				slow++;
			}
		}

	}

}
