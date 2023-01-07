package swd.hashmap;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

//@link - https://leetcode.com/problems/longest-substring-without-repeating-characters/description/
public class LengthOfLongestSubstringWithoutRepeatingChars {
    public int lengthOfLongestSubstring(String s) {

        Map<Character, Integer> map = new HashMap<>();

        //sliding window pointers
        int acquire = 0;
        int release = 0;

        //final answer
        int answer = 0;

        while (acquire < s.length()) {
            //Try to acquire.
            char c = s.charAt(acquire);

            //keep releasing if element to be acquired already seen before.
            if (map.containsKey(c)) {
                char deleteChar = s.charAt(release);
                map.put(deleteChar, map.get(deleteChar)-1);
                if (Objects.equals(map.get(deleteChar), 0)) {
                    map.remove(deleteChar);
                }
                release++; //shorten the window
            } else { //else if char not seen, put in map and update answer length.
                map.put(c, 1);
                answer = Math.max(answer, acquire - release + 1);
                acquire++; //widen the window.
            }
        }

        return answer;
    }
}
