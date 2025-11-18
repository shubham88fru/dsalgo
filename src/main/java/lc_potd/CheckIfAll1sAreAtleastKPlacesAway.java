package lc_potd;

//@link - https://leetcode.com/problems/check-if-all-1s-are-at-least-length-k-places-away/description/?
public class CheckIfAll1sAreAtleastKPlacesAway {
    public boolean kLengthApart(int[] nums, int k) {
        // return revise(nums, k);
        return optimal(nums, k);
    }

    /**
        Better.
     */
    private boolean optimal(int[] nums, int k) {
        int n = nums.length;

        int pi = -1;
        for (int i=0; i<n; i++) {
            if (nums[i] == 1) {
                if (pi != -1 && i-pi-1 < k) return false;
                pi = i;
            }
        }

        return true;
    }

    /**
     My initial intuition.
     But this is dumb, tbh.
     Why did I decide to keep track of
     the dist and check at the end? :/
     We can return early.
     */
    private boolean revise(int[] nums, int k) {
        int n = nums.length;

        int pi = -1;
        int dist = Integer.MAX_VALUE;
        for (int i=0; i<n; i++) {
            if (nums[i] == 1) {
                if (pi != -1) {
                    dist = Math.min(i-pi-1, dist);
                }
                pi = i;
            }
        }

        return dist >= k;
    }
}
