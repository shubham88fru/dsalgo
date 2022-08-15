package basics.stack;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class ReverseListUsingStack {

    List<Integer> reverse(List<Integer> lst) {
        Deque<Integer> stack = new ArrayDeque<>();
        lst.forEach(stack::push);
        lst.clear();
        stack.forEach(el -> lst.add(stack.pop()));
        return lst;
    }

    public static void main(String[] args) {
        ReverseListUsingStack reverseListUsingStack
                = new ReverseListUsingStack();
        ArrayList<Integer> origiLst = new ArrayList<>();
        origiLst.add(1);
        origiLst.add(2);
        origiLst.add(3);
        origiLst.add(4);
        origiLst.add(5);
        System.out.println(origiLst);
        System.out.println(reverseListUsingStack.reverse(origiLst));
    }
}
