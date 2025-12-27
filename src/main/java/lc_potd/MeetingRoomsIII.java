package lc_potd;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

//@link - https://leetcode.com/problems/meeting-rooms-iii/description/
public class MeetingRoomsIII {
    public int mostBooked(int n, int[][] meetings) {
        // return wrong(n, meetings);
        return correct(n, meetings);
    }

    /**
     Following up on the wrong approach below, a slight
     change will fix it. Instead of blindly scheduling the
     meeting on the room with earliest end time, we'll
     keep a track of the rooms that are available at any given time.
     */
    private int correct(int n, int[][] meetings) {
        Comparator<int[]> cmp1 = (a1, a2) -> a1[1] - a2[1];
        Comparator<int[]> cmp2 = (a1, a2) -> a1[0] - a2[0];

        Arrays.sort(meetings, cmp2);

        PriorityQueue<int[]> busy = new PriorityQueue<>(cmp1.thenComparing(cmp2));
        PriorityQueue<Integer> available = new PriorityQueue<>();

        int[] rooms = new int[n];
        for (int i=0; i<n; i++) available.add(i); //earliest index first

        for (int[] meeting: meetings) {

            while (!busy.isEmpty() && meeting[0] >= busy.peek()[1]) {
                available.add(busy.remove()[0]);
            }

            if (!available.isEmpty()) {
                int room = available.remove();
                rooms[room] += 1;
                busy.add(new int[] {room, meeting[1]});
            } else {
                int[] room = busy.remove();
                room[1] = meeting[1] + (room[1] - meeting[0]);
                rooms[room[0]] += 1;
                busy.add(room);
            }
        }

        int maxMeeting = Integer.MIN_VALUE;
        int maxi = -1;
        for (int i=0; i<n; i++) {
            if (rooms[i] > maxMeeting) {
                maxMeeting = rooms[i];
                maxi = i;
            }
        }

        return maxi;
    }

    /**
     This problem seems straightforward at first using a min heap.
     However, the following approach will fail.
     The catch is, when a meeting arrives, we can't blindly
     look at the min heap and schedule it in the room that had
     the earliest end time. It is possible that when the meeting
     is scheduled to start, by that time many other rooms would have
     become vacant (even though they didn't necessarily had the earliest
     end time for the meeting scheduled on them)
     e.g. n=50, meetings = [[18,19],[3,12],[17,19],[2,13],[7,10]]
     expected = 0
     actual = 2
     */
    private int wrong(int n, int[][] meetings) {
        Comparator<int[]> cmp1 = (a1, a2) -> a1[1] - a2[1];
        Comparator<int[]> cmp2 = (a1, a2) -> a1[0] - a2[0];
        PriorityQueue<int[]> pq = new PriorityQueue<>(cmp1.thenComparing(cmp2));

        int[] rooms = new int[n];
        for (int i=0; i<n; i++) pq.add(new int[]{i, 0});

        Arrays.sort(meetings, cmp2);
        for (int[] meeting: meetings) {
            int[] room = pq.remove();
            if (meeting[0] >= room[1]) {
                room[1] = meeting[1];
            } else {
                room[1] = meeting[1] + (room[1] - meeting[0]);
            }
            rooms[room[0]] += 1;
            pq.add(room);
        }

        int maxMeeting = Integer.MIN_VALUE;
        int maxi = -1;
        for (int i=0; i<n; i++) {
            if (rooms[i] > maxMeeting) {
                maxMeeting = rooms[i];
                maxi = i;
            }
        }

        return maxi;
    }
}
