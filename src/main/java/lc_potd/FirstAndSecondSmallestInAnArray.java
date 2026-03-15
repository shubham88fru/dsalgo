package lc_potd;

import java.util.ArrayList;

//@link - https://www.geeksforgeeks.org/problems/find-the-smallest-and-second-smallest-element-in-an-array3226/1
public class FirstAndSecondSmallestInAnArray {
    public ArrayList<Integer> minAnd2ndMin(int[] arr) {
        // code here
        return pass1(arr);
    }

    private ArrayList<Integer> pass1(int[] arr) {
        int n = arr.length;

        int sm = Integer.MAX_VALUE, ssm = Integer.MAX_VALUE;
        for (int i=0; i<n; i++) {
            if (arr[i] < sm) {
                ssm = sm;
                sm = arr[i];
            } else if (arr[i] < ssm && arr[i] > sm) {
                ssm = arr[i];
            }
        }

        ArrayList<Integer> ans = new ArrayList<>();
        if (sm == Integer.MAX_VALUE || ssm == Integer.MAX_VALUE) {
            ans.add(-1);
            return ans;
        }

        ans.add(sm);
        ans.add(ssm);
        return ans;
    }
}
