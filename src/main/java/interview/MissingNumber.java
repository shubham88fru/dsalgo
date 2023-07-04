package interview;

//@link - https://leetcode.com/problems/missing-number/description/
//@company - Avaya
public class MissingNumber {
    public int missingNumber(int[] nums) {
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
}
