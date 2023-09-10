package swd.binarysearch;

import java.util.ArrayList;
import java.util.List;

//@link - https://leetcode.com/problems/find-k-closest-elements/description/
public class FindKClosestElementsInASortedArray {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int n = arr.length;

        //Step 1: Find the floor of x. i.e closest element to x in the array.
        int floorIndex = binarySearch(arr, x);

        //Step 2: use 2 pointer approach.
        //left bound is 0 or k before floor (whichever is greater)
        //right bound is n or k after floor (whichever is lower)
        int left = ((floorIndex - k) < 0) ? 0 : (floorIndex - k);
        int right = ((floorIndex + k) >= n) ? (n - 1) : (floorIndex + k);

        //keep shrinking the size of window untill the window has exactly k elements.
        //if distance of value at left is lower than distance of
        //value at right, shrink from right side because we need the closer value.
        //and vice versa.
        while ((right - left + 1) > k) {
            int valAtLeft = arr[left];
            int valAtRight = arr[right];

            if (Math.abs(x-valAtLeft) <= Math.abs(x-valAtRight)) {
                right -= 1;
            } else {
                left += 1;
            }
        }

        //Step 3: Finally add element in the window inside a
        //list and return.
        List<Integer> ans = new ArrayList<>();
        for (int i=left; i<=right; i++) {
            ans.add(arr[i]);
        }

        return ans;

    }

    private int binarySearch(int[] arr, int x) {
        int start = 0;
        int end = arr.length - 1;

        int floor = -1;
        while (start <= end) {
            int mid = (start + (end-start)/2);

            if (arr[mid] <= x) {
                floor  = mid;
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return floor;
    }
}
