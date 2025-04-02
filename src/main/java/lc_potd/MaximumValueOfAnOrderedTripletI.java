package lc_potd;

//@link - https://leetcode.com/problems/maximum-value-of-an-ordered-triplet-i/description/
//@check - https://www.youtube.com/watch?v=UQZEiyIl4ZA&t=922s&ab_channel=codestorywithMIK
public class MaximumValueOfAnOrderedTripletI {
    public long maximumTripletValue(int[] nums) {
        // return brute(nums);
        // return better(nums);
        return optimal(nums);
    }

    //1) Optimal. T: O(N), S: O(1)
    // Again, based on mik's explanation. Coded by me.
    //There's no way, I can come up this in an interview (probably)
    //if I haven't revised this problem before.
    //Approach 2 is more intuitive.
    private long optimal(int[] nums) {
        int n = nums.length;
        int maxDiff = 0;
        int max = 0;

        long res = 0;
        for (int k=0; k<n; k++) {
            res = Math.max(res, (long)maxDiff * (long)nums[k]);
            maxDiff = Math.max(maxDiff, max - nums[k]);
            max = Math.max(max, nums[k]);
        }

        return res;
    }

    //2) Better approach. T: O(N), S: O(N)
    // Based on mik's explanation. Coded by me.
    // For each j, pre-populate leftMax and rightMax
    private long better(int[] nums) {
        int n = nums.length;

        int[] leftMax = new int[n];
        int[] rightMax = new int[n];

        leftMax[0] = 0;
        for (int j=1; j<n; j++) {
            leftMax[j] = Math.max(leftMax[j-1], nums[j-1]);
        }

        rightMax[n-1] = 0;
        for (int j=n-2; j>=0; j--) {
            rightMax[j] = Math.max(rightMax[j+1], nums[j+1]);
        }

        long res = 0;
        for (int j=1; j<n-1; j++) {
            res = Math.max(res, (long)(leftMax[j] - nums[j])*(long)rightMax[j]);
        }

        return res;
    }

    //3) Brute force. O(N^3)
    //I could only come up with the brute force.
    //3 for loops, and keep track of the max value
    //obtained from the formula.
}
