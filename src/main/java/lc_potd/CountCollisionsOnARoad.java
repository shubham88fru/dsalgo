package lc_potd;

//@link - https://leetcode.com/problems/count-collisions-on-a-road/description/?
public class CountCollisionsOnARoad {
    public int countCollisions(String directions) {
        // return pass1(directions);
        return pass2(directions);
    }

    /*
        Hinted sol.
     */
    private int pass2(String directions) {
        int n = directions.length();

        int l = 0;
        int r = n-1;

        while (l < n && directions.charAt(l) == 'L') l += 1; //can't be destroyed.
        while (r >= l && directions.charAt(r) == 'R') r -= 1; //can't be destroyed.

        /**
         In this window, no matter
         what whether `L` or `R`, it'll
         be destroyed.
         */
        int collisions = 0;
        for (int i=l; i<=r; i++) {
            if (directions.charAt(i) == 'S') continue;
            collisions += 1;
        }

        return collisions;
    }

    /**
     My first intuition was using stack.
     Turns out the code was getting very
     nasty and a lot of edge cases. Probably
     what makes the stack soln difficult
     is the fact that prev cars which hadn't
     been destroyed can get destroyed in future.

     Otherwise, the problem is extremely
     easy and its unfortunate that I couldn't
     solve it myself.
     */
}
