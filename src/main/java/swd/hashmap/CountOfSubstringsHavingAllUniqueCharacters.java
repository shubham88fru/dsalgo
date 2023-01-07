package swd.hashmap;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

//@link - https://www.pepcoding.com/resources/data-structures-and-algorithms-in-java-levelup/hashmap-and-heaps/count-of-substrings-having-all-unique-characters-official/ojquestion
public class CountOfSubstringsHavingAllUniqueCharacters {
    public static int solution(String str) {

        Map<Character, Integer> map = new HashMap<>();
        int acquire = 0;
        int release = 0;

        int ans = 0;

        //Till acquire pointer doesn't reach to end of string.
        while (acquire < str.length()) {
            //try to acquire.
            char c = str.charAt(acquire);

            //if already seen..
            if (map.containsKey(c)) {
                char deleteChar = str.charAt(release);

                //then decrement the count..
                map.put(deleteChar, map.get(deleteChar)-1);

                //or remove if freq is zero
                if (Objects.equals(map.get(deleteChar), 0)) {
                    map.remove(deleteChar);
                }

                //move the release pointer.
                release++;
            } else {
                //seeing for the first time.
                map.put(c, 1);

                //num of substrings seen is -
                ans += (acquire - release + 1);

                //move acquire to next.
                acquire++;
            }
        }
        return ans;

    }
}
