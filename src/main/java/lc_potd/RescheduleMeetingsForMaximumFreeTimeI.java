package lc_potd;

import java.util.ArrayList;
import java.util.List;

//@link - https://leetcode.com/problems/reschedule-meetings-for-maximum-free-time-i/description/
//@check - https://www.youtube.com/watch?v=JPWBTUyGCnM&t=717s&ab_channel=codestorywithMIK
public class RescheduleMeetingsForMaximumFreeTimeI {
    public int maxFreeTime(int eventTime, int k, int[] startTime, int[] endTime) {
        return mikssol(eventTime, k, startTime, endTime);
    }

    /*
        Coded by me based on hints from mik.
        Note that in this part of the problem, relative
        orders of the events can't be changed. Therefore,
        an event can only be 'shifted' left and right but it can
        'jump' across an event before or after it.
    */
    private int mikssol(int eventTime, int k, int[] startTime, int[] endTime) {
        List<Integer> gaps = new ArrayList<>();

        int n = startTime.length;
        int prevEnd = 0;
        for (int i=0; i<n; i++) {
            gaps.add(startTime[i]-prevEnd);
            prevEnd = endTime[i];
        }
        gaps.add(eventTime-prevEnd);


        int l = 0;
        int r = 0;
        int sum = 0;
        int maxSum = 0;
        while (r < gaps.size()) {
            while (r-l+1 <= k+1) {
                sum += gaps.get(r);
                r += 1;
                maxSum = Math.max(sum, maxSum);
            }

            sum -= gaps.get(l);
            l += 1;
        }

        return maxSum;
    }
}
