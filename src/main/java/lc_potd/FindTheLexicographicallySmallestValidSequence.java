package lc_potd;

//@link - https://leetcode.com/problems/find-the-lexicographically-smallest-valid-sequence/?
//@check - https://www.youtube.com/watch?v=Upvd6Kbahqw
public class FindTheLexicographicallySmallestValidSequence {
    public int[] validSequence(String word1, String word2) {
        return mikssol(word1, word2);
    }

    /*
        Coded by me, completely based on miks'
        explanation.
    */
    private int[] mikssol(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        int[] numOfMatchingCharsOnRight = new int[m];
        boolean haveTicketToChange = true; //can only change once.

        int i = m-1, j = n-1;
        while (i >= 0) {
            if (j < 0) {
                numOfMatchingCharsOnRight[i] = numOfMatchingCharsOnRight[i+1];
                i -= 1;
                continue;
            }
            char ch1 = word1.charAt(i), ch2 = word2.charAt(j);
            if (ch1 == ch2) {
                if (i == m-1) numOfMatchingCharsOnRight[i] = 1;
                else numOfMatchingCharsOnRight[i] = 1 + numOfMatchingCharsOnRight[i+1];
                j -= 1;
            } else {
                if (i == m-1) numOfMatchingCharsOnRight[i] = 0;
                else numOfMatchingCharsOnRight[i] = numOfMatchingCharsOnRight[i+1];
            }
            i -= 1;
        }


        i =0; j=0;
        int[] ans = new int[n];
        int k = 0;
        while (i < m && j < n) {
            char ch1 = word1.charAt(i), ch2 = word2.charAt(j);
            if (ch1 == ch2) {
                ans[k++] = i;
                j += 1;
            } else if (i < m-1 && haveTicketToChange && numOfMatchingCharsOnRight[i+1] >= n-j-1) {
                ans[k++] = i;
                j += 1;
                haveTicketToChange = false;
            }

            i += 1;
        }

        if (j >= n) return ans;

        return new int[]{};
    }
}
