class Solution {
    public int maxSumMinProduct(int[] nums) {
        int n = nums.length;
        
        int[] left = new int[n];
        int[] right = new int[n];
        int[] stack = new int[n + 1];
        int top = -1;

        for (int i = 0; i < n; i++) {
            while (top >= 0 && nums[stack[top]] >= nums[i]) {
                top--;
            }

            left[i] = (top == -1) ? -1 : stack[top];
            stack[++top] = i;
        }

        top = -1;

        for (int i = n - 1; i >= 0; i--) {
            while (top >= 0 && nums[stack[top]] >= nums[i]) {
                top--;
            }

            right[i] = (top == -1) ? n : stack[top];
            stack[++top] = i;
        }

        long[] prefixSum = new long[n + 1];
        for (int i = 0; i < n; i++) {
            prefixSum[i + 1] = prefixSum[i] + nums[i];
        }

        long maxMinProduct = 0;

        for (int i = 0; i < n; i++) {
            int l = left[i] + 1;
            int r = right[i] - 1;
            long subArraySum = prefixSum[r + 1] - prefixSum[l];
            maxMinProduct = Math.max(maxMinProduct, nums[i] * subArraySum);
        }

        long MOD = 1_000_000_007;
        return (int) (maxMinProduct % MOD);
    }
}