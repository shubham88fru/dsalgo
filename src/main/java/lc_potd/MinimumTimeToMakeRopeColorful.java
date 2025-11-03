package lc_potd;

//@link - https://leetcode.com/problems/minimum-time-to-make-rope-colorful/?
public class MinimumTimeToMakeRopeColorful {
    public int minCost(String colors, int[] neededTime) {
        return pass1(colors, neededTime);
    }

    /**
     My greedy soln.
     */
    private int pass1(String colors, int[] neededTime) {
        int n = neededTime.length;

        char pc = 'A';
        int pi = -1;

        int minTime = 0;
        for (int i=0; i<n; i++) {
            char ch = colors.charAt(i);
            if (ch == pc) {
                minTime += Math.min(neededTime[i], neededTime[pi]);
                if (neededTime[i] >= neededTime[pi]) {
                    pi = i;
                    pc = ch;
                }
            } else {
                pi = i;
                pc = ch;
            }
        }

        return minTime;
    }
}
