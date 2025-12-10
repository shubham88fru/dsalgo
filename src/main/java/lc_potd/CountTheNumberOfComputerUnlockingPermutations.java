package lc_potd;

//@link - https://leetcode.com/problems/count-the-number-of-computer-unlocking-permutations/description/?
public class CountTheNumberOfComputerUnlockingPermutations {
    public int countPermutations(int[] complexity) {
        return pass1(complexity);
    }

    private int pass1(int[] complexity) {
        int n = complexity.length;

        int min = complexity[0]; //order matters, first should be the minimum.
        long ans = 1;
        for (int i=n-1; i>=1; i--) {
            if (complexity[i] <= min) return 0;
            ans = (ans * i)%1000000007;
        }

        return (int)ans;
    }
}
