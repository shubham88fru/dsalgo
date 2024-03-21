package ptrn.stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;

//@link - https://leetcode.com/problems/flatten-nested-list-iterator/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/5927814030426112
public class FlattenNestedListIterator { }

// This is the interface that allows for creating nested lists.
// You should not implement it, or speculate about its implementation
interface NestedInteger {
    // @return true if this NestedInteger holds a single integer, rather than a nested list.
    public boolean isInteger();

    // @return the single integer that this NestedInteger holds, if it holds a single integer
    // Return null if this NestedInteger holds a nested list
    public Integer getInteger();

    // @return the nested list that this NestedInteger holds, if it holds a nested list
    // Return empty list if this NestedInteger holds a single integer
    public List<NestedInteger> getList();
}
class NestedIterator implements Iterator<Integer> {
    private Deque<Integer> stack = new ArrayDeque<>();
    // private Deque<Integer> stack2 = new ArrayDeque<>();
    public NestedIterator(List<NestedInteger> nestedList) {

        /**
         Note that pushing the elments of the list
         in order into the stack would cause the first
         element to be at the bottom of stack (and so on),
         and so, to return answer in the correct order, we'd
         need to move all the elements from the og stack to
         another stack (stack2 in our case), so that stack2's
         elements are in order of the og list.

         Howerver, if we push the elemnts to the stack in rever order,
         the og stack itself will have the elements in correct order
         and we won't need the second stack for correct ordering.
         */
        // for (NestedInteger ni: nestedList) {
        for (int j=nestedList.size()-1; j>=0; j--) {
            // addRecursive(ni);
            addRecursive(nestedList.get(j));
        }

        // while (!stack.isEmpty()) stack2.addFirst(stack.removeFirst());
    }

    private void addRecursive(NestedInteger ni) {
        if (ni.isInteger()) {
            stack.addFirst(ni.getInteger());
            return;
        }

        List<NestedInteger> nested = ni.getList();
        // for (NestedInteger item: nested) {
        for (int j=nested.size()-1; j>=0; j--) {
            // addRecursive(item);
            addRecursive(nested.get(j));
        }
    }

    @Override
    public Integer next() {
        // return stack2.removeFirst();
        return stack.removeFirst();
    }

    @Override
    public boolean hasNext() {
        // return !stack2.isEmpty();
        return !stack.isEmpty();
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */
