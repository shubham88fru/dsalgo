package lc_potd;

import java.util.HashSet;
import java.util.Set;

//@link - https://leetcode.com/problems/number-of-unique-xor-triplets-i/?
//@check - https://www.youtube.com/watch?v=FbD3Tfwhi0Q
public class NumberOfUniqueXORTripletsI {
    public int uniqueXorTriplets(int[] nums) {
        // return brute(nums);
        return mikssol(nums);
    }

    /*
        Coded by me based on mik's explanation.
        This is purely an observation based
        problem and no way I could have
        come up with this myself.
    */
    private int mikssol(int[] nums) {
        int n = nums.length;

        if (n <= 2) return n;

        int nextPowerOfTwo = (int)Math.floor(Math.log(n)/(Math.log(2)*1.0)) + 1;
        return (int)Math.pow(2, nextPowerOfTwo);
    }

    private int brute(int[] nums) {
        int n = nums.length;
        Set<Integer> st = new HashSet<>();
        for (int i=0; i<n; i++) {
            for (int j=i; j<n; j++) {
                for (int k=j; k<n; k++) {
                    st.add(nums[i]^nums[j]^nums[k]);
                }
            }
        }

        return st.size();
    }
}
