package ptrn.slidingwindow;

import java.util.HashMap;
import java.util.Map;

//@link - https://leetcode.com/problems/minimum-window-substring/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/4858192707452928
public class MinimumWindowSubstring {
    public String minWindow(String s, String t) {
        return minWindowSub(s, t);
    }

    //0) Same sliding window soln
    //but based on my standard sliding window template.
    private String standardSlidingWindowTemplate(String s, String t) {
        int n = s.length();

        Map<Character, Integer> window = new HashMap<>();
        Map<Character, Integer> tMap = new HashMap<>();
        for (int i=0; i<t.length(); i++) {
            char ch = t.charAt(i);
            tMap.put(ch, tMap.getOrDefault(ch, 0)+1);
            window.put(ch, 0);
        }

        int reqSize = tMap.size();
        int currSize = 0;
        int l=0, r=0, minLen = Integer.MAX_VALUE, i=-1, j=-1;

        /*
            NOTE: In sliding window questions
            where we need to shrink even after r
            reaches n, we take `l < n` as our breaking
            condition instead of `r < n`
        */
        while (l < n) {
            /*
                Acquire till we have taken
                enough to have all chars from t.
            */
            while (r < n && reqSize != currSize) {
                char ch = s.charAt(r);
                if (window.containsKey(ch)) {
                    window.put(ch, window.getOrDefault(ch, 0)+1);
                    /*
                        LC map's value comparision is a bit
                        wonky. The `==` doesn't work even if
                        values are same for large values.
                        Therefore, using .equals()
                    */
                    if (window.get(ch).equals(tMap.get(ch))) currSize += 1;
                }

                r += 1;
            }

            /*
                At this point, if we have
                seen all chars from t,
                record the length.
            */
            if (reqSize == currSize) {
                if (r-l < minLen) {
                    minLen = r-l;
                    i = l;
                    j = r;
                }
            }

            /*
                Release and see if
                we have lost a char
                that was needed for
                the window to be valid.
            */
            char toRem = s.charAt(l);
            if (window.containsKey(toRem)) {
                window.put(toRem, window.get(toRem)-1);
                if (window.get(toRem) < tMap.get(toRem)) currSize -= 1;
            }
            l += 1;

        }


        return minLen == Integer.MAX_VALUE ? "": s.substring(i, j);
    }

    //1) Optimal solution - using sliding window.
    //T: O(m+n) ; m & n are lengths of s and t.
    private String minWindowSub(String s, String t) {
        Map<Character, Integer> reqCount = new HashMap<>(); //freq in t
        Map<Character, Integer> window = new HashMap<>(); //freq in window.

        if (t.trim().equals("")) return "";
        for (char ch: t.toCharArray()) {
            reqCount.put(ch, reqCount.getOrDefault(ch, 0)+1); //populate with freq of chars in t.
            //window is populated with char in t but with 0 frequencies.
            //Freq will be incremented as we keep finding the chars in s.
            window.put(ch, 0);
        }

        int start = 0;
        int end = 0;
        int required = reqCount.size(); //size of reqCount (map that stores freq of chars in t)

        //Incremented everytime a particular char's freq in window becomes equal to the freq in t.
        //Hence, when windowCount becomes equal to required, means we have a window that has all chars of t.
        int windowCount = 0;

        int minSubLen = Integer.MAX_VALUE;
        int[] subStartAndEnd = {-1, -1};

        //until the end of the window.
        //NOTE: The window map doesn't simply have all chars of the window.
        //Instead, for this question, we only store the chars of t in the window map.
        while (end < s.length()) {
            char toAcquire = s.charAt(end);
            if (window.containsKey(toAcquire)) {
                window.put(toAcquire, window.get(toAcquire)+1);
                if (window.get(toAcquire).equals(reqCount.get(toAcquire))) {
                    windowCount += 1; //when a char's freq in s becomes same as in t, increase the window count.
                }
            }

            //When we've found a window that has all the chars of t
            //we try to find for the samllest such substring in the window.
            //For this we start shortening the window.
            //Per our logic, windowcount will become equal to required
            while (windowCount == required) {
                int length = end-start+1;
                if (length < minSubLen) {
                    /*
                     Avoid finding the substring in a loop,
                     rather just keep recording the start and end index
                     and we'll find the actual substring in the end. This
                     significantly improves the runtime.
                    */
                    //minSub = s.substring(start, end+1);
                    subStartAndEnd[0] = start;
                    subStartAndEnd[1] = end;
                    minSubLen = length;
                }

                //If the char to be removed is present in the window,
                //decrease its frequency, and if after decreasing the frequency,
                //the frequency in windows becomes smaller than whats required (from reqCount)
                //we decrement the windowCount.
                char toRemove = s.charAt(start);
                if (window.containsKey(toRemove)) {
                    window.put(toRemove, window.get(toRemove)-1);
                    if (window.get(toRemove) < reqCount.get(toRemove)) windowCount -= 1;
                }
                start += 1;
            }

            end += 1;
        }

        //ATQ
        return minSubLen != Integer.MAX_VALUE ? s.substring(subStartAndEnd[0], subStartAndEnd[1]+1) : "";
    }

    //2) Brute force - generate all substring.
}
