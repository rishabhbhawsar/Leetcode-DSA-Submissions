class Solution {
    public static int[] days = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    public int parse(String date) {
        String[] parts = date.split("-");
        int m = Integer.parseInt(parts[0]), d = Integer.parseInt(parts[1]);
        int res = 0;

        for (int i = 1; i < m; i++) {
            res += days[i];
        }
        res += d;
        return res;
    }
    public int countDaysTogether(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
        int startA = parse(arriveAlice);
        int endA = parse(leaveAlice);
        int startB = parse(arriveBob);
        int endB = parse(leaveBob);

        int overlap = Math.min(endB, endA) - Math.max(startB, startA) + 1;

        return overlap>0? overlap : 0;
    }
}