package lc_potd;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

//@link - https://leetcode.com/problems/minimum-swaps-to-sort-by-digit-sum/description/
//@check - https://www.youtube.com/watch?v=2COTaaNSLfg
public class MinimumSwapsToSortByDigitSum {
    public int minSwaps(int[] nums) {
        return mikssol(nums);
    }

    /*
        Coded by me completely based on mik's approach.
        I thought about doing something like this
        briefly but think that it'll work.
        I'm still not fully convinced on
        what proves that the `swaps` below
        will guarantee the min num of swaps.
    */
    private int mikssol(int[] arr) {
        int n = arr.length;

        Integer[] sorted = new Integer[n];
        Map<Integer, Integer> ogToIdx = new HashMap<>();
        for (int i=0; i<n; i++) {
            sorted[i] = arr[i];
            ogToIdx.put(arr[i], i);
        }

        Comparator<Integer> cmp = (i1, i2) -> {
            int sum1 = sum(i1), sum2 = sum(i2);
            if (sum1 == sum2) return i1-i2;
            if (sum1 < sum2) return -1;
            return 1;
        };

        Arrays.sort(sorted, cmp);

        int swaps = 0;
        for (int i=0; i<n; i++) {
            if (arr[i] != sorted[i]) {
                swaps += 1;
                int ogi = ogToIdx.get(sorted[i]);
                int temp = arr[i];
                arr[i] = sorted[i];
                arr[ogi] = temp;

                ogToIdx.put(arr[i], i);
                ogToIdx.put(arr[ogi], ogi);
            }
        }

        return swaps;


    }

    private int sum(int num) {
        int s = 0;
        while (num > 0) {
            s += num%10;
            num /= 10;
        }
        return s;
    }
}
