package basics.stack;

import java.util.ArrayList;

public class StackUsingArrayList {

    private final ArrayList<Integer> els = new ArrayList<>();

    public void push(int el) {
        els.add(el);
    }

    public int pop() throws Exception {
        if (this.isEmpty()) throw new Exception("underflow");
        int lastIndex = els.size() - 1;
        return els.remove(lastIndex);
    }

    public int peek() throws Exception {
        if (this.isEmpty()) throw new Exception("undeflow");
        int lastIndex = els.size() -1;
        return els.get(lastIndex);
    }

    public boolean isEmpty() {
        return els.isEmpty();
    }

    public int size() {
        return els.size();
    }

    @Override
    public String toString() {
        return "StackUsingArrayList{" +
                "els=" + els +
                '}';
    }
}


class Test2 {
    public static void main(String[] args) throws Exception {
        StackUsingArrayList stack = new StackUsingArrayList();
        stack.push(5);
        stack.push(15);
        stack.push(25);

        System.out.println(stack);

        System.out.println(stack.peek());
        System.out.println(stack.pop());

        stack.push(35);
        System.out.println(stack);
        System.out.println(stack.size());
        System.out.println(stack.isEmpty());
    }
}
