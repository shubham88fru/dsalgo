package lc_potd;

//@link - https://leetcode.com/problems/water-bottles/
public class WaterBottles {
    public int numWaterBottles(int numBottles, int numExchange) {
        return revise(numBottles, numExchange);
    }

    /**
     My soln.
     Main intuition is that each time
     drink 'all' the full bottles.
     */
    private int revise(int numBottles, int numExchange) {

        int count = 0;
        int f = numBottles; //full bottles
        int e = 0; //empty bottles
        while (f != 0) {
            count += f;
            e += f;
            f = e/numExchange;
            e = e%numExchange;
        }

        return count;
    }

    private int approach2(int numBottles, int numExchange) {
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
