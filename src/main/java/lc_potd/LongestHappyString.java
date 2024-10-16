package lc_potd;

import java.util.PriorityQueue;

//@link - https://leetcode.com/problems/longest-happy-string/
//@check - https://www.youtube.com/watch?v=tGzgghQEDdA&ab_channel=codestorywithMIK
public class LongestHappyString {
    public String longestDiverseString(int a, int b, int c) {
        return mysol(a, b, c);
    }

    /**
     * My soln. Mik's soln was on the same lines, but maybe a bit cleaner - not sure.
     *
     *  Idea is to use the character with largest frequency
     *  in PairPs and the ones with smaller frequency as fillers, to
     *  break continuous 3 chars.
     */
    private String mysol(int a, int b, int c) {
        PriorityQueue<PairP> pq = new PriorityQueue<>((p1, p2) -> p2.cnt - p1.cnt);
        if (a != 0) pq.add(new PairP('a', a));
        if (b != 0) pq.add(new PairP('b', b));
        if (c != 0) pq.add(new PairP('c', c));

        StringBuffer sb = new StringBuffer();
        while (pq.size() >= 2) {
            
            /*
                Get the char with max frequency,
                and try to see if we can have 2 of it.
                If not, then just use one
            */
            PairP max = pq.remove();
            int maxCnt = max.cnt;
            char maxCh = max.ch;
            if (((sb.length() > 0) && (maxCh == sb.charAt(sb.length()-1))) || (maxCnt == 1)) {
                sb.append(maxCh);
                max.cnt -= 1; //decrease the count.
            } else {
                sb.append(maxCh).append(maxCh);
                max.cnt -= 2; //decrease the count.
            }

            //Then select a filler and append it.
            PairP p = pq.remove();
            sb.append(p.ch);
            p.cnt -= 1;

            if (max.cnt > 0) pq.add(max);
            if (p.cnt > 0) pq.add(p);
        }

        //At this point, we'll only have (if at all)
        //the char with max frequency. So, we'll see
        //if we can add 1 or 2 of it at the end one last time.
        if (!pq.isEmpty()) {
            PairP max = pq.remove();
            int maxCnt = max.cnt;
            char maxCh = max.ch;

            if (((sb.length() > 0) && (maxCh == sb.charAt(sb.length()-1))) || (maxCnt == 1)) {
                sb.append(maxCh);
                maxCnt -= 1;
            } else if (maxCnt >= 2) {
                sb.append(maxCh).append(maxCh);
                maxCnt -= 2;
            }
        }


        return sb.toString();
    }
}

class PairP {
    char ch;
    int cnt;

    public PairP(char ch, int cnt) {
        this.ch = ch;
        this.cnt = cnt;
    }
}
