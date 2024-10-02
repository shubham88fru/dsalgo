package ptrn.stack;

import java.util.ArrayDeque;
import java.util.Deque;

//@link - https://leetcode.com/problems/largest-rectangle-in-histogram/
//@strvr - https://takeuforward.org/data-structure/area-of-largest-rectangle-in-histogram/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/6479108851892224
public class LargestRectangleInHistogram {
    public int largestRectangleArea(int[] heights) {
        //return largestRectangleAreaBrute(heights);
        return largestRectangleAreaBetter(heights);
        //return largestRectangleAreaOptimal(heights);
    }

    //1) Optimal approach. This is kinda crazy. Didn't get it completely (or maybe even partially :D)
    //stick with the approach 2 if it comes in an interview and giveup if the interviewer asks to
    //optimize approach 2 further.
    private int largestRectangleAreaOptimal(int[] heights){
        Deque<Integer> _stack = new ArrayDeque<>();
        int maxArea = Integer.MIN_VALUE;
        int n = heights.length;

        for (int i=0; i<=n; i++) { //notice `<= n` and not just `< n`
            while (!_stack.isEmpty() && (i==n || heights[_stack.peekFirst()] >= heights[i])) {
                int height = heights[_stack.peekFirst()];
                _stack.removeFirst();
                int width = i;
                if (!_stack.isEmpty()) width = i - _stack.peekFirst() - 1;
                maxArea = Math.max(maxArea, width*height);
            }
            _stack.addFirst(i);
        }
        return maxArea;
    }

    //2) Better approach.
    //Following on the similar idea as the brute force, if we can
    //precalculate the first smallest element to left and right for
    //each bar, then we don't to loop over the entire array everytime for
    //each bar. Thus, the solution could turn into a liner solution.
    //area = (rightBounary-leftBoundary+1)*currHeight
    private int largestRectangleAreaBetter(int[] heights) {
        int n = heights.length;
        int[] leftBoundaries = new int[n];
        int[] rightBoundaries = new int[n];

        //for each element keep track of index of first smaller element on left.
        fillWithSmallerOnLeft(heights, leftBoundaries);

        //for each element keep track of index of first smaller element on right.
        fillWithSmallerOnRight(heights, rightBoundaries);

        int maxArea = Integer.MIN_VALUE;
        for (int i=0; i<n; i++) {
            maxArea = Math.max(maxArea, (rightBoundaries[i] - leftBoundaries[i] + 1)*heights[i]);
        }

        return maxArea;
    }

    private void fillWithSmallerOnLeft(int[] heights, int[] leftBoundaries) {
        Deque<Integer> _stack = new ArrayDeque<>();
        int n = heights.length;
        for (int i=0; i<n; i++) {
            while (!_stack.isEmpty() && heights[_stack.peekFirst()] >= heights[i]) _stack.removeFirst();
            if (_stack.isEmpty()) leftBoundaries[i] = 0; //by default index -1 is left wall.
            else leftBoundaries[i] = _stack.peekFirst() + 1;

            _stack.addFirst(i);
        }
    }

    private void fillWithSmallerOnRight(int[] heights, int[] rightBoundaries) {
        Deque<Integer> _stack = new ArrayDeque<>();
        int n = heights.length;
        for (int i=n-1; i>=0; i--) {
            while (!_stack.isEmpty() && heights[_stack.peekFirst()] >= heights[i]) _stack.removeFirst();
            if (_stack.isEmpty()) rightBoundaries[i] = n-1; //by default index length+1 is right wall.
            else rightBoundaries[i] = _stack.peekFirst() - 1;

            _stack.addFirst(i);
        }
    }

    //3) Brute force. For every bar, find the the first bar
    //that is smaller than the current on the left as well as on
    //the right, this helps in getting the longest rectangle of
    //curr height. Calculate its area and keep comparing with subsequent
    //areas to find the max.
    private int largestRectangleAreaBrute(int[] heights) {
        int maxArea = Integer.MIN_VALUE;

        for (int i=0; i<heights.length; i++) {
            int height = heights[i];
            int length = 1;
            for (int j=i-1; j>=0; j--) {
                if (heights[j] >= height) length += 1;
                else break;
            }

            for (int j=i+1; j<heights.length; j++) {
                if (heights[j] >= height) length += 1;
                else break;
            }

            maxArea = Math.max(maxArea, length*height);
        }

        return maxArea;
    }
}
