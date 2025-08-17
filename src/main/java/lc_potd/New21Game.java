package lc_potd;

//@link - https://leetcode.com/problems/new-21-game/description/
//@check - https://www.youtube.com/watch?v=x19SmqQbhWk&ab_channel=codestorywithMIK
public class New21Game {
    public double new21Game(int n, int k, int maxPts) {
        // return brute(n, k, maxPts);
        return dp(n, k, maxPts);
    }

    /**
     * Complete copypasta solns from
     * mik and editorial. Mik also
     * did copypasta.
     *
     * There's no way I can solve this
     * type of questions in a live
     * interview. :(
     * */
    private double dp(int n, int k, int maxPts) {
        double probs[] = new double[n + 1];
        probs[0] = 1;
        double s = k > 0 ? 1 : 0;
        for (int i = 1; i <= n; i++) {
            probs[i] = s / maxPts;
            if (i < k) {
                s += probs[i];
            }
            if (i - maxPts >= 0 && i - maxPts < k) {
                s -= probs[i - maxPts];
            }
        }
        double ans = 0;
        for (int i = k; i <= n; i++) {
            ans += probs[i];
        }
        return ans;
    }

    private double brute(int n, int k, int maxPts) {
        double[] probs = new double[n+1]; //probs[i] -> prob of getting score = i

        probs[0] = 1.0; //score = 0 initially

        for (int i=1; i<=n; i++) {
            for (int card = 1; card <= maxPts; card += 1) {
                //Prob of score card = 1/maxPts
                //Remain score = probs[i-card]
                if (i-card >=0 && i-card < k) {
                    probs[i] += probs[i-card]/maxPts;
                }
            }
        }

        double ans = 0.0;
        for (int i=k; i<=n; i++) {
            ans += probs[i];
        }

        return ans;
    }
}
