package lc_potd;

//@link - https://leetcode.com/problems/subarray-product-less-than-k/description/?
public class SubarrayProductLessThanK {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        // return brute(nums, k);
        return optimal(nums, k);
    }

    /**
     My sliding window approach.
     An important catch is to keep adding 'new'
     subarrays while acquiring the window.

     NOTE: Every new element added to a subarray
     of size `n` adds `n+1` new subarrays.

     Furthermore, sliding window works here
     only because of the constraints that
     nums[i] is always positive, which
     guarantees that running product is
     increasing.
     */
    private int optimal(int[] nums, int k) {
        int n = nums.length;
        int l=0, r=0;
        int rp = 1; //running product
        int count = 0;
        while (r < n) {
            while (r < n && rp*nums[r] < k) {
                count += (r-l+1);
                rp *= nums[r];
                r += 1;
            }

            //This mess handle edge cases.
            //can't simply do rp /= nums[l]
            if (r-l+1 > 1) { //something was acquired.
                rp /= nums[l];
                l += 1;
            } else { //empty window.
                rp = 1;
                r += 1;
                l = r;
            }
        }

        return count;
    }

    private int brute(int[] nums, int k) {
        int n = nums.length;
        int count = 0;
        for (int i=0; i<n; i++) {
            int rp = 1;
            for (int j=i; j<n; j++) {
                rp *= nums[j];
                if (rp >= k) break;
                count += 1;
            }
        }

        return count;
    }
}
