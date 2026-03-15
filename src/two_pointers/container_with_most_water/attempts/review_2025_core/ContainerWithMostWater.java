/* 
	Time Complexity: O(n), hit each element in the array once
	Space Complexity:O(1), constant space regardless of input size
  
	height = [1,8,6,2,5,4,8,3,7]
	index:   [0,1,2,3,4,5,6,7,8]

	left   right   width   height   area   maxArea 
	0      8        8          1    8         8
	1      8        7          7    49       49
	1      7        6          3    18       4
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
