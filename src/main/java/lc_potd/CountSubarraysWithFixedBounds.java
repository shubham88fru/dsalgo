package lc_potd;

//@link - https://leetcode.com/problems/count-subarrays-with-fixed-bounds/
//@check - https://www.youtube.com/watch?v=bBrzHYu58YI&t=1771s&ab_channel=ProgrammingLivewithLarry
public class CountSubarraysWithFixedBounds {
    public long countSubarrays(int[] nums, int minK, int maxK) {
        return slidingWindow(nums, minK, maxK);
    }

    /*
    * Ported larry's sol to match my template for sliding window.
    * */
    private long slidingWindow(int[] nums, int minK, int maxK) {
        int n = nums.length;
        int l = 0;
        int r = 0;

        int mini = -1;
        int maxi = -1;
        long count = 0;

        while (r < n) {
            while (r < n && nums[r] <= maxK && nums[r] >= minK) {
                if (nums[r] == minK) mini = r;
                if (nums[r] == maxK) maxi = r;

                if (mini != -1 && maxi != -1) {
                    count += (long)(Math.min(mini, maxi) - l + 1);
                }
                r += 1;
            }

            //reset
            mini = -1;
            maxi = -1;
            r += 1;
            l = r;

        }

        return count;
    }

    /*
        larry's soln.
     */
    private long larrySol(int[] nums, int minK, int maxK) {
        long count = 0;
        int n = nums.length;

        //base cond.
        int start_of_subarray_index = 0; //index of start of subarray.
        int last_mini = -1; //index of last seen minK
        int last_maxi = -1; //index of last seen maxK

        for (int i=0; i<n; i++) {
            if (nums[i] <= maxK && nums[i] >= minK) {
                //keep updating last min and max index.
                if (nums[i] == minK) last_mini = i;
                if (nums[i] == maxK) last_maxi = i;

                if (last_maxi != -1 && last_mini != -1) {
                    //Fixing last_mini and last_maxi, include all subarrays
                    //that are possible while keeping in mind the start of subarray.
                    count += Math.min(last_mini, last_maxi)-start_of_subarray_index + 1;
                }
            } else {
                //if we see a num that is out of bounds,
                //we cannot have subarrays that includes this
                //element. So, we reset.
                last_mini = -1;
                last_maxi = -1;
                //start of subarray can only be after the
                //current invalid num. This variable does the magic
                //of partition that larry did in his soln.
                //e.g.
                // [934372,927845,479424,49441,17167,17167,65553,927845,17167,927845,17167,
                // 425106,17167,927845,17167,927845,251338,17167]
                // In the above array, index 0 is out of bounds, so we need to
                // update the start to 1, so that any subsequent valid subarrays,
                // will take index 1 as its start to count the length instead of 0.
                start_of_subarray_index = i+1;

            }
        }

        return count;
    }
}
