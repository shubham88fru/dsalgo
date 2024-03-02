package ptrn.cyclicsort;

//@link - https://leetcode.com/problems/missing-number/description/
//@company - Avaya
public class MissingNumber {
    public int missingNumber(int[] nums) {
        //return sol1(nums);
        return sol2(nums);
    }

    //1) My soln using the sum of 1st n nums concept.
    //T: O(N), S: O(1)
    private int sol1(int[] nums) {
        int n = nums.length;

        //formula for sum of 1st N natural nums.
        int sumOf1stNNaturalNums = (n*(n+1))/2;

        //since all nums in array are distinct.
        //ideal sum of n natural nums minus actual sum
        //of the array will give the missing num.
        return (sumOf1stNNaturalNums - sum(nums));
    }

    //sum the given array.
    private int sum(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        return sum;
    }

    //2) Edctv solution. Using cyclic sort (slightly modified though)
    //T: O(2N), S: O(1)
    private int sol2(int[] nums) {
        //Move each num to its correct index by swapping.
        //However, if the num at current index is correct or if
        //the num at current index is greater than or equal to
        //length of array (i.e. can't be fit in the array), skip
        //and move to next element.
        //Once this swapping game is done, we'll iterate the swapped(sorted)
        //array once more. At any index if the num and index don't match, the
        //`index` will be our answer. Otherwise, if after iterating we didn't
        //find any mismatch, then `index+1` is the answer.
        /*********Cyclic Sort******************/
        int index = 0;
        while (index < nums.length) {
            if (!(nums[index] == index || nums[index] >= nums.length)) {
                int curr = nums[index];
                nums[index] = nums[curr];
                nums[curr] = curr;
            } else index += 1;
        }
        /*********Cyclic Sort******************/
        /**** Post Cyclic sort Detection******/
        index = 0;
        while (index < nums.length) {
            if (index != nums[index]) break;
            index += 1;
        }
        /**** Post Cyclic sort Detection******/
        return index;
    }
}
