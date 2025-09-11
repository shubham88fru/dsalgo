package lc_potd;

import java.util.PriorityQueue;

//@link - https://leetcode.com/problems/sort-vowels-in-a-string/description/?
public class SortVowelsInAString {
    public String sortVowels(String s) {
        return pass1(s);
    }

    /*
        Other approaches -
        Instead of pq, just store the vowels in
        an array and sort.

        Mik also showed another interesting approach
        so revise if this is faq for some company.
     */

    /**
     My approach.
     */
    private String pass1(String s) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i=0; i<s.length(); i++) {
            char ch = s.charAt(i);
            if (isVowel(ch)) pq.add((int)ch);
        }

        StringBuilder sb = new StringBuilder();
        for (int i=0; i<s.length(); i++) {
            char ch = s.charAt(i);
            if (isVowel(ch)) sb.append((char)pq.remove().intValue());
            else sb.append(ch);
        }

        return sb.toString();
    }

    private boolean isVowel(char ch) {
        return (ch == 'a' || ch == 'A' || ch == 'e' || ch == 'E'
                || ch == 'i' || ch == 'I' || ch == 'o' || ch == 'O'
                || ch == 'u' || ch == 'U');
    }
}
