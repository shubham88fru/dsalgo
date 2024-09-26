package lc_potd;

import java.util.ArrayList;
import java.util.List;

//@link - https://leetcode.com/problems/my-calendar-i/description/
public class MyCalendarI {

    //my soln.
    //Mik did using sorted set or something. Check Mik's soln as well if needed.
    private final List<int[]> intervals = new ArrayList<>();

    public MyCalendarI() {}

    public boolean book(int start, int end) {
        if (intervals.size() == 0) {
            intervals.add(new int[] {start, end});
            return true;
        }

        //find an index where this interval fits.
        int i = binarySearch(start, end);

        if (i==0) {
            if ((end <= intervals.get(i)[0])) {
                insert(i, start, end);
                return true;
            }
        } else if (i == intervals.size()) {
            if ((start >= intervals.get(i-1)[1])) {
                insert(i, start, end);
                return true;
            }
        } else {
            if ((start >= intervals.get(i-1)[1]) && (end <= intervals.get(i)[0])) {
                insert(i, start, end);
                return true;
            }
        }

        return false;
    }

    private void insert(int i, int start, int end) {
        intervals.add(i, new int[] {start, end});
    }

    //Binary search to find the index where current interval can fit.
    private int binarySearch(int start, int end) {
        int l = 0;
        int r = intervals.size()-1;

        int idx = 0;
        while (l <= r) {
            int mid = l + (r-l)/2;
            if (intervals.get(mid)[0] == start) return mid+1;
            if (intervals.get(mid)[0] < start) {
                l = mid + 1;
                idx = mid+1;
            }
            else r = mid-1;
        }

        return idx;
    }
}
