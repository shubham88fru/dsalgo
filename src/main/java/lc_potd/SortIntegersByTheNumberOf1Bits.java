package lc_potd;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

//@link - https://leetcode.com/problems/sort-integers-by-the-number-of-1-bits/?
public class SortIntegersByTheNumberOf1Bits {
    public int[] sortByBits(int[] arr) {
        return revise(arr);
    }

    private int[] revise(int[] arr) {
        int n = arr.length;
        List<Integer> lst = new ArrayList<>();
        for (int i=0; i<n; i++) lst.add(arr[i]);

        Comparator<Integer> cmp = (i1, i2) -> {
            int o1 = ones(i1), o2 = ones(i2);
            if (o1 > o2) return 1;
            else if (o1 < o2) return -1;
            return i1-i2;
        };

        Collections.sort(lst, cmp);

        return lst.stream().mapToInt(i->i).toArray();
    }

    private int ones(int i) {
        int ones = 0;
        while (i > 0) {
            if ((i&1) != 0) ones += 1;
            i = i >> 1;
        }
        return ones;
    }
}
