class Solution {
    long MOD = 1_000_000_007;
    long[] sum;
    long[] sq;
    long[] lazy;

    public int sumCounts(int[] nums) {
        int n = nums.length;

        sum = new long[4 * n];
        sq = new long[4 * n];
        lazy = new long[4 * n];

        int[] last = new int[100001];
        java.util.Arrays.fill(last, -1);

        long ans = 0;

        for (int i = 0; i < n; i++) {
            int x = nums[i];
            int prev = last[x];

            update(1, 0, n - 1, prev + 1, i);

            ans = (ans + sq[1]) % MOD;

            last[x] = i;
        }
        return (int) ans;
    }

    private void apply(int node, int l, int r, long v) {
        long len = r - l + 1;
        // sum of squares: sq = sq + 2 * sum * v + len * v^2
        sq[node] = (sq[node] + 2 * sum[node] * v + len * v * v) % MOD;
        // sum of counts: sum = sum + len * v
        sum[node] = (sum[node] + len * v) % MOD;
        // lazy tag
        lazy[node] = (lazy[node] + v) % MOD;
    }

    private void push(int node, int l, int r) {
        if (lazy[node] != 0) {
            int mid = l + (r - l) / 2;
            apply(node * 2, l, mid, lazy[node]);
            apply(node * 2 + 1, mid + 1, r, lazy[node]);
            lazy[node] = 0;
        }
    }

    private void update(int node, int l, int r, int ql, int qr) {
        if (ql > r || qr < l) return;
        if (ql <= l && r <= qr) {
            apply(node, l, r, 1);
            return;
        }
        push(node, l, r);
        int mid = l + (r - l) / 2;
        update(node * 2, l, mid, ql, qr);
        update(node * 2 + 1, mid + 1, r, ql, qr);
        sum[node] = (sum[node * 2] + sum[node * 2 + 1]) % MOD;
        sq[node] = (sq[node * 2] + sq[node * 2 + 1]) % MOD;
    }
}