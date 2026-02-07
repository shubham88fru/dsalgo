package strvr.linkedlistandarray;

//@link - https://leetcode.com/problems/trapping-rain-water/description/
//@strvr - https://takeuforward.org/data-structure/trapping-rainwater/
public class TrappingRainWater {
    public int trap(int[] height) {
        //return trapBrute(height);
        //return trapBetter(height);
        return trapOptimal(height);
    }

    //1) Optimal soln - T: O(N), S: O(1)
    private int trapOptimal(int[] height) {
        int n = height.length;
        int left = 0;
        int right = n-1;
        int res = 0;
        int maxLeft = 0;
        int maxRight = 0;

        while (left <= right) {
            if (height[left] <= height[right]){
                if (height[left] >= maxLeft) maxLeft = height[left];
                else res += maxLeft-height[left];
                left += 1;
            } else {
                if (height[right] >= maxRight) maxRight = height[right];
                else res += maxRight-height[right];
                right -= 1;
            }
        }

        return res;
    }

    //2) Better soln - T: O(3N), S: O(2N)
    //using prefix max and suffix max array.
    private int trapBetter(int[] height) {
        int[] pm = new int[height.length]; //prefix max.
        pm[0] = height[0];
        //populate prefix max array for constant time lookup.
        for (int i=1; i<height.length;i++) {
            if (height[i] > pm[i-1]) {
                pm[i] = height[i];
            } else {
                pm[i] = pm[i-1];
            }
        }

        int[] sm = new int[height.length]; //suffix max.
        sm[height.length-1] = height[height.length-1];
        //populate suffix max array for constant time lookup.
        for (int i=height.length-2; i>=0; i--) {
            if (height[i] > sm[i+1]) {
                sm[i] = height[i];
            } else {
                sm[i] = sm[i+1];
            }
        }

        int qty = 0;
        for (int i=0; i<height.length; i++) {
            //every building can trap water equal to
            //minimum of max on left and max on right, minus its own height.
            //here, since we're using prefix and suffix array, the lookup is constant time.
            qty += (Math.min(pm[i], sm[i]) - height[i]);
        }
        return qty;
    }

    //3) Brute force - T: O(N^2), S: O(1)
    //calculate how much water each building can store and sum it up.
    private int trapBrute(int[] height) {
        int qty = 0;
        for (int i=0; i<height.length; i++) {
            //every building can trap water equal to
            //minimum of max on left and max on right, minus its own height.
            qty += (Math.min(maxOnLeft(i, height), maxOnRight(i, height)) - height[i]);
        }
        return qty;
    }

    //find maximum in range [0, idx]
    private int maxOnLeft(int idx, int[] height) {
        int max = Integer.MIN_VALUE;
        for (int i=0; i<= idx; i++) {
            if (height[i] > max) max = height[i];
        }
        return max;
    }

    //find maximum in range [idx, height.length]
    private int maxOnRight(int idx, int[] height) {
        int max = Integer.MIN_VALUE;
        for (int i=idx; i<=(height.length-1); i++) {
            if (height[i] > max) max = height[i];
        }
        return max;
    }
}
