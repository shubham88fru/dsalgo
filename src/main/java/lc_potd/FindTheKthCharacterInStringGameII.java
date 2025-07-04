package lc_potd;

//@link - https://leetcode.com/problems/find-the-k-th-character-in-string-game-ii/
//@check - https://www.youtube.com/watch?v=JlpjAjsA03o&ab_channel=codestorywithMIK
public class FindTheKthCharacterInStringGameII {
    public char kthCharacter(long k, int[] operations) {
        return optimal(k, operations);
    }

    /*
        Coded by me but completely based
        on mik's explanation.
    */
    private char optimal(long k, int[] operations) {
        int n = operations.length;

        if (k == 1) return 'a';

        //jump back (recurse)
        long len = 1;
        int op = 0;
        long k_ = 0;
        for (int i=0; i<n; i++) {
            len *= 2;
            if (len >= k) {
                op = operations[i];
                k_ = k - len/2;
                break;
            }
        }
        char ch = optimal(k_, operations);


        //jump forward (pop recursion)
        if (op == 0) return ch;
        else {
            if (ch == 'z') return 'a';
            return (char)((int)ch + 1);
        }
    }

    //brute force is same part I of this problem.

}
