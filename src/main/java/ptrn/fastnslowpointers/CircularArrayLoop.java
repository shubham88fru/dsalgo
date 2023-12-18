package ptrn.fastnslowpointers;

//@link - https://leetcode.com/problems/circular-array-loop/description/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/4558820534910976
public class CircularArrayLoop {
    public boolean circularArrayLoop(int[] nums) {
        return checkIfCircular(nums);
    }

    private boolean checkIfCircular(int[] nums) {
        int size = nums.length;

        //try finding a loop from each element
        //of the array.
        for (int i=0; i<size; i++) {
            //on reset, set slow and fast
            //both to current element.
            int slow = i;
            int fast = i;

            //the direction both the pointers
            //started with.
            boolean ogDir = nums[i] > 0; //true -> forward, false -> back.

            while (true) {
                //slow moves once.
                slow = moveNext(slow, nums[slow], size);
                //after every move, check if all conditions
                //for a valid loop are met.
                if (!canFormCycle(nums, ogDir, slow)) break;

                //fast moves twice.
                //after every move, check if all conditions
                //for a valid loop are met.
                fast = moveNext(fast, nums[fast], size);
                if (!canFormCycle(nums, ogDir, fast)) break;
                fast = moveNext(fast, nums[fast], size);
                if (!canFormCycle(nums, ogDir, fast)) break;

                //if fast wraps and catches up with
                //slow again, means a loop.
                if (slow == fast) return true;
            }
        }

        return false;
    }

    private int moveNext(int curr, int value, int size) {
        int next = (curr+value)%size;
        if (next < 0) next += size; //handle case when nums[i] is -ve.
        return next;
    }

    private boolean canFormCycle(int[] nums, boolean prevDirection, int curr) {
        boolean currDirection = nums[curr] > 0;
        //a loop, ATQ, is valid only if direction doesn't change
        //and is not a single element loop (i.e. moving the pointer
        //from current location directly takes the pointer back to the same value).
        if (currDirection == prevDirection && Math.abs(nums[curr] % nums.length) != 0) return true;
        return false;
    }
}
