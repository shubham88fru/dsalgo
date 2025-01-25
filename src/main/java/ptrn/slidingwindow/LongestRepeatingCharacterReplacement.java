package ptrn.slidingwindow;

import java.util.HashMap;
import java.util.Map;

//@link - https://leetcode.com/problems/longest-repeating-character-replacement/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/5985553780310016
public class LongestRepeatingCharacterReplacement {
    public int characterReplacement(String s, int k) {
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
            /*
                Update - 01/25/25:
                This mostFreqCharFreq is actually a tricky part
                because if you see we arent' actually updating
                the mostFreqCharFreq within the if-clause below
                when moving the left pointer. Edctv doesn't
                explain this. This stood out to be during a revision
                and even nc says that its a tricky thing
                @here - https://www.youtube.com/watch?v=gqXU1UyA8pk&ab_channel=NeetCode
                Therefore, it could become a problem during an
                actual interview to defend it.

                Did the python solution by calculating the max
                frequency everytime. Which is still a linear O(26*n)
                solution as opposed to this solution which is perfect
                O(n). But I'd say still prefer doing that, coz I'll be
                able to defend the solution.
             */
            mostFreqCharFreq = Math.max(mostFreqCharFreq, freq);

            //standard size of window in sliding window.
            int windowSize = end-start+1;

            //check if num of characters in the window other than most
            //frequent char is greater than k.
            if (windowSize-mostFreqCharFreq > k) {
                //If yes, means till we added the curr char, our
                //window was just perfect i.e. just with the right set
                //of chars which could be replaced to give us a valid string
                //with all same chars. But, now, we'll have to slide our window, since
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
