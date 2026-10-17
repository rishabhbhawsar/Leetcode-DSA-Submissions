class Solution {
    // calculate sum of digits for a number
    private int getDigitSum(int n){
            int sum = 0;
            while(n > 0){
                sum += n % 10;
                n /= 10;
            }
            return sum;
        }
    public int minSwaps(int[] nums) {
        int n = nums.length;
        Element[] elements = new Element[n];

        // pack each number, its digit sum and its current index together 
        for(int i = 0; i < n; i++){
            int sum = getDigitSum(nums[i]);
            elements[i] = new Element(nums[i], sum, i);
        }

        // sort array based on digit sum
        Arrays.sort(elements);

        int swaps = 0;
        boolean[] visited = new boolean[n];

        // use cycle detection to find min swaps needed
        for(int i = 0; i < n; i++){
            if(visited[i] || elements[i].originalIndex == i){
                continue;
            }

            int cycleSize = 0;
            int curr = i;

            // trace the cycle until we hit an already visited index
            while(!visited[curr]){
                visited[curr] = true;
                curr = elements[curr].originalIndex;
                cycleSize++;
            }

            // a cycle of k length takes k-1 swaps 
            if(cycleSize > 1){
                swaps += (cycleSize - 1);
            }
        }
        return swaps;
        }

    // to remember where each number originally lived
    static class Element implements Comparable<Element> {
        int val;
        int digitSum;
        int originalIndex;

        public Element(int val, int digitSum, int originalIndex) {
            this.val = val;
            this.digitSum = digitSum;
            this.originalIndex = originalIndex;
        }

        @Override
        public int compareTo(Element other) {
            if (this.digitSum != other.digitSum) {
                return Integer.compare(this.digitSum, other.digitSum);
            }
            return Integer.compare(this.val, other.val);
        }
    }
}