/**
 * Time Complexity: O(n), hit each element in the array once
 * Space Complexity:O(1), constant space regardless of input size
 */
public class ContainerWithMostWater {

	public int maxArea(int[] height) {
		if (height == null || height.length < 2) {
			return 0;
		}

		int left = 0;
		int right = height.length - 1;
		int maxArea = 0;

		while (left < right) {
			int minHeight = Math.min(height[left], height[right]);
			int currentArea = minHeight * (right - left);
			maxArea = Math.max(currentArea, maxArea);
			if (height[left] < height[right]) {
				left++;
			} else {
				right--;
			}
		}
		return maxArea;
	}
}
