package lc_potd;

//@link - https://leetcode.com/problems/minimum-number-of-operations-to-move-all-balls-to-each-box/
//@check - https://www.youtube.com/watch?v=1j_ssSAjDcI&t=2290s&ab_channel=codestorywithMIK
public class MinimumNumberOfOperationsToMoveAllBallsToEachBox {
    public int[] minOperations(String boxes) {
        // return brute(boxes);
        return mikssol(boxes);
    }

    //my brute force.
    private int[] brute(String boxes) {
        int n = boxes.length();
        int[] ans = new int[n];
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                if (i==j) continue;
                if (boxes.charAt(j)=='1') ans[i] += Math.abs(i-j);
            }
        }

        return ans;
    }

    //copypasta from mik.
    private int[] mikssol(String boxes) {
        int n = boxes.length();
        int[] answer = new int[n];

        int cumValue = 0;
        int cumValueSum = 0;

        // Find moves for all left balls to index i
        for (int i = 0; i < n; i++) {
            answer[i] = cumValueSum;

            cumValue += boxes.charAt(i) == '0' ? 0 : 1;
            cumValueSum += cumValue;
        }

        cumValue = 0;
        cumValueSum = 0;

        // Find moves for all right balls to index i
        for (int i = n - 1; i >= 0; i--) {
            answer[i] += cumValueSum;

            cumValue += boxes.charAt(i) == '0' ? 0 : 1;
            cumValueSum += cumValue;
        }

        return answer;
    }
}
