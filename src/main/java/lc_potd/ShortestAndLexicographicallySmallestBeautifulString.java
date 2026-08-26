package lc_potd;

//@link - https://leetcode.com/problems/shortest-and-lexicographically-smallest-beautiful-string/description/?
public class ShortestAndLexicographicallySmallestBeautifulString {
    public String shortestBeautifulSubstring(String s, int k) {
        return pass1(s, k);
    }

    //my sol
    private String pass1(String s, int k) {
        int n = s.length();

        return slidingWindow(s, k, n);
    }

    private String slidingWindow(String s, int k, int n) {
        int l = 0, r = 0, cnt = 0, ri = -1, li = -1, minLen = Integer.MAX_VALUE;

        while (l < n) {
            while (r < n && cnt < k) {
                char ch = s.charAt(r);
                if (ch == '1') {
                    cnt += 1;
                }
                r += 1;
            }

            if (r >= n && cnt < k) break;

            if (r-l < minLen) {
                minLen = r-l;
                ri = r;
                li = l;
            } else if (r-l == minLen) {
                for (int i=li, j=l; i<ri; i++, j++) {
                    int ci = Character.getNumericValue(s.charAt(i));
                    int cj = Character.getNumericValue(s.charAt(j));
                    if (ci == cj) continue;
                    if (cj < ci) {
                        li = l;
                        ri = r;

                    }
                    break;
                }
            }

            char ch = s.charAt(l);
            if (ch == '1') cnt -= 1;
            l += 1;
        }

        if (li == -1 || ri == -1) return "";

        return s.substring(li, ri);
    }
}
