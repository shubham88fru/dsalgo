package strvr.greedy;

import java.util.ArrayList;
import java.util.Comparator;

//@link - https://practice.geeksforgeeks.org/problems/n-meetings-in-one-room-1587115620/1
//@strvr - https://takeuforward.org/data-structure/n-meetings-in-one-room/
public class NMeetingsInOneRoom {
    public static int maxMeetings(int start[], int end[], int n) {
        return maxMeetingsOptimal(start, end, n);
    }

    //T: O(NlogN), S: O(N)
    private static int maxMeetingsOptimal(int[] start, int[] end, int n) {
        if (start.length == 0) return 0;
        //max meetings can be performed if we sort the ending times
        //and start with the earliest meetings.
        ArrayList<Meeting> meetings = new ArrayList<>();
        for (int i=0; i<start.length; i++) {
            meetings.add(new Meeting(start[i], end[i], i));
        }

        //first sort by end time, and if same, sort by index in orig array.
        Comparator<Meeting> endTimeComparator = (o1, o2) -> o1.end-o2.end; //can be replaced with `Comparator.comparingInt(o -> o.end)`;
        meetings.sort(endTimeComparator.thenComparing((o1, o2) -> o1.idx - o2.idx)); //can be replaced with `Comparator.comparingInt(o -> o.idx)`;

        int count = 1;
        int endTime = meetings.get(0).end;
        for (Meeting meeting: meetings) {
            if (meeting.start > endTime) {
                endTime = meeting.end;
                count += 1;
            }
        }

        return count;

    }

    static class Meeting {
        int start;
        int end;
        int idx;

        public Meeting(int start, int end, int idx) {
            this.start = start;
            this.end = end;
            this.idx = idx;
        }
    }
}
