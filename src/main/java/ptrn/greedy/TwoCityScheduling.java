package ptrn.greedy;

import java.util.Arrays;

//@link - https://leetcode.com/problems/two-city-scheduling/description/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/4512145162371072
public class TwoCityScheduling {
    public int twoCitySchedCost(int[][] costs) {
        /**
         Picking `city a` will be benificial over `city b`
         when cost of `city a` is as less as possible at the same
         time cost of `city b` is as large as possible.
         This sorting ensures that pairs with least cost for a and highest cost for b
         accumulate on the left side of the array.
         */
        Arrays.sort(costs, (city1, city2) -> ((city1[0]-city1[1])-(city2[0]-city2[1])));
        return minSum(costs);
    }

    private int minSum(int[][] costs) {
        int twon = costs.length;
        int a_cnt = twon/2;
        int b_cnt = twon/2;
        int sum = 0;

        /**
         Since the array is now sorted in such a way
         the best pick for city a lies on the left and
         the choice of b goes on increasing as we move right.
         We add the first half to city A, and the next half to
         city B.
         */
        for (int[] cost: costs) {
            if (a_cnt > 0) {
                sum += cost[0];
                a_cnt -= 1;
            } else {
                sum += cost[1];
                b_cnt -= 1;
            }

        }

        return sum;
    }
}
