package lc_potd;

import java.util.Comparator;
import java.util.PriorityQueue;

//@link - https://leetcode.com/problems/lexicographically-minimum-string-after-removing-stars/
public class LexicographicallyMinimumStringAfterRemovingStars {
    public String clearStars(String s) {
        return pass1(s);
    }

    /*
        My soln. Mik had the exact same approach.
    * */
    private String pass1(String s) {
        Comparator<Pair22> cmp1 = (p1, p2) -> (int)p1.ch - (int)p2.ch;
        Comparator<Pair22> cmp2 = (p1, p2) -> p2.i - p1.i;

        PriorityQueue<Pair22> minHeap = new PriorityQueue<>(cmp1.thenComparing(cmp2));

        StringBuffer sb = new StringBuffer(s);
        for (int i=0; i<s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '*') {
                int idx = minHeap.remove().i;
                sb.setCharAt(idx, '-');
                sb.setCharAt(i, '-');
            } else {
                minHeap.add(new Pair22(ch, i));
            }
        }

        StringBuffer ans = new StringBuffer();
        for (int i=0; i<s.length(); i++) {
            if (sb.charAt(i) != '-') ans.append(sb.charAt(i));
        }

        return ans.toString();

    }
}

class Pair22 {
    char ch;
    int i;

    public Pair22(char ch, int i) {
        this.ch = ch;
        this.i = i;
    }
}
