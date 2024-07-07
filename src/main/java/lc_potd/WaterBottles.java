package lc_potd;

//@link - https://leetcode.com/problems/water-bottles/
public class WaterBottles {
    public int numWaterBottles(int numBottles, int numExchange) {
        int fullBottles = numBottles;
        int emptyBottles = 0;
        boolean drink = true;
        int maxDrink = 0;
        while (numBottles >= numExchange) {
            //drink
            if (drink) {
                maxDrink += fullBottles;
                emptyBottles += fullBottles;
            }
            //exchange.
            else {
                fullBottles = emptyBottles/numExchange;
                emptyBottles = emptyBottles%numExchange;
                numBottles = (fullBottles + emptyBottles);
            }

            //toggle - drink/exchange.
            drink = !drink;
        }

        return (maxDrink+fullBottles);
    }
}
