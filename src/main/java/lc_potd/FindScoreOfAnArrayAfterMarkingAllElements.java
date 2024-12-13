package lc_potd;

import java.util.Comparator;
import java.util.PriorityQueue;

//@link - https://leetcode.com/problems/find-score-of-an-array-after-marking-all-elements/
public class FindScoreOfAnArrayAfterMarkingAllElements {
    public long findScore(int[] nums) {
        return mysol(nums);
    }

    private long mysol(int[] nums) {
        Comparator<int[]> cmp1 = (a1, a2) -> a1[0] - a2[0];
        Comparator<int[]> cmp2 = (a1, a2) -> a1[1] - a2[1];

        PriorityQueue<int[]> pq = new PriorityQueue<>(cmp1.thenComparing(cmp2));
        int[] marked = new int[nums.length];

        for (int i=0; i<nums.length; i++) {
            pq.add(new int[] { nums[i], i });
            marked[i] = nums[i];
        }

        long score = 0;
        while (!pq.isEmpty()) {
            int[] rem = pq.remove();
            if (marked[rem[1]] != -1) {
                score += rem[0];
                marked[rem[1]] = -1;
                if (rem[1]-1 >= 0) marked[rem[1]-1] = -1;
                if (rem[1]+1 < nums.length) marked[rem[1]+1] = -1;
            }
        }

        return score;
    }
}
