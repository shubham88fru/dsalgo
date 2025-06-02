package lc_potd;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

//@link - https://leetcode.com/problems/candy/submissions/1651727754/
//@check - https://www.youtube.com/watch?v=YUT13Koh5Jg&ab_channel=codestorywithMIK
public class Candy {
    public int candy(int[] ratings) {
        return pass1(ratings);
    }

    /*
        1. Optimal soln. Constant space.
        Tricky and too greedy to strike in an
        interview but revise from the link if this
        is a recurring problem for some company.
     */


    /* *
        2. Better soln based on mik's explanation.
     */
    private int pass2(int[] ratings) {
        int n = ratings.length;

        int[] l2r = new int[n];
        Arrays.fill(l2r, 1); //atleast 1 candy needs to be given.

        int[] r2l = new int[n];
        Arrays.fill(r2l, 1);

        //check requirements per left neighbour.
        for (int i=1; i<n; i++) {
            if (ratings[i] > ratings[i-1]) {
                l2r[i] = l2r[i-1] + 1;
            }
        }

        //check requirements per right neighbour.
        for (int i=n-2; i>=0; i--) {
            if (ratings[i] > ratings[i+1]) {
                r2l[i] = r2l[i+1] + 1;
            }
        }

        //take the max.
        int minCandies = 0;
        for (int i=0; i<n; i++) {
            minCandies += Math.max(l2r[i], r2l[i]);
        }

        return minCandies;
    }

    /*
    * 3. My soln. Stack based.
    * Bit nasty.
    * */
    private int pass1(int[] ratings) {
        int n = ratings.length;

        Deque<int[]> stack = new ArrayDeque<>();
        int minCandies = 0;
        for (int rating : ratings) {
            if (stack.isEmpty() || (rating <= stack.peekFirst()[0])) {
                stack.addFirst(new int[]{rating, 1});
            } else {
                int topCandy = stack.peekFirst()[1];

                int[] popped = stack.removeFirst();
                int candy = popped[1];
                minCandies += candy;
                while (!stack.isEmpty()) {
                    if (stack.peekFirst()[0] == popped[0]) {
                        minCandies += Math.max(1, stack.peekFirst()[1]);
                        candy = 1;
                    } else {
                        minCandies += Math.max(candy + 1, stack.peekFirst()[1]);
                        candy += 1; //sequence

                    }

                    popped = stack.removeFirst();
                }
                stack.addFirst(new int[]{rating, topCandy + 1});

            }
        }

        if (!stack.isEmpty()) {
            int[] popped = stack.removeFirst();
            int candy = popped[1];
            minCandies += candy;
            while (!stack.isEmpty()) {
                if (stack.peekFirst()[0] == popped[0]) {
                    minCandies += Math.max(1, stack.peekFirst()[1]);
                    candy = 1;
                } else {
                    minCandies += Math.max(candy + 1, stack.peekFirst()[1]);
                    candy += 1; //sequence.
                }
                popped = stack.removeFirst();
            }
        }

        return minCandies;
    }
}
