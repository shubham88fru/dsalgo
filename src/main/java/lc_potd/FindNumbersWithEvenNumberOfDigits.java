package lc_potd;

//@link - https://leetcode.com/problems/find-numbers-with-even-number-of-digits/
public class FindNumbersWithEvenNumberOfDigits {
    public int findNumbers(int[] nums) {
        // return revise(nums);
        return approach2(nums);
    }

    private int approach2(int[] nums) {
        int ans = 0;
        for (int num: nums) {
            if ((Math.floor(Math.log10(num)) + 1)%2 == 0) ans += 1;
        }

        return ans;
    }

    private int approach1(int[] nums) {
        int ans = 0;
        for (int num: nums) {
            if (count(num)%2 == 0) ans += 1;
        }

        return ans;
    }

    private int count(int num) {
        int len = 0;
        while (num > 0) {
            len += 1;
            num /= 10;
        }

        return len;
    }
}
