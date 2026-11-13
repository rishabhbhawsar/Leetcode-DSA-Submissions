class Solution {
    public long countNonDecreasingSubarrays(int[] nums, int k) {
        Deque<Integer> dq = new ArrayDeque<>();
        long cost = 0, answer = 0;
        int right = nums.length - 1;

        for (int left = right; left >= 0; left--) {
            while (!dq.isEmpty() && nums[dq.peekLast()] < nums[left]) {
                int j = dq.removeLast();
                int boundary = dq.isEmpty() ? right + 1 : dq.peekLast();
                cost += (long) (boundary - j) * (nums[left] - nums[j]);
            }
            dq.addLast(left);

            while (cost > k) {
                cost -= (long) nums[dq.peekFirst()] - nums[right];
                if (dq.peekFirst() == right) {
                    dq.removeFirst();
                }
                right--;
            }

            answer += right - left + 1L;
        }
        return answer;
    }
}