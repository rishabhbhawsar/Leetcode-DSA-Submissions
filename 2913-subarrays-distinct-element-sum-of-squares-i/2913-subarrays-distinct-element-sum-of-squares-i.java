class Solution {
    public int sumCounts(List<Integer> nums) {
        int ans = 0;
        int n = nums.size();

        for(int i = 0; i < n; i++){
            int[] freq = new int[101];
            int distinctCount = 0;

            for(int j = i; j < n; j++){
                int val = nums.get(j);

                if(freq[val] == 0){
                    distinctCount++;
                } 
                freq[val]++;

                ans += distinctCount * distinctCount;
            }
        }

        return ans;
    }
}