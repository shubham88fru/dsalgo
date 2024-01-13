package ptrn.kwaymerge;

//@link - https://leetcode.com/problems/merge-sorted-array/description/
//@strvr - https://takeuforward.org/data-structure/merge-two-sorted-arrays-without-extra-space/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/4938339196338176
public class MergeSortedArrays {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        //mergeBrute(nums1, m, nums2, n);
        mergeOptimal(nums1, m, nums2, n);
    }

    //1) Optimal: T: O(M+N), S: O(1)
    //Without extra space.
    public void mergeOptimal(int[] nums1, int m, int[] nums2, int n) {
        int i=m-1; //last (largest element) of arr1
        int j=n-1; //last (largest element) of arr2
        int k=nums1.length-1; //will point at the last position of the nums1 array

        // Now traversing the nums2 array
        while(j>=0){
            // If element at i index of nums1 > element at j index of nums2
            // then it is largest among two arrays and will be stored at k position of nums1
            // using i>=0 to make sure we have elements to compare in nums1 array
            if(i>=0 && nums1[i]>nums2[j]){
                nums1[k]=nums1[i];
                k--;
                i--; //update first array's pointer.
            }else{
                // element at j index of nums2 array is greater than the element at i index of nums1 array
                // or there is no element left to compare with the nums1 array
                // and we just have to push the elements of nums2 array in the nums1 array.
                nums1[k] = nums2[j];
                k--;
                j--; //update second array's pointer.
            }
        }
    }


    //2) Brute force: T: O(M+N), S: O(M+N)
    //Use extra space for a third array.
    private void mergeBrute(int[] nums1, int m, int[] nums2, int n) {
        int i = 0;
        int j = 0;
        int k = 0;
        int[] sortedArr = new int[m+n];

        while (i < m && j < n) {
            if (nums1[i] < nums2[j]) {
                sortedArr[k] = nums1[i];
                i += 1;
            } else if (nums1[i] == nums2[j]) {
                sortedArr[k] = nums1[i];
                sortedArr[k+1] = nums1[i];
                i += 1;
                j += 1;
                k += 1;
            } else {
                sortedArr[k] = nums2[j];
                j += 1;
            }

            k += 1;
        }

        while (j < n) {
            sortedArr[k] = nums2[j];
            j += 1;
            k += 1;
        }

        while (i < m) {
            sortedArr[k] = nums1[i];
            i += 1;
            k += 1;
        }

        for (int l=0; l < m+n ; l++) {
            nums1[l] = sortedArr[l];
        }
    }
}
