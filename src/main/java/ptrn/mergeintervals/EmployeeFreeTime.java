package ptrn.mergeintervals;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

//@link1 - https://leetcode.com/problems/employee-free-time/description/
//@link2 - https://www.lintcode.com/problem/850/note/234881
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/4817194682417152
//@tag - TO_REVISIT
/***
 * Directly copied from edctv. Not explained very properly
 * Need to re-visit.
 */
public class EmployeeFreeTime {
    public static List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[0] - b[0]);

        // Iterate for all employees' schedules
        // and add start of each schedule's first interval along with
        // its index value and a value 0.
        for (int i = 0; i < schedule.size(); i++) {
            List<Interval> employeeSchedule = schedule.get(i);
            Interval interval = employeeSchedule.get(0);
            heap.offer(new int[]{interval.start, i, 0});
        }

        // Take an empty list to store results.
        List<Interval> result = new ArrayList<>();

        // Set 'previous' to the start time of the first interval in heap.
        int previous = schedule.get(heap.peek()[1]).get(heap.peek()[2]).start;

        // Iterate until the heap is empty
        while (!heap.isEmpty()) {
            // Poll an element from the heap and get values of i and j
            int[] tuple = heap.poll();
            int i = tuple[1];
            int j = tuple[2];

            // Select an interval
            Interval interval = schedule.get(i).get(j);

            // If the selected interval's start value is greater than the previous value,
            // it means that this interval is free. So, add this interval
            // (previous, interval's end value) into the result.
            if (interval.start > previous) {
                result.add(new Interval(previous, interval.start));
            }

            // Update the previous as the maximum of previous and interval's end value.
            previous = Math.max(previous, interval.end);

            // If there is another interval in the current employee's schedule,
            // push that into the heap.
            if (j + 1 < schedule.get(i).size()) {
                Interval nextInterval = schedule.get(i).get(j + 1);
                heap.offer(new int[]{nextInterval.start, i, j + 1});
            }
        }

        // When the heap is empty, return the result.
        return result;
    }
}

class Interval{
    int start;
    int end;
    boolean closed;

    public Interval(int start, int end)
    {
        this.start = start;
        this.end = end;
        this.closed = true; // by default, the interval is closed
    }

    // set the flag for closed/open
    public void setClosed(boolean closed)
    {
        this.closed = closed;
    }
}