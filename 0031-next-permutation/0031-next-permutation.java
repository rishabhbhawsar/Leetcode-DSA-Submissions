class Solution {
    public void nextPermutation(int[] nums) {
        int n = nums.length;
        if (n < 2) return;

        int curr = n - 2, prev = n - 1;
        while (curr >= 0) {
            if (nums[curr] < nums[prev]) break;
            curr--;
            prev--;
        }
        if (curr >= 0) {
            int replaceIdx = n - 1;
            while (replaceIdx > 0) {
                if (nums[curr] < nums[replaceIdx]) break;
                replaceIdx--;
            }
            swap(nums, replaceIdx, curr);
        }
        reverse(nums, prev, n - 1);
    }
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            swap(nums, start, end);
            start++;
            end--;
        }
    }
}