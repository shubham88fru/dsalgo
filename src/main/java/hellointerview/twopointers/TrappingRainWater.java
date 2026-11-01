package hellointerview.twopointers;

//@link - https://www.hellointerview.com/learn/code/two-pointers/trapping-rain-water
public class TrappingRainWater {
    public int trappingWater(int[] height) {
        // return brute(height);
        // return better(height);
        return optimal(height);
    }

    private int optimal(int[] height) {
        int n = height.length;
        int l=0, r=n-1, lm=0, rm=0, water = 0;
        while (l <= r) {
            if (height[l] <= height[r]) {
                if (height[l] >= lm) lm = height[l];
                else water += (lm-height[l]);
                l += 1;
            } else {
                if (height[r] >= rm) rm = height[r];
                else water += (rm-height[r]);
                r -= 1;
            }
        }

        return water;
    }

    private int better(int[] height) {
        int n = height.length;
        int[] pm = new int[n], sm = new int[n];
        pm[0] = height[0];
        sm[n-1] = height[n-1];

        for (int i=1; i<n; i++) {
            pm[i] = Math.max(height[i], pm[i-1]);
            sm[n-1-i] = Math.max(height[n-1-i], sm[n-i]);
        }

        int water = 0;
        for (int i=0; i<n; i++) {
            water += (Math.min(pm[i], sm[i])-height[i]);
        }

        return water;
    }

    private int brute(int[] height) {
        int n = height.length;

        int water = 0;
        for (int i=0; i<n; i++) {
            int lb = 0;
            for (int j=0; j<=i; j++) {
                lb = Math.max(height[j], lb);
            }

            int rb = 0;
            for (int j=i; j<n; j++) {
                rb = Math.max(height[j], rb);
            }

            water += (Math.min(rb, lb)-height[i]);
        }

        return water;
    }
}
