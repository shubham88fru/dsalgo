package ptrn.modifiedbinarysearch;

import java.util.ArrayList;
import java.util.List;

//@link - https://leetcode.com/problems/find-k-closest-elements/description/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/5939927448813568
public class FindKClosestElementsInASortedArray {
    //1) optimal approach - using binary search.
    /**
     This solution can be further optimised by adding the following
     checks before starting the binary search :-
     1) If the length of nums is the same as the value of k, return all the elements.

     2) If target is less than or equal to the first element in nums,
     the first k elements in nums are the closest integers to target.
     For example, if nums= [1, 2, 3], target= 0, and k = 2,
     then the two closest integers to target are [1, 2].

     3) If target is greater than or equal to the last element in nums,
     the last k elements in nums are the closest integers to target.
     For example, if nums= [1, 2, 3], target= 4, and k = 2,
     then the two closest integers to target are [2, 3].

     Otherwise, we search for the k closest elements in the whole array.
    */
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int n = arr.length;

        //Step 1: Find the floor of x. i.e the closest element to x in the array.
        //in other words, the largest value smaller than or equal to x.
        int floorIndex = binarySearch(arr, x);

        //Step 2: use 2 pointer approach.
        //left bound is 0 or k before floor (whichever is greater)
        //right bound is n or k after floor (whichever is lower)
        int left = ((floorIndex - k) < 0) ? 0 : (floorIndex - k);
        int right = ((floorIndex + k) >= n) ? (n - 1) : (floorIndex + k);

        //keep shrinking the size of window until the window has exactly k elements.
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

    //2) suboptimal/binary search approach.
    //store each element and its distance from target as a pair
    //in a new array. sort this new array w.r.t distance and if
    //distances are same, sort them based on value of the element.
    //Finally, select the first k elements from this array.
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
