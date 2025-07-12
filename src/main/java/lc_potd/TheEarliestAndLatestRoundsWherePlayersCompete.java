package lc_potd;

//@link - https://leetcode.com/problems/the-earliest-and-latest-rounds-where-players-compete/description/
//@check - https://www.youtube.com/watch?v=NnfyGypen1w&ab_channel=codestorywithMIK
public class TheEarliestAndLatestRoundsWherePlayersCompete {
    public int[] earliestAndLatest(int n, int firstPlayer, int secondPlayer) {
        return mikssol(n, firstPlayer, secondPlayer);
    }

    /*
    * Shameless copypasta of mik's code.
    * Got 80% of his explanation.
    * */
    private int[] mikssol(int n, int firstPlayer, int secondPlayer) {
        int left = firstPlayer;
        int right = secondPlayer;

        if (left == n-right+1) {
            return new int[] {1, 1};
        }

        if (left > n - right + 1) {
            int temp = n - left + 1;
            left = n - right + 1;
            right = temp;
        }

        int minRound = Integer.MAX_VALUE;
        int maxRound = 0;
        int nextRoundPlayersCount = (n+1)/2;

        if (right <= nextRoundPlayersCount) {
            int countLeft = left-1;
            int midCount = right - left - 1;

            for (int survivorsLeft = 0; survivorsLeft <= countLeft; survivorsLeft++ ) {
                for (int survivorsMid=0; survivorsMid <= midCount; survivorsMid++) {
                    int pos1 = survivorsLeft + 1;
                    int pos2 = pos1 + survivorsMid + 1;
                    int[] temp = mikssol(nextRoundPlayersCount, pos1, pos2);
                    minRound = Math.min(minRound, temp[0]+1);
                    maxRound = Math.max(maxRound, temp[1]+1);
                }
            }
        } else {
            int fightsRight = n - right + 1;
            int countLeft = left - 1;
            int midCount = fightsRight - left - 1;
            int remainingMidCount = right - fightsRight - 1;
            for (int survivorsLeft = 0; survivorsLeft <= countLeft; survivorsLeft++ ) {
                for (int survivorsMid=0; survivorsMid <= midCount; survivorsMid++) {
                    int pos1 = survivorsLeft + 1;
                    int pos2 = pos1 + survivorsMid + (remainingMidCount+1)/2 + 1;
                    int[] temp = mikssol(nextRoundPlayersCount, pos1, pos2);
                    minRound = Math.min(minRound, temp[0]+1);
                    maxRound = Math.max(maxRound, temp[1]+1);
                }
            }
        }

        return new int[]{minRound, maxRound};
    }
}
