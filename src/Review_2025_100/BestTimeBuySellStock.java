package Review_2025_100;

/**
 * Time Complexity: O(n), traverse the list once
 * Space Complexity: O(1), always track minPrice and maxProfit regardless size
 * of list
 */
public class BestTimeBuySellStock {

	public int maxProfit(int[] prices) {
		int minPrice = prices[0];
		int maxProfit = 0;

		for (int i = 1; i < prices.length; i++) {
			if (prices[i] < minPrice) {
				minPrice = prices[i];
			} else {
				maxProfit = Math.max(maxProfit, prices[i] - minPrice);
			}
		}
		return maxProfit;
	}
}
