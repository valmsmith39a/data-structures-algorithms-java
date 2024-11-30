package Review_2024;

import java.util.List;
import java.util.ArrayList;

/**
 * Permutations: #46
 * Strategy: Backtracking
 * 
 * Time Complexity: O(n x n!):
 * n = length of nums
 * Takes O(n) time to find each permutation
 * Finds n! permutations.
 * 
 * Space Complexity: O(n x n!):
 * Takes O(n) space to store each permutation.
 * n! permutations.
 * 
 */
public class Permutations {

	public List<List<Integer>> permute(int[] nums) {
		List<List<Integer>> result = new ArrayList<>();
		backtrack(nums, new ArrayList<>(), result);
		return result;
	}

	private void backtrack(int[] nums, List<Integer> tempList, List<List<Integer>> result) {
		if (tempList.size() == nums.length) {
			result.add(new ArrayList<>(tempList));
		} else {
			for (int num : nums) {
				if (tempList.contains(num))
					continue;
				tempList.add(num);
				backtrack(nums, tempList, result);
				tempList.remove(tempList.size() - 1);
			}
		}
	}

}

/*
 * call tempList
 * 1 [], [1]: Start call 1 with []. Add 1 to tempList. tempList is now [1] in
 * call 1.
 * 2 [1], [1, 2]: Start call 2 with [1]. Add 2 to tempList. tempList is now [1,
 * 2] in call 2.
 * 3 [1, 2], [1, 2, 3]: Start call 3 with [1, 2]. Add 3 to templist. tempList is
 * now [1, 2, 3] in call 3.
 * 4 [1, 2, 3]: FOUND. result = [[1, 2, 3]]. Pop back up.
 * 3 [1, 2, 3], [1, 2]: When pop back up to call 3, tempList is [1, 2, 3].
 * Remove 3. Iteration complete. Pop back up.
 * 2 [1, 2], [1, 3]: When pop back up to call 2, tempList is [1, 2]. Remove 2.
 * Add 3. Make call 3 with [1, 3]
 * 3 [1, 3], [1, 3, 2]: Skip 1. Add 2. Make call 4 with [1, 3, 2]
 * 4 [1, 3, 2]. FOUND. result = [[1, 2, 3], [1, 3, 2]]. Pop back up.
 * 3 [1, 3, 2], [1, 3]: Remove 2. Skip 3. Iteration Complete. Pop back up.
 * 2 [1, 3], [1]: Remove 3. Iteration complete. Pop back up.
 * 1 [1], [2]: When pop back up to call 1, tempList is [1]. Remove 1. Add 2.
 * Make call 2 with [2]
 * 2 [2], [2, 1]: Start call 2 with [2]. Add 1. Make call 3 with [2, 1]
 * 3 [2, 1], [2, 1, 3]: Skip 1. Skip 2. Add 3.
 * 4 [2, 1, 3]: FOUND. result = [[1, 2, 3], [1, 3, 2], [2, 1, 3]]
 * 3 [2, 1, 3], [2, 1]: Remove 3. Iteration complete. Pop back up.
 * 2 [2, 1], [2, 3]: Remove 1. Skip 2. Add 3.
 * 3 [2, 3], [2, 3, 1]: Add 1
 * 4 [2, 3, 1]: FOUND. result = [[1, 2, 3], [1, 3, 2], [2, 1, 3], [2, 3, 1]]
 * 3 [2, 3, 1], [2, 3]: Remove 1. Skip 2. Skip 3. Iteration complete. Pop back
 * up.
 * 2 [2, 3], [2]: Remove 3. Iteration complete. Pop back up.
 * 1 [2], [3]: Remove 2. Add 3.
 * 2 [3], [3, 1]
 * 3 [3, 1], [3, 1, 2]
 * 4 [3, 1, 2]: FOUND. result = [[1, 2, 3], [1, 3, 2], [2, 1, 3], [2, 3, 1], [3,
 * 1, 2]]
 * 3 [3, 1, 2], [3, 1]: Remove 2. Skip 3. Iteration complete. Pop back up
 * 2 [3, 1], [3, 2]
 * 3 [3, 2], [3, 2, 1]
 * 4 [3, 2, 1]: FOUND. result = [[1, 2, 3], [1, 3, 2], [2, 1, 3], [2, 3, 1], [3,
 * 1, 2], [3, 2, 1]]
 * 3 [3, 2, 1], [3, 2]
 * 2 [3, 2], [3]
 * 1 [3], []: Remove 3. Iteration Complete. Pop back up.
 * 
 * 
 * 
 * call tempList
 * 1 [], [1]
 * 2 [1], [1, 2]
 * 3 [1, 2], [1, 2, 3]
 * 4 [1, 2, 3] FOUND
 * 3 [1, 2, 3], [1, 2]
 * 2 [1, 2], [1, 3]
 * 3 [1, 3], [1, 3, 2]
 * 4 [1, 3, 2] FOUND
 * 3 [1, 3, 2], [1, 3]
 * 2 [1, 3], [1]
 * 1 [1], [2]
 * 2 [2], [2, 1]
 * 3 [2, 1], [2, 1, 3]
 * 4 [2, 1, 3] FOUND
 * 3 [2, 1, 3], [2, 1]
 * 2 [2, 1], [2, 3]
 * 3 [2, 3], [2, 3, 1]
 * 4 [2, 3, 1] FOUND
 * 3 [2, 3, 1], [2, 3]
 * 2 [2, 3], [2]
 * 1 [2], [3]
 * 2 [3], [3, 1]
 * 3 [3, 1], [3, 1, 2]
 * 4 [3, 1, 2] FOUND
 * 3 [3, 1, 2], [3, 1]
 * 2 [3, 1], [3, 2]
 * 3 [3, 2], [3, 2, 1]
 * 4 [3, 2, 1] FOUND
 * 3 [3, 2, 1], [3, 2]
 * 2 [3, 2], [3]
 * 1 [3], []
 * 
 */