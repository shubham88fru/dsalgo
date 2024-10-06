package ptrn.greedy;

//@link - https://leetcode.com/problems/container-with-most-water/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/5658894316863488
//       - https://www.youtube.com/watch?v=KVU4JNNJkVg&t=615s&ab_channel=codestorywithMIK
public class ContainerWithMostWater {
    public int maxArea(int[] height) {
        /*
            mik's greedy soln.

            NOTE how this problem is figuratively subtly different but
            algorithmically and meaningwise significantly
            different from the area of largest rectangle
            in histogram problem. Unlike, the latter, we don't
            have to worry about previous rods in this problem.
        */
        return greedy(height);
        // return brute(height);
    }

    //1. greedy approach using two pointer.
    private int greedy(int[] height) {
        int i = 0;
        int j = height.length-1;
        int maxArea = Integer.MIN_VALUE;
        while (i < j) {
            int h = Math.min(height[i], height[j]);
            int area = h * (j-i); //area = height * width;
            maxArea = Math.max(area, maxArea);
            if (height[i] < height[j]) {//move the smaller height.
                i += 1;
            } else j -= 1;
        }

        return maxArea;
    }

    //2. Brute force.
    //take each rod as the left side of the container
    //and the find the right side of the contianer that
    //gives max area.
    //T: O(N^2), will give TLE.
    private int brute(int[] height) {
        int n = height.length;
        int maxAreaOverall = 0;

        for (int i=0; i<n; i++) {
            int maxArea = 0;
            for (int j=i+1; j<n; j++) {
                int h = Math.min(height[i], height[j]);
                int area = h * (j-i); //area = height * width;
                maxArea = Math.max(area, maxArea);
            }
            maxAreaOverall = Math.max(maxArea, maxAreaOverall);
        }

        return maxAreaOverall;
    }
}
