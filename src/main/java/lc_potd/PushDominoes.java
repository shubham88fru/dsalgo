package lc_potd;

//@link - https://leetcode.com/problems/push-dominoes/description/
//@check - https://www.youtube.com/watch?v=HrUZKTzStII&t=1981s&ab_channel=codestorywithMIK
public class PushDominoes {
    public String pushDominoes(String dominoes) {
        return mikssol(dominoes);
    }

    /**
     Coded by me but
     Following is a soln completely based on
     mik's explanation.

     My initial intuition was stack, which didn't
     fit very well. Then I thought of dp, and calculating
     effective force from left and right --> which was on
     the right track but I couldn't come up with a solution.

     The problem with stack and other approach for this problem
     is that they'll either process from left or right sequentially
     but the fact of this question is that things will happen
     parallely. We also have to take into account the distances from
     which we are getting the push.
     */
    private String mikssol(String dominoes) {
        int n = dominoes.length();
        int[] leftClosestEffectiveR = new int[n];
        int[] rightClosestEffectiveL = new int[n];

        //There can be optimization around this
        //calculation -
        // 1. we can populate the arrays in one loop.
        // 2. we can use just on array or a resultant array.
        // Mik said that he has multiple solutions for this
        // problem on his github.
        for (int i=0; i<n; i++) {
            char ch = dominoes.charAt(i);
            if (ch == 'R') leftClosestEffectiveR[i] = i;
            else if (i==0 || ch == 'L') leftClosestEffectiveR[i] = -1; //'L' cancels 'R' seen before.
            else leftClosestEffectiveR[i] = leftClosestEffectiveR[i-1];
        }

        for (int i=n-1; i>=0; i--) {
            char ch = dominoes.charAt(i);
            if (ch == 'L') rightClosestEffectiveL[i] = i;
            else if (i==n-1 || ch == 'R') rightClosestEffectiveL[i] = -1; //'R' cancels 'L' seen before.
            else rightClosestEffectiveL[i] = rightClosestEffectiveL[i+1];
        }

        StringBuffer sb = new StringBuffer();
        for (int i=0; i<n; i++) {
            char ch = dominoes.charAt(i);
            if (ch != '.') {
                sb.append(ch);
                continue;
            }

            if (leftClosestEffectiveR[i] == -1 && rightClosestEffectiveL[i] == -1) {
                sb.append(ch);
                continue;
            }

            if (leftClosestEffectiveR[i] == -1) {
                sb.append('L');
            } else if (rightClosestEffectiveL[i] == -1) {
                sb.append('R');
            } else if (i-leftClosestEffectiveR[i] == rightClosestEffectiveL[i]-i) {
                sb.append('.');
            } else if (i - leftClosestEffectiveR[i] > rightClosestEffectiveL[i]-i) {
                sb.append('L');
            } else {
                sb.append('R');
            }
        }

        return sb.toString();
    }
}
