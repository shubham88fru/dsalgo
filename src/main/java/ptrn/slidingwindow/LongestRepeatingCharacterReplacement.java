package ptrn.slidingwindow;

import java.util.HashMap;
import java.util.Map;

//@link - https://leetcode.com/problems/longest-repeating-character-replacement/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/5985553780310016
public class LongestRepeatingCharacterReplacement {
    public int characterReplacement(String s, int k) {
        // return edctvSol(s, k);
        return standardSlidingWindowTemplate(s, k);
    }

    private int standardSlidingWindowTemplate(String s, int k) {
        int n = s.length();
        int l = 0, r = 0, maxLen = 0, dominantCharFreq = 0;
        Map<Character, Integer> mp = new HashMap<>();


        while (r < n) {
            while (r < n && (((r-l+1) - Math.max(dominantCharFreq, (mp.getOrDefault(s.charAt(r), 0)+1))) <= k)) {
                char ch = s.charAt(r);
                int freq = mp.getOrDefault(ch, 0)+1;
                mp.put(ch, freq);
                dominantCharFreq = Math.max(dominantCharFreq, freq);
                r += 1;
            }

            maxLen = Math.max(maxLen, r-l);
            char toRem = s.charAt(l);
            mp.put(toRem, mp.get(toRem)-1);
            l += 1;

        }

        return maxLen;
    }

    private int edctvSol(String s, int k) {
        Map<Character, Integer> mp = new HashMap<>();

        int start = 0; //start of window.
        int end = 0; //end of window.
        int maxi = Integer.MIN_VALUE; //maximum length substring with only same chars after k replacements.

        //Keep track of frequency of the most frequent char.
        int mostFreqCharFreq = 0;
        while (end < s.length()) {
            char currChar = s.charAt(end);
            int freq = mp.getOrDefault(currChar, 0)+1;
            mp.put(currChar, freq);

            //Keep track of the char of highest frequency in the window.
            //This will help us (below) to decide if the current window
            //has become invalid coz even if we do most replacements, we
            //won't be able to have a string with all same chars.
            mostFreqCharFreq = Math.max(mostFreqCharFreq, freq);

            //standard size of window in sliding window.
            int windowSize = end-start+1;

            //check if num of characters in the window other than most
            //frequent char is greater than k.
            if (windowSize-mostFreqCharFreq > k) {
                //If yes, means till we added the curr char, our
                //window was just perfect i.e. just with the right set
                //of chars which could be replaced to give us a valid string
                //with all same chars. But, now, we'll have to slid our window, since
                //this window is no longer valid.
                char toRemove = s.charAt(start);
                mp.put(toRemove, mp.get(toRemove)-1);
                start += 1;
            }

            //Note: we can't use the `windowSize` variable directly here.
            //coz the start pointer might have moved above. So, need to
            //again compute the window size by `end-start+1`.
            maxi = Math.max(maxi, end-start+1);

            end += 1;
        }

        return maxi;
    }


    //3) Brute force: Generate all subs, in each
    //sub, try replacing each char by a char of that
    //sub and see if it becomes a sub of all same chars.
    //If yes, record its length if it is more than the max
    //such length that you've seem so far.
}
