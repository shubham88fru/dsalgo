package lc_potd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@link - https://leetcode.com/problems/find-common-characters/
public class FindCommonCharacters {
    public List<String> commonChars(String[] words) {
        List<Map<Character, Integer>> mps = new ArrayList<>();
        for (String word: words) {
            Map<Character, Integer> mp = new HashMap<>();
            for (char ch: word.toCharArray()) {
                mp.put(ch, mp.getOrDefault(ch, 0)+1);
            }
            mps.add(mp);
        }

        List<String> ans = new ArrayList<>();
        Map<Character, Integer> mp1 = mps.get(0);
        for (Map.Entry<Character, Integer> entry: mp1.entrySet()) {
            char key = entry.getKey();
            int val = entry.getValue();

            boolean presentInAll = true;
            int times = val;
            for (int i=1; i<mps.size(); i++) {
                if (!mps.get(i).containsKey(key)) {
                    presentInAll = false;
                    break;
                }
                times = Math.min(mps.get(i).get(key), times);
            }

            if (presentInAll) {
                for (int i=0; i<times; i++)
                    ans.add(String.valueOf(key));
            }
        }


        return ans;
    }
}
