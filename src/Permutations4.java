import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 46. Permutations
 * Time Complexity: O(N * N!) because there are N! permutations and generating
 * each permutation takes O(N) time
 * Space Complexity: O(N) because of the recursion stack and the 'used' array
 */
public class Permutations4 {

	public List<List<Integer>> permute(int[] nums) {
		List<List<Integer>> res = new ArrayList<>();
		backtrack(nums, new ArrayList<>(), new boolean[nums.length], res);
		return res;
	}

	private void backtrack(int[] nums, List<Integer> perm, boolean[] used, List<List<Integer>> res) {
		if (perm.size() == nums.length) {
			res.add(new ArrayList<>(perm));
			return;
		}

		for (int i = 0; i < nums.length; i++) {
			if (used[i]) {
				continue;
			}

			perm.add(nums[i]);
			used[i] = true;

			backtrack(nums, perm, used, res);

			perm.remove(perm.size() - 1);
			used[i] = false;
		}

	}
}
