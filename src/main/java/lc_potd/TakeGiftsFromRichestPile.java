package lc_potd;

import java.util.PriorityQueue;

//@link - https://leetcode.com/problems/take-gifts-from-the-richest-pile/
public class TakeGiftsFromRichestPile {
    public long pickGifts(int[] gifts, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((i1 , i2) -> i2 - i1);

        long sum = 0;
        for (int gift: gifts) {
            sum += gift;
            maxHeap.add(gift);
        }

        while (!maxHeap.isEmpty() && k > 0) {
            int rem = maxHeap.remove();
            int put = (int)Math.sqrt(rem);
            maxHeap.add(put);
            sum -= (rem-put);
            k -= 1;
        }

        return sum;
    }
}
