class Solution {
    public int minMoves(int[] nums) {
        int minimum = Integer.MAX_VALUE;

        for (int n: nums) {
            if (n < minimum) minimum = n;
        }

        int sum = 0;

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i] - minimum; 
        }

        return sum;
    }
}