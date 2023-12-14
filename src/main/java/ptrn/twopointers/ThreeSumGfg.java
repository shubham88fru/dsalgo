package ptrn.twopointers;

import java.util.Arrays;

//@link - https://www.geeksforgeeks.org/problems/triplet-sum-in-array-1587115621/1
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/5015922227281920
public class ThreeSumGfg {
    public boolean find3Numbers(int A[], int n, int X) {

        // Your code Here
        return threeSumOptimal(A, X);
    }

    private boolean threeSumOptimal(int[] nums, int X) {
        //sort the array in desc. This helps
        //making the choice of which pointer to move.
        Arrays.sort(nums);

        for (int i=0; i<nums.length;i++) {
            //fix one num (and ensure that we aren't repeating this num)
            if (i>0 && nums[i] == nums[i-1]) continue;

            //standard code for 2 sum from here.
            int j = i+1; //left
            int k = nums.length-1; //right
            while (j<k) {
                int sum = nums[i] + nums[j] + nums[k];
                //if sum more than target,
                //we need to decrease the sum.
                if (sum>X) k--; //since the array is sorted.

                    //if sum less than target,
                    //we need to increase the sum.
                else if (sum<X) j++; //since the array is sorted.

                    //else we found the sum.
                else return true;
            }
        }

        return false;
    }
}
