package lc_potd;

//@link - https://leetcode.com/problems/minimum-cost-to-convert-string-i/description/
public class MinCostToConvertStringI {
    public long minimumCost(String source, String target, char[] original, char[] changed, int[] cost) {
        long[][] costs = getCosts(original, changed, cost);

        //Floyd-warshall algorithm.
        for (int k=0; k<26; k++) {
            for (int i=0; i<26; i++) {
                for (int j=0; j<26; j++) {
                    if (costs[i][k] != Integer.MAX_VALUE && costs[k][j] != Integer.MAX_VALUE) {
                        costs[i][j] = Math.min(
                                costs[i][j],
                                costs[i][k] + costs[k][j]
                        );
                    }
                }
            }
        }

        long minCost = 0;
        for (int i=0; i<source.length(); i++) {
            int src = source.charAt(i)-'a';
            int des = target.charAt(i)-'a';

            if (costs[src][des] == Integer.MAX_VALUE) return -1;
            minCost += costs[src][des];
        }

        return minCost;
    }


    private long[][] getCosts(char[] original, char[] changed, int[] cost) {
        long[][] costs = new long[26][26];

        for (int i=0; i<26; i++) {
            for (int j=0; j<26; j++) {
                if (i==j) costs[i][j] = 0;
                else costs[i][j] = Integer.MAX_VALUE;
            }
        }

        for (int i=0; i<cost.length; i++) {
            char src = original[i];
            char des = changed[i];
            long c = (long)cost[i];

            //costs[src-'a'][des-'a'] = c;
            /**
             Note that simply assigning the cost to the edge
             wasn't working. Possibly because same mapping from
             source to destination had multiple costs. So had to
             select the min cost for a mapping by doing Math.min
             */
            costs[src-'a'][des-'a'] = Math.min(costs[src-'a'][des-'a'], c);
        }

        return costs;
    }
}
