package lc_potd;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

//@link - https://leetcode.com/problems/design-a-number-container-system/
//@check - https://www.youtube.com/watch?v=689qj769Gt4&t=1555s&ab_channel=codestorywithMIK
public class DesignNumberContainerSystem {
    /*
       This is my approach.
       Mik showed this approach and an approach
       using heap, which is also straight forward
       and came to my mind too, but I didn't
       do it.

       If this problem is a popular problem
       for some company, @check for second approach.
   */
    Map<Integer, Integer> I_N;
    Map<Integer, TreeSet<Integer>> N_I;

    public DesignNumberContainerSystem() {
        I_N = new HashMap<>();
        N_I = new HashMap<>();
    }

    public void change(int index, int number) {
        if (I_N.containsKey(index)) {
            int currNum = I_N.get(index);
            N_I.get(currNum).remove(index);
            if (N_I.get(currNum).size() == 0) N_I.remove(currNum);
        }

        I_N.put(index, number);

        if (!N_I.containsKey(number)) {
            N_I.put(number, new TreeSet<>());
        }
        N_I.get(number).add(index);
    }

    public int find(int number) {
        if (N_I.containsKey(number)) return N_I.get(number).first();

        return -1;
    }
}
