package strvr.greedy;

import java.util.Arrays;

//@link - https://practice.geeksforgeeks.org/problems/fractional-knapsack-1587115620/1
//@strvr - https://takeuforward.org/data-structure/fractional-knapsack-problem-greedy-approach/
public class FractionalKnapSack {
    double fractionalKnapsack(int W, Item arr[], int n)  {
        // Your code here
        return fractionalKnapsackGreedy(W, arr, n);
    }

    //T: O(nlogn+n)
    private double fractionalKnapsackGreedy(int W, Item[] arr, int n) {
        //Sort by cost per weight and starting picking
        //in decreasing order of cost per weight.
        Arrays.sort(arr, (o1, o2) -> {
            //Need to write the comparator this way, because
            //need to type cast during value per weight, otherwise
            //sorting doesn't happen properly and all test cases don't pass.
            double c1 = (double)o1.value/(double)o1.weight;
            double c2 = (double)o2.value/(double)o2.weight;

            if (c1<c2) return 1;
            else if (c1>c2) return -1;
            else return 0;
        });
        double val = 0.0;
        int remainWeight = W;
        for (Item item: arr) {
            //if possible pick all of it, to get all value.
            if (remainWeight-item.weight > 0) {
                remainWeight -= item.weight;
                val += ((double)item.value);
            } else if (remainWeight >0) {
                //otherwise, get the fractional value.
                //at this point, there won't be any reamining value. So, break.
                val += ((double)item.value/((double)item.weight))*(double)remainWeight;
                break;
            }
        }

        return val;
    }
}

class Item {
    int value, weight;
    Item(int x, int y){
        this.value = x;
        this.weight = y;
    }
}