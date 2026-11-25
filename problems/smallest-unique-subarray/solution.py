class Solution:
    def smallestUniqueSubarray(self, nums: List[int]) -> int:
        MOD = 10**9 + 7
        base = max(nums) + 1

        def check(size):
            seen = defaultdict(int)
            h = 0
            for i in range(size): h = (h * base + nums[i]) % MOD
            seen[h] += 1

            p = pow(base, size - 1, MOD)
            for i in range(size, n):
                h = (h - p * nums[i - size]) % MOD
                h = (h * base + nums[i]) % MOD
                seen[h] += 1
            return any(v == 1 for v in seen.values())

        n = len(nums)
        l = 1
        h = n
        while l < h:
            mid = l + (h - l) // 2
            if check(mid): h = mid
            else: l = mid + 1
        return h