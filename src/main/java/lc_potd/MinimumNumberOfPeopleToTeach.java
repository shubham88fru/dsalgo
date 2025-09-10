package lc_potd;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

//@link - https://leetcode.com/problems/minimum-number-of-people-to-teach/description/
//@check - https://www.youtube.com/watch?v=mToBak5z4BA&ab_channel=codestorywithMIK
public class MinimumNumberOfPeopleToTeach {
    public int minimumTeachings(int n, int[][] languages, int[][] friendships) {
        return mikssol(n, languages, friendships);
    }

    /**
     Very stupid question. There's no way I could have come
     up with this solution myself. Coded by me based on miks
     explanation.
     */
    private int mikssol(int n, int[][] languages, int[][] friendships) {

        Set<Integer> sadUsers = new HashSet<>();
        for (int[] friendship: friendships) {
            int u = friendship[0] - 1;
            int v = friendship[1] - 1;

            Set<Integer> langs = Arrays.stream(languages[u])
                    .boxed()
                    .collect(Collectors.toSet());
            boolean canTalk = false;
            for (int lang: languages[v]) {
                if (langs.contains(lang)) {
                    canTalk = true;
                    break;
                }
            }

            if (!canTalk) {
                sadUsers.add(u);
                sadUsers.add(v);
            }
        }

        int[] langFreq = new int[n+1];
        int popularLanguage = 0;

        for (int su: sadUsers) {
            for (int lang: languages[su]) {
                langFreq[lang] += 1;
                popularLanguage = Math.max(popularLanguage, langFreq[lang]);
            }
        }

        return sadUsers.size() - popularLanguage;
    }
}
