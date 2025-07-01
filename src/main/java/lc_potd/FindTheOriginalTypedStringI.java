package lc_potd;

//@link - https://leetcode.com/problems/find-the-original-typed-string-i/description/
public class FindTheOriginalTypedStringI {
    public int possibleStringCount(String word) {
        return pass3(word);
    }

    /*
        My approach 3: cleaner.
    */
    private int pass3(String word) {
        int n = word.length();

        int cnt = 0;
        int i = 1;
        while (i<n) {
            while (i < n && (word.charAt(i) == word.charAt(i-1))) {
                cnt += 1;
                i += 1;
            }

            i += 1;
        }

        return cnt + 1; //+1 coz of the case none of the chars are mistyped.
    }

    /*
        My approach 2: correct but nasty.
    */
    private int pass2(String word) {
        int n = word.length();

        int cnt = 0;
        int misstypedCnt = 0;
        int i = 1;
        while (i<n) {
            int repeat = 1;
            while (i < n && (word.charAt(i) == word.charAt(i-1))) {
                repeat += 1;
                i += 1;
            }

            if (repeat > 1) {
                cnt += repeat;
                misstypedCnt += 1;
            }
            i += 1;
        }

        if (misstypedCnt == 0) return 1;
        return misstypedCnt > 1 ? cnt-(misstypedCnt-1): cnt;
    }

    /*
        My approach 1: wrong;
        Calculating frequence won't work. we are
        interested in consecutive repeating chars.
        Failing T.C. : ere
    */
    private int pass1(String word) {
        int n = word.length();
        int[] freq = new int[26];

        for (int i=0; i<n; i++) {
            freq[word.charAt(i)-'a'] += 1;
        }


        int cnt = 0;
        int misstypedCnt = 0;
        for (int i=0; i<26; i++) {
            if (freq[i] > 1) {
                cnt += freq[i];
                misstypedCnt += 1;
            }
        }

        if (misstypedCnt == 0) return 1;
        return misstypedCnt > 1 ? cnt-1: cnt;
    }
}
