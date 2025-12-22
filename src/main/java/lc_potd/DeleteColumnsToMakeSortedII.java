package lc_potd;

//@link - https://leetcode.com/problems/delete-columns-to-make-sorted-ii/description/?
//@check - https://www.youtube.com/watch?v=tkDcJ-F7foI
public class DeleteColumnsToMakeSortedII {
    public int minDeletionSize(String[] strs) {
        return mikssol(strs);
    }

    private int mikssol(String[] strs) {
        int n = strs.length;
        int m = strs[0].length();
        int count = 0;
        boolean[] sorted = new boolean[n-1];
        for (int j=0; j<m; j++) {
            boolean delete = false;
            for (int i=0; i<n-1; i++) {
                if (!sorted[i] && strs[i].charAt(j) > strs[i+1].charAt(j)) {
                    delete = true;
                    count += 1;
                    break;
                }
            }

            if (delete) continue;
            //note when consecutive chars in the column
            //are same, we're not marking the row sorted.
            //the row is marked sorted strictly when curr
            //row char is smaller than the next row char.
            for (int k=0; k<n-1; k++) sorted[k] |= (strs[k].charAt(j) < strs[k+1].charAt(j));
        }

        return count;
    }

    /**
     Deleted my nasty code.
     Tried a lot from my end,
     but wasn't able to solve.
     */
    private int pass1(String[] strs) {
        return -1;
    }
}
