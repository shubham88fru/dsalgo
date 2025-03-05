package lc_potd;

//@link - https://leetcode.com/problems/count-total-number-of-colored-cells/
//@check - https://www.youtube.com/watch?v=LakDs5OBiGM&t=352s&ab_channel=codestorywithMIK
public class CountTotalNumberOfColoredCells {
    public long coloredCells(int n) {
        return pass1(n);
    }

    /*
        Had to take a slight hint from mik. But mik showed a
        different math soln which was O(1). So check if this is
        a recurring problem for some company.
        My first intuition was to solve it using
        BFS, I think BFS can be done for this problem.

        This soln is completely based on observation.
     */
    private long pass1(int n) {

        //By observation, for each given n, the
        //number of cells at the middle layer will be n + (n-1)
        //and then 1-1 layer of decrementing number of cells on each side.
        long middleLayer = n + (n-1);

        long cells = middleLayer;
        n = (int)middleLayer-2;
        while (n > 0) {
            cells += 2*n; //*2 because these layers will be on each side.
            n = n-2;
        }

        return cells;
    }
}
