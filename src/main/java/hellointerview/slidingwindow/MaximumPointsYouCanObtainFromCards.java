package hellointerview.slidingwindow;

//@link - https://leetcode.com/problems/maximum-points-you-can-obtain-from-cards/
//      - https://www.hellointerview.com/learn/code/sliding-window/maximum-points-you-can-obtain-from-cards
public class MaximumPointsYouCanObtainFromCards {
    public int maxScore(int[] cardPoints, int k) {
        return optimal(cardPoints, k);
    }

    private int optimal(int[] cardPoints, int k) {
        int n = cardPoints.length;
        int totalSum = sum(cardPoints);

        int l=0, r=0, sum=0, minSum = Integer.MAX_VALUE, K=n-k;
        if (K == 0) return totalSum;

        while (r < n) {
            while (r < n && r-l < K) {
                sum += cardPoints[r];
                r += 1;
            }

            minSum = Math.min(sum, minSum);

            sum -= cardPoints[l];
            l += 1;
        }

        return totalSum - minSum;
    }

    private int sum(int[] cardPoints) {
        int n = cardPoints.length;
        int sum = 0;
        for (int p: cardPoints) sum += p;

        return sum;
    }
}
