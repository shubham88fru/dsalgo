package ptrn.slidingwindow;

//@link - https://leetcode.com/problems/minimum-number-of-flips-to-make-the-binary-string-alternating/
//@check - https://www.youtube.com/watch?v=MOeuK6gaC2A&t=308s&ab_channel=NeetCode
//       - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/6437555261407232
public class MinimumNumberOfFlipsToMakeBinaryStringAlternating {
    public int minFlips(String s) {
        // return kindaBrute(s);
        return optimal(s);
    }

    //uses sliding window to smartly
    //use already calculated values and avoid
    //recounting. Coded by me, inspired by neetcode.
    private int optimal(String s) {
        String twoS = s + s; //contains evey cyclic rotation of s.
        int l = 0;
        int r = 0;

        int startingOne = 1; //value at current index for the alternating string starting with  1.
        int startingZero = 0; //value at current index for the alternating string starting with 0.

        int min = Integer.MAX_VALUE;
        int s0Cnt = 0; //flips compared to alternating string starting with 1.
        int s1Cnt = 0; //flips compared to alternating string starting with 0.

        //sliding window.
        while (r < twoS.length()) {
            if ((r-l+1) <= s.length()) {
                int ch = Character.getNumericValue(twoS.charAt(r));

                startingOne = r%2 == 0 ? 1: 0; //value at current position for the alternating string starting with 1.
                startingZero = r%2 == 0 ? 0: 1; //value at current position for the alternating string starting with 0.

                if (ch != startingOne) s1Cnt += 1;
                if (ch != startingZero) s0Cnt += 1;

                r += 1;

            } else {
                int ch = Character.getNumericValue(twoS.charAt(l)); //shorten the window.

                min = Math.min(min, Math.min(s1Cnt, s0Cnt));

                startingOne = l%2 == 0 ? 1: 0;
                startingZero = l%2 == 0 ? 0: 1;


                if (ch != startingOne) s1Cnt -= 1; //account for the element being removed if it had contributed to count.
                if (ch != startingZero) s0Cnt -= 1;

                l += 1;
            }

        }

        return min;
    }

    //for every rotation, check the flips.
    private int kindaBrute(String s) {
        StringBuffer sb = new StringBuffer(s);
        int min = Integer.MAX_VALUE;
        for (int i=0; i<s.length(); i++) {
            min = Math.min(min, flipAndCnt(sb.toString()));
            char ch = sb.charAt(0);
            sb.deleteCharAt(0);
            sb.append(ch);
        }

        return min;
    }


    private int flipAndCnt(String s) {
        char startingOne = '1';
        char startingZero = '0';
        int s0Cnt = 0;
        int s1Cnt = 0;

        for (int i=0; i<s.length(); i++) {
            char ch = s.charAt(i);

            if (ch != startingOne) s0Cnt += 1;
            if (ch != startingZero) s1Cnt += 1;

            if (startingOne == '1') startingOne = '0';
            else startingOne = '1';

            if (startingZero == '1') startingZero = '0';
            else startingZero = '1';
        }

        return Math.min(s0Cnt, s1Cnt);
    }
}
