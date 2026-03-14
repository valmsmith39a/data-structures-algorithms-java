import java.util.*;
/**
 * Problem Product Except Self 
 * 
 * Constraints: 
 * 1. At least O(n) time. 
 * 2. Cannot use division. 
 *
 * Solution: 
 * 
 * Find the prefix product (product of numbers up to but not including the current number) at each index and 
 * the suffix product (product of numbers following but not including the current number) at each index. 
 *
 * Key insight to reduce space complexity (assuming result array doesn't count as extra space): 
 * 
 * Compute and store prefix products in res[]. Compute suffix product at each index and multiply with prefix product 
 * and store in res[]
 *
 * Time Complexity: O(n) time 
 * Space Complexity: O(1) space
 */

public class ProductExceptSelf {
	
	int[] productExceptSelf(int[] nums) {
		int[] res = new int[nums.length];
		/**
		 * Compute prefix products.
		 * First prefix product is 1 because no numbers before first element.
		 */
		res[0] = 1;
		for (int i = 1; i < nums.length; i++) {
			res[i] = res[i - 1] * nums[i - 1];
		}

		/**
		 * Compute suffix products. 
		 * First product (from the end of array) is 1 because no numbers after last element.
		 */
		int suffix = 1;
		for (int j = nums.length - 1; j >= 0; j--) {
			// Multiply the computed suffix by the prefix product stored in res[]
			res[j] *= suffix;
			suffix *= nums[j];
		}
		return res;
	}
}
