package ptrn.bitmanipulation;

//@link - https://leetcode.com/problems/sum-of-two-integers/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/6491145648734208
//       - https://www.youtube.com/watch?v=gVUrDV4tZfY&t=333s&ab_channel=NeetCode
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

        UPDATE: checked nc's explanation. Attached link.
    */
    private int optimal(int a, int b) {
        //one question that you must ask interviewer is
        //that can the numbers be -ve.
        //However, it doesn't matter, this approach somehow
        //takes care of that as well.
        while (b != 0) {
            int carry = a & b; //this will give the carry
            a = a ^ b; //gives the right bit as we should get when performing binary addition.
            b = carry << 1; //but carry will go the next digits, hence we need to shift
        }

        return a;
    }

    private int lol(int a, int b) {
        return a + b;
    }
}
