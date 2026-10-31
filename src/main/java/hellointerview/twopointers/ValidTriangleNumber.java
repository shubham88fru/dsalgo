package hellointerview.twopointers;

import java.util.Arrays;

//@link - https://leetcode.com/problems/valid-triangle-number/
//      - https://www.hellointerview.com/learn/code/two-pointers/valid-triangle-number
public class ValidTriangleNumber {
    public int triangleNumber(int[] nums) {
        return optimal(nums);
    }

    /*
        Coded by me based on some hints from HI.
        The key to this question is realizing that if
        we sort three numbers from smallest to
        largest (say a ≤ b ≤ c), we only need
        to check if a + b > c.
    */
    private int optimal(int[] nums) {
        int n = nums.length;

        Arrays.sort(nums);
        int c = n-1, count = 0;
        while (c >= 2) {
            int a=0, b=c-1;
            while (a < b) {
                int sum = nums[a] + nums[b];
                if (sum > nums[c]) {
                    count += (b-a);
                    b -= 1;
                } else {
                    a += 1;
                }
            }

            c -= 1;
        }

        return count;
    }
}
