package swd.recursionbacktracking;

import java.util.ArrayList;

//@link - https://practice.geeksforgeeks.org/problems/find-all-possible-paths-from-top-to-bottom/1
public class AllPossiblePathsFromTopToBottomOfMatrix {
    public ArrayList<ArrayList<Integer>> findAllPossiblePaths(int n, int m, int[][] grid) {
        // code here
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        allPossiblePaths(n, m, 0, 0, grid, res, new ArrayList<>());
        return res;
    }

    private void allPossiblePaths(int n, int m, int i, int j, int[][] grid, ArrayList<ArrayList<Integer>> res, ArrayList<Integer> aMove) {
        if (i >= n || j >= m) return;

        if (i == n-1 && j == m-1) {
            aMove.add(grid[i][j]);
            res.add(new ArrayList<>(aMove));
            aMove.remove(aMove.size()-1);
            return;
        }

        aMove.add(grid[i][j]);
        allPossiblePaths(n, m, i+1, j, grid, res, aMove);
        allPossiblePaths(n, m, i, j+1, grid, res, aMove);
        aMove.remove(aMove.size()-1);
    }
}
