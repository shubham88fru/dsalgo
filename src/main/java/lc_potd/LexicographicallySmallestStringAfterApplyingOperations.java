package lc_potd;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

//@link - https://leetcode.com/problems/lexicographically-smallest-string-after-applying-operations/description/?
//@check - https://www.youtube.com/watch?v=SUkbctHiREI
public class LexicographicallySmallestStringAfterApplyingOperations {
    public String findLexSmallestString(String s, int a, int b) {
        return brute(s, a, b);
    }

    /**
     Didn't come up with this solution myself.
     Surprisingly, a lot of people were able to
     solve themselves, so I'm not really sure if
     i'm really that bad :(. I got confused and started
     thinking on the lines of how to strategically
     choose between rotate/add ops. One more important
     observation which I missed was that although it
     might seem that add and rotate ops can lead to
     infinite state, it turns out that no matter what
     comb of ops we do, there will always a fixed list
     of states - difficult for me to comprehend but
     that's how it is.

     Any ways, turns out the soln is to generate all possibilities
     and pick the smallest between them. Since the interleaving
     of ops can lead to different states, this is basically a
     graph traversal problem.

     Following is coded by me based on hints from mik.
     */
    private String brute(String s, int a, int b) {
        int n = s.length();

        //BFS
        Deque<String> q = new ArrayDeque<>();
        Set<String> v = new HashSet<>(); //visited.
        q.addLast(s);
        v.add(s);

        String min = s;
        while (!q.isEmpty()) {
            String str = q.removeFirst();

            if (min.compareTo(str) > 0) {
                min = str;
            }

            String aops = add(str, a);
            if (!v.contains(aops)) {
                v.add(aops);
                q.addLast(aops);
            }

            String bops = rotate(str, b);
            if (!v.contains(bops)) {
                v.add(bops);
                q.addLast(bops);
            }
        }

        return min;
    }

    private String add(String s, int a) {
        int n = s.length();
        StringBuilder sb = new StringBuilder();

        for (int i=0; i<n; i++) {
            char ch = s.charAt(i);
            if (i%2 == 0) sb.append(ch);
            else sb.append((Character.getNumericValue(ch)+a)%10);
        }

        return sb.toString();
    }

    private String rotate(String s, int b) {
        int n = s.length();
        StringBuilder sb = new StringBuilder();
        char[] carr = s.toCharArray();

        for (int i=0; i<n; i++) {
            sb.append(carr[(i+b)%n]);
        }

        return sb.toString();
    }
}
