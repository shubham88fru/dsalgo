package swd.graphs;

import java.util.ArrayList;
import java.util.List;

//@link - https://leetcode.com/problems/word-search-ii/description/
public class WordSearchII {

    //-------------------------------------
    // 1) My & SWD Soln. Gives TLE, though.
    //--------------------------------------
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


    //-------------------
    //2) Soln using trie.
    //-------------------
    public List<String> findWords2(char[][] board, String[] words) {
        TrieNodeOfAlphabets root = new TrieNodeOfAlphabets();

        //Create a trie from the given words
        //and while searching for those in the board,
        //proceed with seach only if the characters are aliging
        //with what we have in the trie.
        for (String word: words) {
            insertWord(root, word);
        }

        List<String> ans = new ArrayList<>();
        int m = board.length;
        int n = board[0].length;


        //start searching in the board,
        //and proceed only if the characters align
        //with the words that we have stored in the trie.
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                searchAndAdd(board, ans, i, j, root, m, n);
            }
        }

        return ans;
    }

    private void searchAndAdd(char[][] board, List<String> ans, int row, int col, TrieNodeOfAlphabets root, int rowSize, int colSize) {
        //means we have found a word.
        //add that word to the answers list
        //and mark the word as null in the trie
        //so that we dont add it again.
        if (root.word != null) {
            ans.add(root.word);
            root.word = null;
        }

        //out of bound and already visited case.
        if (row < 0 || row >= rowSize || col < 0 || col >= colSize || board[row][col] == '.') return;

        //current char of the board.
        char currentChar = board[row][col];

        //if current char on the board, is not present as a child
        //of current node in the trie, means the word isn't present
        //in the list of words to be searched. No point going ahead.
        //Simply return.
        if (root.children[currentChar - 'a'] == null) return;

        //otherwise, move to next node in the tie and match with
        //characters at up, down, left and right of current word.
        char temp = currentChar;
        board[row][col] = '.'; //mark the current word visited so we don't visit it again.

        searchAndAdd(board, ans, row-1, col, root.children[currentChar-'a'], rowSize, colSize);

        searchAndAdd(board, ans, row+1, col, root.children[currentChar-'a'], rowSize, colSize);

        searchAndAdd(board, ans, row, col-1, root.children[currentChar-'a'], rowSize, colSize);

        searchAndAdd(board, ans, row, col+1, root.children[currentChar-'a'], rowSize, colSize);

        //once explored all possibilities, backtrack.
        board[row][col] = temp;

    }

    private void insertWord(TrieNodeOfAlphabets crawler, String word) {
        for (char ch: word.toCharArray()) {
            if (crawler.children[ch-'a'] == null) {
                crawler.children[ch-'a'] = new TrieNodeOfAlphabets();
            }

            crawler = crawler.children[ch-'a'];
        }

        crawler.word = word;
    }
}

class TrieNodeOfAlphabets {
    TrieNodeOfAlphabets[] children;
    String word;

    public TrieNodeOfAlphabets() {
        children = new TrieNodeOfAlphabets[26];
    }
}
