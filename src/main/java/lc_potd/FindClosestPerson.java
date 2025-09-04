package lc_potd;

//@link - https://leetcode.com/problems/find-closest-person/
public class FindClosestPerson {
    public int findClosest(int x, int y, int z) {
        return pass1(x, y, z);
    }

    private int pass1(int x, int y, int z) {
        int xz = Math.abs(z-x);
        int yz = Math.abs(z-y);

        if (xz == yz) return 0;
        return xz > yz ? 2: 1;
    }
}
