package basics.stack;

import java.util.ArrayDeque;
import java.util.Deque;

//span of a stock
//no. of consecutive days before the current day,
//including the day itself, when the stock price was
//less of equal to current day.
//eg: {13, 15, 12, 14, 16, 8, 6, 4, 10, 30}
//span {1, 2, 1, 2, 5, 1, 1, 1, 4, 10}
public class StockSpan {

    //T: O(N^2)
    void printSpanNaive(int[] stockPrices) {
        for (int i=0; i< stockPrices.length; i++) {
            int span = 1;
            for (int j=i-1; j>=0 && stockPrices[j] <= stockPrices[i]; j--)
                span++;
            System.out.println(span+ " ");
        }
    }

    //T:Theta(N), S: O(N)
    void printSpan(int[] stockPrices) {
        Deque<Integer> stack = new ArrayDeque<>();

        //Part 1: Processing the first el.
        stack.push(0); //Smart. Push index of last larger els instead of els themselves. Coz that's what we need for comparison.
        int span = 1; //span of 1st el is always 1.
        System.out.println(span+" ");

        //Part 2: Process remaining els.
        for (int i=1; i<stockPrices.length; i++) {
            while (!stack.isEmpty() &&
                    stockPrices[stack.peek()] <= stockPrices[i]) {
                stack.pop();
            }
            span = (stack.isEmpty()) ? (i+1): (i-stack.peek());
            System.out.println(span+" ");
            stack.push(i);
        }
    }

    public static void main(String[] args) {
        StockSpan stockSpan = new StockSpan();
        int[] stockPrices = new int[] {18, 12, 13, 14, 11, 16};
        stockSpan.printSpanNaive(stockPrices);
        System.out.println("-----------------------");

        stockPrices = new int[] {60, 10, 20, 40, 35, 30, 50, 70, 65};
        stockSpan.printSpan(stockPrices);
    }
}
