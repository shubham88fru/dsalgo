package lc_potd;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//@link - https://leetcode.com/problems/check-if-grid-can-be-cut-into-sections/
//@check - https://www.youtube.com/watch?v=9rCmrMed8h8&t=1501s&ab_channel=codestorywithMIK
public class CheckIfGridCanBeCutIntoSections {
    public boolean checkValidCuts(int n, int[][] rectangles) {
        return pass1(n, rectangles);
    }

    /*
        Coded based on my intuition.
        Mik had the same intuition and explanation.
    */
    private boolean pass1(int n, int[][] rectangles) {
        List<int[]> xIntervals = new ArrayList<>();
        List<int[]> yIntervals = new ArrayList<>();

        for (int[] rectangle: rectangles) {
            int x1 = rectangle[0];
            int y1 = rectangle[1];
            int x2 = rectangle[2];
            int y2 = rectangle[3];

            xIntervals.add(new int[] {x1, x2});
            yIntervals.add(new int[] {y1, y2});
        }

        List<int[]> xMerged = mergeIntervals(xIntervals);
        if (xMerged.size() >= 3) return true;

        List<int[]> yMerged = mergeIntervals(yIntervals);
        return yMerged.size() >= 3;
    }

    private List<int[]> mergeIntervals(List<int[]> intervals) {
        Collections.sort(intervals, (i1, i2) -> i1[0] - i2[0]);
        List<int[]> merged = new ArrayList<>();

        for (int i=0; i<intervals.size(); i++) {
            if (merged.size() == 0 || intervals.get(i)[0] >= merged.get(merged.size()-1)[1]) {
                merged.add(intervals.get(i));
            } else {
                merged.get(merged.size()-1)[1] = Math.max(merged.get(merged.size()-1)[1], intervals.get(i)[1]);
            }
        }

        return merged;
    }
}
