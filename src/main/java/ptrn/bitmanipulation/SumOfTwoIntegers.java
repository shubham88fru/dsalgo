package ptrn.bitmanipulation;

//@link - https://leetcode.com/problems/sum-of-two-integers/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/6263849939632128
public class SumOfTwoIntegers {
    public int getSum(int a, int b) {
        // return lol(a, b);
        return optimal(a, b);
    }

    /*
        Copypasta from gpt.
        No clue why this works. If this is a
        recurring problem for some company, check YT
        for explanation.
    */
    private int optimal(int a, int b) {
        while (b != 0) {
            int carry = a & b;
            a = a ^ b;
            b = carry << 1;
        }

        return a;
    }

    private int lol(int a, int b) {
        return a + b;
    }
}
