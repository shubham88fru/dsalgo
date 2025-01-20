package lc_potd;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

//@link - https://leetcode.com/problems/valid-sudoku/
public class ValidSudoku {
    public boolean isValidSudoku(char[][] board) {
        return pass1(board);
    }

    /*
        This is my approach. Mik had a similar
        approach but slightly different.
        So maybe @check if this is a recurring problem
        for some company.
     */
    private boolean pass1(char[][] board) {
        Map<Integer, Set<Character>> rows = new HashMap<>();
        Map<Integer, Set<Character>> cols = new HashMap<>();
        Map<String, Set<Character>> smGrids = new HashMap<>();

        for (int i=0; i<9; i++) {
            for (int j=0; j<9; j++) {
                char curr = board[i][j];
                if (curr == '.') continue;

                if (!rows.containsKey(i)) rows.put(i, new HashSet<>());
                if (rows.get(i).contains(curr)) return false;
                rows.get(i).add(curr);

                if (!cols.containsKey(j)) cols.put(j, new HashSet<>());
                if (cols.get(j).contains(curr)) return false;
                cols.get(j).add(curr);

                String smGridKey = (i/3) + "" + (j/3);
                if (!smGrids.containsKey(smGridKey)) smGrids.put(smGridKey, new HashSet<>());
                if (smGrids.get(smGridKey).contains(curr)) return false;
                smGrids.get(smGridKey).add(curr);
            }
        }

        return true;
    }
}
