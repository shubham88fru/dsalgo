package lc_potd;

//@link - https://leetcode.com/problems/maximum-count-of-positive-integer-and-negative-integer/description
public class MaximumCountOfPositiveIntegerAndNegativeInteger {
    public int maximumCount(int[] nums) {
        // return pass1(nums);
        return pass2(nums);
    }

    /*
        Binary search
    */
    private int pass2(int[] nums) {
        int n = nums.length;

        int positiveCnt = findFirstGreaterThanZero(nums);
        int negativeCnt = findLastSmallerThanZero(nums);


        return Math.max(positiveCnt, negativeCnt);
    }

    /*
        linear time soln.
        Doesn't use the fact that the
        given array is sorted in non-decreasing order.
    */
    private int pass1(int[] nums) {
        int n = nums.length;

        int pc = 0;
        int nc = 0;
        for (int i=0; i<n; i++) {
            if (nums[i] > 0) pc += 1;
            else if (nums[i] < 0) nc += 1;
        }

        return Math.max(pc, nc);
    }

    private int findFirstGreaterThanZero(int[] nums) {
        int n = nums.length;

        int l = 0;
        int r = n-1;

        int idx = -1;
        while (l <= r) {
            int mid = l + (r-l)/2;
            if (nums[mid] > 0) {
                idx = mid;
                r = mid-1;
            } else {
                l = mid + 1;
            }
        }

        return idx == -1 ? 0: n-idx;
    }


    private int findLastSmallerThanZero(int[] nums) {
        int n = nums.length;

        int l = 0;
        int r = n-1;

        int idx = -1;
        while (l <= r) {
            int mid = l + (r-l)/2;
            if (nums[mid] < 0) {
                idx = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }

        return idx == -1 ? 0: idx + 1;
    }
}
