package misc;

import java.util.ArrayDeque;
import java.util.Deque;

//If size of input is `n` and we have
//`k` sized subarray, then total no. of
//subarrays will n-k+1.
public class MaxInAllSubarraysOfSizeK {

    //T: O((n-1+k)*k)
    void printMaxNaive(int[] arr, int k) {
        //n-1+k
        for (int i=0; i<=arr.length-k; i++) {
            int max = arr[i];
            for (int j=i+1; j<i+k; j++) {
                max = Math.max(max, arr[j]);
            }
            System.out.print(max+ " ");
        }
    }

    //T: Theta(N), S:Theta(K)
    void printMax(int[] arr, int k) {
        Deque<Integer> dq = new ArrayDeque<>();

        //only first window
        for (int i=0; i<k; i++) {
            //keep items in deque in dec order.
            while (!dq.isEmpty()
                    && arr[i]>=arr[dq.peekLast()])
                dq.removeLast();

            dq.addLast(i); //adding index instead of el.
        }

        //remaining windows
        for (int i=k; i<arr.length; i++) {
            //at this point for every window, first el of deque is max
            System.out.print(arr[dq.peek()]+" ");

            //remove all items that are not part of curr window.
            while (!dq.isEmpty() && dq.peekFirst() <= i-k)
                dq.removeFirst();

            while (!dq.isEmpty() && arr[i]>=arr[dq.peekLast()])
                dq.removeLast();

            dq.addLast(i);
        }

        //once done with loop, print max of last window
        System.out.print(arr[dq.peekFirst()]+" ");
    }

    public static void main(String[] args) {
        MaxInAllSubarraysOfSizeK maxK =
                new MaxInAllSubarraysOfSizeK();
        int[] arr = new int[] {10, 8, 5, 12, 15, 7, 6};
        maxK.printMaxNaive(arr, 4);
        System.out.println();

        maxK.printMax(arr, 4);
        System.out.println();

        arr = new int[] {20, 40, 30, 10, 60};
        maxK.printMax(arr, 3);
    }
}
