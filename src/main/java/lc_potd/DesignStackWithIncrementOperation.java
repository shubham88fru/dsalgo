package lc_potd;

import java.util.ArrayList;
import java.util.List;

//@link - https://leetcode.com/problems/design-a-stack-with-increment-operation/description/
//@check - https://www.youtube.com/watch?v=1KYzjryTRmg&ab_channel=codestorywithMIK
/**
 * This is my soln. Its simple but the increment function isn't O(1).
 */
public class DesignStackWithIncrementOperation {
    int[] stack;

    int sz;
    int head;

    public DesignStackWithIncrementOperation(int maxSize) {
        stack = new int[maxSize];
        sz = maxSize;
    }

    public void push(int x) {
        if (head < sz) {
            stack[head] = x;
            head += 1;
        }
    }

    public int pop() {
        if (head > 0) {
            head -= 1;
            int val = stack[head];

            return val;
        }

        return -1;
    }

    public void increment(int k, int val) {
        /**
         This method doesn't
         run in O(1), its O(k). Mik used the `Lazy propagation`
         technique, to solve this method also in o(1).
         */
        for (int i=0; i<Math.min(k, head); i++) {
            stack[i] += val;
        }
    }
}

/**
 * This is a optimal soln by mik. All operations are O(1).
 * Used a List for stack instead of array. For some reason, synchronizing
 * the indexes between the stack and increments array was getting difficult,
 * so chose to use list instead of the array, which simplified things.
 */
class DesignStackWithIncrementOperationOptimal {
    List<Integer> stack;
    List<Integer> increments;

    int sz;

    public DesignStackWithIncrementOperationOptimal(int maxSize) {
        stack = new ArrayList<>();
        increments = new ArrayList<>();
        sz = maxSize;
    }

    public void push(int x) {
        if (stack.size() < sz) {
            stack.add(x);
            increments.add(0);
        }
    }

    public int pop() {

        if (stack.size() == 0) return -1;

        int idx = stack.size() -1;
        if (idx > 0) {
            increments.set(idx-1, increments.get(idx-1)+increments.get(idx)); //propagate the increment to lower indexes.
        }

        int topVal = stack.get(idx) + increments.get(idx); //w/ lazy propagation - lazily add the increment only when the value is requested.
        stack.remove(stack.size()-1);
        increments.remove(increments.size()-1);

        return topVal;

    }

    public void increment(int k, int val) {


        /* Lazy propagation */
        /*
         Record the increment that has to be made till the kth index,
         and add the increment when the value is requested during pop
         */
        int idx = Math.min(k, stack.size()) - 1;
        if (idx >= 0) {
            increments.set(idx, increments.get(idx)+val);
        }
    }
}