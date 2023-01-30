package swd.hashmap;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

//@link - https://leetcode.com/problems/word-pattern/description/
public class WordProblem {
    public boolean wordPattern(String pattern, String s) {
        Map<Character, Integer> patternmap = new HashMap<>();
        Map<String, Integer> smap = new HashMap<>();

        String[] sarr = s.split(" ");
        if (pattern.length() != sarr.length) return false;

        for (int i=0; i<pattern.length(); i++) {
            String sel = sarr[i];
            Character pel = pattern.charAt(i);
            if (patternmap.containsKey(pel)) {
                //if current index's entry already in pattern's map,
                //it should also be in string's map and that too, the same indexe's entry.
                if (!smap.containsKey(sel) || (!Objects.equals(smap.get(sel), patternmap.get(pel)))) return false;
            } else {
                //If no entry present in pattern map,
                //It shouldn't be in string's map as well.
                //We put the entry against its index, so later if we
                //encounter an already seen entry, we can check if both maps have entries from same index.
                patternmap.put(pel, i);
                if (smap.containsKey(sel)) return false;
                smap.put(sel, i);
            }
        }
        return true;
    }

    public static void main(String[] args) {
        WordProblem wordProblem = new WordProblem();
        wordProblem.wordPattern("ccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccdd"
                ,"s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s t t");

    }
}
