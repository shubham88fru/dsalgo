package lc_potd;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

//@link - https://leetcode.com/problems/meeting-rooms-iii/description/
public class MeetingRoomsIII {
    public int mostBooked(int n, int[][] meetings) {
        return pass1(n, meetings);
    }

    private int pass1(int n, int[][] meetings) {
        int[] rooms = new int[n];

        Comparator<int[]> cmp1 = (a1, a2) -> a1[1] - a2[1];
        Comparator<int[]> cmp2 = (a1, a2) -> a1[0] - a2[0];
        Arrays.sort(meetings, cmp2);
        PriorityQueue<int[]> q = new PriorityQueue<>(cmp1.thenComparing(cmp2));
        PriorityQueue<Integer> availableRooms = new PriorityQueue<>();
        for (int i=0; i<n; i++) {
            availableRooms.add(i);
        }

        int maxr = Integer.MIN_VALUE;
        int maxi = -1;
        for (int[] mtg: meetings) {
            int start = mtg[0];
            int end = mtg[1];

            while (!q.isEmpty() && start >= q.peek()[1]) {
                availableRooms.add(q.remove()[0]);
            }

            if (q.isEmpty() || !availableRooms.isEmpty()) {
                int aroom = availableRooms.remove();
                q.add(new int[]{aroom, end});
                rooms[aroom] += 1;
                maxr = Math.max(maxr, rooms[aroom]);
            } else {
                int[] rem = q.remove();
                q.add(new int[] {rem[0], end + (rem[1] - start)});
                rooms[rem[0]] += 1;
                maxr = Math.max(maxr, rooms[rem[0]]);
            }

        }

        int cnt = 0;
        for (int i=0; i<n; i++) {
            if (rooms[i] == maxr) return i;
        }

        return -1;
    }
}
