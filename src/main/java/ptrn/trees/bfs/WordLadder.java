package ptrn.trees.bfs;

import java.util.*;

//@link - https://leetcode.com/problems/word-ladder/description/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/6012440393023488
public class WordLadder {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // return dfsSol(beginWord, endWord, wordList);
        return bfs(beginWord, endWord, wordList);
    }

    //1) Works but is still slow.
    //This is mostly my soln, with a slight hint from edctv to keep removing
    //words that have been used from the wordList. Without doing that, BFS also
    //gives TLE. Edctv soln is very different and didin't get it completely.
    private int bfs(String beginWord, String endWord, List<String> wordListList) {
        Deque<Pair> q = new ArrayDeque<>();
        Set<String> wordList = new HashSet<String>(wordListList);
        if (!wordList.contains(endWord)) return 0;
        q.addLast(new Pair(beginWord, 0)); //start on level 0.
        Set<String> visited = new HashSet<>();
        visited.add(beginWord);
        int level = 0;
        while (!q.isEmpty()) {
            Pair pair = q.removeFirst();
            if (pair.word.equals(endWord)) return pair.level+1;
            visited.add(pair.word);
            level = pair.level;

            List<String> w = new ArrayList<>();
            for (String dictWord: wordList) {

                if (visited.contains(dictWord)) continue;
                if (diff(pair.word, dictWord)) {
                    w.add(dictWord);
                    q.addLast(new Pair(dictWord, level+1));
                }
            }

            wordList.removeAll(w);
        }

        return 0;
    }

    //1) My dfs sol - Gives TLE.
    private int dfsSol(String beginWord, String endWord, List<String> wordList) {
        String word = beginWord;
        int ans = dfs(beginWord, endWord, wordList, new HashSet<>());
        return ans == 1000000 ? 0: ans+1;
    }

    private int dfs(String word, String endWord, List<String> wordList, Set<String> visited ) {
        if (word.equals(endWord)) return 0;
        List<String> oneDWords = oneDWords(wordList, word, visited);
        if (oneDWords.size() < 1) return 1000000;

        int minPath = 1000000;
        for (String oneDWord: oneDWords) {
            visited.add(oneDWord);
            int path = 1 + dfs(oneDWord, endWord, wordList, visited);
            visited.remove(oneDWord);
            minPath = Math.min(minPath, path);
        }

        return minPath;
    }

    private List<String> oneDWords(List<String> wordList, String word, Set<String> visited ) {
        List<String> oneDWords = new ArrayList<>();
        for (String dictWord: wordList) {
            if (dictWord.equals(word) || visited.contains(dictWord)) continue;
            if (diff(word, dictWord)) oneDWords.add(dictWord);
        }

        return oneDWords;
    }

    private boolean diff(String dictWord, String word) {
        int diffCount = 0;
        for (int i=0; i<dictWord.length(); i++) {

            if (dictWord.charAt(i) != word.charAt(i)) {
                diffCount += 1;
                if (diffCount > 1) return false;
            }
        }

        return diffCount == 1;
    }
}

class Pair {
    String word;
    int level;

    public Pair(String word, int level) {
        this.level = level;
        this.word = word;
    }
}