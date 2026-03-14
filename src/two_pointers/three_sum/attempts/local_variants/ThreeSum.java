import java.util.*;

public class ThreeSum {
    
    public int[] threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < nums.length && nums[i] <= 0; i++) {
            if (i == 0 || nums[i - 1] != nums[i]) {
                int left = 0;
                int right = nums.length - 1;
                while (left < right) {
                    int sum = nums[i] + nums[left] + nums[right];
                    if (sum < 0) {
                        left++;
                    } else if (sum > 0) {
                        right--;
                    } else {
                        res.add(Arrays.asList(nums[i], nums[left++], nums[right--]))
                        // Only need to update for left because, if duplicate for right, will 
                        // be updated in else if (sum > 0) block
                        while (left < right && nums[i] == nums[i - 1]) {
                            left++;
                        }
                    }
                }
            }
        }
        return res;
    }
}
