package lc_potd;

import java.util.Arrays;

//@link - https://leetcode.com/problems/count-days-without-meetings/description/
//@check - https://www.youtube.com/watch?v=6-GbDuhOOdc&t=925s&ab_channel=codestorywithMIK
public class CountDaysWithoutMeetings {
    public int countDays(int days, int[][] meetings) {
        return pass1(days, meetings);
    }

    /*
        I was in the right direction, but my
        intuition was to sort and then perform
        merge. However, with a bit of hint from
        mik, I realized that we don't really need
        to perform merge. Sort should be enough.

        Coded by me.
    */
    private int pass1(int days, int[][] meetings) {
        Arrays.sort(meetings, (m1, m2) -> m1[0] - m2[0]);

        int n = meetings.length;
        int prevEnd = 0;

        int available = 0;
        for (int i=0; i<n; i++) {
            int currStart = meetings[i][0];
            if (currStart > days) {
                available += (days - prevEnd);
                break;
            } else if (currStart > prevEnd) {
                available += (currStart - prevEnd - 1);
            }

            //[(1, 5), (2, 4)] --> prev end should be 5 not 4.
            prevEnd = Math.max(meetings[i][1], prevEnd); //tricky.
        }

        if (days > prevEnd) available += (days - prevEnd);

        return available;
    }
}
