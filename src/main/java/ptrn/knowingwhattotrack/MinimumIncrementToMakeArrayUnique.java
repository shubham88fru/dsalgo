package ptrn.knowingwhattotrack;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

//@link - https://leetcode.com/problems/minimum-increment-to-make-array-unique/description/
public class MinimumIncrementToMakeArrayUnique {
    public int minIncrementForUnique(int[] nums) {
        return sol1(nums);
    }

    //1) My soln. Better. Not sure if optimal.
    //can check YT if there are better approaches.
    private int sol1(int[] nums) {
        Set<Integer> st = new HashSet<>();
        int moves = 0;

        //sort the array.
        Arrays.sort(nums);

        //keep track of the smallest number that
        //we can replace the any repeating nums with.
        //Note that since a num can only be incremented,
        //we have to keep track of smallest largest num.
        //i.e. we can't just keep track of smallest num seen so far.
        int leastLargest = nums[0]+1;
        for (int num: nums) {
            //If a num is already seen before,
            //we need to update it with the least
            //number that we can (for min moves).
            if (st.contains(num)) {
                int diff = leastLargest-num;
                moves += (diff); //update the moves.
                st.add(leastLargest); //update the curr num with the least possible num.
                leastLargest += 1; //increment the smallest number that can be used for replacement.
            } else {
                //If curr num is not seen before,
                //add it to the set.
                st.add(num);
                //since any next num can only be incremented
                //it can't be smaller than num. So, we keep track
                //of the least num that it can be replaced with.
                leastLargest = num + 1;
            }
        }

        return moves;
    }

    //2) My soln. Gives TLE.
    private int sol2(int[] nums) {
        Set<Integer> st = new HashSet<>();
        int moves = 0;
        for (int num: nums) {
            if (st.contains(num)) {
                while (st.contains(num)) {
                    num += 1;
                    moves += 1;
                }
            }
            st.add(num);
        }

        return moves;
    }
}
