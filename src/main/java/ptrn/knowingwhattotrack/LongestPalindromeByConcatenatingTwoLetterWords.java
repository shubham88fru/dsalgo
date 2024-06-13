package ptrn.knowingwhattotrack;

import java.util.HashMap;
import java.util.Map;

//@link - https://leetcode.com/problems/longest-palindrome-by-concatenating-two-letter-words/description/
//@check -
public class LongestPalindromeByConcatenatingTwoLetterWords {
    //1) My soln.
    public int longestPalindrome(String[] words) {
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

    //2) Edctv soln also similar with slight differences.
    public static int longestPalindrome2(String[] words) {
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
}
