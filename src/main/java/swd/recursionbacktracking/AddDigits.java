package swd.recursionbacktracking;

//@link - https://leetcode.com/problems/add-digits/description/
public class AddDigits {
    public int addDigits(int num) {
        //reduce the num to single digit.
        //i.e. keeping summing the digits
        //until the sum reduces to a single
        //digit num.
        while (num >= 10) {
            num = reduce(num);
        }
        return num;
    }

    //sum all digits of given num.
    private int reduce(int num) {
        if (num == 0) return 0;
        return ((num % 10) + addDigits(num / 10));
    }
}
