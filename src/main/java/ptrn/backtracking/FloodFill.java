package ptrn.backtracking;

//@link - https://leetcode.com/problems/flood-fill/description/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/4960459742248960
public class FloodFill {
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        //return floodFill1(image, sr, sc, color);
        return floodFill2(image, sr, sc, color);
    }

    //1) swd solution
    private int[][] floodFill1(int[][] image, int sr, int sc, int color) {
        int m = image.length;
        int n = image[0].length;

        //color all the connected cells of same color
        //as that of starting cell with the new color.
        dfs(image, m, n, sr, sc, image[sr][sc], color);

        return image;
    }

    private void dfs(int[][] image, int m, int n, int i, int j, int origcolor, int color) {
        //Base case:
        //1) Out of bounds
        //2) if target cell already of new color.
        //3) if target cell not of the same of color as that of the starting index.
        //Since we are changing the og matrix directly, there's no need to keep a separate
        //visited matrix.
        if (i < 0 || i >= m || j < 0 || j >= n || image[i][j] == color || image[i][j] != origcolor) return;

        image[i][j] = color;

        int row, col;

        //Each vertex is connected to up, down, left and right.

        //up
        dfs(image, m, n, i-1, j, origcolor, color);

        //down
        dfs(image, m, n, i+1, j, origcolor, color);

        //right
        dfs(image, m, n, i, j+1, origcolor, color);

        //left
        dfs(image, m, n, i, j-1, origcolor, color);
    }

    //2) Edctv soln.
    private int[][] floodFill2(int[][] image, int sr, int sc, int color) {
        //already flood-filled.
        if (image[sr][sc] == color) return image;

        int ogColor = image[sr][sc];
        image[sr][sc] = color;
        dfs2(image, sr, sc, ogColor, color);

        return image;
    }

    private void dfs2(int[][] image, int row, int col, int ogColor, int target) {
        int[][] adjacentCells = { {0, 1}, { 1, 0 }, {-1, 0}, {0, -1} };
        int m = image.length;
        int n = image[0].length;

        for (int[] adjacent: adjacentCells) {
            int i = row + adjacent[0];
            int j = col + adjacent[1];

            if (i<m && i>=0 && j<n && j>=0 && image[i][j] == ogColor) {
                image[i][j] = target;
                dfs2(image, i, j, ogColor, target);
            }
        }
    }
}
