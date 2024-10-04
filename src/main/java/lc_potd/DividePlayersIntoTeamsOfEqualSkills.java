package lc_potd;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//@link - https://leetcode.com/problems/divide-players-into-teams-of-equal-skill/description/
//@check - https://www.youtube.com/watch?v=DtXt4DTrvOw&t=1442s&ab_channel=codestorywithMIK
public class DividePlayersIntoTeamsOfEqualSkills {
    public long dividePlayers(int[] skill) {
        // return mysol(skill);
        return mikssol(skill);
    }

    //TC wise poorer. But tricky to come up with.
    //Mik also gave second soln, which is same as mine.
    private long mikssol(int[] skill) {
        int n = skill.length;

        Arrays.sort(skill);
        int i =0;
        int j = n-1;
        int gs = skill[i] + skill[j];

        long ans = 0;
        while (i < j) {
            long s = skill[i] + skill[j]; //pair the smallest with the largest.
            if (s != gs) return -1;
            ans += ((long)skill[i]*(long)skill[j]);
            i += 1;
            j -= 1;
        }

        return ans;
    }

    //TC wise better.
    private long mysol(int[] skill) {
        int n = skill.length;
        long ts = 0;
        Map<Integer, Integer> mp = new HashMap<>();

        for (int sk: skill) {
            ts += sk;
            mp.put(sk, mp.getOrDefault(sk, 0)+1);
        }

        if (ts%(n/2) != 0) return -1;

        long gs = ts/(n/2); //each group's sum

        long ans = 0;
        for (int sk: skill) {
            if (!mp.containsKey(sk)) continue;
            int target = (int)gs-sk; //to find in map.

            if (mp.containsKey(target)) {
                if (target == sk && mp.get(target) < 2) return -1; //to handle edge case, when only one instance of target is present and target==sk.

                mp.put(sk, mp.get(sk)-1);
                if (mp.get(sk) == 0) mp.remove(sk);

                mp.put(target, mp.get(target)-1);
                if (mp.get(target) == 0) mp.remove(target);

                ans += ((long) target *sk);
            }
        }

        //if at this point map is empty, means
        //all nums are paired up.
        return (mp.size() == 0 ? ans: -1);
    }
}
