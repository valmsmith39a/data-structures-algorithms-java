package Review_2025_100;

public class MissingNumber {
	/**
	 * Time Complexity: O(n)
	 * Space Complexity: O(1)
	 */
	public int missingNumber(int[] nums) {
		int n = nums.length;
		int expectedSum = n * (n + 1) / 2;
		int actualSum = 0;
		for (int num : nums) {
			actualSum += num;
		}
		return expectedSum - actualSum;
	}

	public int missingNumberXOR(int[] nums) {
		int result = nums.length;
		for (int i = 0; i < nums.length; i++) {
			result ^= i;
			result ^= nums[i];
		}
		return result;
	}
}
