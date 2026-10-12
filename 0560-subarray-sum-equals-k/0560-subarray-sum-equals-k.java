class Solution {
    public int subarraySum(int[] nums, int k) {
        int count = 0;
        int prefixSum = 0;

        Map<Integer, Integer> seen = new HashMap<>();

        seen.put(0, 1);
        for( int num: nums){
            prefixSum += num;
            count += seen.getOrDefault(prefixSum - k, 0);
            seen.put(prefixSum, seen.getOrDefault(prefixSum, 0)+1);
        }
        return count;
    }
}