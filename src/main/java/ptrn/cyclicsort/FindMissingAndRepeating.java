package ptrn.cyclicsort;

import java.util.HashSet;
import java.util.Set;

//@link - https://www.geeksforgeeks.org/problems/find-missing-and-repeating2512/1
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/4525751434215424
public class FindMissingAndRepeating {
    int[] findTwoElement(int arr[], int n) {
        // code here
        return sol4(arr);
    }

    private int[] sol4(int[] nums) {
        Set<Integer> swapped = new HashSet<>();
        /*********Cyclic Sort******************/
        int i=0;
        while (i<nums.length) {
            int correct = nums[i]-1;
            /**This if condn works but needs an extra set.**/
            //Notice `>` and not `>=` (because size of arr is `n` and elements are 1..n)
            if (!(nums[i] > nums.length || nums[i] == i+1 || swapped.contains(nums[i]))) {
            /**This if condn also works and doesn't need set.**/
            //if (!(nums[i] == nums[correct])) {
                swapped.add(nums[i]);
                int temp = nums[i];
                nums[i] = nums[correct];
                nums[correct] = temp;
            } else i += 1;
        }
        /*********Cyclic Sort******************/

        int[] ans = new int[2];
        for (int j=0; j<nums.length; j++) {
            if (nums[j] != j+1) {
                ans[0] = nums[j];
                ans[1] = j+1;
                return ans;
            }
        }

        return null;
    }
}
