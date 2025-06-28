package lc_potd;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

//@link - https://leetcode.com/problems/find-subsequence-of-length-k-with-the-largest-sum/
public class FindSubsequenceOfLengthKWithTheLargestSum {
    public int[] maxSubsequence(int[] nums, int k) {
        // return sol1(nums, k);
        return sol2(nums, k);
    }

    /*
        It's a shame that I jumped to a backtracking
        soln first without even thinking of this
        straightforward and easy soln.

        //Idea is to select the largest k nums.
        //But we can't simply sort the array and return
        //the largest 3 nums, as the question is asking for
        //subsequence, we have to return the largest 3 nums in the order
        //they appear in the array.
    */
    private int[] sol2(int[] nums, int k) {
        int n = nums.length;
        Comparator<int[]> cmp = (a1, a2) -> a1[0] - a2[0];
        PriorityQueue<int[]> pq = new PriorityQueue<>(cmp);

        for (int i=0; i<n; i++) {
            if (pq.size() < k) pq.add(new int[]{nums[i], i});
            else if (nums[i] > pq.peek()[0]) {
                pq.remove();
                pq.add(new int[]{nums[i], i});
            }
        }

        return pq.stream()
                .sorted(Comparator.comparingInt(a -> a[1]))
                .mapToInt(a -> a[0]).toArray();
    }

    /*
        First intuitiion.
    */
    //Using recursion/backtracking. Suboptimal. TLE.
    private int[] sol1(int[] nums, int k) {
        int[] max = new int[]{Integer.MIN_VALUE};
        List<Integer> ans = new ArrayList<>();
        maxSub(nums, k, new ArrayList<>(), ans, 0, max);
        return ans.stream().mapToInt(i -> i).toArray();
    }


    private void maxSub(int[] nums, int k, List<Integer> lst,
                        List<Integer> ans, int curr, int[] max) {

        if (lst.size() == k) {
            int sum = 0;
            for (int num: lst) sum += num;
            if (sum > max[0]) {
                ans.clear();
                for (int i=0; i<k; i++) ans.add(lst.get(i));
                max[0] = sum;
            }
            return;
        }
        if (curr >= nums.length) return;

        lst.add(nums[curr]);
        maxSub(nums, k, lst, ans, curr+1, max);
        lst.remove(lst.size()-1);

        maxSub(nums, k, lst, ans, curr+1, max);
    }
}
