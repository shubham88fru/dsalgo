package ptrn.twopointers;

import java.util.Arrays;

//@link - https://leetcode.com/problems/reverse-words-in-a-string/
//@strvr - https://takeuforward.org/data-structure/reverse-words-in-a-string/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/4737418008985600
public class ReverseWordsInString {
    public String reverseWords(String s) {
        //return reverse(s);
        return reverseWordOptimal(s);
    }

    //1) (kinda) optimal solution ?
    private String reverseWordOptimal(String s) {
        //reverse the entire string.
        StringBuffer sb = new StringBuffer(s.trim());
        sb.reverse();

        StringBuffer ans = new StringBuffer();

        int start = 0;
        int end = 0;
        while (end < sb.length()) {
            //for each word, reverse the word and
            //append to the answer string. NOTE: Need
            //to handle the spaces properly.
            if (sb.charAt(end) == ' ') {
                String sub = sb.substring(start, end);
                String reversed = (new StringBuffer(sub)).reverse().toString().trim();
                if (reversed.length() > 0) {
                    ans.append(reversed);
                    ans.append(" ");
                }

                //when word is done, start moves to
                //start of the next word (i.e. after the space)
                start = end + 1;
            }

            //keep moving the end untill
            //word end is ecountered.
            end += 1;
        }

        //process the last word.
        String sub = sb.substring(start, end);
        String reversed = (new StringBuffer(sub)).reverse().toString().trim();
        if (reversed.length() > 0) {
            ans.append(reversed);
        }

        return ans.toString();
    }

    //2) Kinda naive solution
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
