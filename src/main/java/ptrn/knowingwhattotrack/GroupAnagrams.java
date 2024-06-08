package ptrn.knowingwhattotrack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@link - https://leetcode.com/problems/group-anagrams/description/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/6523979314757632
public class GroupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> mp = new HashMap<>();
        for (String str: strs) {

            //for each string, count frequency of
            //each char of alphabets.
            int[] arr = new int[26];
            for (char ch: str.toCharArray()) {
                arr[ch-'a'] += 1;
            }

            //Form a string from the frequencies.
            //and use that string as the key in a hashmap
            //that stores the strings from the array against a key formed
            //using the frequencies of character. Idea is that, if two strings
            //are anagrams, they'll end up having the exact same key (because frequencies
            //of characters will be same) and so, they'll end up falling in the same
            //bucket in the hashmap. Therefore, by the end of our iteration over
            //all the strings, we'll hace bucketed each anagram in their respective groups.
            StringBuffer sb = new StringBuffer();
            for (int i=0; i<26; i++) sb.append(String.valueOf(arr[i])).append("#");
            if (!mp.containsKey(sb.toString())) {
                mp.put(sb.toString(), new ArrayList<>());
            }
            mp.get(sb.toString()).add(str);
        }

        //collect the anagrams as list of list.
        List<List<String>> ans = new ArrayList<>();
        for (Map.Entry<String, List<String>> entry: mp.entrySet()) {
            ans.add(entry.getValue());
        }

        return ans;
    }
}
