package ptrn.slidingwindow;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

//@link - https://leetcode.com/problems/longest-substring-without-repeating-characters/description/
//@strvr - https://takeuforward.org/data-structure/length-of-longest-substring-without-any-repeating-character/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/5868097493008384
public class LengthOfLongestSubstringWithoutRepeatingChars {
    public int lengthOfLongestSubstring(String s) {
        //return lengthOfLongestSubstringSlidingWindow1(s);
        //return lengthOfLongestSubstringSlidingWindow2(s);
        return lengthOfLongestSubstringSlidingWindow3(s);
    }

    //1) Optimal approach 1: T: O(N), S: O(N)
    //Even better than optimal approach 2.
    private int lengthOfLongestSubstringSlidingWindow3(String s) {
        Map<Character, Integer> map = new HashMap<>();

        //sliding window pointers
        int acquire = 0;
        int release = 0;

        //final answer
        int answer = 0;

        while (acquire < s.length()) {
            //Try to acquire.
            char c = s.charAt(acquire);

            //if already seen, directly release all elements till the
            //index the character to be acquired was last seen.
            //Note that when considering if the element is seen or not,
            //we have to ensure that it was truly seen in the range [release, acquire].
            //since here were are directly skipping to curr element's last seen index,
            //we don't have the opportunity to remove the in between elements from the
            //map. And so, even if some element is there in the map but outside, the
            //[release, acquire] range, it doesn't matter.
            if (map.containsKey(c) && map.get(c) >= release && map.get(c) <= acquire) {
                release = map.get(c) + 1;
            }

            map.put(c, acquire);
            answer = Math.max(answer, acquire - release + 1);
            acquire++;
        }

        return answer;
    }

    //2) Optimal approach 2: T: O(N), S: O(N)
    //since we keep releasing untill the duplicate element is removed
    //from map, we can store each element earliest seen index and while
    //release, we can directly move the release pointer to that index.
    //This will save us some iterations when releasing.
    private int lengthOfLongestSubstringSlidingWindow2(String s) {
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
                release = map.get(deleteChar) + 1;
                map.remove(deleteChar);
            } else { //else if char not seen, put in map and update answer length.
                //Note here, in this quest, we store char and its index in the map
                //and not its frequency. In this question, all we are interested in is
                //whether the char was seen before or not.
                map.put(c, acquire);
                answer = Math.max(answer, acquire - release + 1);
                acquire++; //widen the window.
            }
        }

        return answer;
    }

    //2) Better approach: T: O(2N), S: O(N)
    //Basically, in worst case, acquire and release both will
    //have to iterate through the element, meaning we'll have to
    //iterate through the elements twice.
    private int lengthOfLongestSubstringSlidingWindow1(String s) {
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
