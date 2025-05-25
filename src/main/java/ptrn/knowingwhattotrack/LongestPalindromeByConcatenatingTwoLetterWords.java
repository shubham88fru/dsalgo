package ptrn.knowingwhattotrack;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

//@link - https://leetcode.com/problems/longest-palindrome-by-concatenating-two-letter-words/description/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/4996689485889536
public class LongestPalindromeByConcatenatingTwoLetterWords {

    public int longestPalindrome(String[] words) {
        return revise(words);
//        return mysol1(words);
//        return edctvSol(words);
    }

    //1) my soln. mik also had the soln on similar lines.
    private int revise(String[] words) {
        int n = words.length;

        int sameMax = Integer.MIN_VALUE;
        Map<String, Integer> st = new HashMap<>();
        Set<String> same = new HashSet<>();

        int len = 0;
        for (String word: words) {
            String rev = (new StringBuffer(word)).reverse().toString();
            if (word.equals(rev)) {
                if (same.contains(word)) {
                    len += 4;
                    same.remove(word);
                } else same.add(word);
            } else if (st.containsKey(rev)) {
                len += 4;
                st.put(rev, st.get(rev)-1);
                if (st.get(rev) == 0) st.remove(rev);
            } else {
                st.put(word, st.getOrDefault(word, 0)+1);
            }
        }

        if (!same.isEmpty()) len += 2;
        return len;
    }

    //2) My soln - 1 is better coz only one loop.
    private int mysol1(String[] words) {
        Map<String, Integer> mp = new HashMap<>();
        int len = 0;
        for (String word: words) {
            StringBuffer sb = new StringBuffer(word);
            String rev = sb.reverse().toString();

            //If reverse of current word exists,
            //pair current word and the reverse.
            //Decrement the count of the reverse word, since
            //its already paired.
            if (mp.containsKey(rev)) {
                len += 4; //word and its reverse paired. Add length of two words.
                mp.put(rev, mp.get(rev)-1); //decrement the freq of rev, since it got paired.
                if (mp.get(rev) == 0) mp.remove(rev); //if freq becomes zero, remove it altogether.
            } else mp.put(word, mp.getOrDefault(word, 0)+1); //if reverse doesn't exist, add the word to map.
        }

        //Finally, at this point, the words that could be paired,
        //have already been accounted for. We are just left with checking
        //if we can add a central word. The central word will be a palindrome.
        //So we just check if of the pending words, we have any palindrome, if we
        //do, we just add it and break.
        for (Map.Entry<String, Integer> entry: mp.entrySet()) {
            if (palindrome(entry.getKey())) {
                len += 2;
                break;
            }
        }

        return len;
    }

    //2) Edctv soln also similar with slight differences.
    public static int edctvSol(String[] words) {
        // Create a HashMap to store word frequencies
        HashMap<String, Integer> frequencies = new HashMap<>();

        // Count the frequencies of words
        for (String word : words) {
            frequencies.put(word, frequencies.getOrDefault(word, 0) + 1);
        }

        int count = 0;
        boolean central = false;

        // Iterate over the word frequencies
        for (HashMap.Entry<String, Integer> entry : frequencies.entrySet()) {
            String word = entry.getKey();
            int frequency = entry.getValue();

            // If word is a palindrome
            if (word.charAt(0) == word.charAt(1)) {
                // If a word has even occurrences
                if (frequency % 2 == 0) {
                    count += frequency;
                }
                // If a word has odd occurrences
                else {
                    count += frequency - 1;
                    central = true;
                }
            }
            // If word is not a palindrome
            // Ensuring that a word and its reverse is only considered once
            else if (word.charAt(1) > word.charAt(0) && frequencies.containsKey(word.charAt(1) + "" + word.charAt(0))) {
                // Get the minimum of the occurrences of the word and its reverse
                count += 2 * Math.min(frequency, frequencies.get(word.charAt(1) + "" + word.charAt(0)));
            }
        }

        if (central) {
            count += 1;
        }

        return 2 * count;
    }

    private boolean palindrome(String s) {
        int l = 0;
        int r = s.length() - 1;
        while (l <= r) {
            if (s.charAt(l) != s.charAt(r)) return false;
            l += 1;
            r -= 1;
        }

        return true;
    }
}
