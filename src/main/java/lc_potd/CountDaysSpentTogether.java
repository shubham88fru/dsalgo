package lc_potd;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

//@link - https://leetcode.com/problems/count-days-spent-together/?
public class CountDaysSpentTogether {
    public int countDaysTogether(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
        return brute(arriveAlice, leaveAlice, arriveBob, leaveBob);
    }

    /*
        My nasty brute force approach.
        There's definitely a better approach (check mik).
        Check optimal approach on next encounter.
        Try https://leetcode.com/problems/number-of-days-between-two-dates/description/
        with optimal approach.
    */
    private int brute(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
        String[] aa = arriveAlice.split("-");
        int aam = Integer.parseInt(aa[0]);
        int aad = Integer.parseInt(aa[1]);

        String[] al = leaveAlice.split("-");
        int alm = Integer.parseInt(al[0]);
        int ald = Integer.parseInt(al[1]);

        String[] ba = arriveBob.split("-");
        int bam = Integer.parseInt(ba[0]);
        int bad = Integer.parseInt(ba[1]);

        String[] bl = leaveBob.split("-");
        int blm = Integer.parseInt(bl[0]);
        int bld = Integer.parseInt(bl[1]);

        int[] dm = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        Map<Integer, Set<Integer>> aStay = new HashMap<>();
        Map<Integer, Set<Integer>> bStay = new HashMap<>();

        if (aam == alm) {
            if (!aStay.containsKey(aam)) {
                aStay.put(aam, new HashSet<>());
            }

            for (int i=aad; i<=ald; i++) aStay.get(aam).add(i);
        } else {
            if (!aStay.containsKey(aam)) {
                aStay.put(aam, new HashSet<>());
            }
            for (int i=aad; i<=dm[aam-1]; i++) aStay.get(aam).add(i);

            for (int m=aam+1; m<alm; m++) {
                if (!aStay.containsKey(m)) {
                    aStay.put(m, new HashSet<>());
                }

                for (int i=1; i<=dm[m-1]; i++) aStay.get(m).add(i);
            }

            if (!aStay.containsKey(alm)) {
                aStay.put(alm, new HashSet<>());
            }
            for (int i=1; i<=ald; i++) aStay.get(alm).add(i);
        }


        if (bam == blm) {
            if (!bStay.containsKey(bam)) {
                bStay.put(bam, new HashSet<>());
            }

            for (int i=bad; i<=bld; i++) bStay.get(bam).add(i);
        } else {
            if (!bStay.containsKey(bam)) {
                bStay.put(bam, new HashSet<>());
            }
            for (int i=bad; i<=dm[bam-1]; i++) bStay.get(bam).add(i);

            for (int m=bam+1; m<blm; m++) {
                if (!bStay.containsKey(m)) {
                    bStay.put(m, new HashSet<>());
                }

                for (int i=1; i<=dm[m-1]; i++) bStay.get(m).add(i);
            }

            if (!bStay.containsKey(blm)) {
                bStay.put(blm, new HashSet<>());
            }
            for (int i=1; i<=bld; i++) bStay.get(blm).add(i);
        }

        // System.out.println(aStay);
        // System.out.println(bStay);

        int count = 0;
        for (Map.Entry<Integer, Set<Integer>> entry: aStay.entrySet()) {
            int month = entry.getKey();
            if (bStay.containsKey(month)) {
                for (int d: entry.getValue()) {
                    if (bStay.get(month).contains(d)) count += 1;
                }
            }
        }

        return count;

    }
}
