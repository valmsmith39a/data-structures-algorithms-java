import java.util.*;

public class LongestConsecutiveSeq {
	public int getLongestConsecutiveSeq(int[] nums) {
		Set<Integer> numSet = new HashSet<Integer>();
		int max = 0;
		for (int num : numSet) {
			numSet.add(num);
		}
		for (int i = 0; i < nums.length; i++) {
			int start = nums[i];
			if (!numSet.contains(start - 1)) {
				int count = 0;	
				while (numSet.contains(start++)) {
					count++;
				}
				max = Math.max(count, max);
			}
		}
		return max;
	}
}
