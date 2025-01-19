package ptrn.knowingwhattotrack;

import java.util.*;

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
            //all the strings, we'll have bucketed each anagram in their respective groups.
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

    //TLE
    private List<List<String>> brute(String[] strs) {
        Map<String, int[]> freqs = new HashMap<>();

        for (String str: strs) {
            int[] freq = new int[26];
            if (freqs.containsKey(str)) continue;

            for (int i=0; i<str.length(); i++) {
                char ch = str.charAt(i);
                freq[ch-'a'] += 1;
            }
            freqs.put(str, freq);
        }

        List<List<String>> ans = new ArrayList<>();
        Set<Integer> grouped = new HashSet<>();
        for (int i=0; i<strs.length; i++) {
            List<String> grp = new ArrayList<>();
            if (grouped.contains(i)) continue;
            for (int j=0; j<strs.length; j++) {
                if (isAnagram(strs[i], strs[j], freqs)) {
                    grp.add(strs[j]);
                    grouped.add(j);
                }
            }
            ans.add(grp);
        }

        return ans;
    }

    private boolean isAnagram(String str1, String str2, Map<String, int[]> freqs) {
        int[] f1 = freqs.get(str1);
        int[] f2 = freqs.get(str2);

        for (int i=0; i<26; i++) {
            if (f1[i] != f2[i]) return false;
        }

        return true;
    }
}
