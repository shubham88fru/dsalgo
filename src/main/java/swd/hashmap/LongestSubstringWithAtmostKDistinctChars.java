package swd.hashmap;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

//@link - https://www.codingninjas.com/codestudio/problems/distinct-characters_2221410?leftPanelTab=0
public class LongestSubstringWithAtmostKDistinctChars {
    public static int kDistinctChars(int k, String str) {
        Map<Character, Integer> map = new HashMap<>();
        int release = 0;
        int acquire = 0;
        int ans = 0;

        while (acquire < str.length()) {
            char c = str.charAt(acquire);
            if (map.size() <= k) {
                if (map.containsKey(c) || (map.size() < k)) {
                    map.put(c, map.getOrDefault(c, 0)+1);
                    ans = Math.max(ans, (acquire - release + 1));
                    acquire++;
                } else {
                    char deleteChar = str.charAt(release);
                    map.put(deleteChar, map.get(deleteChar)-1);
                    if (Objects.equals(map.get(deleteChar), 0)) {
                        map.remove(deleteChar);
                    }
                    release++;
                }
            }
        }

        return ans;
    }

}
