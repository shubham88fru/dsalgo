package ptrn.bitmanipulation;

//@link - https://leetcode.com/problems/single-number-ii/
//@check - https://www.youtube.com/watch?v=5Bb2nqA40JY&t=1283s&ab_channel=takeUforward
public class SingleNumberII {
    public int singleNumber(int[] nums) {
        /**
         Following soln isn't the best soln.
         Strvr had 2 more solution. If this is
         high frequency problem for some company,
         rewatch the other solns.
         */
        return better(nums);
    }

    //T: O(N*32)
    private int better(int[] nums) {

        int ans = 0;
        for (int bitPos=0; bitPos<32; bitPos++) {
            int setCnt = 0;
            for (int i=0; i<nums.length; i++) {
                if (((nums[i])&(1<<bitPos)) != 0) setCnt += 1;
            }

            //if a particular bit position
            //doesn't have 3n number of ones means the odd one
            //out is coming from the lone number and so, that bit
            //will be set in the lone number.
            if (setCnt%3 != 0) ans = ans | (1 << bitPos);
        }
        return ans;
    }
}
