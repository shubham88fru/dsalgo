package lc_potd;

//@link - https://leetcode.com/problems/rotate-array/description/?
public class RotateArray {
    public void rotate(int[] nums, int k) {
        // pass1(nums, k);
        // pass2(nums, k);
        // pass3(nums, k);
        optimal(nums, k);
    }

    /**
     Very intuitive once you see
     the soln. Based on LC editorial.
     Coded by me. The pro is that this
     approach doesn't take extra space.

     This approach is based on the fact that when
     we rotate the array k times, k elements from
     the back end of the array come to the front
     and the rest of the elements from the front
     shift backwards.
     */
    private void optimal(int[] nums, int k) {
        int n = nums.length;
        k %= n;
        reverse(nums, 0, n-1); //reverse entire array.
        reverse(nums, 0, k-1); //reverse first k.
        reverse(nums, k, n-1); //reverse remaining n-k.
    }

    /**
         All the below solutions are
         my approaches.

         Bellow is a O(n) TC soln.
         However, takes extra space.
     */
    private void pass3(int[] nums, int k) {
        int n = nums.length;
        int r = k%n;

        int[] ri = new int[n];
        for (int i=0; i<n; i++) {
            ri[(i+r)%n] = nums[i];
        }

        for (int i=0; i<n; i++) {
            nums[i] = ri[i];
        }
    }

    private void pass2(int[] nums, int k) {
        int n = nums.length;
        int mod = k%n;
        for (int i=1; i<=mod; i++) {
            int p = nums[n-1];
            for (int j=0; j<n; j++) {
                int curr = nums[j];
                nums[j] = p;
                p = curr;
            }
        }
    }

    private void pass1(int[] nums, int k) {
        int mod = k%nums.length;
        for (int i=1; i<=mod; i++) {
            // int last = nums[arr.length-1];
            for (int j=nums.length-1; j>=1; j--) {
                int temp = nums[j];
                nums[j] = nums[j-1];
                nums[j-1] = temp;
            }
        }
    }

    private void reverse(int[] nums, int i, int j) {

        while (i <= j) {
            int left = nums[i];
            nums[i] = nums[j];
            nums[j] = left;
            i += 1;
            j -= 1;
        }
    }
}
