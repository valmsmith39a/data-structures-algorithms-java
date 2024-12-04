package Review_2024;

import java.util.Arrays;

/**
 * Coin Change # 322
 * 
 * Time complexity: O(m x n), m = number of coins, n = amount
 * Space complexity: O(n)
 */
public class CoinChange {

	public int coinChange(int[] coins, int amount) {
		// dp array tracks the min number of coins to make up each amount represented by
		// each index
		int[] dp = new int[amount + 1];
		Arrays.fill(dp, amount + 1);
		dp[0] = 0;
		for (int coin : coins) {
			for (int i = coin; i <= amount; i++) {
				// Either the min number of coins to make up the amount is dp[i]
				// Or it's the current coin (1) + min number of coins at amount i - coin
				dp[i] = Math.min(dp[i], dp[i - coin] + 1);
			}
		}
		return dp[amount] > amount ? -1 : dp[amount];
	}

}
