package lc_cntst;

//@link - https://leetcode.com/problems/water-bottles-ii/
public class WaterBottlesII {
    public int maxBottlesDrunk(int numBottles, int numExchange) {
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
}
