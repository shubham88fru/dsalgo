package lc_potd;

//@link - https://leetcode.com/problems/remove-one-element-to-make-the-array-strictly-increasing/
public class RemoveOneElementToMakeTheArrayStrictlyIncreasing {
    public boolean canBeIncreasing(int[] nums) {
        return optimal(nums);
    }

    //0) Brute - check by deleting each element
    //if remaining is strictly increasing.

    //1) Optimal
    /*
        Extremely tricky to come up with.
        God knows how this came to my mind.
        Following is my soln. Don't get
        demotivated if not able to come up
        with this again next time.
        There might be a cleaner soln,
        but this seems more intuitive to me.
    */
    private boolean optimal(int[] nums) {
        int n = nums.length;

        /*
            First largest, second largest and
            allowed deletions.
            Start from third element.
        */
        int sl=nums[0], fl= nums[1] , i=2, count=1;

        /*
            If fl and sl and not
            aligned properly. Let
            fl be the smaller one and
            proceed with one deletion.
        */
        if (fl <= sl) count -= 1;

        while (i<n) {
            //If curr num is larger than
            //largest, we're good.
            //Just rearrange and move one.
            if (nums[i] > fl) {
                sl = fl;
                fl = nums[i];
            }
            //If curr num is between fl and sl,
            //we'll delete fl, and make curr as
            //fl and keep sl same. i.e. we choose
            //nums[i] as new fl because it is smaller
            //than fl and so it gives a better chance
            //to form increasing array.
            else if (nums[i] <= fl && nums[i] > sl) {
                if (count == 0) return false;
                count -= 1;
                fl = nums[i];
            }
            //If curr num is smaller than fl and sl both,
            //we have no option but to delete the curr,
            //otherwise we'll have to delete fl and sl both
            //which we can't do.
            else {
                if (count == 0) return false;
                count -= 1;
            }
            i += 1;
        }

        return true;
    }
}
