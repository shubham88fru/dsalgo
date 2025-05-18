package ptrn.stack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

//@link - https://leetcode.com/problems/largest-rectangle-in-histogram/
//@strvr - https://www.youtube.com/watch?v=Bzat9vgD0fs&ab_channel=takeUforward
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/6479108851892224
public class LargestRectangleInHistogram {
    public int largestRectangleArea(int[] heights) {
        //return largestRectangleAreaBrute(heights);
        return largestRectangleAreaBetter(heights);
        //return largestRectangleAreaOptimal(heights);
    }

    //1) Optimal approach.
    /*
        Idea is to find nse and pse
        (next smaller element and prev smaller element).
        We push elements to stack only
        if its larger than the top of the stack.
        Note that this way, for each element pushed
        on to stack, we have that element's pse.
        If the element is smaller than the top of
        stack, means we have stack's top's nse
        as well. This way we can have nse and pse
        for each element on the fly.
    */
    private int reviseOptimal(int[] heights) {
        int n = heights.length;

        Deque<Integer> stack = new ArrayDeque<>();
        int maxArea = Integer.MIN_VALUE;

        for (int i=0; i<n; i++) {
            while (!stack.isEmpty() && heights[i] < heights[stack.peekFirst()]) {
                int nse = i; //nse for element to be popped.
                int height = heights[stack.removeFirst()]; //height of element being popped.
                int pse = stack.isEmpty() ? -1: stack.peekFirst(); //pse for popped element.
                maxArea = Math.max((nse - pse - 1)*height, maxArea);
            }
            stack.addFirst(i);
        }

        //At this point, the elements remaining
        //in stack are guaranteed to have no nse.
        //pse for each element will be the element
        //below it in the stack.
        while (!stack.isEmpty()) {
            int nse = n; //the elements in stack at this point, dont have any nse, therefore nse is n.
            int height = heights[stack.removeFirst()];
            int pse = stack.isEmpty() ? -1: stack.peekFirst();
            maxArea = Math.max((nse - pse - 1)*height, maxArea);
        }

        return maxArea;
    }

    //2) Better approach - revision.
    private int revise(int[] heights) {
        int n = heights.length;

        int[] rb = new int[n];
        Arrays.fill(rb, n-1);

        int[] lb = new int[n];

        fillRb(heights, rb);
        fillLb(heights, lb);

        int maxArea = Integer.MIN_VALUE;
        for (int i=0; i<n; i++) {
            maxArea = Math.max(maxArea, (rb[i]-lb[i]+1)*heights[i]);
        }

        return maxArea;
    }

    private void fillRb(int[] heights, int[] rb) {
        int n = rb.length;
        Deque<int[]> stack = new ArrayDeque<>();
        for (int i=0; i<n; i++) {
            while (!stack.isEmpty() && heights[i] < stack.peekFirst()[0]) {
                int idx = stack.removeFirst()[1];
                rb[idx] = i - 1;
            }
            stack.addFirst(new int[] { heights[i], i});
        }
    }

    private void fillLb(int[] heights, int[] lb) {
        int n = lb.length;
        Deque<int[]> stack = new ArrayDeque<>();
        for (int i=n-1; i>=0; i--) {
            while (!stack.isEmpty() && heights[i] < stack.peekFirst()[0]) {
                int idx = stack.removeFirst()[1];
                lb[idx] = i + 1;
            }
            stack.addFirst(new int[] { heights[i], i});
        }
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
