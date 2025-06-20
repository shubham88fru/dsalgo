package lc_potd;

import java.util.HashMap;
import java.util.Map;

//@link - https://leetcode.com/problems/maximum-manhattan-distance-after-k-changes/
public class MaximumManhattanDistanceAfterKChanges {
    public int maxDistance(String s, int k) {
        // return pass1(s, k);
        return pass2(s, k);
    }

    /*
        Brute force.
        Coded by me, based on hints from LC.
        Mik showed optimal soln. check if this is a recurring problem.
    * */
    private int pass2(String s, int k) {
        int n = s.length();

        int ne = ne(s, k);
        int nw = nw(s, k);
        int se = se(s, k);
        int sw = sw(s, k);

        return Math.max(ne, Math.max(nw, Math.max(se, sw)));
    }

    private int ne(String s, int k) {
        int n = s.length();
        int maxi = 1;

        int x = 0;
        int y = 0;
        for (int i=0; i<n; i++) {
            char ch = s.charAt(i);
            if (ch == 'N') {
                y += 1;
            } else if (ch == 'E') {
                x += 1;
            } else if (ch == 'S') {
                if (k > 0) {
                    k -= 1;
                    y += 1;
                } else y -= 1;
            } else if (ch == 'W') {
                if (k > 0) {
                    k -= 1;
                    x += 1;
                } else x -= 1;
            }
            maxi = Math.max(maxi, Math.abs(x) + Math.abs(y));
        }

        return maxi;
    }

    private int nw(String s, int k) {
        int n = s.length();
        int maxi = 1;

        int x = 0;
        int y = 0;
        for (int i=0; i<n; i++) {
            char ch = s.charAt(i);
            if (ch == 'N') {
                y += 1;
            } else if (ch == 'W') {
                x += 1;
            } else if (ch == 'S') {
                if (k > 0) {
                    k -= 1;
                    y += 1;
                } else y -= 1;
            } else if (ch == 'E') {
                if (k > 0) {
                    k -= 1;
                    x += 1;
                } else x -= 1;
            }
            maxi = Math.max(maxi, Math.abs(x) + Math.abs(y));
        }

        return maxi;
    }

    private int se(String s, int k) {
        int n = s.length();
        int maxi = 1;

        int x = 0;
        int y = 0;
        for (int i=0; i<n; i++) {
            char ch = s.charAt(i);
            if (ch == 'S') {
                y += 1;
            } else if (ch == 'E') {
                x += 1;
            } else if (ch == 'N') {
                if (k > 0) {
                    k -= 1;
                    y += 1;
                } else y -= 1;
            } else if (ch == 'W') {
                if (k > 0) {
                    k -= 1;
                    x += 1;
                } else x -= 1;
            }
            maxi = Math.max(maxi, Math.abs(x) + Math.abs(y));
        }

        return maxi;
    }

    private int sw(String s, int k) {
        int n = s.length();
        int maxi = 1;

        int x = 0;
        int y = 0;
        for (int i=0; i<n; i++) {
            char ch = s.charAt(i);
            if (ch == 'S') {
                y += 1;
            } else if (ch == 'W') {
                x += 1;
            } else if (ch == 'N') {
                if (k > 0) {
                    k -= 1;
                    y += 1;
                } else y -= 1;
            } else if (ch == 'E') {
                if (k > 0) {
                    k -= 1;
                    x += 1;
                } else x -= 1;
            }
            maxi = Math.max(maxi, Math.abs(x) + Math.abs(y));
        }

        return maxi;
    }

    /*
        Doesn't work.
        My initial intuition.
    */
    private int pass1(String s, int k) {
        int n = s.length();

        Map<Character, int[]> dirs = new HashMap<>();
        dirs.put('N', new int[]{0, 1});
        dirs.put('S', new int[]{0, -1});
        dirs.put('E', new int[]{1, 0});
        dirs.put('W', new int[]{-1, 0});
        int x = dirs.get(s.charAt(0))[0];
        int y = dirs.get(s.charAt(0))[1];
        int maxi = Integer.MIN_VALUE;

        for (int i=1; i<n; i++) {
            int[] xy = dirs.get(s.charAt(i));
            int x_ = xy[0];
            int y_ = xy[1];

            if (k > 0) {
                if (Math.abs(x + x_) < Math.abs(x)) {
                    k -= 1;
                    x += (-1*x_);
                } else {
                    x += x_;
                }

                if (Math.abs(y + y_) < Math.abs(y)) {
                    k -= 1;
                    y += (-1*y_);
                } else {
                    y += y_;
                }
            } else {
                x += x_;
                y += y_;
            }

            maxi = Math.max(maxi, Math.abs(x) + Math.abs(y));
        }

        return maxi;
    }
}
