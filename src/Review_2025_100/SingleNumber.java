package Review_2025_100;

/**
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 * Strategy: XOR
 * arr = [1, 2, 1]
 * 
 * 0001
 * 0010
 * 0011
 * 
 * 0011
 * 0001
 * 0010
 * 
 * Solution: 0010
 */
public class SingleNumber {

	public int singleNumber(int[] nums) {
		int result = 0;
		for (int num : nums) {
			result = result ^ num;
		}
		return result;
	}
}
