public class Solution {
    static int[] sortArray(int[] nums) {
        mergeSort(nums, 0, nums.length - 1);
        return nums;
    }

    static void mergeSort(int[] nums, int l, int r) {
        if (l >= r) return;
        int mid = (l + r) / 2;
        mergeSort(nums, l, mid);                   
        mergeSort(nums, mid + 1, r);               
        int[] merged = new int[r - l + 1];
        int i = l, j = mid + 1, k = 0;
        while (i <= mid && j <= r) {                  
            merged[k++] = nums[i] <= nums[j] ? nums[i++] : nums[j++];
        }
        while (i <= mid) merged[k++] = nums[i++];
        while (j <= r) merged[k++] = nums[j++];
        for (int idx = 0; idx < merged.length; idx++) nums[l + idx] = merged[idx];
    }
}