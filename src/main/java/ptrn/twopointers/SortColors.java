package ptrn.twopointers;

//@link - https://leetcode.com/problems/sort-colors/description/
//@strvr - https://takeuforward.org/data-structure/sort-an-array-of-0s-1s-and-2s/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/4599593117155328
public class SortColors {

    public void sortColors(int[] nums) {
        //arrangeColorsBetter(nums);
        arrangeColorsOptimal(nums);
    }

    /****************STRVR's solutions***********************/

    //1) Optimal Soln (Using the Dutch National Flag algorithm)
    /*
        -----------------------------
        Dutch National Flag algorithm
        -----------------------------
        - [0.. low-1] --> all 0s
        - [low, mid-1] --> all 1s
        - [mid, high] --> unsorted portion (mix of random 0, 1 and 2)
        - [high+1, n-1] --> all 2s
        Based on above rules, note that, the array is sorted
        in the range [0, mid-1] and [high+1, n-1]. All that we'd
        need to do is sort the unsorted portion i.e. [mid, high].
        Since array elements can only be one of 0, 1 and 2, we know
        that a[mid] will only have 3 possible values -

        if (a[mid] == 0)
            //swap(a[mid], a[low])
            //mid++, low++; //must have swapped 0 and 1

        if (a[mid] == 1)
            //mid++

        if (a[mid] == 2)
            //swap(a[mid], a[high])
            //high-- //could have swapped 0, 2 or 1, 2. So, we can only concretely move high.
     */
    private void arrangeColorsOptimal(int[] nums) {
        int n = nums.length;

        int low = 0;

        //to start with entire array is
        //considered unsorted.
        int mid = 0;
        int high = n-1;

        //till mid is less than high
        //we have unsorted elements.
        while (mid <= high) {
            if (nums[mid] == 0) {
                swap(mid, low, nums);
                //Per dutch national flag algo's assumption
                //nums[low] is 1, therefore after the above
                //swap, nums[mid] will be 1. And so, we can increment
                //mid as well.
                mid += 1;
                low += 1;
            } else if (nums[mid] == 1) {
                mid += 1;
            } else { //nums[mid] will be 2.
                swap(mid, high, nums);
                high -= 1;
            }
        }
    }

    //2) Better soln: 3 pass
    private void revise(int[] nums) {
        int n = nums.length;

        int i = 0;

        //sort 0
        for (int j = i; j<n; j++) {
            if (nums[j] == 0) {
                int numi = nums[i];
                nums[i] = 0;
                nums[j] = numi;
                i += 1;
            }
        }

        //sort 1
        for (int j = i; j<n; j++) {
            if (nums[j] == 1) {
                int numi = nums[i];
                nums[i] = 1;
                nums[j] = numi;
                i += 1;
            }
        }

        //sort 2
        for (int j = i; j<n; j++) {
            if (nums[j] == 2) {
                int numi = nums[i];
                nums[i] = 2;
                nums[j] = numi;
                i += 1;
            }
        }
    }

    //2) Better Soln (2-3 pass T: O(3N), S: O(1))
    private void arrangeColorsBetter(int[] nums) {
        int n = nums.length;
        //first arrange all zeros.
        int zeroEnd = arrangeInRange(0, n-1, nums, 0);

        //then arrange all 1 after the last index of 0
        int oneEnd = arrangeInRange(zeroEnd, n-1, nums, 1);

        //then arrange all 2s after the last index of 1.
        arrangeInRange(oneEnd, n-1, nums, 2);
    }

    private int arrangeInRange(int start, int end, int[] nums, int color) {
        int marker = start;
        for (int i=start; i<=end; i++) {
            if (nums[i] == color) {
                swap(i, marker, nums);
                marker += 1;
            }
        }

        return marker;
    }

    private void swap(int _atThis, int _atThat, int[] nums) {
        int temp = nums[_atThis];
        nums[_atThis] = nums[_atThat];
        nums[_atThat] = temp;
    }

    //3) Better soln. T: O(2N)
    //Since it is guaranteed that the array only consists of 0, 1 and 2,
    //we can have 3 variables to track each of 0, 1 and 2s frequency by
    //making a pass. Once we have the frequencies, we can simply replace
    //the indexes one by one (for 0s 1s and 2s) with the num of 0s 1s and 2s we have.

    //4) Brute force - Use a sorting algorithm (T: O(NlogN), S: O(N) in some sorting algo)
}
