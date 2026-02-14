package ptrn.twopointers;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

//@link - https://leetcode.com/problems/reverse-words-in-a-string/
//@strvr - https://takeuforward.org/data-structure/reverse-words-in-a-string/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/4737418008985600
public class ReverseWordsInString {
    public String reverseWords(String s) {
        return optimal(s);
        // return brute(s);
    }

    private String optimal(String s) {
        int n = s.length();
        StringBuffer sb = new StringBuffer();

        int i=n-1;
        while (i >= 0) {
            while (i >= 0 && !Character.isLetterOrDigit(s.charAt(i))) {
                i -= 1;
            }

            int j=i;
            while (i >=0 && Character.isLetterOrDigit(s.charAt(i))) {
                i -= 1;
            }

            sb.append(s.substring(i+1, j+1));
            sb.append(" ");

            i -= 1;
        }

        return sb.toString().trim();
    }

    private String brute(String str) {
        List<String> rev = Arrays.stream(str.split(" "))
                .map(s -> s.trim()).filter(s -> s.length()>0).collect(Collectors.toList());
        Collections.reverse(rev);

        return rev.stream().collect(Collectors.joining(" "));
    }
}
