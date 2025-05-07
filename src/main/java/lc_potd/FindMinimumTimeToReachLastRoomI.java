package lc_potd;

import java.util.PriorityQueue;

//@link - https://leetcode.com/problems/find-minimum-time-to-reach-last-room-i/
public class FindMinimumTimeToReachLastRoomI {
    private int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public int minTimeToReach(int[][] moveTime) {
        return pass1(moveTime);
    }

    /*
    * My soln.
    * */
    private int pass1(int[][] moveTime) {

        int n = moveTime.length;
        int m = moveTime[0].length;

        PriorityQueue<int[]> pq = new PriorityQueue<>(
                (a1, a2) -> a1[0] - a2[0]
        );

        int[][] minTime = new int[n][m];

        for (int i=0; i<n; i++) {
            for (int j=0; j<m; j++) {
                minTime[i][j] = Integer.MAX_VALUE;
            }
        }

        pq.add(new int[] {0, 0, 0});
        minTime[0][0] = 0; //@t=0 starts at (0,0)

        while (!pq.isEmpty()) {
            int[] data = pq.remove();
            int i = data[1];
            int j = data[2];

            for (int[] dir: dirs) {
                int nextI = i + dir[0];
                int nextJ = j + dir[1];

                if (nextI < 0 || nextI >= n || nextJ < 0 || nextJ >= m) continue;

                if (minTime[nextI][nextJ] > (data[0] + 1)) {
                    minTime[nextI][nextJ] = Math.max(moveTime[nextI][nextJ]+1, data[0] + 1);
                    pq.add(new int[]{minTime[nextI][nextJ], nextI, nextJ});
                }
            }
        }

        return minTime[n-1][m-1];
    }
}
