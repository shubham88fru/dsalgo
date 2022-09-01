package basics.priority_queue;

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

//Given a sum and an array of costs,
//need to return max no. of items that can
//be bought with the sum.
public class PurchasingMaxItems {

    //T: O(n+res*log(n)), S: Theta(N)
    int maxItems(List<Integer> priceOfItems,
                 int moneyWithUs) {
        //get a priority queue from the prices.
        //a min heap.
        PriorityQueue<Integer> priorityQueue
                = new PriorityQueue<>(priceOfItems);
        int res = 0;
        while (moneyWithUs>=0 && !priorityQueue.isEmpty()
        && priorityQueue.peek()<=moneyWithUs) {
            moneyWithUs = moneyWithUs - priorityQueue.poll();
            res++;
        }
        return res;
    }

    public static void main(String[] args) {
        PurchasingMaxItems maxItems
                = new PurchasingMaxItems();
        Integer[] arr = {1, 12, 5, 111, 200};
        List<Integer> list = Arrays.asList(arr);
        System.out.println(maxItems.maxItems(list, 10));

    }
}
