package hellointerview.twopointers;

//@link - https://www.hellointerview.com/learn/code/two-pointers/container-with-most-water/
public class ContainerWithMostWater {
    public int max_area(int[] heights) {
        // Your code goes here
        // return brute(heights);
        return optimal(heights);
    }

    private int brute(int[] heights) {
        int n = heights.length;
        int maxArea = 0;
        for (int i=0; i<n; i++) {
            for (int j=i+1; j<n; j++) {
                int l = j-i, b = Math.min(heights[i], heights[j]);
                maxArea = Math.max(maxArea, l*b);
            }
        }

        return maxArea;
    }

    private int optimal(int[] heights) {
        int n = heights.length, maxArea = 0;
        int i=0, j=n-1;
        while (i < j) {
            int l = j-i, b = Math.min(heights[i], heights[j]);
            maxArea = Math.max(maxArea, l*b);
            if (heights[i] <= heights[j]) {
                i += 1;
            } else j -= 1;
        }

        return maxArea;
    }
}
