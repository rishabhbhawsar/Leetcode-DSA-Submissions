class Solution {
    public int missingNumber(int[] nums) {
        Set<Integer> present = new HashSet<>();
        for(int num: nums) present.add(num);
        int n = nums.length;
        for(int candidate = 0; candidate <= n; candidate++){
            if (!present.contains(candidate)) return candidate;
        }
        return -1;
    }
}