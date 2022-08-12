package basics.stack;

import java.util.Arrays;

//capacity - how many els it can have.
//size - how many els it has.
public class StackUsingArray {

    public class StackUnderFlowException extends Exception {
        public StackUnderFlowException() {
            super("Stack Underflow");
        }
    }

    public class StackOverFlowException extends Exception {
        public StackOverFlowException() {
            super("Stack Overflow");
        }
    }
    private final int[] arr;
    private int top;
    private final int cap;
    public StackUsingArray(int cap) {
        this.cap = cap;
        this.top = -1;
        arr = new int[cap];
    }

    public void push(int el) throws StackOverFlowException {
        if (this.top >= cap) throw new StackOverFlowException();
        this.top++;
        arr[top] = el;
    }

    public int pop() throws StackUnderFlowException {
        if (this.isEmpty()) throw new StackUnderFlowException();
        int temp = arr[top];
        arr[top] = 0; //assuming 0 means empty
        this.top--;
        return temp;
    }

    public int peek() throws StackUnderFlowException {
        if (this.isEmpty()) throw new StackUnderFlowException();
        return arr[top];
    }

    public int size() {
        return this.top+1;
    }

    public boolean isEmpty() {
        return this.top == -1;
    }

    @Override
    public String toString() {
        return "StackUsingArray{" +
                "arr=" + Arrays.toString(arr) +
                ", size=" + top +
                ", cap=" + cap +
                '}';
    }
}

class Test {
    public static void main(String[] args) throws StackUsingArray.StackOverFlowException, StackUsingArray.StackUnderFlowException {
        StackUsingArray stack = new StackUsingArray(10);
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
