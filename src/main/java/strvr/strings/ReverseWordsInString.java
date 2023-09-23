package strvr.strings;

import java.util.Arrays;

//@link - https://leetcode.com/problems/reverse-words-in-a-string/
//@strvr - https://takeuforward.org/data-structure/reverse-words-in-a-string/
public class ReverseWordsInString {
    public String reverseWords(String s) {
        return reverse(s);
    }

    //My Soln. TODO: Check strvr's soln.
    private String reverse(String s) {
        String[] arr = Arrays.stream(s.trim().split(" "))
                .filter(str -> !str.equals(""))
                .toArray(String[]::new);

        int start = 0;
        int end = arr.length-1;

        while (start <= end) {
            String temp = arr[start].trim();
            arr[start] = arr[end].trim();
            arr[end] = temp;

            start += 1;
            end -= 1;
        }

        return String.join(" ", arr);
    }
}
