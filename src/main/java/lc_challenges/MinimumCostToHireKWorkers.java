package lc_challenges;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.*;

public class MinimumCostToHireKWorkers {
    //Couldn't understand completely.
    //refer -
    //https://www.youtube.com/watch?v=MTODULo0BGs&t=905s&ab_channel=AlgorithmHQ
    //https://www.youtube.com/watch?v=KytaqLXEeJA
    //https://www.youtube.com/watch?v=kxR52OB_I8k&ab_channel=codestorywithMIK
    public double mincostToHireWorkers(int[] quality, int[] wage, int k) {
        List<Pair> ratios = new ArrayList<>();
        for (int i=0; i<wage.length; i++) {
            ratios.add(new Pair(wage[i], quality[i]));
        }
        Collections.sort(ratios, (a, b) -> Double.compare(a.ratio, b.ratio));

        double ans = Double.MAX_VALUE, qsum = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (Pair p: ratios) {
            qsum += p.quality;
            pq.add(p.quality);
            if (pq.size()>k) qsum -= pq.remove();
            if (k==pq.size()) ans = Math.min(ans, p.ratio*qsum);
        }

        return ans;
    }
}

class Pair {
    int quality;
    double ratio;
    Pair(int wage, int quality) {
        this.quality = quality;
        this.ratio = ((double)wage/quality);
    }
}
