package ptrn.cyclicsort;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

//@link - https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/description/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/5100478506729472
public class FindAllNumbersDisappearedInArray {
    public List<Integer> findDisappearedNumbers(int[] nums) {
//        return usingHashMap(nums);
        return revise(nums);
    }

    private List<Integer> usingHashMap(int[] nums) {
        ArrayList<Integer> rnd = new ArrayList<>();
        HashSet<Integer> set1 = new HashSet<>();
        int n = nums.length;
        for(int i =0; i<n; i++){
            set1.add(nums[i]);
        }
        for(int i =1; i<=n; i++){
            if(!set1.contains(i)){
                rnd.add(i);
            }
        }
        return rnd;
    }

    /*
        My sol using cyclic sort. T: O(2N), S: O(1)
     */
    private List<Integer> revise(int[] nums) {
        List<Integer> ans = new ArrayList<>();

        //cyclic sort
        int i=0;
        while (i < nums.length) {
            if ((nums[i] != (i+1)) && (nums[i] <= nums.length) && (nums[nums[i]-1] != nums[i])) {
                int temp = nums[i];
                nums[i] = nums[nums[i]-1];
                nums[temp-1] = temp;
            } else i += 1;
        }

        for (int j=0; j<nums.length; j++) {
            if (nums[j] != j+1) ans.add(j+1);
        }

        return ans;
    }

}
