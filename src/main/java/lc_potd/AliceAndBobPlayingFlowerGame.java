package lc_potd;

//@link - https://leetcode.com/problems/alice-and-bob-playing-flower-game/
public class AliceAndBobPlayingFlowerGame {
    public long flowerGame(int n, int m) {
        return pass1(n, m);
    }

    private long pass1(int n, int m) {
        long e1 = 0;
        long o1 = 0;
        long e2 = 0;
        long o2 = 0;

        for (int i=1; i<=n; i++) {
            if (i%2==0) e1 += 1;
            else o1 += 1;
        }

        for (int i=1; i<=m; i++) {
            if (i%2==0) e2 += 1;
            else o2 += 1;
        }

        return (e1*o2) + (o1*e2);
    }
}
