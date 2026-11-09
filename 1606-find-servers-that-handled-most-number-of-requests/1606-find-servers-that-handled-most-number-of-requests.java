class Solution {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        // available servers
        TreeSet<Integer> available = new TreeSet<>();

        for (int i = 0; i < k; i++) {
            available.add(i);
        }

        // busy servers : [finishTime, serverIndex]
        PriorityQueue<int[]> busy = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));

        // number of requests handled by user
        int[] count = new int[k];

        for (int i = 0; i < arrival.length; i++) {
            // release servers whose requests have finished

            while (!busy.isEmpty() && busy.peek()[0] <= arrival[i]) {
                int[] server = busy.poll();
                available.add(server[1]);
            }

            if (available.isEmpty()) {
                continue;
            }

            // preferred server
            int target = i % k;

            // find next available server
            Integer server = available.ceiling(target);

            if (server == null) {
                server = available.first();
            }

            // assign request
            available.remove(server);
            count[server]++;

            // calculate request completion time
            busy.offer(new int[] {arrival[i] + load[i], server});

        }
        // find maximum requests handled
        int max = 0;
        for (int c : count) {
            max = Math.max(c, max);
        }

        // collect all busiest servers
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            if (count[i] == max) {
                result.add(i);
            }
        } 
        return result;
    }
}