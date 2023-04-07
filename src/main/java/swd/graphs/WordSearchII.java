package swd.graphs;

import java.util.ArrayList;
import java.util.List;

//@link - https://leetcode.com/problems/word-search-ii/description/
public class WordSearchII {

    /*** My & SWD Soln. Gives TLE, though. ***/
    public List<String> findWords(char[][] board, String[] words) {
        int m = board.length;
        int n = board[0].length;

        boolean[][] visited = new boolean[m][n];
        List<String> foundWords = new ArrayList<>();
        //Iterate through the list of words and keep
        //adding them to list if they're found.
        for (String word: words) {
            boolean ans = exist(board, word);
            if (ans) {
                foundWords.add(word);
            }
        }

        return foundWords;
    }


    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;

        boolean[][] visited = new boolean[m][n];
        //Iterate through the grid (unless the ans is found)
        //and search only if char at curr index is same as first
        //letter of string (because there's no point searching if the
        //first char itself is not matching)
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                if (board[i][j] == word.charAt(0)) {
                    boolean ans = wordExists(board, word, 0, m, n, i, j, visited);
                    if (ans) return true;
                }
            }
        }
        return false;
    }

    private boolean wordExists(char[][] board, String word, int charIdx, int m, int n, int i, int j, boolean[][] visited) {
        //whole string is processed. Means we have already found all chars.
        if (charIdx >= word.length()) return true;

        //Out of bounds.
        //Also, don't continue if the char at curr grid position is not matching the char of string.
        if (i < 0 || i >= m|| j < 0 || j >= n || visited[i][j] || (board[i][j] != word.charAt(charIdx))) return false;

        //otherwise, process the curr grid location, and serach its' up, down, left and right
        //for the next char of the string (i.e. charIdx+1)
        visited[i][j] = true;

        boolean up = wordExists(board, word, charIdx+1, m, n, i-1, j, visited);
        if (up) {
            visited[i][j] = false;
            return true;
        }

        boolean down = wordExists(board, word, charIdx+1, m, n, i+1, j, visited);
        if (down) {
            visited[i][j] = false;
            return true;
        }

        boolean left = wordExists(board, word, charIdx+1, m, n, i, j-1, visited);
        if (left) {
            visited[i][j] = false;
            return true;
        }

        boolean right = wordExists(board, word, charIdx+1, m, n, i, j+1, visited);
        if (right) {
            visited[i][j] = false;
            return true;
        }

        //backtrack once all possibilites from curr location explored.
        visited[i][j] = false;

        return up || left || down || right;
    }


    // private boolean wordExistsBFS(char[][] board, String word, int charIdx, int m, int n, int i, int j, boolean[][] visited) {
    //     Deque<String> queue = new ArrayDeque<>();

    //     queue.addLast(String.valueOf(word.charAt(charIdx)));

    //     visited[i][j] = true;
    //     String ans = "";

    //     while (!queue.isEmpty()) {
    //         String str = queue.removeFirst();

    //         if (word.equals(str)) return true;

    //         int upRow = i-1;
    //         int upCol = j;

    //         int downRow = i+1;
    //         int downCol = j;

    //         int leftRow = i;
    //         int leftCol = j-1;

    //         int rightRow = i;
    //         int rightCol = j+1;

    //         if (!(upRow < 0 || upRow >= m || upCol < 0 || upCol >= n || visited[upRow][upCol] || (board[upRow][upCol] != word.charAt(charIdx)))) {
    //             visited[upRow][upCol] = true;
    //             queue.addLast(str + String.valueOf(board[upRow][upCol]));
    //         }

    //         if (!(downRow < 0 || downRow >= m || downCol < 0 || downCol >= n || visited[downRow][downCol] || (board[downRow][downCol] != word.charAt(charIdx)))) {
    //             visited[downRow][downCol] = true;
    //             queue.addLast(str + String.valueOf(board[downRow][downCol]));
    //         }

    //         if (!(leftRow < 0 || leftRow >= m || leftCol < 0 || leftCol >= n || visited[leftRow][leftCol] || (board[leftRow][leftCol] != word.charAt(charIdx)))) {
    //             visited[leftRow][leftCol] = true;
    //             queue.addLast(str + String.valueOf(board[leftRow][leftCol]));
    //         }

    //         if (!(rightRow < 0 || rightRow >= m || rightCol < 0 || rightCol >= n || visited[rightRow][rightCol] || (board[rightRow][rightCol] != word.charAt(charIdx)))) {
    //             visited[rightRow][rightCol] = true;
    //             queue.addLast(str + String.valueOf(board[rightRow][rightCol]));
    //         }
    //     }

    //     return false;
    // }
}
