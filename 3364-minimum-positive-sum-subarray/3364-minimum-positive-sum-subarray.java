class Solution {
    public int minimumSumSubarray(List<Integer> nums, int l, int r) {
        int n = nums.size();
        int ans = Integer.MAX_VALUE;

        // try for every starting index
        for (int i = 0; i < n; i++) {
            int sum = 0;
            // extend the subarray
            for (int j = i; j < n; j++) {
                sum += nums.get(j);
                int len = j - i + 1;
                // check valid subarray length
                if (len >= l && len <= r) {
                    // only positive sums are considered
                    if (sum > 0) {
                        ans = Math.min(ans, sum);
                    }
                }
                // lengths greater than r are not considered
                if (len >= r) {
                    break;
                }
            }
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}