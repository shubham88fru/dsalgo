package lc_potd;

import java.util.Arrays;
import java.util.Comparator;

//@link - https://leetcode.com/problems/minimum-initial-energy-to-finish-tasks/description/
//@check - https://www.youtube.com/watch?v=Bw0HySng9wo
public class MinimumInitialEnergyToFinishTasks {
    public int minimumEffort(int[][] tasks) {
        return mikssol(tasks);
    }

    /*
        Coded by me based on miks
        explanation.
     */
    private int mikssol(int[][] tasks) {
        int l = 0, r = (int)1e9; //10ˆ4*10ˆ5
        int ans = 0;

        int n = tasks.length;
        Comparator<int[]> cmp = (a1, a2) -> (a2[1] - a2[0]) - (a1[1] - a1[0]);
        Arrays.sort(tasks, cmp);

        while (l <= r) {
            int start = l + (r-l)/2;
            if (canFinish(tasks, start)) {
                ans = start;
                r = start - 1;
            } else l = start + 1;
        }

        return ans;
    }

    private boolean canFinish(int[][] tasks, int start) {

        for (int[] t: tasks) {
            int red = t[0];
            int min = t[1];
            if (start < min) return false;
            start -= red;
        }

        return true;
    }
}
