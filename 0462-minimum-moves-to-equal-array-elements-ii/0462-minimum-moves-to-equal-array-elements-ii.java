class Solution {
    public int minMoves2(int[] nums) {
        Arrays.sort(nums);

        int k = nums[nums.length >> 1];
        int ans = 0;

        for (int n : nums) {
            ans += Math.abs(n - k);
        }

        return ans;
    }
}