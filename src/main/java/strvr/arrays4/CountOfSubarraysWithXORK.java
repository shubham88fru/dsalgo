package strvr.arrays4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

//@link - https://www.interviewbit.com/problems/subarray-with-given-xor/
//@strvr - https://takeuforward.org/data-structure/count-the-number-of-subarrays-with-given-xor-k/
public class CountOfSubarraysWithXORK {
    public int solve(ArrayList<Integer> A, int B) {
        return countOfSubArrayWithXOREquals(A, B);
    }

    private int countOfSubArrayWithXOREquals(ArrayList<Integer> A, int B) {
        Map<Integer, Integer> mp = new HashMap<>(); //prefix xor and count.

        int pxor = 0; //prefix xor.
        mp.put(0, 1); //since prefix xor starts with 0, we have seen it once.

        int ans = 0;
        for (int a: A) {
            /*
                If say till index x1, the pxor is `x`
                and curr pxor is `pxor`, then for the
                curr index to be the end of a valid subarray,
                we will have this equation -
                x^B=pxor (i.e. xor of elements between x1 and curr should be B)
                from above equation,
                x = pxor^B
                therefore, if at any point, if we see a key `pxor^B` in our Map
                then we are sure that we have a sub array [x1 to curr] that has
                xor equal to B.
            */
            pxor ^= a;

            //If we've seen `pxor^B` before, add the
            //counts to ans else don't add anything.
            ans += mp.getOrDefault(pxor^B, 0);

            //Put current pxor and move next.
            //NOTE: Put the curr `pxor` in map only after calculating
            //ans above. Doesn't work if do it before the above step.
            mp.put(pxor, mp.getOrDefault(pxor, 0) + 1);
        }

        return ans;
    }
}
