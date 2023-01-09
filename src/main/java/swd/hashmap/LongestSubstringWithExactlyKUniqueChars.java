package swd.hashmap;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

//@link - https://practice.geeksforgeeks.org/problems/longest-k-unique-characters-substring0853/1
public class LongestSubstringWithExactlyKUniqueChars {
    public int longestkSubstr(String str, int k) {
        Map<Character, Integer> map = new HashMap<>();
        int release = 0;
        int acquire = 0;
        int ans = -1;

        while (acquire < str.length()) {
            char c = str.charAt(acquire);
            if (map.containsKey(c) || (map.size() < k)) {
                map.put(c, map.getOrDefault(c, 0)+1);
                if (map.size() == k) {
                    ans = Math.max(ans, (acquire - release + 1));
                }
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

        return ans;
    }
}
