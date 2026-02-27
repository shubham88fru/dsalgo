package ptrn.knowingwhattotrack;

import java.util.*;

//@link - https://leetcode.com/problems/first-unique-character-in-a-string/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/6628285363781632
public class FirstUniqueCharacterInAString {
    public int firstUniqChar(String s) {
        return sol1(s);
    }

    //more straightforward. Edctv soln.
    //Form a frequency map. Iterate over the input
    //string again, and for each char check its freq.
    //For the first char for which we get a frequency of 1, is our answe.
    private int sol1(String s) {
        Map<Character, Integer> freq = new HashMap<>();
        for (int i=0; i<s.length(); i++) {
            char ch = s.charAt(i);
            freq.put(ch, freq.getOrDefault(ch, 0)+1);
        }

        for (int i=0; i<s.length(); i++) {
            char ch = s.charAt(i);
            if (freq.get(ch) == 1) return i;
        }

        return -1;
    }

    private int revise(String s) {
        int n = s.length();
        int[] freq = new int[26];

        for (int i=0; i<n; i++) {
            char ch = s.charAt(i);
            freq[ch-'a'] += 1;
        }

        for (int i=0; i<n; i++) {
            char ch = s.charAt(i);
            if (freq[ch-'a'] == 1) return i;
        }

        return -1;
    }

    //Using LinkedHashMap. My soln.
    //T: O(2n)
    private int sol2(String s) {
        Map<Character, List<Integer>> freq = new LinkedHashMap<>();

        for (int i=0; i<s.length(); i++) {
            char ch = s.charAt(i);
            if (!freq.containsKey(ch)) {
                freq.put(ch, new ArrayList<>());
            }
            freq.get(ch).add(i);
        }

        for (Map.Entry<Character, List<Integer>> entry: freq.entrySet()) {
            if (entry.getValue().size() == 1) return entry.getValue().get(0);
        }

        return -1;
    }
}
