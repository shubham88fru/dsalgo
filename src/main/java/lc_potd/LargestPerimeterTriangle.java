package lc_potd;

import java.util.Arrays;

//@link - https://leetcode.com/problems/largest-perimeter-triangle/description/
public class LargestPerimeterTriangle {
    public int largestPerimeter(int[] nums) {
        // return revise(nums);
        return reviseOptimized(nums);
    }

    private int reviseOptimized(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);

        int max = 0;
        for (int j=n-1; j>=2; j--) {
            int a = nums[j];
            int b = nums[j-1];
            int c = nums[j-2];

            if (a+b <= c) continue;
            if (a+c <= b) continue;
            if (b+c <= a) continue;

            return a + b + c;
        }

        return max;
    }

    private int revise(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);

        int max = 0;
        for (int j=n-1; j>=2; j--) {
            int a = nums[j];
            int b = nums[j-1];
            int c = nums[j-2];

            if (a+b <= c) continue;
            if (a+c <= b) continue;
            if (b+c <= a) continue;
            /**
             unoptimized.
             since we are iterating from
             the back, the momemt we get
             a valid triangle, we know its
             the largest. No point continuing.
             */
            max = Math.max(a+b+c, max);
        }

        return max;
    }
}
