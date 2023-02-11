import java.util.*;
/**
 * Problem: Best Time to Buy / Sell Stock 
 *
 * Solution Strategy: 
 * 1. At each index (day), determine the minBuyPrice and compute the maxProfit if sold on that day. 
 *
 * Time Complexity: 
 * O(n) time: iterate through the array of prices once. 
 *
 * Space Complexity: 
 * O(1) space: Only track 2 variables (minBuyPrice and maxProfit) for any arbitrary number of prices. 
 */
public class BestTimeBuySellStock {
	
	public int getMaxProfit(int[] prices) {
		int minBuyPrice = Integer.MAX_VALUE;
		int maxProfit = 0;
		for (int i = 0; i < prices.length; i++) {
			if (prices[i] < minBuyPrice) {
				minBuyPrice = prices[i];
			}
			if (prices[i] - minBuyPrice > maxProfit) {
				maxProfit = prices[i] - minBuyPrice;
			}
		}
		return maxProfit;
	}

	public static void main(String[] args) {
		int[] prices = new int[]{ 7, 1, 5, 3, 6, 4 };
		BestTimeBuySellStock bt = new BestTimeBuySellStock();
		int maxProfit = bt.getMaxProfit(prices);
		System.out.println("Max profit is: " + maxProfit);
	}
}
