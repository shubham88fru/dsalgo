package ptrn.hashmap;

import java.util.HashMap;
import java.util.Map;

//@link - https://leetcode.com/problems/isomorphic-strings/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/6036448651509760
public class IsomorphicStrings {
    public boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) return false;

        Map<Character, Character> mp = new HashMap<>();
        Map<Character, Character> mp2 = new HashMap<>();
        for (int i=0; i<s.length(); i++) {
            char chs = s.charAt(i);
            char cht = t.charAt(i);
            if (!mp.containsKey(chs)) {
                mp.put(chs, cht);
            } else if (mp.get(chs) != cht) return false;

            if (!mp2.containsKey(cht)) {
                mp2.put(cht, chs);
            } else if (mp2.get(cht) != chs) return false;
        }

        return true;
    }
}
