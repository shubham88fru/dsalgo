package strvr.arrays3;

//@link - https://leetcode.com/problems/reverse-pairs/description/
//@strvr - https://takeuforward.org/data-structure/count-reverse-pairs/#dsa_article_video_explanation
public class ReversePairs {
    public int reversePairs(int[] nums) {
        //return countReversePairsBrute(nums);
        return countReversePairsOptimal(nums);
    }

    //1) Optimal solution: T: O(2NlogN), S: O(N)
    private int countReversePairsOptimal(int[] nums) {
        int[] ans = {0};
        mergeSortAndFindTheCount(nums, 0, nums.length-1, ans);
        return ans[0];
    }

    //merge sort algorithm.
    private void mergeSortAndFindTheCount(int[] a, int low, int high,
                                          int[] ans) {
        if (low >= high) return;

        int mid = (high+low)/2;
        //sort left half.
        mergeSortAndFindTheCount(a, low, mid, ans);
        //sort right half.
        mergeSortAndFindTheCount(a, mid + 1, high, ans);
        //merge the halves.
        mergeWithGivenRange(a, low, mid, high, ans);

    }

    //merges two sorted ranges of the same array.
    private void mergeWithGivenRange(int[] a, int low, int mid,
                                     int high, int[] ans) {
        int i = low;
        int j = mid + 1;

        int idx = 0;
        int[] temp = new int[high-low+1];

        //count
        count(a, i, j, mid, high, ans);

        //merge sorted ranges [low, mid] and [mid+1, high]
        while (i <= mid && j <= high) {
            if (a[i] <= a[j]) {
                temp[idx++] = a[i];
                i += 1;
            } else {
                temp[idx++] = a[j];
                j += 1;
            }
        }


        while (i <= mid) {
            temp[idx++] = a[i];
            i += 1;
        }

        while (j <= high) {
            temp[idx++] = a[j];
            j += 1;
        }

        //copy the sorted range back to the original
        //array.
        for (int k=low; k<=high; k++) {
            a[k] = temp[k-low];
        }
    }

    private void count(int[] a, int i, int j, int mid, int high, int[] ans) {
        //`a` is sorted in the range [i, mid] and [mid+1, high]
        //iterate the left sorted array and for all valid pairs
        //keep moving the right array's pointer. Once condition
        //fails, all elements before the right array's pointer, will
        //form a pair with the curr element of the left array.
        //For, Next iteration, we'll start comparing only from
        //the curr pointer in right (and not from begining of right array)
        //since all the elements to prev of curr pointer in right will definitely
        //form a pair with the new left array element (because the new left array element
        //is greater than the prev left array element).
        while (i <= mid) {
            while ((j <= high) && (a[i]/2.0 > a[j])) {
                j += 1;
            }

            ans[0] += (j-(mid+1));
            i += 1;
        }
    }


    //2) Brute force solution: T: O(N^2), S: O(1)
    //will overflow when multiplying 2 with large nums.
    private int countReversePairsBrute(int[] nums) {
        int count = 0;
        for (int i = 0; i<nums.length; i++) {
            for (int j = i+1; j<nums.length; j++) {
                if (nums[i] > 2*nums[j]) count += 1;
            }
        }

        return count;
    }
}
