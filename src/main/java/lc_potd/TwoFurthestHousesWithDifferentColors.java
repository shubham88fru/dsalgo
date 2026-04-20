package lc_potd;

//@link - https://leetcode.com/problems/two-furthest-houses-with-different-colors/?
public class TwoFurthestHousesWithDifferentColors {
    public int maxDistance(int[] colors) {
        // return brute(colors);
        // return better(colors);
        return morebetter(colors);
    }

    private int morebetter(int[] colors) {
        int n = colors.length;
        int d = 0;
        for (int i=1; i<n; i++) {
            if (colors[i] != colors[0] || colors[i] != colors[n-1]) {
                d = Math.max(d, Math.max(i, n-1-i));
            }
        }

        return d;
    }

    private int better(int[] colors) {
        int n = colors.length;
        int d = 0;
        for (int i=1; i<n; i++) {
            if (colors[i] != colors[0]) d = i;
        }

        for (int i=n-2; i>=0; i--) {
            if (colors[i] != colors[n-1]) d = Math.max(d, n-1-i);
        }

        return d;
    }

    private int brute(int[] colors) {
        int n = colors.length;
        int d = 0;

        for (int i=0; i<n; i++) {
            for (int j=i+1; j<n; j++) {
                if (colors[j] != colors[i]) d = Math.max(d, j-i);
            }
        }

        return d;
    }
}
