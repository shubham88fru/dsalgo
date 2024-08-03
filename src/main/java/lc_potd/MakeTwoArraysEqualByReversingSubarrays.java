package lc_potd;

import java.util.HashMap;
import java.util.Map;

//@link - https://leetcode.com/problems/make-two-arrays-equal-by-reversing-subarrays/
public class MakeTwoArraysEqualByReversingSubarrays {
    public boolean canBeEqual(int[] target, int[] arr) {
        // return myBadSol(target, arr);
        return myGoodSol(target, arr);
    }

    private boolean myGoodSol(int[] target, int[] arr) {
        Map<Integer, Integer> mp = new HashMap<>();
        for (int tg: target) {
            mp.put(tg, mp.getOrDefault(tg, 0)+1);
        }

        //If all elements from first array are
        //contained in the second array, then there will
        //certainly be a way to jumble the second array back
        //to the first array.
        for (int ar: arr) {
            if (!mp.containsKey(ar)) return false;
            mp.put(ar, mp.get(ar)-1);
            if (mp.get(ar) == 0) mp.remove(ar);
        }

        return mp.isEmpty();

    }

    private boolean myBadSol(int[] target, int[] arr) {
        for (int i=0; i < target.length; i++) {
            boolean found = false;
            for (int j=i; j < arr.length; j++) {
                if (arr[j] == target[i]) {
                    found = true;
                    reverse(arr, i, j);
                    break;
                }

            }
            if (!found) return false;
        }

        return true;
    }

    private void reverse(int[] arr, int start, int end) {

        while (start <= end) {
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start += 1;
            end -= 1;
        }
    }
}
