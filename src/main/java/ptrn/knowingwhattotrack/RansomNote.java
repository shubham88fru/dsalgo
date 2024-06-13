package ptrn.knowingwhattotrack;

import java.util.HashMap;
import java.util.Map;

//@link - https://leetcode.com/problems/ransom-note/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/6425779467714560
public class RansomNote {
    //1) Edctv soln. Optimal.
    //Create frequency map for magazine.
    //Then iterate over ransomNote's character and each time we
    //find a char in magazine's map, decrement its occurrence.
    //If at any point during iteration, a char can't be found with
    //a freq > 1 in magazine's map, return false.

    //2) My soln. suboptimal.
    //Uses two maps.
    public boolean canConstruct(String ransomNote, String magazine) {
        Map<Character, Integer> mmp = new HashMap<>();
        Map<Character, Integer> rmp = new HashMap<>();
        for (char ch: magazine.toCharArray()) {
            mmp.put(ch, mmp.getOrDefault(ch, 0)+1);
        }

        for (char ch: ransomNote.toCharArray()) {
            rmp.put(ch, rmp.getOrDefault(ch, 0)+1);
        }

        for (Map.Entry<Character, Integer> entry: rmp.entrySet()) {
            char key = entry.getKey();
            int val = entry.getValue();
            if (!mmp.containsKey(key)) return false;
            if (val > mmp.get(key)) return false;
        }

        return true;
    }
}
