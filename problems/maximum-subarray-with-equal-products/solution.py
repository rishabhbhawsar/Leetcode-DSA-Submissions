import math

class Solution:
    def maxLength(self, nums: list[int]) -> int:
        n = len(nums)
        overall_lcm = 1
        for x in nums:
            overall_lcm = math.lcm(overall_lcm, x)
        max_p = overall_lcm * max(nums)

        ans = 0
        for i in range(n):
            prod = 1
            g = 0
            l = 1
            for j in range(i, n):
                prod *= nums[j]
                g = math.gcd(g, nums[j])
                l = math.lcm(l, nums[j])
                if prod == g * l:
                    ans = max(ans, j - i + 1)
                if prod > max_p:
                    break
        return ans
