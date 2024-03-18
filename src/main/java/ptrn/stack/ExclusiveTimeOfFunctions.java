package ptrn.stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

//@link - https://leetcode.com/problems/exclusive-time-of-functions/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/6397305277382656
public class ExclusiveTimeOfFunctions {
    public int[] exclusiveTime(int n, List<String> logs) {
        int[] ans = new int[n];
        Deque<FuncDetails> stack = new ArrayDeque<>();
        for (String log: logs) {
            String[] data = log.split(":");
            int id = Integer.parseInt(data[0]);
            String state = data[1];
            int ts = Integer.parseInt(data[2]);

            //A new function on the stack.
            //Everytime we see a start, means a new function started, so put on the stack.
            if (state.equals("start")) stack.addFirst(new FuncDetails(id, state, ts));
            else {
                //Otherwise, The function on top of stack needs to end.
                FuncDetails popped = stack.removeFirst();
                //calculate the time this function remained on stack (i.e. executed)
                //and add it to its index in the ans array.
                int execTime = ts-popped.timestamp+1;
                //Note, a function may come multiple times
                //on the stack, and so we need to add its
                //execution time instead of overwriting the prev value.
                ans[popped.id] += execTime;
                //After removing the function, if the stack
                //is not empty, means the currently removed
                //function was called from within a parent function (which is on stack rt now.)
                //And so, the function that we just removed from stack actually ended up
                //taking the `execTime` from the total time that the parent function will be on stack,
                //coz for the `execTime` duration, the called function was being executed and not the
                //parent function itself. Therefore, to account for this loss of execution time,
                //we subtract `execTime` from the total time of the parent.
                if (!stack.isEmpty()) {
                    ans[stack.peekFirst().id] -= execTime;
                }
            }
        }

        return ans;
    }
}

class FuncDetails {
    int id;
    String state;
    int timestamp;
    public FuncDetails(int id, String state, int timestamp) {
        this.id = id;
        this.state = state;
        this.timestamp = timestamp;
    }
}
