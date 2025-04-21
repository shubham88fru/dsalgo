package lc_potd;

//@link - https://leetcode.com/problems/count-the-hidden-sequences/description/
public class CountTheHiddenSequences {
    public int numberOfArrays(int[] differences, int lower, int upper) {
        return pass1(differences, lower, upper);
    }

    //My soln. Mik had a soln on similar lines.
    //T: O(2N)
    private int pass1(int[] differences, int lower, int upper) {
        int n = differences.length;
        int minAdd = Integer.MAX_VALUE;
        int maxAdd = Integer.MIN_VALUE;

        int sum = 0;
        for (int i=0; i<n; i++) {
            sum += differences[i];
            minAdd = Math.min(minAdd, sum);
            maxAdd = Math.max(maxAdd, sum);
        }

        int count = 0;
        for (int i=lower; i<=upper; i++) {
            int min = i + minAdd; //min element we'll get.
            int max = i + maxAdd; //max element we'll get.

            if (max > upper) break;
            if (min >= lower && max <= upper) count += 1;
        }

        return count;
    }
}
