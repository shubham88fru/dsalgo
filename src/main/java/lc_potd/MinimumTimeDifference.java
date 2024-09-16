package lc_potd;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

//@link - https://leetcode.com/problems/minimum-time-difference/description/
public class MinimumTimeDifference {
    //my soln.
    public int findMinDifference(List<String> timePoints) {
        Comparator<String> cmp = (s1, s2) -> {
            if (s1.equals(s2)) return 0;

            for (int i=0; i<s1.length(); i++) {
                if (s1.charAt(i)==':') continue;

                int e1 = Character.getNumericValue(s1.charAt(i));
                int e2 = Character.getNumericValue(s2.charAt(i));
                if (e1>e2) return 1;
                else if (e1<e2) return -1;

            }

            return 0;
        };

        Collections.sort(timePoints, cmp);

        int minDiff = Integer.MAX_VALUE;
        for (int i=0; i<timePoints.size()-1; i++) {
            //once sorted, we are guaranteed that the min difference will be between
            //adjacent times.
            minDiff = Math.min(minDiff, diff(timePoints.get(i), timePoints.get(i+1)));
        }

        //we also calculate the diff between the last and the first time.
        //to handle a sorted array like - [00:00, 06:35, 13:45, 23:59] etc.
        minDiff = Math.min(minDiff, diff(timePoints.get(timePoints.size()-1), timePoints.get(0)));

        return minDiff;
    }

    private int diff(String t1, String t2) {
        String[] t1s = t1.split(":");
        String[] t2s = t2.split(":");

        //string 1 to hours and minutes.
        int t1h = Integer.parseInt(t1s[0]);
        int t1m = Integer.parseInt(t1s[1]);

        //string 2 to hours and minutes.
        int t2h = Integer.parseInt(t2s[0]);
        int t2m = Integer.parseInt(t2s[1]);

        int min1 = 0;
        int min2 = 0;

        //covert to minutes. 24h = 60mins.
        min1 = (t1h*60)+t1m;
        min2 = (t2h*60)+t2m;

        //This is important.
        //We calculate two differences -
        //First a simple difference between t1 and t2.
        //And second, we add 24h worth of minutes to t2 and calculate the the difference with t2.
        //And finally, take the minimum of the two differences.
        //This is important to handle cases where t2 is say 22:35 and t1 is 00:35.
        return Math.min(Math.abs(min1-min2), Math.abs(min1-(((24+t2h)*60)+t2m)));
    }
}
