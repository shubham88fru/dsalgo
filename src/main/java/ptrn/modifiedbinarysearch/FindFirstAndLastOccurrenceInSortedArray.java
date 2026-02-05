package ptrn.modifiedbinarysearch;

//@link - https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/description/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/6359810766536704
public class FindFirstAndLastOccurrenceInSortedArray {
    public int[] searchRange(int[] nums, int target) {
        // return linearSearch(nums, target);
        return binarySearch(nums, target);
    }

    private int[] binarySearch(int[] nums, int target) {
        int n = nums.length;

        int f = binarySearchLowerBound(nums, target);
        if (f == -1) return new int[]{-1, -1};

        int s = binarySearchUpperBound(nums, target);
        return new int[]{f, s};
    }

    private int binarySearchLowerBound(int[] nums, int target) {
        int n = nums.length;
        int l=0, r=n-1;

        int f = -1;
        while (l <= r) {
            int mid = l + (r-l)/2;
            if (nums[mid] == target) {
                f = mid;
                r = mid - 1;
            } else if (nums[mid] > target) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }

        return f;
    }

    private int binarySearchUpperBound(int[] nums, int target) {
        int n = nums.length;
        int l=0, r=n-1;

        int s = -1;
        while (l <= r) {
            int mid = l + (r-l)/2;
            if (nums[mid] == target) {
                s = mid;
                l = mid + 1;
            } else if (nums[mid] > target) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }

        return s;
    }

    private int[] linearSearch(int[] nums, int target) {
        int n = nums.length;
        int f = -1, s = -1;

        for (int i=0; i<n; i++) {
            if (nums[i] == target) {
                if (f == -1) f = i;
                s = i;
            }
        }

        return new int[]{f, s};
    }
}
