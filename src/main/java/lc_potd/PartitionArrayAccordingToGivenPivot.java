package lc_potd;

//@link - https://leetcode.com/problems/partition-array-according-to-given-pivot
//@check - https://www.youtube.com/watch?v=yHJpnqW5ZSU&ab_channel=codestorywithMIK
public class PartitionArrayAccordingToGivenPivot {
    public int[] pivotArray(int[] nums, int pivot) {
        return pass1(nums, pivot);
    }

    /**
     My first impression was that this problem
     can be solved using comparators, but that
     didn't work out.

     Below is my O(3N) approach. I don't think
     interviewer will be very satisfied with
     this though. Miks showed a O(2N) approach for
     this. So @check if this is recurring problem
     for some company.
     */
    private int[] pass1(int[] nums, int pivot) {
        int n = nums.length;
        int[] ans = new int[n];

        //fill all elements smaller than pivot.
        int idx =0;
        for (int i=0;i<n;i++) {
            if (nums[i] < pivot) {
                ans[idx] = nums[i];
                idx += 1;
            }
        }

        //fill all elements equal pivot.
        for (int i=0; i<n; i++) {
            if (nums[i] == pivot) {
                ans[idx] = nums[i];
                idx += 1;
            }
        }

        //fill all elements larger than pivot.
        for (int i=0; i<n; i++) {
            if (nums[i] > pivot) {
                ans[idx] = nums[i];
                idx += 1;
            }
        }

        return ans;
    }
}
