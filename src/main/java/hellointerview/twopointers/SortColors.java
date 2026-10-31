package hellointerview.twopointers;

//@link - https://www.hellointerview.com/learn/code/two-pointers/sort-colors
public class SortColors {
    public void sortColors(int[] nums) {
        // suboptimal(nums);
        dnf(nums);
    }

    private void dnf(int[] nums) {
        int n = nums.length;

        /*
            [0..l-1] -> 0
            [l..mid-1] -> 1
            [mid..r] -> unsorted
            [r+1..n-1] -> 2
        */
        int l=0, mid=0, r=n-1;
        while (mid <= r) {
            if (nums[mid] == 0) {
                nums[mid] = nums[l];
                nums[l] = 0;
                l += 1;
                mid += 1;
            } else if (nums[mid] == 1) {
                mid += 1;
            } else {
                nums[mid] = nums[r];
                nums[r] = 2;
                r -= 1;
            }
        }
    }

    private void suboptimal(int[] nums) {
        int n = nums.length;
        int z = 0;
        for (int i=0; i<n; i++) {
            if (nums[i] == 0) {
                int temp = nums[z];
                nums[z] = 0;
                nums[i] = temp;
                z += 1;
            }
        }

        int o = z;
        for (int i=o; i<n; i++) {
            if (nums[i] == 1) {
                int temp = nums[o];
                nums[o] = 1;
                nums[i] = temp;
                o += 1;
            }
        }

        int t = o;
        for (int i=t; i<n; i++) {
            if (nums[i] == 2) {
                int temp = nums[t];
                nums[t] = 1;
                nums[i] = temp;
                t += 1;
            }
        }
    }
}
