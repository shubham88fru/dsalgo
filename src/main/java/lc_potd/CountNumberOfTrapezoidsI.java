package lc_potd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@link - https://leetcode.com/problems/count-number-of-trapezoids-i/?
public class CountNumberOfTrapezoidsI {
    public int countTrapezoids(int[][] points) {
        // return pass1(points);
        return pass2(points);
    }

    private int pass2(int[][] points) {
        int n = points.length;

        Map<Integer, Integer> yM = new HashMap<>();
        for (int[] point: points) {
            int y = point[1];
            yM.put(y, yM.getOrDefault(y, 0)+1);
        }

        List<Integer> vals = new ArrayList<>(yM.values());
        long ways = 0;
        long horizLines = 0;
        for (int i=0; i<vals.size(); i++) {
            long f = nC2(vals.get(i));
            ways = (ways + (f*horizLines))%1000000007;
            horizLines = (horizLines + f)%1000000007;
        }

        return (int)ways;
    }

    private int pass1(int[][] points) {
        int n = points.length;

        Map<Integer, Integer> yM = new HashMap<>();
        for (int[] point: points) {
            int y = point[1];
            yM.put(y, yM.getOrDefault(y, 0)+1);
        }

        List<Integer> vals = new ArrayList<>(yM.values());
        long ways = 0;
        for (int i=0; i<vals.size(); i++) {
            for (int j=i+1; j<vals.size(); j++) {
                long f = nC2(vals.get(i));
                long s = nC2(vals.get(j));
                ways = (ways + (f*s))%1000000007;
            }
        }

        return (int)ways;
    }

    private long nC2(int n) {
        if (n < 2) return 0;
        return (long) n * (n - 1) / 2;
    }
}
