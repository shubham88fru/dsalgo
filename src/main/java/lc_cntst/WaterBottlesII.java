package lc_cntst;

//@link - https://leetcode.com/problems/water-bottles-ii/
public class WaterBottlesII {
    public int maxBottlesDrunk(int numBottles, int numExchange) {
        return revise(numBottles, numExchange);
    }

    private int approach2(int numBottles, int numExchange) {
        int drunk = 0;
        int empty = 0;
        while (numBottles > 0 || empty >= numExchange) {
            if (empty >= numExchange) {
                empty -= numExchange;
                numExchange += 1;
                numBottles += 1;
            } else if (numBottles > 0) {
                drunk += numBottles;
                empty += numBottles;
                numBottles = 0;

            }
        }

        return drunk;
    }

    private int revise(int numBottles, int numExchange) {

        int f = numBottles;
        int e = 0;

        int count = 0;
        while ((f > 0) || (e >= numExchange)) {
            if (e >= numExchange) {
                e -= numExchange;
                f += 1;
                numExchange += 1;
            } else {
                count += f;
                e += f;
                f = 0;
            }
        }

        return count;
    }
}
