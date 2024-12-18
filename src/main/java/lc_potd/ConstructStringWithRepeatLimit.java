package lc_potd;

//@link - https://leetcode.com/problems/construct-string-with-repeat-limit/
//@check - https://www.youtube.com/watch?v=Jghze8B3E7M&t=1462s&ab_channel=codestorywithMIK
public class ConstructStringWithRepeatLimit {
    /*
        This was a nasty question with confusing description.
        Anyways, after a while I realized that I was thinking it to
        be much more complicated that it was.
        Still, even after that realization, I couldn't solve the problem
        on my own.

        Following is mik's first approach (code by me). Mik also showed
        a solution using Heap, which had also come to my mind while trying
        on my onw, but I couldn't fruitify it.

        @check for heap solution if this is some recurring problem for a company.
     */
    public String repeatLimitedString(String s, int repeatLimit) {
        return mikssol(s, repeatLimit);
    }

    private String mikssol(String s, int repeatLimit) {

        /*
        * This was a catch. To store the freq in
        * array instead of map. This helps to
        * get to the second larger char. This also
        * didn't come to my mind and I was stuck
        * because I was trying to store freq in map
        * and in map there is not direct way to
        * get second larger char (if not using treemap or something)
        *  */
        int[] freq = new int[26];
        for (char ch: s.toCharArray()) {
            freq[ch-'a'] += 1;
        }


        StringBuilder sb = new StringBuilder();
        int i = 25;
        while (i >= 0) {
            while (i >= 0 && freq[i] == 0) i -= 1;
            if (i < 0) return sb.toString();
            int times = Math.min(freq[i], repeatLimit);
            int repeat = times;
            while (repeat > 0) {
                sb.append((char)(97+i));
                repeat -= 1;
            }

            int j = i-1;
            if (freq[i] > repeatLimit) {
                while (j >= 0 && freq[j] == 0) {
                    j -= 1;
                }
                if (j < 0) return sb.toString();
                sb.append((char)(97+j));
                freq[j] -= 1;
            }

            freq[i] -= times;
        }

        return sb.toString();
    }
}
