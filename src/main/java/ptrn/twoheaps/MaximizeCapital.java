package ptrn.twoheaps;

import java.util.PriorityQueue;

//@link - https://leetcode.com/problems/ipo/description/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/5562193396629504
public class MaximizeCapital {
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        return optimal(k, w, profits, capital);
    }

    //Using two heaps.
    private int optimal(int k, int w, int[] profits, int[] capital) {
        //to get least capital
        PriorityQueue<int[]> minHeap =
                new PriorityQueue<>((p1, p2) -> p1[0] - p2[0]);
        //Push all the capital values in a min-heap.
        //We'll push the capital and its index so that later
        //when we have to push the profits, we can pick the corresponding
        //profit based on the indexes.
        for (int i = 0; i < capital.length; i++) {
            minHeap.add(new int[]{capital[i], i});
        }

        //to get max profit.
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b-a);

        /**
         Basically, in this question we want to earn maximum profit
         with the least capital that we can spend (a typical investment)
         Idea is, while the capital at i in the array is less than or
         equal to currCapital, we'll keep pushing the corresponding profits
         to the maxHeap so that by the time capital in array becomes larger,
         we'll have the max proift that we can get for the currCapital from
         the maxHeap.
         */
        int currCapital = w; //start with initial capital.
        int finishedProjects = 0;
        //coz only k projects need to be completed.
        while (finishedProjects < k) {
            //As long as we have enough capital, we want to select that (corresponding)
            //profit which is maximum. So, we keep pushing the profits to a max heap.
            while (!minHeap.isEmpty() && currCapital >= minHeap.peek()[0]) {
                int[] capitalIdxPair = minHeap.remove();
                maxHeap.add(profits[capitalIdxPair[1]]);
            }

            //if no profits available in heap
            //means no more investment is possible.
            //we have no choice but to return whatever profits
            //we have accumulated till now.
            if (maxHeap.isEmpty()) break;

            //otherwise, at this point, the maxHeap will
            //have the max profit we can get for the currCapital.
            //And so, as per question, we accumulate that profit
            //back to our capital for further investments.
            currCapital += maxHeap.remove();

            //project completed, so increment.
            finishedProjects += 1;
        }


        return currCapital;
    }
}
