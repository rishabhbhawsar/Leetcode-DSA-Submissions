class Solution {
    public int sortableIntegers(int[] nums) {

        int n = nums.length, ans = 0;

        for (int i = 1; i * i <= n; i++) {
            if (n % i == 0) {
                if (check(nums, i)) ans += i; 
                int k = n / i;
                if (k != i && check(nums, k)) ans += k;
            }
        }
        return ans;
     }
        private boolean check(int[] nums, int k) {
            int prev = Integer.MIN_VALUE, n = nums.length;

            for (int l = 0; l < n; l += k) {
                int r = l + k, min = nums[l], max = nums[l], desc = 0;

                for(int i = l; i < r; i++) {
                    min = Math.min(min, nums[i]);
                    max = Math.max(max, nums[i]);

                    if (nums[i] > nums[i + 1 < r ? i + 1 : l]) desc++;
                }
                if (desc > 1 || prev > min) return false;
                prev = max;
            }
            return true;
        }
}