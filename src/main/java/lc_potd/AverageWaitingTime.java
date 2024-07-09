package lc_potd;

//@link - https://leetcode.com/problems/average-waiting-time/
public class AverageWaitingTime {
    public double averageWaitingTime(int[][] customers) {
        long totalWaitTime = 0;
        long prevEnd = 0;

        totalWaitTime = customers[0][1];
        prevEnd = customers[0][0] + customers[0][1];

        for (int i=1; i<customers.length; i++) {
            long arrival = customers[i][0];
            long currEndTime = 0;
            if (arrival <= prevEnd) {
                currEndTime = prevEnd + customers[i][1];
            } else {
                currEndTime = arrival + customers[i][1];
            }
            totalWaitTime += (currEndTime - arrival);
            prevEnd = currEndTime;
        }

        return totalWaitTime/(double)(customers.length);
    }
}
