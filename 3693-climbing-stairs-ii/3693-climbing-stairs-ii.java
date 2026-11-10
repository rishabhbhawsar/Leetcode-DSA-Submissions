class Solution {
    public int climbStairs(int n, int[] costs) {
        int[] dp = new int[n + 1];

        // prevent integer overflow
        final int inf = Integer.MAX_VALUE / 2;
        Arrays.fill(dp, inf);
        dp[0] = 0;

        for (int i = 1; i <= n; i++) {
            int stepCost = costs[i - 1];
            for (int j = Math.max(0, i - 3); j < i; ++j) {
                int jumpLength = i - j;
                int totalJumpCost = stepCost + (jumpLength * jumpLength);

                dp[i] = Math.min(dp[i], dp[j] + totalJumpCost);
            }
        }

        return dp[n];
    }
}