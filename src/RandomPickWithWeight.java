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
		/* Usually to generate a random number within a range 
		   randomNumber = Math.random() * (max - min) + min
		   But in this case min = 0, so Math.random() * (max - 0) + 0 => Math.random() * max */
		double target = Math.random() * this.totalSum;

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
