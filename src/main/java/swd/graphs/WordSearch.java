package swd.graphs;

import java.util.HashMap;
import java.util.Map;

//@link - https://leetcode.com/problems/word-search/description/
public class WordSearch {

    //1) Using simple recursion/backtracking.
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

        boolean down = wordExists(board, word, charIdx+1, m, n, i+1, j, visited);

        boolean left = wordExists(board, word, charIdx+1, m, n, i, j-1, visited);

        boolean right = wordExists(board, word, charIdx+1, m, n, i, j+1, visited);

        //backtrack once all possibilites from curr location explored.
        visited[i][j] = false;

        return up || left || down || right;
    }

    //2) Using tries.
    public boolean exist2(char[][] board, String word) {
        TrieNode root = new TrieNode();
        insertWord(word, root);

        for (int i=0; i<board.length; i++) {
            for (int j=0; j<board[0].length; j++) {
                if (board[i][j] == word.charAt(0)) {
                    boolean ans = search(board, i, j, root);
                    if (ans) return true;
                }
            }
        }

        return false;
    }

    private boolean search(char[][] board, int row, int col, TrieNode root) {
        if (root.isEndOfAWord) return true;

        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length) {
            return false;
        }

        if (!root.children.containsKey(board[row][col])) return false;


        TrieNode nextRoot = root.children.get(board[row][col]);
        char temp = board[row][col];
        board[row][col] = '.';

        boolean up = search(board, row-1, col, nextRoot);

        boolean down = search(board, row+1, col, nextRoot);

        boolean left = search(board, row, col-1, nextRoot);

        boolean right = search(board, row, col+1, nextRoot);

        board[row][col] = temp;
        return (up || down || left || right);
    }

    private void insertWord(String word, TrieNode root) {
        TrieNode crawler = root;
        //iterate through every charachter in the string
        for (char ch: word.toCharArray()) {
            //if curr char not child of curr node,
            //add it as a child to curr node.
            if (!crawler.children.containsKey(ch)) {
                TrieNode trieNode = new TrieNode();
                crawler.children.put(ch, trieNode);
            }

            //and eitherways, move to the next node in the trie.
            crawler = crawler.children.get(ch);
        }

        //after all words done, mark the node as
        //end of word node.
        crawler.isEndOfAWord = true;
    }
}

class TrieNode {
    Map<Character, TrieNode> children;
    boolean isEndOfAWord;

    public TrieNode() {
        children = new HashMap<>();
    }
}
