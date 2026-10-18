class Solution {
    private static final int MOD = 1_000_000_007;
    private List<List<Long>> comb;

    // generate combinations (pascal's triangle)
    private List<List<Long>> generate(int numRows){
        List<List<Long>> table = new ArrayList<>();
        for(int i = 0; i < numRows; ++i){
            Long[] temp = new Long[i + 1];
            Arrays.fill(temp, 1L);
            table.add(Arrays.asList(temp));
        }
        for(int i = 2; i < numRows; ++i){
            for(int j = 1; j < table.get(i).size() - 1; ++j){
                long val = (table.get(i - 1).get(j - 1) + table.get(i - 1).get(j)) % MOD;
                table.get(i).set(j, val);
            }
        }
        return table;
    }

    // divide and conquer
    private long ways(List<Integer> nums){
        if (nums.size() <= 2) return 1;

        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();

        int root = nums.get(0);
        for(int i = 1; i < nums.size(); ++i){
            if(nums.get(i) < root){
                left.add(nums.get(i));
            } else{
                right.add(nums.get(i));
            }
        }

        // calcuulate combinations for interleaving branches
        long ans = comb.get(nums.size() - 1).get(left.size());
        ans = (ans * ways(left)) % MOD;
        ans = (ans * ways(right)) % MOD;

        return ans;
    } 

    public int numOfWays(int[] nums) {
        int n = nums.length;
        comb = generate(n + 1);

        // array to list conversion
        List<Integer> list = new ArrayList<>();
        for(int num: nums){
            list.add(num);
        }

        long totalWays = ways(list);
        // subtracting 1 cause original array is not reordering itself
        return (int) ((totalWays - 1 + MOD) % MOD);
    }
}