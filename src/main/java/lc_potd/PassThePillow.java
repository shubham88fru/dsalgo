package lc_potd;

//@link - https://leetcode.com/problems/pass-the-pillow/
public class PassThePillow {
    public int passThePillow(int n, int time) {
        //if (time < n) return time+1;

        int rounds = time/(n-1);
        int pos = time%(n-1);

        if (rounds%2 == 0) return 1 + pos;
        return n-pos;
    }
}
