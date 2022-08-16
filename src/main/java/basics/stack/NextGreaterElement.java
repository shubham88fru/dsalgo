package basics.stack;

import java.util.ArrayDeque;
import java.util.Deque;

//Find first instance of next
//greater element.
public class NextGreaterElement {

    //T:O(N^2), S: O(1)
    void nextGreaterElementNaive(int[] arr) {
        for (int i=0; i<arr.length; i++) {
            int ng = -1;
            for (int j=i+1; j<arr.length;j++) {
                if (arr[j]>arr[i]) {
                    ng = arr[j];
                    break;
                }
            }
            System.out.print(ng+" ");
        }
    }

    //T: Theta(N), S: Theta(N)
    void nextGreaterElement(int[] arr) {
        Deque<Integer> stack = new ArrayDeque<>();
        int n = arr.length;
        stack.push(arr[n-1]);
        int[] res = new int[n];
        res[n-1] = -1;
        for (int i=n-2; i>=0;i--) {
            while (!stack.isEmpty() && stack.peek() <= arr[i]) {
                stack.pop();
            }
            res[i] = stack.isEmpty() ? -1: stack.peek();
            stack.push(arr[i]);
        }
        for (int i=0; i<n;i++) {
            System.out.print(res[i]+" ");
        }
    }

    public static void main(String[] args) {
        NextGreaterElement nextGreaterElement =
                new NextGreaterElement();
        int[] arr = new int[] {5, 15, 10, 8, 6, 12, 7};
        nextGreaterElement.nextGreaterElement(arr);
        System.out.println();
        System.out.println("----------------");
        nextGreaterElement.nextGreaterElementNaive(arr);

    }
}
