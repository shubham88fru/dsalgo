package lc_potd;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

//@link - https://leetcode.com/problems/maximum-candies-you-can-get-from-boxes/description/
//@check - https://www.youtube.com/watch?v=fGa9jczRS2o&ab_channel=codestorywithMIK
public class MaximumCandiesYouCanGetFromBoxes {
    public int maxCandies(int[] status, int[] candies, int[][] keys, int[][] containedBoxes, int[] initialBoxes) {
        return bfs(status, candies, keys, containedBoxes, initialBoxes);
    }

    /*
        Had 80% of it figured out just couldn't iron a bit of details.
        Took some hint from mik.
    */
    private int bfs(int[] status, int[] candies, int[][] keys, int[][] containedBoxes, int[] initialBoxes) {
        Deque<Integer> q = new ArrayDeque<>();
        int n = status.length;

        //note that per constraints, a box will atmost be contained
        //in one box, and therefore visited array might not be needed
        //however, this can be asked as a followup in an interview, so
        //writing this generic code.
        int[] visited = new int[n];

        int maxCandies = 0;
        Set<Integer> seenBoxes = new HashSet<>();
        for (int initial: initialBoxes) {
            seenBoxes.add(initial);
            if (status[initial] == 1) {
                visited[initial] = -1;
                maxCandies += candies[initial];
                q.addLast(initial);
            }
        }

        while (!q.isEmpty()) {
            int box = q.removeFirst();

            for (int cb: containedBoxes[box]) {
                seenBoxes.add(cb);
                if (status[cb]==1 && visited[cb] != -1) {
                    q.addLast(cb);
                    visited[cb] = -1;
                    maxCandies += candies[cb];
                }
            }

            //this was the part that I wasn't
            //able to piece together in an efficient way
            //so took hint from mik.
            for (int key: keys[box]) {
                status[key] = 1;
                if (seenBoxes.contains(key) && visited[key] != -1) {
                    visited[key] = -1;
                    q.addLast(key);
                    maxCandies += candies[key];
                }
            }
        }

        return maxCandies;
    }
}
