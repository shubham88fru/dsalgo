package basics.stack;

import java.util.ArrayDeque;
import java.util.Deque;

//Find first instance of previous
//greater element.
public class PreviousGreaterElement {

    //T: O(N^2), S:Theta(1)
    void previousGreaterNaive(int[] arr) {
        for (int i=0; i<arr.length; i++) {
            int pg = -1;
            for (int j=i-1; j>=0; j--) {
                if (arr[j] > arr[i]) {
                    pg = arr[j];
                    break;
                }
            }
            System.out.print(pg+" ");
        }
    }

    //T:Theta(N), S:O(N)
    void previousGreater(int[] arr) {
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(arr[0]);
        int pg = -1;
        System.out.print(pg+ " ");
        for (int i=1; i<arr.length; i++) {
            while (!stack.isEmpty() && stack.peek()<=arr[i]) {
                stack.pop();
            }
            pg = stack.isEmpty() ? -1: stack.peek();
            System.out.print(pg+ " ");
            stack.push(arr[i]);
        }
    }

    public static void main(String[] args) {
        PreviousGreaterElement previousGreaterElement =
                new PreviousGreaterElement();
        int[] arr = new int[] {20, 30, 10, 5, 15};
        previousGreaterElement.previousGreaterNaive(arr);
        System.out.println();
        System.out.println("-------------");
        previousGreaterElement.previousGreater(arr);
    }
}
