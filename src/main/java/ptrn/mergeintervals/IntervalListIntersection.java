package ptrn.mergeintervals;

import java.util.ArrayList;
import java.util.List;

//@link - https://leetcode.com/problems/interval-list-intersections/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/6282076304441344
public class IntervalListIntersection {
    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        return findIntersection(firstList, secondList);
    }

    //T: O(M+N), S: O(1)
    private int[][] findIntersection(int[][] firstList, int[][] secondList) {
        int f = 0;
        int s = 0;
        int fl = firstList.length;
        int sl = secondList.length;
        List<int[]> intersections = new ArrayList<>();

        while (f < fl && s < sl) {
            /** May need to visit multiple times to wrap my head. **/
            //out of f and s, whichever starts the most later.
            int start = Math.max(firstList[f][0], secondList[s][0]);

            //out of f and s, whichever ends the most earlier.
            int end = Math.min(firstList[f][1], secondList[s][1]);

            if (start <= end) {
                intersections.add(new int[]{start, end});
            }

            //move ahead in the list that ends ealier.
            if (firstList[f][1] < secondList[s][1]) f += 1;
            else s += 1;
        }

        return intersections.toArray(new int[0][0]);
    }
}
