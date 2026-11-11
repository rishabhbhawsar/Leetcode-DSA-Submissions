class Solution {
    public long minimumCost(int cost1, int cost2, int costBoth, int need1, int need2) {
        long c1 = cost1;
        long c2 = cost2;
        long cb = costBoth;
        long n1 = need1;
        long n2 = need2;

        // case 1: buy all required items separately
        long allSeparate = (n1 * c1) + (n2 * c2);

        // case 2: cover overlapping needs with bundles, then buy the rest individually 
        long overlap = Math.min(n1, n2);
        long mixed = (overlap * cb) + ((n1 - overlap) * c1) + ((n2 - overlap) * c2);

        // case 3: cover largest need entirely with bundles
        long allBundles = Math.max(n1, n2) * cb;

        return Math.min(allSeparate, Math.min(mixed, allBundles));

    }
}