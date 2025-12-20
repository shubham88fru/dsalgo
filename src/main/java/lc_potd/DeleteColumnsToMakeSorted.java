package lc_potd;

import java.util.Arrays;

//@link - https://leetcode.com/problems/delete-columns-to-make-sorted/?
public class DeleteColumnsToMakeSorted {
    public int minDeletionSize(String[] strs) {
        // return brute(strs);
        // return better(strs);
        return optimal(strs);
    }

    /**
     Traverse each column for every row.
     Optimal, doesn't need extra space.
     */
    private int optimal(String[] strs) {
        int n = strs.length;
        int m = strs[0].length();

        int count = 0;
        for (int j=0; j<m; j++) {
            for (int i=1; i<n; i++) {

                if (strs[i].charAt(j) < strs[i-1].charAt(j)) {
                    count += 1;
                    break; //next column.
                }
            }
        }

        return count;
    }

    /**
     My sol.
     Iterate column for each row.
     Suboptimal because needs extra space.
     */
    private int better(String[] strs) {
        int n = strs.length;
        int m = strs[0].length();
        int[] pos = new int[m];
        int count = 0;
        for (int i=0; i<n; i++) {
            for (int j=0; j<m; j++) {
                if (pos[j] == -1) continue;

                if ((int)strs[i].charAt(j) < pos[j]) {
                    pos[j] = -1;
                    count += 1;
                } else {
                    pos[j] = (int)strs[i].charAt(j);
                }
            }
        }

        return count;
    }

    /**
     Brute force.
     */
    private int brute(String[] strs) {
        int n = strs.length;
        int m = strs[0].length();

        int count = 0;
        for (int j=0; j<m; j++) {
            StringBuilder row = new StringBuilder();
            for (int i=0; i<n; i++) {
                row.append(strs[i].charAt(j));
            }
            char[] sorted = row.toString().toCharArray();
            Arrays.sort(sorted);
            if (!row.toString().equals(new String(sorted))) count += 1;
        }

        return count;
    }
}
