package lc_potd;

import java.util.PriorityQueue;

//@link - https://leetcode.com/problems/trapping-rain-water-ii/
//@check - https://www.youtube.com/watch?v=TzsbIDtTlsQ&t=3076s&ab_channel=codestorywithMIK
public class TrappingRainWaterII {
    public int trapRainWater(int[][] heightMap) {
        return miksapproach(heightMap);
    }

    /*
        Completely based on mik's explanation.
        Could only understand it partially. Didn't
        understandly thoroughly why this works.
        But the trick lied in picking the
        min height building everytime and running a dfs.
    */
    private int miksapproach(int[][] heightMap) {
        int m = heightMap.length;
        int n = heightMap[0].length;

        int[][] visited = new int[m][n];
        PriorityQueue<Data> minHeap = new PriorityQueue<>((d1, d2) -> d1.height - d2.height);

        //Put all bounderies in the heap. Water can never
        //accumulate on the boundaries.

        //top row.
        for (int j=0; j<n; j++) {
            minHeap.add(new Data(heightMap[0][j], 0, j));
            visited[0][j] = -1;
        }

        //left col.
        for (int i=0; i<m; i++) {
            minHeap.add(new Data(heightMap[i][0], i, 0));
            visited[i][0] = -1;
        }

        //right col.
        for (int i=0; i<m; i++) {
            minHeap.add(new Data(heightMap[i][n-1], i, n-1));
            visited[i][n-1] = -1;
        }

        //bottom row.
        for (int j=0; j<n; j++) {
            minHeap.add(new Data(heightMap[m-1][j], m-1, j));
            visited[m-1][j] = -1;
        }

        int water = 0;
        while (!minHeap.isEmpty()) {
            Data min = minHeap.remove();
            int[][] dirs = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
            for (int[] dir: dirs) {
                int nextI = min.i + dir[0];
                int nextJ = min.j + dir[1];

                if (nextI < 0 || nextI >= m || nextJ < 0 || nextJ >= n || visited[nextI][nextJ] == -1) {
                    continue;
                }

                water += Math.max((min.height - heightMap[nextI][nextJ]), 0);
                visited[nextI][nextJ] = -1;
                minHeap.add(new Data(Math.max(min.height,  heightMap[nextI][nextJ]), nextI, nextJ));
            }
        }

        return water;
    }
}

class Data {
    int height;
    int i;
    int j;

    public Data(int height, int i, int j) {
        this.height = height;
        this.i = i;
        this.j = j;
    }
}
