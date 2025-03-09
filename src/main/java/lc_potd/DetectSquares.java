package lc_potd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@link - https://leetcode.com/problems/detect-squares/
//@check - https://www.youtube.com/watch?v=bahebearrDc&ab_channel=NeetCode
public class DetectSquares {
    /*
        Code by me but completely based on nc's approach.
        I was trying something really naive.
     */
    Map<String, Integer> mp = new HashMap<>();
    List<int[]> lst = new ArrayList<>();
    public DetectSquares() {

    }

    public void add(int[] point) {
        String key = point[0] + "_" + point[1];
        mp.put(key, mp.getOrDefault(key, 0) + 1);
        lst.add(point);
    }

    public int count(int[] point) {
        int px = point[0];
        int py = point[1];
        int count = 0;

        for (int[] entry: lst) {
            int x = entry[0];
            int y = entry[1];

            /*
                Key observation without which this problem is very
                difficult to solve. Find a valid diagonal element to
                the given point and then check if the diagonal has valid
                other two corners.
             */
            if ((Math.abs(px-x) != Math.abs(py-y)) || px == x || py == y) continue;

            count += (mp.getOrDefault((x+"_"+py), 0) * mp.getOrDefault((px+"_"+y), 0));
        }

        return count;
    }
}

/**
 * Your DetectSquares object will be instantiated and called as such:
 * DetectSquares obj = new DetectSquares();
 * obj.add(point);
 * int param_2 = obj.count(point);
 */

