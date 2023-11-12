package ptrn.slidingwindow;

//@link - https://practice.geeksforgeeks.org/problems/minimum-window-subsequence/1
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/5959655726776320
public class MinimumWindowSubSequence {
    String minWindow(String str1, String str2) {
        // code here
        //return minLenSub(str1, str2);
        return minLenSubstrOptimal(str1, str2);
    }

    //1) Optimal solution - optimised sliding window.
    private String minLenSubstrOptimal(String str1, String str2) {
        int s1len = str1.length();
        int s2len = str2.length();
        float minSubLen = Float.POSITIVE_INFINITY;
        String minLenSubstr = "";

        int p1 = 0; //str1
        int p2 = 0; //str2
        int start = 0; //points to start of substring.

        while (p1 < s1len) {
            int ch1 = str1.charAt(p1);
            int ch2 = str2.charAt(p2);
            //standard isSubsequence problem.
            //we move p1 regardless, but we move
            //p2 only if chars match.
            if (ch1 == ch2) {
                p1 += 1;
                p2 += 1;
            } else p1 += 1;

            //Following the above algo to check if
            //str2 is a susequence of str1, if p2 reaches
            //to the end of str2, means we have successfully
            //found a substring in str1 such that it has all
            //the chars of str1 (and in order.)
            //Therefore, in that case, find the substring length
            //from `start` to `p1` (i.e. current window) and check
            //if its length is lesser than the previous recorded
            //length. If yes, then mark it min.
            //Finally, reset p2 to start of str1, p1 to second index of
            //curr window (start +1) and start to p1, for the next iteration
            //in search of finding the next potential window.
            if (p2 >= s2len) {
                start = p1-1; //p1 would have gone 1 more than max index.
                p2 -= 1; //p2 would have gone 1 more than max index.

                //start going in reverse direction
                //in both strings. This will help us
                //get the smallest substring (in current window, if any)
                //that has all the chars of str2.
                while (p2 >= 0) {
                    char ch11 = str1.charAt(start);
                    char ch22 = str2.charAt(p2);

                    if (ch11 == ch22) {
                        start -= 1;
                        p2 -= 1;
                    } else start -= 1;
                }

                //start would have gone one more lesser than
                start += 1;
                String potenz = str1.substring(start, p1);
                if (potenz.length() < minSubLen) {
                    minLenSubstr = potenz;
                    minSubLen = potenz.length();
                }

                p1 = start + 1;
                p2 = 0;
            }
        }

        return minLenSubstr;

    }

    //2) Better solution - Sliding window
    //Only consider the substrings which we are sure has
    //all the chars of str2. And then try to figure out
    //the smallest such substring.
    //The problem with this approach is that it tries to
    //find a valid substring from each char of str1.
    private String minLenSub(String str1, String str2) {
        int s1len = str1.length();
        int s2len = str2.length();
        float minSubLen = Float.POSITIVE_INFINITY;
        String minLenSubstr = "";

        int p1 = 0; //str1
        int p2 = 0; //str2
        int start = 0; //points to start of substring.

        //Till we cover the entire str1 using p1.
        while (p1 < s1len) {
            int ch1 = str1.charAt(p1);
            int ch2 = str2.charAt(p2);
            //standard isSubsequence problem.
            //we move p1 regardless, but we move
            //p2 only if chars match.
            if (ch1 == ch2) {
                p1 += 1;
                p2 += 1;
            } else p1 += 1;

            //Following the above algo to check if
            //str2 is a susequence of str1, if p2 reaches
            //to the end of str2, means we have successfully
            //found a substring in str1 such that it has all
            //the chars of str1 (and in order.)
            //Therefore, in that case, find the substring length
            //from `start` to `p1` (i.e. current window) and check
            //if its length is lesser than the previous recorded
            //length. If yes, then mark it min.
            //Finally, reset p2 to start of str1, p1 to second index of
            //curr window (start +1) and start to p1, for the next iteration
            //in search of finding the next potential window.
            if (p2 >= s2len) {
                String potenz = str1.substring(start, p1);
                if (potenz.length() < minSubLen) {
                    minLenSubstr = potenz;
                    minSubLen = potenz.length();
                }
                p2 = 0;
                p1 = start + 1;
                start = p1;
            }
        }

        return minLenSubstr;

    }

    //3) Brute force solution - T: O(N^3)
    //Generate all substrings and for each substring check
    //if str2 is a subsequence. Select smallest such substring.
}
