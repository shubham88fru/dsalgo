package lc_potd;

import java.util.HashMap;
import java.util.Map;

//@link - https://leetcode.com/problems/maximum-score-words-formed-by-letters/description/
public class MaximumScoreWordsFormedByLetters {
    public int maxScoreWords(String[] words, char[] letters, int[] score) {
        Map<Character, Integer> mp = new HashMap<>();
        for (char letter: letters) {
            mp.put(letter, mp.getOrDefault(letter, 0)+1);
        }

        //for each word, store the freq of each char in the word.
        Map<String, Map<Character, Integer>> freqs = new HashMap<>();
        //for each word, calculate its score.
        Map<String, Integer> scores = new HashMap<>();
        for (String word: words) {
            int sc = 0;
            if (!freqs.containsKey(word)) {
                freqs.put(word, new HashMap<>());
            }
            for (char ch: word.toCharArray()) {
                sc += score[ch-'a'];
                freqs.get(word).put(ch, freqs.get(word).getOrDefault(ch, 0)+1);
            }
            scores.put(word, sc);
        }



        int[] maxScore = {0};
        max(words, letters, scores, freqs, 0, 0, maxScore, mp);

        return maxScore[0];
    }

    private void max(String[] words, char[] letters, Map<String, Integer> scores, Map<String, Map<Character, Integer>> freqs,
                     int currScore, int curr, int[] maxScore, Map<Character, Integer> mp) {

        //if formed a subset (array exhausted), calculate max score.
        if (curr >= words.length) {
            maxScore[0] = Math.max(maxScore[0], currScore);
            return;
        }

        //choice - pick curr word in subset.
        if (canPick(words[curr], mp, freqs)) {
            //use the chars in words[curr] from mp.
            updateMap(words[curr], mp, freqs);

            max(words, letters, scores, freqs, currScore+scores.get(words[curr]), curr+1, maxScore, mp);

            //backtrack and add chars in words[curr] to mp.
            backTrackMap(words[curr], mp, freqs);
        }

        //choice - don't pick current word in the subset.
        max(words, letters, scores, freqs, currScore, curr+1, maxScore, mp);
    }

    private void updateMap(String word, Map<Character, Integer> mp,
                           Map<String, Map<Character, Integer>> freqs) {

        Map<Character, Integer> freq = freqs.get(word);
        for (Map.Entry<Character, Integer> entry: freq.entrySet()) {
            char key = entry.getKey();
            int val = entry.getValue();
            mp.put(key, mp.get(key)-val); //subtract the freq of char from chosen word.
        }
    }

    private void backTrackMap(String word, Map<Character, Integer> mp,
                              Map<String, Map<Character, Integer>> freqs) {

        Map<Character, Integer> freq = freqs.get(word);
        for (Map.Entry<Character, Integer> entry: freq.entrySet()) {
            char key = entry.getKey();
            int val = entry.getValue();
            mp.put(key, mp.get(key)+val); //add back the freq of char from chosen word.
        }
    }

    private boolean canPick(String word, Map<Character, Integer> mp,
                            Map<String, Map<Character, Integer>> freqs) {
        Map<Character, Integer> freq = freqs.get(word);
        for (Map.Entry<Character, Integer> entry: freq.entrySet()) {
            char key = entry.getKey();
            int val = entry.getValue();
            //if freq of any char in current word is more
            //than that's available, can't pick it then.
            if (!mp.containsKey(key)) return false;
            if (val > mp.get(key)) return false;
        }

        return true;
    }
}
