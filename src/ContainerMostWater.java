public class ContainerMostWater {

    public int findMaxWater(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int maxArea = 0;
        while (left < right) {
           int width = right - left;
           maxArea = Math.max(maxArea, Math.min(height[left], height[right]) * width);
           if (left <= right) {
               left++;
           } else {
               right--;
           }
        }
        return maxArea;
    }
    public static void main(String[] args) {
        ContainerMostWater container = new ContainerMostWater();
        int[] height = { 1, 8, 6, 2, 5, 4, 8, 3, 7 };
        int maxArea = container.findMaxWater(height);
        System.out.println("Heights of sides are: " );
        for (int h : height) {
            System.out.println(h);
        }
        System.out.println("Max water that largest container can hold is: " + maxArea);
    }
}
