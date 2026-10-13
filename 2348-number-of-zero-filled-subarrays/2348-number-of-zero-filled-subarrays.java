class Solution {
    public long zeroFilledSubarray(int[] nums) {
        long ans = 0;
        long streak = 0;

        for(int i = 0; i < nums.length; i++){
            if(nums[i] == 0){
                streak++;
                ans += streak;
            } else{
                streak = 0;
            }
        }
        return ans;
    }
}