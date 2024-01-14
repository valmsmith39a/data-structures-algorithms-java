/**
 * Problem: Edit Distance (#72)
 *  
 * Key Insight: 
 * 1. Create a DP (dynamic programming) table 
 * 2. Rows (i) represent each character in word 1
 * 3. Columns (j) represent each character in word 2
 * 4. If a character in word1 and word2 are the same, no operation is needed, just take prev operation count 
 * 5. If character is different, then need replacement operation 
 * 6. If there is an additional character in word1 not in word2, deletion is needed 
 * 7. If there is an additional character in word2 not in word1, insertion is needed
 * 
 * Time Complexity: O(m * n)
 * 
 * Space Complexity: O(m * n)
 */
public class EditDistance {
    
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0) {
                    dp[i][j] = j; // word1 is "", so all insertions 
                } else if (j == 0) {
                    dp[i][j] = i; // word2 is "", so all deletions
                } else if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(dp[i - 1][j - 1], // replacement 
                                    Math.min(dp[i-1][j],      // deletion  
                                             dp[i][j - 1]));  // insertion 
                }
            }
        }
        return dp[m][n];
    }
}