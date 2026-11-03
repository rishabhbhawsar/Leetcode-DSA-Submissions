class Solution {
    public int minMoves(int[] nums) {
        int n = nums.length;
        int maxVal = 0;
        int sumOfElements = 0;

        for (int num : nums) {
            maxVal = Math.max(maxVal, num);
            sumOfElements += num;
        } 

        return maxVal * n - sumOfElements;
    }
}