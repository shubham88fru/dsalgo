package misc;

import java.util.TreeSet;

//Given an array, we need to find the closest
//higher value on the right side for each element
public class CeilingOnRightSide {

    //T: theta(N^2), S: O(1)
    void printCeilingOnRightSideNaive(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            int diff = Integer.MAX_VALUE;

            for (int j = i + 1; j < n; j++)
                if (arr[j] >= arr[i])
                    diff = Math.min(diff, arr[j] - arr[i]);

            if (diff == Integer.MAX_VALUE)
                System.out.print(-1 + " ");
            else
                System.out.print((arr[i] + diff) + " ");
        }
    }

    //T: O(nlogn), S: Theta(N)
    void printCeilingOnRightSide(int[] arr) {
        int n = arr.length;
        TreeSet<Integer> treeSet
                = new TreeSet<>();

        int[] res = new int[n];
        for (int i=n-1; i>=0; i--) {
            Integer ceil = treeSet.ceiling(arr[i]);
            if (ceil==null)
                res[i] = -1;
            else
                res[i] = ceil;
            treeSet.add(arr[i]);
        }

        for (int x: res)
            System.out.print(x+" ");
        //treeSet.
    }

    public static void main(String[] args) {
        CeilingOnRightSide ceilingOnRightSide
                = new CeilingOnRightSide();
        int[] arr = {100, 50, 30, 60, 55, 32};
        ceilingOnRightSide.
                printCeilingOnRightSideNaive(arr);

        System.out.println();
        ceilingOnRightSide.
                printCeilingOnRightSide(arr);
    }
}
