package lc_potd;

import java.util.Comparator;
import java.util.PriorityQueue;

//@link - https://leetcode.com/problems/maximum-average-pass-ratio/description/
public class MaximumAveragePassRatio {
    public double maxAverageRatio(int[][] classes, int extraStudents) {
        return pass1(classes, extraStudents);
    }

    /**
     * My soln. Mik's soln was pretty much on the same
     * lines.
     *
     * The problem description is a bit confusing though.
     * At first, it felt that all the `extraStudents` need
     * to be assigned to the same class, which is not the case.
     */
    private double pass1(int[][] classes, int extraStudents) {
        Comparator<int[]> cmp = (a1, a2) -> Double.compare(
                ((((a2[0]+1)*1.0)/(a2[1]+1)) - ((a2[0]*1.0)/a2[1])), ((((a1[0]+1)*1.0)/(a1[1]+1)) - ((a1[0]*1.0)/a1[1]))
        );
        PriorityQueue<int[]> heap = new PriorityQueue<>(cmp);

        double sameSum = 0.0;
        for (int[] clazz: classes) {
            heap.add(clazz);
        }

        while (extraStudents > 0) {
            int[] maxIncrease = heap.remove();
            heap.add(new int[] { maxIncrease[0] + 1, maxIncrease[1] + 1});
            extraStudents -= 1;
        }

        double rsum = 0.0;
        while (!heap.isEmpty()) {
            int[] rem = heap.remove();
            rsum += (rem[0]/(rem[1]*1.0));
        }

        return (rsum)/classes.length;
    }
}
