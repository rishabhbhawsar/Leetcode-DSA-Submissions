class Solution {
    private List<Integer>[] adj;
    private int[] parent, depth, heavy, head, pos;
    private int[] tree;
    private int curPos;
    private char[] nodeChars;
    private int n;

    public List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {
        this.n = n;
        this.nodeChars = s.toCharArray();
        this.adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            adj[edge[0]].add(edge[1]);
            adj[edge[1]].add(edge[0]);
        }

        parent = new int[n];
        depth = new int[n];
        heavy = new int[n];
        head = new int[n];
        pos = new int[n];
        Arrays.fill(heavy, -1);

        parent[0] = -1;
        dfs1(0, 0);
        curPos = 0;
        dfs2(0, 0);

        revPos = new int[n];
        for (int i = 0; i < n; i++) {
            revPos[pos[i]] = i;
        }

        tree = new int[4 * n];
        build(1, 0, n - 1);

        List<Boolean> ansList = new ArrayList<>();

        for (String qStr : queries) {
            String[] parts = qStr.trim().split("\\s+");
            String cmd = parts[0];

            if (cmd.equals("1") || (cmd.equals("update"))) {
                int u = Integer.parseInt(parts[1]);
                char newChar;
                
                if (Character.isDigit(parts[2].charAt(0))) {
                    newChar = (char) ('a' + Integer.parseInt(parts[2]));
                } else {
                    newChar = parts[2].charAt(0);
                }
                nodeChars[u] = newChar;

                update(1, 0, n - 1, pos[u], 1 << (newChar - 'a'));
            } else {
                int u = Integer.parseInt(parts[1]);
                int v = Integer.parseInt(parts[2]);
                int pathMask = queryPath(u, v);
                ansList.add(isPalindrome(pathMask));
            }
        }

        return ansList;
    }

    private int dfs1(int curr, int p) {
        int size = 1;
        int maxSubTreeSize = 0;
        
        for (int neighb : adj[curr]) {
            if (neighb != p) {
                parent[neighb] = curr;
                depth[neighb] = depth[curr] + 1;
                int subSize = dfs1(neighb, curr);
                size += subSize;

                if (subSize > maxSubTreeSize) {
                    maxSubTreeSize = subSize;
                    heavy[curr] = neighb;
                }
            }
        }
        return size;
    }

    private void dfs2(int curr, int h) {
        head[curr] = h;
        pos[curr] = curPos++;

        if (heavy[curr] != -1) {
            dfs2(heavy[curr], h);
        }

        for (int neighb : adj[curr]) {

            if (neighb != parent[curr] && neighb != heavy[curr]) {
                dfs2(neighb, neighb);
            }
        }
    }

    private void build(int node, int start, int end) {
        if (start == end) {
            int u = revPos[start];
            tree[node] = 1 << (nodeChars[u] - 'a');
            return;
        }
        int mid = (start + end) / 2;
        build(2 * node, start, mid);
        build(2 * node + 1, mid + 1, end);
        tree[node] = tree[2 * node] ^ tree[2 * node + 1];
    }

    private int[] revPos = null;

    private void update(int node, int start, int end, int idx, int val) {
        if (start == end) {
            tree[node] = val;
            return;
        }
        int mid = (start + end) / 2;
        if (idx >= start && idx <= mid) {
            update(2 * node, start, mid, idx, val);
        } else {
            update(2 * node + 1, mid + 1, end, idx, val);
        }
        tree[node] = tree[2 * node] ^ tree[2 * node + 1];
    }

    private int querySegment(int node, int start, int end, int l, int r) {

        if (r < start || end < l) return 0;
        if (l <= start && end <= r) return tree[node];
        int mid = (start + end) / 2;
        return querySegment(2 * node, start, mid, l, r) ^ querySegment(2 * node + 1, mid + 1, end, l, r);
    }

    private int queryPath(int u, int v) {
        int res = 0;

        while (head[u] != head[v]) {

            if (depth[head[u]] > depth[head[v]]) {
                int temp = u;
                u = v;
                v = temp;
            }

            res ^= querySegment(1, 0, n - 1, pos[head[v]], pos[v]);
            v = parent[head[v]];
        }
        if (depth[u] > depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }
        res ^= querySegment(1, 0, n - 1, pos[u], pos[v]);
        return res;
    }

    private boolean isPalindrome(int mask) {
        return mask == 0 || (mask & (mask - 1)) == 0;
    }
}