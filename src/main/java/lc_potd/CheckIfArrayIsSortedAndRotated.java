package lc_potd;

//@link - https://leetcode.com/problems/check-if-array-is-sorted-and-rotated/
//@check - https://www.youtube.com/watch?v=-o4vCUI7jmk&ab_channel=codestorywithMIK
public class CheckIfArrayIsSortedAndRotated {
    public boolean check(int[] nums) {
        // return revise(nums);
        return mikssol(nums);
    }

    /*
        1) My soln.
        TC is linear, but it not truly single pass
        because some elements will be visited twice
        in the second iteration after finding the peak.
     */
    private boolean revise(int[] nums) {
        int peakIndex = -1;
        int n = nums.length;

        if (n==1) return true;

        for (int i=1; i<n; i++) {
            if (nums[i] < nums[i-1]) {
                peakIndex = i-1;
                break;
            }
        }


        if (peakIndex == -1) return true;

        int i = peakIndex + 1;
        int count = n-1;
        while (count > 0) {
            int next = (i+1)%n;

            if (nums[next] < nums[i]) return false;

            i = next;
            count -= 1;
        }

        return true;
    }

    /*
        2) Miks approach.
        Truly one pass.
        Keep moving along the array keeping
        track of peaks that have been seen so far.
        If at the end, more than one peak have been
        seen, then its not a rotated sorted array.
    */
    private boolean mikssol(int[] nums) {
        int n = nums.length;
        int peaks = 0;

        int count = n;
        int i = 0;
        while (count > 0) {
            count -= 1;
            if (nums[i] <= nums[(i+1)%n]) {
                i += 1;
            } else {
                i += 1;
                peaks += 1;
            }
        }

        return peaks > 1 ? false: true;

    }
}
