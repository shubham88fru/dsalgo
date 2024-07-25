package lc_potd;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

//@link - https://leetcode.com/problems/sort-the-jumbled-numbers/description/
public class SortTheJumbledNumber {
    public int[] sortJumbled(int[] mapping, int[] nums) {
        return sol1(mapping, nums);
    }

    private int[] sol1(int[] mapping, int[] nums) {
        Comparator<Integer> cmp = (n1, n2) -> mapped(n1, mapping) - mapped(n2, mapping);
        List<Integer> sorted = Arrays.stream(nums)
                .boxed().sorted(cmp).collect(Collectors.toList());

        for (int i=0; i<nums.length; i++) {
            nums[i] = sorted.get(i);
        }
        return nums;
    }

    private int mapped(int n1, int[] mapping) {
        int pow = 0;
        int mappedNum = 0;
        if (n1 == 0) return mapping[0];
        while (n1 > 0) {
            int dig = n1%10;
            mappedNum += (Math.pow(10, pow)*mapping[dig]);
            pow += 1;
            n1 /= 10;
        }

        return mappedNum;
    }
}
