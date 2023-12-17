package ptrn.fastnslowpointers;

import java.util.HashSet;
import java.util.Set;

//@link - https://leetcode.com/problems/happy-number/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/4892193883160576
public class HappyNumber {
    public boolean isHappy(int n) {
        //return isHappyBrute(n);
        return isHappyOptimal(n);
    }

    //1) Optimal approach - using fast & slow pointers.
    //T: O(logn), S: O(1)
    private boolean isHappyOptimal(int n) {
        //Imaging the fast and slow pointers to
        //be moving over the list of sum of squares of the digits.
        //The slow pointer is initialized to the first element of the list
        //i.e. sum of squre of digits of og num. While the fast pointer is
        //initialized to the next num in the list.
        long slow = sumDigs(n);
        long fast = sumDigs(sumDigs(n));

        while (fast != 1) {
            if (fast == slow) return false;

            //at each iteration, the slow pointer will
            //move one step i.e. to the next square sum num.
            slow = sumDigs(slow);

            //while the fast pointer will move two steps.
            //And we'll hope to either hit a 1 or the fast and
            //slow will collide, indicating the presence of a cycle
            //i.e. the square sums repeating.
            fast = sumDigs(sumDigs(fast));
        }

        return true;
    }

    //2) Kinda brute force - T: O(logN), S: O(logN)
    //add each sum to a hashmap and everytime a sum
    //is calculated, check if the sum has been seen before,
    //if so, then sums form a cycle and the number can't be
    //a happy number.
    private boolean isHappyBrute(int n) {
        Set<Long> sq_sums = new HashSet<>();
        long sq_sum = sumDigs(n);

        //If a particular square sum was seen before
        //means we have entered a loop.
        while (!sq_sums.contains(sq_sum)) {
            //otherwise, if sum becomes 1, we have
            //a happy number.
            if (sq_sum == 1l) return true;
            sq_sums.add(sq_sum);
            sq_sum = sumDigs(sq_sum);
        }

        return false;
    }

    //standard algorithm to get individual
    //digits of a number and sum them.
    private long sumDigs(long n) {
        long sq_sum = 0l;
        while (n > 0) {
            long ldig = n%10;
            sq_sum += (ldig*ldig);
            n = n/10;
        }
        return sq_sum;
    }
}
