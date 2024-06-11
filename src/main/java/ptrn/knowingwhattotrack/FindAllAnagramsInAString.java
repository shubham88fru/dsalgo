package ptrn.knowingwhattotrack;

import java.util.*;

//@link - https://leetcode.com/problems/find-all-anagrams-in-a-string/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/6573921026703360
public class FindAllAnagramsInAString {
    public List<Integer> findAnagrams(String s, String p) {
        Map<Character, Integer> freqp = new HashMap<>();

        //frequency of all chars in p.
        for (char ch: p.toCharArray()) {
            freqp.put(ch, freqp.getOrDefault(ch, 0)+1);
        }

        int l = 0;
        int r = 0;
        Map<Character, Integer> freqw = new HashMap<>(); //frequency of chars in current window.
        List<Integer> ans = new ArrayList<>();

        //implement a sliding window.
        while (r < s.length()) {
            char ch = s.charAt(r);
            //If current char of s is present in p and the frequency in current
            //window is lesser than that in p, acquire it.
            if (freqp.containsKey(ch) && freqw.getOrDefault(ch, 0) < freqp.get(ch)) {
                freqw.put(ch, freqw.getOrDefault(ch, 0)+1);
                if (isAnagram(freqp, freqw)) ans.add(l);
                r += 1;
            }
            //Otherwise, if curr char is not present in p at all,
            //there's no point checking, so reset everything and move to
            //next char in s.
            else if (!freqp.containsKey(ch)) {
                freqw = new HashMap<>();
                r += 1;
                l = r;
            }
            //else, curr char is present in p, but
            //frequency might have gone higher, so release chars
            //from the left end.
            else {
                char toRemove = s.charAt(l);
                freqw.put(toRemove, freqw.get(toRemove)-1);

                //`==` comparision usually trips me here.
                //Not sure if this is a leetcode specific issue, but
                //to be safe, maybe always compare with `Objects.equals(..)` instead
                //of `==`.
                if (Objects.equals(freqw.get(toRemove), 0)) freqw.remove(toRemove);
                l += 1;
            }
        }
        return ans;
    }

    private boolean isAnagram(Map<Character, Integer> freqp, Map<Character, Integer> freqw) {
        if (freqp.size() != freqw.size()) return false;
        for (Map.Entry<Character, Integer> entry: freqw.entrySet()) {
            if (!freqp.containsKey(entry.getKey())) return false;

            //`==` comparison usually trips me here.
            //Not sure if this is a leetcode specific issue, but
            //to be safe, maybe always compare with `Objects.equals(..)` instead
            //of `==`.
            if (!Objects.equals(freqp.get(entry.getKey()), entry.getValue())) return false;
        }

        return true;
    }
}
