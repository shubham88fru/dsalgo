package lc_potd;

//@link - https://leetcode.com/problems/find-the-lexicographically-largest-string-from-the-box-i/description/
//@check - https://www.youtube.com/watch?v=P74WiyWY3Pk&t=1303s&ab_channel=codestorywithMIK
//       - https://www.youtube.com/watch?v=4w-_rtiau_Q&ab_channel=codestorywithMIK
public class FindTheLexicographicallyLargestStringFromTheBoxI {
    /*
        This is a pretty tricky (not hard) problem.
        The core idea
    */
    public String answerString(String word, int numFriends) {
        // return attempt1(word, numFriends);
        return attempt2(word, numFriends);
    }

    /*
        Instead of attempting to only find the largest
        length substring from the largest char, try the
        longestpossible length substring from every char
        and pick the lexicographically longest one.

        However, this is not the optimal approach.
        Optimal approach is using 2 pointers.
        Check second video link.
    */
    private String attempt2(String word, int numFriends) {
        if (numFriends==1) return word;

        int n = word.length();

        int longestPossibleSubtring = n-(numFriends-1);

        String ans = "";
        for (int i=0; i<n; i++) {
            String sub = word.substring(i, Math.min(i+longestPossibleSubtring, n));
            if (sub.compareTo(ans) > 0) {
                ans = sub;
            }
        }

        return ans;

    }

    //doesn't work when multiple largest char
    //e.g.  word = nbjnc, numFriends = 2, output = nbjn, expected = nc
    private String attempt1(String word, int numFriends) {
        int n = word.length();

        if (numFriends==1) return word;

        int li = 0; //index of largest char
        int largest = word.charAt(0);
        for (int i=0; i<n; i++) {
            int ch = word.charAt(i);
            if (ch > largest) {
                largest = ch;
                li = i;
            }
        }

        int largestSublen = Math.min(n-(numFriends-1), n-li);
        return word.substring(li, li+largestSublen);
    }
}
