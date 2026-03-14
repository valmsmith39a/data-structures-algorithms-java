package Review_2024;

/**
 * Container with Most Water #11
 * 
 * Time Complexity: O(n) time
 * Space Complexity: O(1) space
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
			int h = Math.min(height[left], height[right]);
			int w = right - left;
			int currentArea = h * w;

			if (currentArea > maxArea) {
				maxArea = currentArea;
			}

			if (currentArea > maxArea) {
				maxArea = currentArea;
			}

			if (height[left] < height[right]) {
				left++;
			} else {
				right--;
			}
		}
		return maxArea;
	}

}
