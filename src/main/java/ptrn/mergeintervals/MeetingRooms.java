package ptrn.mergeintervals;

import java.util.Comparator;
import java.util.List;

//@link - https://neetcode.io/problems/meeting-schedule
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/6207468695977984
public class MeetingRooms {
    public boolean canAttendMeetings(List<Interval> intervals) {
        Comparator<Interval> cmp1 = (i1, i2) -> i1.start - i2.start;
        Comparator<Interval> cmp2 = (i1, i2) -> i1.end - i2.end;

        intervals.sort(cmp1.thenComparing(cmp2));
        for (int i=0; i<intervals.size()-1; i++) {
            if (intervals.get(i).end > intervals.get(i+1).start) return false;
        }

        return true;
    }
}
