public class RandomPickWithWeight {
	private int[] prefixSums;
	private int totalSum;

	public RandomPickWithWeight(int[] weights) {
		this.prefixSums = new int[weights.length];
		
		int prefixSum = 0;
		for (int i = 0; i < weights.length; i++) {
			prefixSum += weights[i];
			this.prefixSums[i] = prefixSum;
		}
		this.totalSum = prefixSum;
	}

	public int pickIndex() {
		double target = this.totalSum * Math.random();

		int start = 0, end = this.prefixSums.length;
		while (start < end) {
			int mid = start + (end - start) / 2;

			if (target > this.prefixSums[mid])
				start = mid + 1;
			else 
				end = mid;
		}
		return start;
	}

	public static void main(String args[]) {
		System.out.println("hello metaverse");
		
		int[] weights = new int[] { 1, 3 };
		RandomPickWithWeight randomPick = new RandomPickWithWeight(weights);
		int selected = randomPick.pickIndex();
		System.out.println("Selected index is: " + selected);
	}
	
}
