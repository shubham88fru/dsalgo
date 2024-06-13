package lc_potd;

import java.util.Arrays;

//@link - https://leetcode.com/problems/minimum-number-of-moves-to-seat-everyone/
public class MinimumNumberOfMovesToSeatEveryone {
    public int minMovesToSeat(int[] seats, int[] students) {
        Arrays.sort(seats);
        Arrays.sort(students);
        int moves = 0;
        for (int i=0; i<students.length; i++) {
            moves += Math.abs((students[i]-seats[i]));
        }

        return moves;
    }
}
