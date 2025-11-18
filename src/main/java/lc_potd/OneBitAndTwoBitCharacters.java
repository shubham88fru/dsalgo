package lc_potd;

//@link - https://leetcode.com/problems/1-bit-and-2-bit-characters/?
public class OneBitAndTwoBitCharacters {
    public boolean isOneBitCharacter(int[] bits) {
        // return revise(bits);
        return approach2(bits);
    }

    /**
     Approach 1.
     */
    private boolean revise(int[] bits) {
        int n = bits.length;

        int i=0;
        while (i<n) {
            if (i == n-1) return true;
            if (bits[i] == 1) i += 2;
            else i += 1;
        }

        return false;
    }

    /**
     This is a clever greedy approach
     that came to my mind first, but
     couldn't fully complete it because
     of an edge case.
     Idea is - if n-2th bit is 0, then
     ans is always true. When n-2th bit
     is 1, then we need to check continuous
     1s.
     */
    private boolean approach2(int[] bits) {
        int n = bits.length;

        int i=n-2;
        int continousOnes = 0;
        while (i>=0 && bits[i] == 1) {
            i -= 1;
            continousOnes += 1;
        }

        // return (n-2-i)%2==0;
        return continousOnes%2==0;
    }
}
