package lc_challenges;

//@link - https://leetcode.com/problems/find-the-maximum-sum-of-node-values/
//@check - https://www.youtube.com/watch?v=3t7y4mBJDoM&t=0s&ab_channel=AryanMittal
public class FindMaximumSumOfNodeValues {
    //Only got like 70%, rewatch his and other's approach maybe.
    public long maximumValueSum(int[] nums, int k, int[][] edges) {
        return checkAndXOR(nums, k, edges);
    }

    //1) Using XOR properties.
    //Note that, since k is not going to be zero (ATQ),
    //when xoring with k, each node's value will either increase or decrease.
    //Since we want to maximize the sum, we wan't to increase the value of all
    //the nodes whose values will increase after xoring with k.
    //With a bit of observation (check the linked video), it can be shown that
    //no matter what, since we are allowed to do the xor operation any number of
    //times, we can repeteadly select edges for xoring and reach in a state where
    //xor is applied only on those nodes whose values increase and not applied
    //on the nodes whose value decreases. Therefore, we effectively can just
    //iterate over the nodes, check if xoring will increase its value, if it does,
    //xor and add it to sum, otherwise add the non-xored value to sum.
    private long checkAndXOR(int[] nums, int k, int[][] edges) {
        long sum = 0;
        int count = 0;
        long sacrifice = Integer.MAX_VALUE;
        for (int i=0; i<nums.length; i++) {
            if ((nums[i]^k) > nums[i]) {
                sum += (nums[i]^k);
                sacrifice = Math.min(sacrifice, ((nums[i]^k)-nums[i]));
                count += 1; //no. of nodes that can increase after xoring.
            } else {
                sum += nums[i];
                sacrifice = Math.min(sacrifice, (nums[i]-(nums[i]^k)));
            }
        }

        if (count%2 == 0) return sum;

        return sum-sacrifice;
    }

    //2) DP soln. There is certainly a DP soln, but I'm not sure
    //how to do using top-down.
}
