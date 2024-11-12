package lc_potd;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

//@link - https://leetcode.com/problems/most-beautiful-item-for-each-query/description/
public class MostBeautifulItemForEachQuery {
    public int[] maximumBeauty(int[][] items, int[] queries) {
        return mysol(items, queries);
    }

    /*
        My soln. Mik's approach was exactly the same.
     */
    private int[] mysol(int[][] items, int[] queries) {
        //sort items.
        Comparator<int[]> cmp1 = (a1, a2) -> a1[0] - a2[0];
        Comparator<int[]> cmp2 = (a1, a2) -> a1[1] - a2[1];
        Arrays.sort(items, cmp1.thenComparing(cmp2));

        //store the max beauty seen till each item.
        Map<Integer, Integer> maxTill = new HashMap<>();
        int max = Integer.MIN_VALUE;
        for (int[] item: items) {
            int k = item[0];
            int v = item[1];

            max = Math.max(max, v);
            maxTill.put(k, max);
        }


        //Apply binary search and find
        //max beauty for each query.
        int idx = 0;
        int[] ans = new int[queries.length];
        for (int query: queries) {
            if (maxTill.containsKey(query)) {
                ans[idx] = maxTill.get(query);
            } else {
                int item = binarySearch(items, query);
                if (item == -1) ans[idx] = 0;
                else ans[idx] = maxTill.get(item);
            }
            idx += 1;
        }

        return ans;
    }

    private int binarySearch(int[][] items, int query) {
        int l = 0;
        int r = items.length-1;

        int item = -1;
        while (l <= r) {
            int mid = l + (r-l)/2;
            if (items[mid][0] == query) {
                return mid;
            } else if (items[mid][0] < query) {
                item = items[mid][0];
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }

        return item;
    }
}
