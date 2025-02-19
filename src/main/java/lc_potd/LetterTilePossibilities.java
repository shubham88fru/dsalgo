package lc_potd;

import java.util.HashSet;
import java.util.Set;

//@link - https://leetcode.com/problems/letter-tile-possibilities/
//@check - https://www.youtube.com/watch?v=cD0D-QSKG20&t=1209s&ab_channel=codestorywithMIK
public class LetterTilePossibilities {
    public int numTilePossibilities(String tiles) {
        Set<String> st = new HashSet<>();
        pass1(tiles, new StringBuffer(), st, new HashSet<>(), 0);

        return st.size()-1;
    }

    private void pass1(String tiles, StringBuffer curr, Set<String> st, Set<Integer> picked, int count) {
        // if (count == tiles.length()) {
        //     st.add(curr.toString());
        //     return;
        // }
        st.add(curr.toString());

        for (int i=0; i<tiles.length(); i++) {
            if (picked.contains(i)) continue;

            picked.add(i);
            curr.append(tiles.charAt(i));
            pass1(tiles, curr,  st, picked, count + 1);
            curr.deleteCharAt(curr.length()-1);
            picked.remove(i);

            // pass1(tiles, curr, st, picked, count+1);
        }
    }
}
