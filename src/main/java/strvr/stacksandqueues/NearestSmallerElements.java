package strvr.stacksandqueues;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

//@link - https://www.interviewbit.com/problems/nearest-smaller-element/
public class NearestSmallerElements {
    public ArrayList<Integer> prevSmaller(ArrayList<Integer> A) {
        return getNearestSmallerElements(A);
    }


    private ArrayList<Integer> getNearestSmallerElements(ArrayList<Integer> arr) {
        Deque<Integer> stack = new ArrayDeque<>();
        ArrayList<Integer> ans = new ArrayList<>();

        //since need to find nearest smaller before the element (i.e. to the left of elements)
        //start iterating from start of the list.
        for (int i=0; i < arr.size(); i++) {
            if (stack.isEmpty()) {
                ans.add(-1);
            } else if (arr.get(i) <= stack.peekFirst()) {
                while (!stack.isEmpty() && arr.get(i) <= stack.peekFirst()) {
                    stack.removeFirst();
                }

                if (stack.isEmpty()) ans.add(-1);
                else {
                    ans.add(stack.peekFirst());
                }

            } else {
                ans.add(stack.peekFirst());
            }

            //in any case, add curr element to stack.
            stack.addFirst(arr.get(i));
        }

        return ans;
    }
}
