package lc_potd;

//@link - https://leetcode.com/problems/shortest-subarray-with-or-at-least-k-ii/description/
//@check - https://www.youtube.com/watch?v=pXr8CF7-5_Y&ab_channel=codestorywithMIK
public class ShortestSubarrayWithORAtleastKII {
    public int minimumSubarrayLength(int[] nums, int k) {
        return mysol(nums, k);
    }

    /*
        Following is not completely my sol. Its inspired by Mik's explanation.
        I was on the right track, but couldn't figure out how would be update the
        OR when releasing (shortening) the window.
     */
    private int mysol(int[] nums, int k) {
        //Running OR will increase or atleast remain the same. It
        //will never decrease.
        int l = 0;
        int r = 0;

        int[] setBits = new int[32]; //overall count of number of 1 bits at each index.

        int minLen = Integer.MAX_VALUE;
        int[] or = {0}; //OR of the elements in window.
        while (r < nums.length) {
            if ((nums[r] | or[0]) < k) {
                or[0] |= nums[r];
                addCount(setBits, nums[r]);
                r += 1;
            } else {
                minLen = Math.min(r-l+1, minLen);
                if (minLen == 1) return 1;

                removeCount(setBits, nums[l], or);
                l += 1;
            }
        }

        return minLen == Integer.MAX_VALUE ? -1: minLen;
    }

    private void updateOr(int[] setBits, int[] or) {
        int pow = 0;
        int compute = 0;
        for (int bit: setBits) {
            if (bit != 0) compute += Math.pow(2, pow);
            pow += 1;
        }
        or[0] = compute;
    }

    private void removeCount(int[] setBits, int num, int[] or) {
        int idx = 0;
        int updatedOr = 0;
        while (num > 0) {
            int bit = num & 1;
            if (bit == 1) {
                setBits[idx] -= 1;
                if (setBits[idx] == 0) updateOr(setBits, or);

            }
            num = num >> 1;
            idx += 1;
        }
    }

    private void addCount(int[] setBits, int num) {
        int idx = 0;
        while (num > 0) {
            int bit = num & 1;
            if (bit == 1) setBits[idx] += 1;
            num = num >> 1;
            idx += 1;
        }
    }
}
