package ptrn.knowingwhattotrack;

import java.util.HashMap;
import java.util.Map;

//@link - https://leetcode.com/problems/valid-anagram/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/4828913926406144
public class ValidAnagram {
    public boolean isAnagram(String s, String t) {
        int slen = s.length();
        int tlen = t.length();

        //can't be anagrams if of different lengths.
        //even if diff length strings contains same chars,
        //their count will certainly be diff, so there's no
        //way to create the bigger string from the smaller.
        if (slen != tlen) return false;

        Map<Character, Integer> mp = new HashMap<>();
        int p1 = 0; //string s
        int p2 = 0; //string t

        while (p1 < slen) {
            char schar = s.charAt(p1);
            char tchar = t.charAt(p2);
            mp.put(schar, mp.getOrDefault(schar, 0) + 1);//s increases the count
            mp.put(tchar, mp.getOrDefault(tchar, 0) - 1);//t decreases the count.

            //if freq becomes zero means till now we've found
            //equal num of a particular char in both strings, so
            //delete it from the map.
            if (mp.containsKey(schar) && mp.get(schar) == 0) mp.remove(schar);
            if (mp.containsKey(tchar) && mp.get(tchar) == 0) mp.remove(tchar);

            //move to next char.
            p1 += 1;
            p2 += 1;
        }

        //at the end of map has no chars,
        //means both strings had same frequency
        //of same chars. Hence, they can both
        //be created from one another by rearranging.
        return (mp.size() == 0);
    }
}
