import java.util.Set;
import java.util.HashSet;

/**
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */
public class ContainsDuplicate {

	public boolean containsDuplicate(int[] nums) {
		Set<Integer> numSet = new HashSet<>();
		for (int num : nums) {
			if (numSet.contains(num)) {
				return true;
			}
			numSet.add(num);
		}
		return false;
	}

	public static void main(String[] args) {
		int[] nums = [1, 2, 3, 1];
		ContainsDuplicate cd = new ContainsDuplicate();
		boolean res = cd.ContainsDuplicate(nums);
		// Expected: true
		System.out.println(res);
	}
}