package lc_potd;

import java.util.Arrays;

//@link - https://leetcode.com/problems/maximum-matching-of-players-with-trainers/
public class MaximumMatchingOfPlayersWithTrainers {
    public int matchPlayersAndTrainers(int[] players, int[] trainers) {
        return pass1(players, trainers);
    }

    private int pass1(int[] players, int[] trainers) {
        int p = players.length;
        int t = trainers.length;

        Arrays.sort(players);
        Arrays.sort(trainers);

        int count = 0;
        int i = 0;
        int j = 0;

        while (i<p && j < t) {
            if (players[i] <= trainers[j]) {
                count += 1;
                i += 1;
                j += 1;
            } else j += 1;
        }

        return count;
    }
}
